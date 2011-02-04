package org.basic.datastructures.heaps;

import org.basic.datastructures.sorting.Sorter;

import java.util.Arrays;

public class IntHeapSorter1 extends Sorter {
    @Override
    protected int[] sort(int[] array, int a, int b) {
        IntHeap heap = new IntHeap();
        for (int i = a; i < b; ++i)
            heap.add(array[i]);

        for (int i = a; i < b; ++i) {
            int min = heap.takeMin();
            array[i] = min;
        }
        return Arrays.copyOfRange(array, a, b);
    }
}

