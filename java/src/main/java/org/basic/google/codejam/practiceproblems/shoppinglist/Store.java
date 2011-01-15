package org.basic.google.codejam.practiceproblems.shoppinglist;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Store {
    private final Map<Integer, Integer> items = newHashMap();
    public final int x;
    public final int y;

    public Store(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addItem(int itemId, int price) {
        items.put(itemId, price);
    }
}


