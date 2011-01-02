package datastrutcures.trees;

import java.util.List;

public interface ITree<TKey extends Comparable<TKey>, TValue> extends Iterable<TKey> {
    void put(TKey key, TValue value);

    boolean delete(TKey item);
    TValue find(TKey item);

    /**
     * In nodes. Empty tree - 0, Root - 1, ...
     * @return
     */
    int height();

    int size();

    List<TKey> toList();
}

