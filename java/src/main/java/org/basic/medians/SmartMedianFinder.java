package org.basic.medians;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.basic.utils.ListSplitter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class SmartMedianFinder implements IMedianFinder {
    public int findMedian(List<Integer> list) {
        return list.get(findIndexOfNth(list, list.size() / 2));
    }

    public int findIndexOfNth(List<Integer> list, int index) {
        if (list.size() <= 5)
            return findSmall(list, index);

        List<List<Integer>> lists = ListSplitter.splitToListsOfSizeN(list, 5);
        ArrayList<Integer> medians = Lists.newArrayList(
                Collections2.transform(lists, new Function<List<Integer>, Integer>() {
                    public Integer apply(List<Integer> from) {
                        return findMedian(from);
                    }
                }));
        int medianOfMedians = findMedian(medians);
        int medianPlace = partition(list, medianOfMedians);
        if (medianPlace == index)
            return medianPlace;
        if (index < medianPlace)
            return findIndexOfNth(list.subList(0, medianPlace), index);
        return medianPlace + 1 + findIndexOfNth(sublist(list, medianPlace + 1), index - medianPlace - 1);
    }

    private int findSmall(List<Integer> list, int index) {
        return new DumbMedianFinder().findIndexOfNth(list, index);
    }

    /**
     * Put x in its correct place (make sure all items to the left of it are <=
     * and all items to the right are >=)
     * <p/>
     * Returns the place
     *
     * @param list
     * @param x
     * @return
     */
    public static int partition(List<Integer> list, int x) {
        int head = 0;
        int tail = list.size() - 1;
        int equal = 0;

        // invariants:
        // [0, head) are < x
        // [head, equal) are = x
        // (tail, n] are > x
        //
        // [equal, tail] we haven't looked at yet
        // we'll finish when equal = tail
        while (equal <= tail) {
            int current = list.get(equal);
            if (current < x) {
                swap(list, equal++, head++);
                continue;
            }
            if (current == x) {
                ++equal;
                continue;
            }
            // else (current > x)
            swap(list, equal, tail--);
        }
        return head;
    }

    private List<Integer> sublist(List<Integer> list, int x) {
        return list.subList(x, list.size());
    }

}
