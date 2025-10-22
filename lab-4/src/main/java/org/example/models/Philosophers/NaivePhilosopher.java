package org.example.models.Philosophers;

import org.example.models.Philosopher;

public class NaivePhilosopher extends Philosopher {

    @Override
    public void run() {
        try {
            while (mealsToEat > 0) {
                think();
                float startTime = System.currentTimeMillis();
                eat();
                float endTime = System.currentTimeMillis();
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " interrupted.");
        }
    }

    public void eat(){
        getRightFork();
        getLeftFork();
        Consume();
    }

}
