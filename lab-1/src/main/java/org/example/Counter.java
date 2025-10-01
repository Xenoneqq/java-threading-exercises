package org.example;

public class Counter {
    public int value = 0;

    public void IncrementUnsynchronized() {
        value++;
    }

    public synchronized void IncrementSynchronized() {
        value++;
    }

    public void DecrementUnsynchronized() {
        value--;
    }

    public synchronized void DecrementSynchronized() {
        value--;
    }
}
