package org.example.simulation;
import org.example.models.Philosophers.SymmetricPhilosopher;
import org.example.models.Simulator;

/// This is usually the fastest approach. Prevents deadlock with smart fork picking rules.

public class Symmetry {
    public static boolean printStatus = true;

    static void main(String args[]) {
        runSimulation();
    }

    static void setPrintStatus (boolean status) {
        printStatus = status;
    }

    static void runSimulation() {
        SymmetricPhilosopher[] philosophers = new SymmetricPhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new SymmetricPhilosopher();
            philosophers[i].printState = printStatus;
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
