package org.example.simulation;

import org.example.models.Philosophers.NaivePhilosopher;
import org.example.models.Simulator;

/// This is the simplest implementation, but can lead to deadlock.

public class Naive {

    public static boolean printStatus = true;

    static void main(String args[]) {
        runSimulation();
    }

    static void setPrintStatus (boolean status) {
        printStatus = status;
    }

    static void runSimulation() {
        NaivePhilosopher[] philosophers = new NaivePhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new NaivePhilosopher();
            philosophers[i].printState = printStatus;
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
