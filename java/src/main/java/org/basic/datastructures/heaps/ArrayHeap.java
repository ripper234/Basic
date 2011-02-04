package org.basic.datastructures.heaps;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.basic.google.codejam.practicecontest.Swapper.swap;

public class ArrayHeap<T extends Comparable<T>> implements Heap<T> {
    private final List<T> items = newArrayList();

    public void addAll(Iterable<T> items) {
        for (T item : items)
            add(item);
    }

    public void add(T item) {
        items.add(item);
        siftUp(items.size() - 1);
    }

    private void siftUp(int i) {
        while (i > 0 && items.get(i).compareTo(items.get(getParent(i))) < 0) {
            swap(items, i, getParent(i));
            i = getParent(i);
        }
    }

    private static int getParent(int i) {
        return (i - 1) / 2;
    }

    private static int getLeftChild(int i) {
        return 2 * i + 1;
    }

    private static int getRightChild(int i) {
        return 2 * i + 2;
    }

    private int siftOneLeft(int i) {
        int left = getLeftChild(i);
        swap(items, i, left);
        return left;
    }

    private int siftOneRight(int i) {
        int right = getRightChild(i);
        swap(items, i, right);
        return right;
    }

    private int siftDown(int i) {
        while (true) {
            int left = getLeftChild(i);
            int right = getRightChild(i);
            if (right >= items.size()) {
                if (left >= items.size()) {
                    return i;
                }
                if (items.get(left).compareTo(items.get(i)) > 0)
                    return i;

                return siftOneLeft(i);
            }
            if (items.get(left).compareTo(items.get(i)) >= 0 &&
                    items.get(right).compareTo(items.get(i)) >= 0)
                return i;

            if (items.get(left).compareTo(items.get(right)) < 0) {
                i = siftOneLeft(i);
            } else {
                i = siftOneRight(i);
            }
        }
    }

    public T peekMin() {
        if (items.size() == 0)
            return null;

        return items.get(0);
    }

    public T takeMin() {
        T top = peekMin();
        swap(items, 0, items.size() - 1);
        items.remove(items.size() - 1);
        siftDown(0);
        return top;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    /*
     * O(n)
     */
    public static <T extends Comparable<T>> Heap<T> makeHeap(List<T> items) {
        ArrayHeap<T> result = new ArrayHeap<T>();
        result.items.addAll(items);
        for (int i = items.size() - 2; i >= 0; --i)
            result.siftDown(i);
        return result;
    }
}
