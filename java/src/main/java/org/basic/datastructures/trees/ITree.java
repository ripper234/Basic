package org.basic.datastructures.trees;

import org.basic.datastructures.Pair;

import java.util.List;

public interface ITree<TKey extends Comparable<TKey>, TValue> extends Iterable<Pair<TKey, TValue>> {
    void put(TKey key, TValue value);

    boolean delete(TKey item);
    TValue find(TKey item);

    /**
     * In nodes. Empty tree - 0, Root - 1, ...
     * @return
     */
    int height();

    int size();

    List<Pair<TKey, TValue>> toList();
    List<TValue> values();

    List<TKey> keys();
}

