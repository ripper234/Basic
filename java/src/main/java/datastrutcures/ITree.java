package datastrutcures;

import java.util.List;

public interface ITree<T extends Comparable<T>> extends Iterable<T> {
    void add(T item);

    boolean delete(T item);
    boolean exists(T item);

    /**
     * In nodes. Empty tree - 0, Root - 1, ...
     * @return
     */
    int height();

    int size();

    List<T> toList();
}

