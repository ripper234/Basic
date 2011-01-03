package datastrutcures.trees;

import com.google.inject.internal.Lists;
import datastrutcures.Pair;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertNotSame;

public class BTree<TKey extends Comparable<TKey>, TValue> implements ITree<TKey, TValue> {
    private TreeNode<TKey, TValue> root;

    public void put(TKey key, TValue value) {
        if (value == null)
            throw new UnsupportedOperationException("Null values are not supported");

        if (root == null) {
            root = new TreeNode<TKey, TValue>(key, value);
            return;
        }

        TreeNode<TKey, TValue> newNode = root.put(key, value);
        if (newNode == null) {
            // Nothing to do
            return;
        }

        int comparison = root.key1.compareTo(newNode.key1);
        assertNotSame(0, comparison);
        if (comparison < 0) {
            TreeNode<TKey, TValue> newRoot = new TreeNode<TKey, TValue>(root.key1, null);
            newRoot.setLeft(root);
            newRoot.setRight(newNode);
            root = newRoot;
            return;
        }
        // comparison > 0
        TreeNode<TKey, TValue> newRoot = new TreeNode<TKey, TValue>(key, null);
        newRoot.setLeft(root);
        newRoot.setRight(newNode);
        root = newRoot;
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

    public Iterator<Pair<TKey, TValue>> iterator() {
        return new TreeIterator<TKey, TValue>(root);
    }


}
