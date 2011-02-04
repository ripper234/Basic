package org.basic.datastructures.sorting;

import org.basic.datastructures.sorting.Sorter;

import java.util.Arrays;

public class MergeSort extends Sorter {
    public int[] sort(int[] arr, int a, int b) {
        if (b <= a+1)
            return Arrays.copyOfRange(arr, a, b);

        int mid = (a+b)/2;
        assert mid > a;
        assert mid < b;

        int[] left = sort(arr, a, mid);
        int[] right = sort(arr, mid, b);
        return merge(left, right);
    }

    int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < result.length) {
            if (j == b.length) {
                result[k++] = a[i++];
                continue;
            }
            if (i == a.length) {
                result[k++] = b[j++];
                continue;
            }
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            }
            else {
                result[k++] = b[j++];
            }
        }
        return result;
    }
}
