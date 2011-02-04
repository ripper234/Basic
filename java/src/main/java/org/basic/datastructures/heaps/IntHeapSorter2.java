package org.basic.datastructures.heaps;

import org.basic.datastructures.sorting.Sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntHeapSorter2 extends Sorter {

    @Override
    protected int[] sort(int[] array, int a, int b) {
        IntHeap heap = new IntHeap(toList(array));
        for (int i = a; i < b; ++i)
            array[i] = heap.takeMin();
        return Arrays.copyOfRange(array, a, b);
    }

    private List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x : array)
            list.add(x);
        return list;
    }
}
