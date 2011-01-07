package org.basic.datastrutcures.trees;

import org.basic.datastrutcures.Pair;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeIterator<TKey extends Comparable<TKey>, TValue> implements Iterator<Pair<TKey, TValue>> {
    private Queue<TreeNode<TKey, TValue>> queue = new LinkedList<TreeNode<TKey, TValue>>();

    public TreeIterator(TreeNode<TKey, TValue> root) {
        queue.add(root);
    }

    public boolean hasNext() {
        return !queue.isEmpty();

    }

    public Pair<TKey, TValue> next() {
        TreeNode<TKey, TValue> current = queue.remove();

        // go all the way left, while pushing right
        while (!current.children.isEmpty()) {
            for (int i = 1; i < current.children.size(); ++i)
                queue.add(current.children.get(i));
            current = current.children.get(0);
        }
        return current.toPair();
    }

    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}
