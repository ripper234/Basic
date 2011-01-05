package org.basic.datastrutcures.trees;

import org.basic.datastrutcures.Pair;

import java.util.Iterator;

public class TreeIterator<TKey extends Comparable<TKey>, TValue> implements Iterator<Pair<TKey, TValue>> {
    private TreeNode<TKey, TValue> current;

    public TreeIterator(TreeNode<TKey, TValue> root) {
        current = root;
        while (current.children != null)
            current = current.children.get(0);
    }

    public boolean hasNext() {
        return current != null;

    }

    public Pair<TKey, TValue> next() {
        Pair<TKey,TValue> result = current.toPair();

        // find next node
        current = current.findNodeGreaterThan(current.keys.get(0));
        return result;
    }

    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}
