package org.example.models.Philosophers;

import org.example.models.Philosopher;

public class HungryPhilosopher extends Philosopher {
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
        if((leftForkObtained = leftFork.tryAcquire()) && (rightForkObtained = rightFork.tryAcquire())){
            endTime = System.currentTimeMillis();
            waitingTimes.add(endTime - startTime);
            Consume();
        }
        else{
            if (leftForkObtained) leftFork.release();
            if (rightForkObtained) rightFork.release();
            Thread.sleep(10);
        }
    }
}
