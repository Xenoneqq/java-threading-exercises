package org.example.iterativeModels;

import org.example.simulation.Symmetry;

public class SymmetryTest {

    public static int testCount = 10000;

    public static void main(String[] args) {
        Thread[] tests = new Thread[testCount];
        for (int i = 0; i < testCount; i++) {
            System.out.println("Starting Symmetry Simulation: " + (i + 1));
            Symmetry sim = new Symmetry();
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

        System.out.println("\n\nAll Symmetry Simulation Tests Completed.");
        double averageTime = 0.0;
        for (int i = 0; i < testCount; i++) {
            Symmetry sim = (Symmetry) tests[i];
            averageTime += sim.simulator.simulationTime;
        }
        averageTime /= testCount;
        double roundedAvgTime = Math.round(averageTime * 1000.0) / 1000.0;
        System.out.println("Average Simulation Time for Symmetry Model over " + testCount + " tests: " + roundedAvgTime + " seconds.");

        double averageWaitingTime = 0.0;
        for (int i = 0; i < testCount; i++) {
            Symmetry sim = (Symmetry) tests[i];
            averageWaitingTime += sim.simulator.averagePhilosopherWaitingTime;
        }
        averageWaitingTime /= testCount;
        double roundedAvgWaitingTime = Math.round(averageWaitingTime * 1000.0) / 1000.0;
        System.out.println("Average Philosopher Waiting Time for Symmetry Model over " + testCount + " tests: " + roundedAvgWaitingTime + " ms.");
    }

}