package org.basic.datastructures.heaps;

public interface Heap<T extends Comparable<T>> {
    void add(T item);
    T peekMin();
    T takeMin();

    boolean isEmpty();
}

