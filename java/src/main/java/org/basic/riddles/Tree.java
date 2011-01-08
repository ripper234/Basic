package org.basic.riddles;

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
}
