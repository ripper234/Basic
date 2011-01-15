package org.basic.datastructures;

import java.util.List;

public class IntTrie {
    private IntTrie[] children = new IntTrie[2];
    boolean terminator;
    private int size;

    public IntTrie() {}
    public IntTrie(List<Integer> list) {
        insert(list);
    }

    public void insert(List<Integer> list) {
        for (int x : list)
            insert(x);
    }

    public boolean insert(int x) {
        if (x < 0)
            throw new RuntimeException("Only positive numbers are supported");
        if (x == 0) {
            if (terminator)
                return false;

            size++;
            terminator = true;
            return true;
        }

        int bit = x % 2;
        if (children[bit] == null)
            children[bit] = new IntTrie();
        boolean result = children[bit].insert(x / 2);
        if (result)
            size++;
        return result;
    }

    public boolean delete(int x) {
        if (x < 0)
            throw new RuntimeException("Negative");

        if (x == 0) {
            if (!terminator)
                return false;

            terminator = false;
            size--;
            return true;
        }
        int bit = x % 2;
        if (children[bit] == null)
            return false;

        boolean result = children[bit].delete(x / 2);
        if (result)
            size--;
        return result;
    }

    public boolean find(int x) {
        IntTrie node = findNode(x);
        return node != null && node.terminator;
    }

    private IntTrie findNode(int x) {
        if (x < 0)
            throw new RuntimeException("Only positive numbers are supported");

        if (x == 0)
            return this;
        int bit = x % 2;
        if (children[bit] == null)
            return null;
        return children[bit].findNode(x / 2);
    }

    public int countMod(int m, int d) {
        if (d == 0)
            return size;

        int bit = m % 2;
        if (children[bit] == null) {
            if (m == 0)
                return size;
            return 0;
        }
        return children[bit].countMod(m / 2, d - 1);
    }
}
