package org.basic.datastrutcures.trees;

import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.basic.CollectionUtils;
import org.basic.datastrutcures.Pair;
import org.testng.Assert;

import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static org.basic.CollectionUtils.single;
import static org.testng.AssertJUnit.*;

class TreeNode<TKey extends Comparable<TKey>, TValue> {
    List<TreeNode<TKey, TValue>> children;
    List<TKey> keys;
    TValue value;

    TreeNode(TKey key, TValue value) {
        keys = Lists.newArrayList(key);
        children = Lists.newArrayList();
        this.value = value;
    }

    TreeNode<TKey, TValue> find(TKey key) {
        if (isLeaf()) {
            TKey singleKey = single(keys);
            if (singleKey.compareTo(key) != 0)
                return null;
            return this;
        }

        validateChildrenCount();
        for (int i = 0; i < keys.size(); ++i) {
            TKey currentKey = keys.get(i);
            int comparison = key.compareTo(currentKey);
            if (comparison < 0) {
                // search the current node
                return children.get(i).find(key);
            }
            if (comparison == 0) {
                return children.get(i + 1).find(key);
            }

        }
        // search the last node
        return children.get(keys.size());
    }

    private void validateChildrenCount() {
        Assert.assertEquals(children.size(), keys.size() + 1);
    }

    /**
     * If this returns not null, the caller must take care to add both the key and the node in the upper level
     */
    Pair<TKey, TreeNode<TKey, TValue>> put(TKey key, TValue value) {
        if (isLeaf()) {
            final TKey leafKey = single(keys);
            final int comparison = key.compareTo(leafKey);
            if (comparison == 0) {
                // existing key
                this.value = value;
                return null;
            }

            // new leaf
            if (comparison > 0)
                return newKeyNodePair(key, newTreeNode(key, value));
            else
                return newKeyNodePair(leafKey, newTreeNode(key, value));
        }

        validateChildrenCount();

        for (int i = 0; i < keys.size(); ++i) {
            TKey currentKey = keys.get(i);
            int comparison = key.compareTo(currentKey);
            if (comparison < 0) {
                return putAndAdjust(key, value, i);
            }

            // todo - optimize for comparison == 0
        }
        // The key is greater than all existing keys
        return putAndAdjust(key, value, keys.size());
    }

    private Pair<TKey, TreeNode<TKey, TValue>> putAndAdjust(TKey key, TValue value, int position) {
        TreeNode<TKey, TValue> child = children.get(position);
        Pair<TKey, TreeNode<TKey, TValue>> newStuff = child.put(key, value);
        if (newStuff == null)
            return null;

        if (children.size() < maxChildren()) {
            assertNotNull(newStuff.getKey());
            assertNotNull(newStuff.getValue());

            if (position == 0) {
                // put it before all other children
                children.add(0, newStuff.getValue());
            } else {
                if (newStuff.getKey().compareTo(keys.get(position - 1)) > 0)
                    children.add(position + 1, newStuff.getValue());
                else
                    children.add(position, newStuff.getValue());
            }
            keys.add(position, newStuff.getKey());
            return null;
        }

        // we'll have too many children if we add this one
        // let's remove our last child and create a new node, which we'll return to our parent to insert
        assertTrue(keys.size() >= 2);
        if (position == 0) {
            TKey removedKey;
                    TreeNode<TKey, TValue> removedChild;

            removedKey = keys.remove(0);
            removedChild = children.remove(0);
            TreeNode<TKey, TValue> newTree = newTreeNode(getLast(removedChild.keys), null);
            newTree.children.add(newStuff.getValue());
            newTree.children.add(removedChild);
            return newKeyNodePair(removedKey, newTree);
        } else {
            if (position != keys.size()) {
                throw new RuntimeException("Let's debug this one");
            }
            TKey removedKey = keys.remove(position - 1);
            TreeNode<TKey, TValue> removedChild = children.remove(position);
            TreeNode<TKey, TValue> newTree = newTreeNode(key, null);
            newTree.children.add(removedChild);
            newTree.children.add(newStuff.getValue());
            return newKeyNodePair(removedKey, newTree);
        }

    }

    private Pair<TKey, TreeNode<TKey, TValue>> newKeyNodePair(TKey key, TreeNode<TKey, TValue> node) {
        return new Pair<TKey, TreeNode<TKey, TValue>>(key, node);
    }

    private int maxChildren() {
        return 3;
    }

    int height() {
        if (isLeaf()) {
            // leaf
            return 1;
        }
        return children.get(0).height() + 1;
    }

    private boolean isLeaf() {
        return value != null;
    }

    private TreeNode<TKey, TValue> newTreeNode(TKey key, TValue value) {
        return new TreeNode<TKey, TValue>(key, value);
    }

    public int size() {
        if (isLeaf()) {
            return 1;
        }
        int size = 0;
        for (TreeNode<TKey, TValue> child : children) {
            size += child.size();
        }
        return size;
    }

    Pair<TKey, TValue> toPair() {
        assertTrue(isLeaf());
        return new Pair<TKey, TValue>(CollectionUtils.single(keys), value);
    }

    /**
     * Validate this is a legitimate B-Tree.
     */
    void validate() {
        validateAndFindHeight();
        validateAllValuesAreInLeaves();
        validateBounds(null, null);
    }

    private void validateAllValuesAreInLeaves() {
        if (isLeaf()) {
            assertNotNull(value);
            return;
        }
        for (TreeNode<TKey, TValue> child : children) {
            child.validateAllValuesAreInLeaves();
        }
    }

    private void validateBounds(TKey min, TKey max) {
        if (isLeaf()) {
            validateKey(min, max, single(keys));
            return;
        }

        for (TKey key : keys) {
            validateKey(min, max, key);
        }
        TKey currentMin = min;
        for (int i = 0; i < keys.size(); i++) {
            TKey currentMax = keys.get(i);
            TreeNode<TKey, TValue> child = children.get(i);
            child.validateBounds(currentMin, currentMax);
            currentMin = currentMax;
        }
        children.get(children.size() - 1).validateBounds(currentMin, max);
    }

    private void validateKey(TKey min, TKey max, TKey key) {
        if (key == null)
            return;
        if (min != null) {
            if (key.compareTo(min) < 0)
                throw new RuntimeException("Inner key " + key + " is smaller than " + min);
        }
        if (max != null) {
            if (key.compareTo(max) >= 0)
                throw new RuntimeException("Inner key " + key + " is greater or equal to " + max);
        }
    }

    private int validateAndFindHeight() {
        if (isLeaf()) {
            return 1;
        }

        validateChildrenCount();
        // go from right to left
        int height = children.get(keys.size()).validateAndFindHeight();
        for (int i = keys.size() - 1; i >= 0; --i) {
            assertEquals(height, children.get(i).validateAndFindHeight());
        }
        return height;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
