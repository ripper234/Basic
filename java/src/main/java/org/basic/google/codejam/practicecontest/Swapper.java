package org.basic.google.codejam.practicecontest;

import java.util.List;

public class Swapper {
    private Swapper () {}

    public static void swap(int[] array, int i, int j) {
        if (i == j)
            return;

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> void swap(List<T> items, int i, int j) {
        if (i == j)
            return;

        T item = items.get(i);
        items.set(i, items.get(j));
        items.set(j, item);
    }
}
