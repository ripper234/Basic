package org.basic.datastructures.linkedlists;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNull;

public class MyLinkedList<T> implements ILinkedList<T> {
    private int size;
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    public MyLinkedList() {
        clear();
    }

    public MyLinkedList(Iterable<T> input) {
        this();
        for (T item : input)
            insertLast(item);
    }

    public LinkedListNode<T> find(T item) {
        if (item == null)
            throw new RuntimeException("Null not supported");

        for (LinkedListNode<T> i = head; i != null; i = i.next)
            if (i.item == item)
                return i;

        return null;
    }

    public void insertAfter(T item, LinkedListNode<T> node) {
        LinkedListNode<T> newNode = newNode(item);
        newNode.next = node.next;
        newNode.prev = node;
        if (node.next != null) {
            node.next.prev = newNode;
        }
        node.next = newNode;
        ++size;
    }

    public void insertBefore(T item, LinkedListNode<T> node) {
        LinkedListNode<T> newNode = newNode(item);
        if (node == head) {
            throw new RuntimeException("Can't insert before the head dummy node");
        }

        LinkedListNode<T> prev = node.prev;
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = node;
        node.prev = newNode;
        ++size;
    }

    public void insertFirst(T item) {
        insertAfter(item, head);
    }

    public void insertLast(T item) {
        LinkedListNode<T> node = newNode(item);
        assertNull(node.next);
        tail.next = node;
        node.prev = tail;
        tail = node;
        ++size;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = new LinkedListNode<T>(null);
        size = 0;
    }

    public List<T> toList() {
        final ArrayList<T> result = new ArrayList<T>();
        for (LinkedListNode<T> i = head.next; i != null; i = i.next)
            result.add(i.item);
        return result;
    }

    public void remove(LinkedListNode removed) {
        removed.prev.next = removed.next;
        if (removed.next != null)
            removed.next.prev = removed.prev;

        --size;
    }

    private LinkedListNode<T> newNode(T item) {
        return new LinkedListNode<T>(item);
    }

}


