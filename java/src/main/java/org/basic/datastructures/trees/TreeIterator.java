package org.basic.datastructures.trees;

import org.basic.datastructures.Pair;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator<TKey extends Comparable<TKey>, TValue> implements Iterator<Pair<TKey, TValue>> {
    private Stack<TreeNode<TKey, TValue>> stack = new Stack<TreeNode<TKey, TValue>>();

    public TreeIterator(TreeNode<TKey, TValue> root) {
        stack.push(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();

    }

    public Pair<TKey, TValue> next() {
        TreeNode<TKey, TValue> current = stack.pop();

        // go all the way left, while pushing right
        while (!current.children.isEmpty()) {
            for (int i = current.children.size() - 1; i > 0; --i)
                stack.push(current.children.get(i));
            current = current.children.get(0);
        }
        return current.toPair();
    }

    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}
