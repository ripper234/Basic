package org.basic.google.codejam.practiceproblems.shoppinglist;

import org.basic.datastructures.Point;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;

public class Store {
    private final Map<Integer, Integer> items = newHashMap();
    public final Point location;

    public Store(int x, int y) {
        this.location = new Point(x, y);
    }

    public void addItem(int itemId, int price) {
        items.put(itemId, price);
    }

    public Point getLocation() {
        return location;
    }

    public Set<Integer> getItems() {
        return items.keySet();
    }

    public double getCost(int itemId) {
        return items.get(itemId);
    }

    @Override
    public String toString() {
        return "Store{" +
                "location=" + location +
                '}';
    }
}


