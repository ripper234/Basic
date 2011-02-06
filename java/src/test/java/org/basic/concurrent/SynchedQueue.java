package org.basic.concurrent;

import java.util.ArrayList;
import java.util.List;

public class SynchedQueue<T> {
    private final Object lock = new Object();
    private final Object full = new Object();
    private final Object free = new Object();

    private final List<T> buffer;
    private int head;
    private int tail;
    private int size;
    private final int capacity;

    public SynchedQueue(int capacity) {
        // http://stackoverflow.com/questions/4912088/how-to-create-a-fixed-size-generic-buffer-in-java
        buffer = new ArrayList<T>(capacity);
        for (int i = 0; i < capacity; ++i)
            buffer.add(null);
        this.capacity = capacity;
    }

    public void enqueue(T x) {
        if (x == null)
            throw new RuntimeException("Doesn't allow storing nulls");

        synchronized (lock) {
            while (!tryEnqueue(x)) {
                try {
                    free.wait();
                } catch (InterruptedException ignored) {
                }
            }
            full.notify();
        }
    }

    public T dequeue() {
        synchronized (lock) {
            while (true) {
                T item = tryDequeue();
                if (item != null)
                {
                    free.notify();
                    return item;
                }
                try {
                    full.wait();
                }
                catch (InterruptedException ignored) {}
            }
        }
    }

    private boolean tryEnqueue(T x) {
        assert size <= capacity;
        if (size >= capacity) {
            return false;
        }

        buffer.set(tail, x);
        tail = (tail + 1) % capacity;
        ++size;
        return true;
    }

    private T tryDequeue() {
        assert size >= 0;
        if (size == 0)
            return null;
        T item = buffer.get(head);
        head = (head + 1) % capacity;
        --size;
        return item;
    }
}
