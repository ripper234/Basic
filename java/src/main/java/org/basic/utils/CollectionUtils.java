package org.basic.utils;

import java.util.List;

public class CollectionUtils {
    public static <T> T single(List<T> list) {
        if (list.size() != 1)
            throw new RuntimeException("Expected list with one element, got " + list.size() + " instead");
        return list.get(0);
    }
}
