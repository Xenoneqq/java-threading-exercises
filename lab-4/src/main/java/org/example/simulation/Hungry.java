package org.example.simulation;

import org.example.models.Philosophers.HungryPhilosopher;
import org.example.models.Simulator;

/// Usually it is the slowest.

public class Hungry {
    public static boolean printStatus = true;

    static void main(String args[]) {
        runSimulation();
    }

    static void setPrintStatus (boolean status) {
        printStatus = status;
    }

    static void runSimulation() {
        HungryPhilosopher[] philosophers = new HungryPhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new HungryPhilosopher();
            philosophers[i].printState = printStatus;
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
