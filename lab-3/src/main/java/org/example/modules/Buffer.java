package org.example.modules;
import java.util.concurrent.locks.*;

public class Buffer {
    private final int[] buffer;
    private int count = 0, in = 0, out = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int size) {
        buffer = new int[size];
    }

    public void put(int value) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                notFull.await();
            }
            buffer[in] = value;
            in = (in + 1) % buffer.length;
            count++;
            notEmpty.signal(); // obudź tylko konsumentów
        } finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            int value = buffer[out];
            out = (out + 1) % buffer.length;
            count--;
            notFull.signal(); // obudź tylko producentów
            return value;
        } finally {
            lock.unlock();
        }
    }
}
