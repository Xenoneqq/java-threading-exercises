package org.example.models;

public class Simulator {
    Philosopher[] philosophers;

    public Simulator(Philosopher[] philosophers) {
        this.philosophers = philosophers;
    }

    public void start() {
        int N = philosophers.length;
        Fork[] forks = new Fork[N];

        // Setting up forks
        for (int i = 0; i < N; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < N; i++) {
            philosophers[i].leftFork = forks[i];
            philosophers[i].rightFork = forks[(i+1) % N];
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

        System.out.println("Simulation ended.");
    }
}
