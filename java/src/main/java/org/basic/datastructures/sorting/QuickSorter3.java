package org.basic.datastructures.sorting;

import java.util.Arrays;

public class QuickSorter3 extends Sorter {
    public int[] sort(int[] arr, int a, int b) {
        if (a + 1 >= b)
            return Arrays.copyOfRange(arr, a, b);

        int pivot = arr[a];
        int i = a;
        int j = a;
        int k = b;
        // arr[a,i) are < pivot
        // arr[i,j) are = pivot
        // arr[j,k) are ??
        // arr[k,b) are > pivot

        while (j < k) {
            int x = arr[j++];
            if (x == pivot)
                continue;
            if (x < pivot) {
                swap(arr, i, j-1);
                i++;
                continue;
            }
            // x > pivot
            --j;
            --k;
            swap(arr, j, k);
        }

        sort(arr, a, i);
        sort(arr, k, b);
        return arr;
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

