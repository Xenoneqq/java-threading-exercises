package org.example.iterativeModels;

import org.example.simulation.Polite;

public class PoliteTest {

    public static int testCount = 10000;

    public static void main(String[] args) {
        Thread[] tests = new Thread[testCount];
        for (int i = 0; i < testCount; i++) {
            System.out.println("Starting Polite Simulation: " + (i + 1));
            Polite sim = new Polite();
            sim.setPrintStatus(false);
            tests[i] = sim;
            sim.start();
        }

        for (int i = 0; i < testCount; i++) {
            try {
                tests[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\nAll Polite Simulation Tests Completed.");
        double averageTime = 0.0;
        for (int i = 0; i < testCount; i++) {
            Polite sim = (Polite) tests[i];
            averageTime += sim.simulator.simulationTime;
        }
        averageTime /= testCount;
        double roundedAvgTime = Math.round(averageTime * 1000.0) / 1000.0;
        System.out.println("Average Simulation Time for Polite Model over " + testCount + " tests: " + roundedAvgTime + " seconds.");

        double averageWaitingTime = 0.0;
        for (int i = 0; i < testCount; i++) {
            Polite sim = (Polite) tests[i];
            averageWaitingTime += sim.simulator.averagePhilosopherWaitingTime;
        }
        averageWaitingTime /= testCount;
        double roundedAvgWaitingTime = Math.round(averageWaitingTime * 1000.0) / 1000.0;
        System.out.println("Average Philosopher Waiting Time for Polite Model over " + testCount + " tests: " + roundedAvgWaitingTime + " ms.");
    }

}