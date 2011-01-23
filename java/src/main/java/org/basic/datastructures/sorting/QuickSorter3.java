package org.basic.datastructures.sorting;

import java.util.Random;

import static org.basic.google.codejam.practicecontest.Swapper.swap;

public class QuickSorter3 extends Sorter {
    private final Random random = new Random(0);

    @Override
    public int[] sort(int[] array, int a, int b) {
        if (a >= b)
            return array;

        int pivot = a + random.nextInt(b - a + 1);
        swap(array, pivot, a);
        int pivotValue = array[a];

        int i = a;
        int j = a;
        int k = b;

        // [a..i) are < pivotValue
        // [i..j] are = pivotValue
        // (j, k] are not yet scanned
        // (k..b] are > pivotValue
        // loop finishes when j = k
        while (j < k ) {
            ++j;
            int comparison = array[j] - pivotValue;
            if (comparison == 0) {
                continue;
            }
            if (comparison < 0) {
                swap(array, j, i++);
                continue;
            }

            swap(array, j--, k--);
        }
        sort(array, 0, i - 1);
        sort(array, k + 1, b);
        return array;
    }

}

