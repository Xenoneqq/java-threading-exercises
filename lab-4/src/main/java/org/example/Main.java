package org.example;

import org.example.models.Fork;
import org.example.models.Philosopher;

public class Main {
    static void main(String args[]) {

        int N = 5;
        Fork[] forks = new Fork[N];
        Philosopher[] philosophers = new Philosopher[N];

        // Setting up forks
        for (int i = 0; i < N; i++) {
            forks[i] = new Fork(1);
        }

        philosophers[0] = new Philosopher();
        philosophers[0].leftFork = forks[N - 1];
        philosophers[0].rightFork = forks[0];

        for (int i = 1; i < N; i++) {
            philosophers[i] = new Philosopher();
            philosophers[i].leftFork = forks[i - 1];
            philosophers[i].rightFork = forks[i];
        }

        // Starting philosopher threads
        for (int i = 0; i < N; i++) {
            philosophers[i].start();
        }

        for(int i = 0; i < philosophers.length; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        float averageTimeToEat = 0;
        for(int i = 0; i < philosophers.length; i++) {
            float totalTime = 0;
            for(int j = 0; j < philosophers[i].consumptionTime.size(); j++) {
                totalTime += philosophers[i].consumptionTime.get(j);
            }
            float averageTime = totalTime / philosophers[i].consumptionTime.size();
            averageTimeToEat += averageTime;
        }
        averageTimeToEat /= philosophers.length;
        System.out.println("Average time to eat across all philosophers: " + averageTimeToEat + " ms" +
                " / " + (averageTimeToEat / 1000) + " s");

    }
}
