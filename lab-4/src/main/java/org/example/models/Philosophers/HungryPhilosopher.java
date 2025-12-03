package org.example.models.Philosophers;

import org.example.models.Philosopher;

public class HungryPhilosopher extends Philosopher {

    private boolean obtainedBothForks = true;

    @Override
    public void run() {
        try {
            while (mealsToEat > 0) {
                if(obtainedBothForks) {
                    startTime = System.currentTimeMillis();
                    obtainedBothForks = false;
                }
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
            obtainedBothForks = true;
            Consume();
        }
        else{
            if (leftForkObtained) leftFork.release();
            if (rightForkObtained) rightFork.release();
            Thread.sleep(10);
        }
    }
}
