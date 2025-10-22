package org.example.models;

import java.util.concurrent.Semaphore;

public class Fork extends Semaphore {

    public Fork(int permits) {
        super(permits);
    }

    public Fork(int permits, boolean fair) {
        super(permits, fair);
    }
}
