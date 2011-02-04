package org.basic.datastructures.sorting;

public abstract class Sorter {
    protected abstract int[] sort(int[] array, int a, int b);
    public int[] sort(int[] array) {
        return sort(array, 0, array.length);
    }
}

