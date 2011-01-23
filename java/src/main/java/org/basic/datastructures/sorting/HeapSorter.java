package org.basic.datastructures.sorting;

import org.basic.datastructures.heaps.ArrayHeap;
import org.basic.datastructures.heaps.Heap;

import java.util.ArrayList;
import java.util.List;

public class HeapSorter extends Sorter {

    @Override
    int[] sort(int[] array, int a, int b) {
        Heap<Integer> heap = ArrayHeap.makeHeap(toList(array));
        int[] result = new int[array.length];
        int i = 0;
        while (!heap.isEmpty())
        {
            result[i++] = heap.takeMin();
        }
        return result;
    }

    private List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<Integer>(array.length);
        for (int x : array)
            list.add(x);
        return list;
    }
}
