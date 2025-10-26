package org.example.models;

public class Simulator {
    Philosopher[] philosophers;
    public double simulationTime = 0;
    public boolean printPhilosopherStates = true;

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

        double startTime = System.currentTimeMillis();

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

        double endTime = System.currentTimeMillis();
        simulationTime = (endTime - startTime) / 1000.0;
        System.out.println("Simulation ended with time of " + simulationTime + " seconds.");

        double averagePhilosopherWaitingTime = 0.0;
        for(int i = 0; i < philosophers.length; i++) {
            double totalWaitingTime = 0.0;
            for(int j = 0; j < philosophers[i].waitingTimes.size(); j++) {
                totalWaitingTime += philosophers[i].waitingTimes.get(j);
            }
            double averageWaitingTime = totalWaitingTime / philosophers[i].waitingTimes.size();
            averagePhilosopherWaitingTime += averageWaitingTime;
        }
        averagePhilosopherWaitingTime /= philosophers.length;
        double avgTime = Math.round(averagePhilosopherWaitingTime * 1000.0) / 1000.0;
        System.out.println("Average philosopher waiting time: " + avgTime + " ms.");
    }
}
