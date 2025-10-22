package org.example.models.Philosophers;

import org.example.models.Philosopher;

public class SymmetricPhilosopher extends Philosopher {

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
        if(id % 2 == 0){
            getRightFork();
            getLeftFork();
        }
        else{
            getLeftFork();
            getRightFork();
        }
        Consume();
    }
}
