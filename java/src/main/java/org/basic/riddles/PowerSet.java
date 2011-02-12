package org.basic.riddles;

import java.util.HashSet;
import java.util.Set;

public class PowerSet {
    public static <T> Set<Set<T>> getPset(Set<T> set) {
        Object[] arr = set.toArray();
        Set<Set<T>> pset = new HashSet<Set<T>>();
        return getPset(pset, arr, 0);
    }

    public static <T> Set<Set<T>> getPset(Set<Set<T>> pset, Object[] arr, int index) {
        if (index == arr.length) {
            pset.add(new HashSet<T>());
            return pset;
        }

        Set<Set<T>> without = getPset(pset, arr, index + 1);
        Set<Set<T>> with = new HashSet<Set<T>>();
        for (Set<T> set : without) {
            set = new HashSet<T>(set);
            set.add((T) arr[index]);
            with.add(set);
        }
        with.addAll(without);
        return with;
    }
}
