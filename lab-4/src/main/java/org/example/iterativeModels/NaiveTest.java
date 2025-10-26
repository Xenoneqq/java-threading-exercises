package org.example.iterativeModels;

import org.example.simulation.Naive;

public class NaiveTest {

    public static int testCount = 10000;

    public static void main(String[] args) {
        Thread[] tests = new Thread[testCount];
        for(int i = 0; i < testCount; i++) {
            System.out.println("Starting Naive Simulation: " + (i + 1));
            Naive naiveSimulation = new Naive();
            naiveSimulation.setPrintStatus(false);
            tests[i] = naiveSimulation;
            naiveSimulation.start();
        }

        for(int i = 0; i < testCount; i++) {
            try {
                tests[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n");
        System.out.println("All Naive Simulation Tests Completed.");
        double averageTime = 0.0;
        for(int i = 0; i < testCount; i++) {
            Naive naiveSimulation = (Naive) tests[i];
            averageTime += naiveSimulation.simulator.simulationTime;
        }
        averageTime /= testCount;
        double roundedAvgTime = Math.round(averageTime * 1000.0) / 1000.0;
        System.out.println("Average Simulation Time for Naive Model over " + testCount + " tests: " + roundedAvgTime + " seconds.");

        double averageWaitingTime = 0.0;
        for(int i = 0; i < testCount; i++) {
            Naive naiveSimulation = (Naive) tests[i];
            averageWaitingTime += naiveSimulation.simulator.averagePhilosopherWaitingTime;
        }
        averageWaitingTime /= testCount;
        double roundedAvgWaitingTime = Math.round(averageWaitingTime * 1000.0) / 1000.0;
        System.out.println("Average Philosopher Waiting Time for Naive Model over " + testCount + " tests: " + roundedAvgWaitingTime + " ms.");
    }

}
