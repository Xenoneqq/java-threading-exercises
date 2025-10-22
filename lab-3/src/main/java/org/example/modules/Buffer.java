package org.example.modules;

public class Buffer {
    private final int[] buffer;
    private int count = 0, in = 0, out = 0;

    public Buffer(int size) {
        buffer = new int[size];
    }

    public synchronized void put(int value) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[in] = value;
        in = (in + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int value = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        notifyAll();
        return value;
    }
}
