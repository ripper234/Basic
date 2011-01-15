package org.basic.utils;

import com.google.common.collect.Lists;

import java.util.List;

public class ListSplitter {
    private ListSplitter (){}

    public static List<List<Integer>> splitToNLists(List<Integer> list, int n) {
        if (list.size() < n)
            n = list.size();

        List<List<Integer>> result = Lists.newArrayList();
        int selected = 0;
        final int step = list.size() / n; // rounded down
        for (int i = 0; i < n; ++i) {
            int currentStep = findCurrentStep(n, selected, step, i, list.size());
            result.add(list.subList(selected, selected + currentStep));
            selected += currentStep;
        }
        return result;
    }

    public static int findCurrentStep(int n, int selected, int step, int i, int listSize) {
        if (selected + (step + 1) * (n - i) <= listSize)
            return step + 1;
        return step;
    }

    public static List<List<Integer>> splitToListsOfSizeN(List<Integer> list, int n) {
        List<List<Integer>> result = Lists.newArrayList();
        for (int i = 0; i < list.size(); i += 5) {
            result.add(list.subList(i, Math.min(i + 5, list.size())));
        }
        return result;
    }
}
