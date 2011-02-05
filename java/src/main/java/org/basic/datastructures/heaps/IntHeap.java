package org.basic.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

public class IntHeap {
    final List<Integer> list;

    public IntHeap() {
        list = new ArrayList<Integer>();
    }

    public int peekMin() {
        return list.get(0);
    }

    public void add(int x) {
        list.add(x);
        siftUp(list.size() - 1);
    }

    void siftUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (list.get(i) >= list.get(p)) {
                return;
            }
            swap(i, p);
            i = p;
        }
    }

    public int takeMin() {
        int min = peekMin();
        swap(0, list.size() - 1);
        list.remove(list.size() - 1);
        siftDown(0);
        return min;
    }

    static int parent(int i) {
        return (i - 1) / 2;
    }

    public IntHeap(List<Integer> list) {
        this.list = new ArrayList<Integer>(list);
        for (int i = parent(list.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    void siftDown(int i) {
        while (true) {
            if (child(i) + 1 >= list.size()) {
                if (child(i) >= list.size()) {
                    return;
                }
                if (list.get(i) <= list.get(child(i)))
                    return;
                siftOneLeft(i);
                return;
            }
            if (list.get(child(i)) > list.get(child(i) + 1)) {
                if (!siftOneRight(i))
                    return;
                i = child(i) + 1;
                continue;
            }
            if (!siftOneLeft(i))
                return;
            i = child(i);
        }
    }

    static int child(int i) {
        return 2 * i + 1;
    }

    boolean siftOneLeft(int i) {
        if (list.get(i) <= list.get(child(i)))
            return false;

        swap(i, child(i));
        return true;
    }

    boolean siftOneRight(int i) {
        if (list.get(i) <= list.get(child(i) + 1))
            return false;

        swap(i, child(i) + 1);
        return true;
    }

    void swap(int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public int size() {
        return list.size();
    }
}

