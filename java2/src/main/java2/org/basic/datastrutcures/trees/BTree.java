package org.basic.datastrutcures.trees;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;
import com.google.inject.internal.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.basic.datastrutcures.Pair;

import java.util.Iterator;
import java.util.List;

public class BTree<TKey extends Comparable<TKey>, TValue> implements ITree<TKey, TValue> {
    private TreeNode<TKey, TValue> root;

    public void put(TKey key, TValue value) {
        if (value == null)
            throw new UnsupportedOperationException("Null values are not supported");

        if (root == null) {
            root = new TreeNode<TKey, TValue>(key, value);
            validate();
            return;
        }

        Pair<TKey, TreeNode<TKey, TValue>> newNode = root.put(key, value);
        if (newNode == null) {
            // Nothing to do
            validate();
            return;
        }

        TreeNode<TKey, TValue> newRoot = newTreeNode(newNode.getKey());
        if (key.compareTo(root.keys.get(0)) > 0) {
            newRoot.children.add(root);
            newRoot.children.add(newNode.getValue());
        } else {
            newRoot.children.add(newNode.getValue());
            newRoot.children.add(root);
        }
        root = newRoot;
        validate();
    }

    private TreeNode<TKey, TValue> newTreeNode(TKey key) {
        return new TreeNode<TKey, TValue>(key, null);
    }

    public boolean delete(TKey item) {
        throw new RuntimeException("Not implemented yet");
    }

    public TValue find(TKey item) {
        if (root == null)
            return null;

        final TreeNode<TKey, TValue> node = root.find(item);
        if (node == null)
            return null;

        return node.value;
    }

    public int height() {
        if (root == null)
            return 0;

        return root.height();
    }

    public int size() {
        if (root == null)
            return 0;

        return root.size();
    }

    public List<Pair<TKey, TValue>> toList() {
        List<Pair<TKey, TValue>> result = Lists.newArrayList();
        for (Pair<TKey, TValue> key : this)
            result.add(key);
        return result;
    }

    public List<TValue> values() {
        return Lambda.convert(toList(), new Converter<Pair<TKey, TValue>, TValue>() {
            public TValue convert(Pair<TKey, TValue> from) {
                return from.getValue();
            }
        });
    }

    public List<TKey> keys() {
        return Lambda.convert(toList(), new Converter<Pair<TKey, TValue>, TKey>() {
            public TKey convert(Pair<TKey, TValue> from) {
                return from.getKey();
            }
        });
    }

    public Iterator<Pair<TKey, TValue>> iterator() {
        return new TreeIterator<TKey, TValue>(root);
    }

    public void validate() {
        if (root == null)
            return;
        root.validate();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
