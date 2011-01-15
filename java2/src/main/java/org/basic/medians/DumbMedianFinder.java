package org.basic.medians;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DumbMedianFinder implements IMedianFinder{
    public int findMedian(List<Integer> list) {
        return list.get(findIndexOfNth(list, list.size() / 2));
    }
    public int findIndexOfNth(List<Integer> list, int index) {
        ArrayList<Integer> sorted = new ArrayList<Integer>(list);
        Collections.sort(sorted);

        return list.indexOf(sorted.get(index));
    }
}
