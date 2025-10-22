package org.example.models.Philosophers;

import org.example.models.Philosopher;

public class HungryPhilosopher extends Philosopher {

    @Override
    public void run() {
        try {
            while (mealsToEat > 0) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " interrupted.");
        }
    }

    public void eat() throws InterruptedException {
        if((leftForkObtained = leftFork.tryAcquire()) && (rightForkObtained = rightFork.tryAcquire())){
            Consume();
        }
        else{
            if (leftForkObtained) leftFork.release();
            if (rightForkObtained) rightFork.release();
            Thread.sleep(10);
        }
    }
}
