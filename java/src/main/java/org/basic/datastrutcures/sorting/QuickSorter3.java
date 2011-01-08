package org.basic.datastrutcures.sorting;

import java.util.Random;

public class QuickSorter3 extends Sorter {
    private final Random random = new Random(0);

    @Override
    public int[] sort(int[] array, int a, int b) {
        if (a >= b)
            return array;

        int pivot = random.nextInt(b-a + 1);
        swap(array, pivot, 0);
        int pivotValue = array[0];

        int i = a;
        int j = a;
        int k = b;

        // [a..i) are < pivotValue
        // [i..j] are = pivotValue
        // (k..b] are > pivotValue
        // loop finishes when j = k
        while (j < k) {
            ++j;
            int comparison = array[j] - pivotValue;
            if (comparison == 0) {
                continue;
            }
            while (comparison > 0 && j < k) {
                swap(array, j, k--);
                comparison = array[j] - pivotValue;
            }
            if (comparison < 0) {
                swap(array, j, i++);
            }
        }
        sort(array, 0, i-1);
        sort(array, k+1, b);
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j)
            return;

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
