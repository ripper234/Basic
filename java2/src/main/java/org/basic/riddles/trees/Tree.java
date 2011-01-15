package org.basic.riddles.trees;

public class Tree {
    Tree left, right;
    public int size() {
        int size = 1;
        if (left != null)
            size += left.size();
        if (right != null)
            size += right.size();
        return size;
    }

    public boolean leaf() {
        return right == null && left == null;
    }
}
