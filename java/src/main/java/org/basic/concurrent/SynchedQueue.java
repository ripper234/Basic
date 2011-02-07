package org.basic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class SynchedQueue<T> {
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition free = lock.newCondition();

    private final List<T> buffer;
    private int head;
    private int tail;
    private volatile int size;
    private final int capacity;

    public SynchedQueue(int capacity) {
        // http://stackoverflow.com/questions/4912088/how-to-create-a-fixed-size-generic-buffer-in-java
        buffer = new ArrayList<T>(capacity);
        for (int i = 0; i < capacity; ++i)
            buffer.add(null);
        this.capacity = capacity;
    }

    public int size() {
        return size;
    }

    public void enqueue(T x) {
        if (x == null)
            throw new RuntimeException("Doesn't allow storing nulls");

        lock.lock();
        try {
            while (!tryEnqueue(x)) {
                try {
                    free.await();
                } catch (InterruptedException ignored) {
                }
            }
            full.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public T dequeue() {
        lock.lock();
        try {
            while (true) {
                T item = tryDequeue();
                if (item != null) {
                    free.signal();
                    return item;
                }
                try {
                    full.await();
                } catch (InterruptedException ignored) {
                }
            }
        }
        finally {
            lock.unlock();
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

    static final Object lll = new Object();
    static void rentrantTest(int n) {
        synchronized (lll) {
            if (n == 0)
                return;

            rentrantTest(n-1);
            System.out.println(n);
        }
    }
    public static void main(String[] args) {
        rentrantTest(3);


        ExecutorService taskExecutor = Executors.newCachedThreadPool();
//        CompletionService<Long> taskCompletionService =
//                new ExecutorCompletionService<Long>(taskExecutor);
        Callable<Long> sortAlgo = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return 1L;
            }
        };

        Future<Long> sortAlgoFuture = // taskCompletionService.submit(sortAlgo);
            taskExecutor.submit(sortAlgo);

        while (!sortAlgoFuture.isDone()) {
            // Do some work...
            System.out.println("Working on something...");
        }
        try {
            System.out.println(sortAlgoFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
