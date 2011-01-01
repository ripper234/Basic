package datastrutcures;

import java.util.List;

public interface ILinkedList<T> {
    LinkedListNode<T> find(T item);

    void insertAfter(T item, LinkedListNode<T> node);
    void insertBefore(T item, LinkedListNode<T> node);
    void insertFirst(T item);
    void insertLast(T item);

    int size();
    void clear();
    List<T> toList();
    void remove(LinkedListNode removed);
}

