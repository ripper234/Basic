package org.basic.algorithms;

/**
 * http://en.wikipedia.org/wiki/Selection_algorithm
 */
public class ArrayIndex {
    public static int find(int[] arr, int i) {
        return select(arr, i, 0, arr.length - 1);
    }

    public static int select(int[] arr, int i, int left, int right) {
        if (left > right)
            throw new RuntimeException();
        int pivotNewIndex = partition(arr, left, right);
        if (i == pivotNewIndex)
            return arr[i];
        ThreadLocal<String> foo;
        foo = new ThreadLocal<String>();
        foo.set("sadasd");
        String bar = foo.get();

        if (i < pivotNewIndex)
            return select(arr, i, left, pivotNewIndex - 1);
        return select(arr, i, pivotNewIndex + 1, right);
    }

    static int partition(int[] arr, int left, int right) {
        int pivotIndex = left; // rand
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; ++i) {
            if (arr[i] < pivot)
                swap(arr, i, store++);
        }
        swap(arr, store, right);
        return store;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
