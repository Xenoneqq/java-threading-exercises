package org.example.models.Philosophers;

import org.example.models.Philosopher;
import org.example.models.Waiter;

public class PolitePhilosopher extends Philosopher {

    public Waiter waiter;

    public PolitePhilosopher(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try {
            while (mealsToEat > 0) {
                startTime = System.currentTimeMillis();
                think();
                eat();
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " interrupted.");
        }
    }

    public void eat() throws InterruptedException {
        waiter.requestPermission();
        getRightFork();
        getLeftFork();
        endTime = System.currentTimeMillis();
        waitingTimes.add(endTime - startTime);
        Consume();
        waiter.releasePermission();
    }
}
