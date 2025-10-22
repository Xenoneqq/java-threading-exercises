package org.example.models;

import java.util.concurrent.Semaphore;

public class Waiter {
    private final Semaphore semaphore;

    public Waiter(int maxEating) {
        semaphore = new Semaphore(maxEating);
    }

    public void requestPermission() throws InterruptedException {
        semaphore.acquire();
    }

    public void releasePermission() {
        semaphore.release();
    }
}

