package org.basic.google.codejam.practiceproblems.shoppinglist;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;

public class ItemsRepo {
    private static int id;
    private final Map<Integer, Boolean> perishables = newHashMap();
    private final Map<String, Integer> names = newHashMap();

    public void addItem(Item item) {
        names.put(item.name, ++id);
        perishables.put(id, item.perishable);
    }

    public int getId(String name) {
        return names.get(name);
    }

    public Set<Integer> getItemIds() {
        return new HashSet<Integer>(names.values());
    }

    public boolean isPerishable(int itemId) {
        Boolean isPerishable = perishables.get(itemId);
        return isPerishable != null && isPerishable;
    }
}
