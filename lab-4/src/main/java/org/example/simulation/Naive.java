package org.example.simulation;

import org.example.models.Philosophers.NaivePhilosopher;
import org.example.models.Simulator;

/// This is the simplest implementation, but can lead to deadlock.

public class Naive extends Thread {

    public boolean printStatus = true;
    public Simulator simulator;

    public static void main(String[] args) {
        Naive naiveSimulation = new Naive();
        naiveSimulation.runSimulation();
    }

    @Override
    public void run() {
        runSimulation();
    }

    public void setPrintStatus (boolean status) {
        printStatus = status;
    }

    public void runSimulation() {
        NaivePhilosopher[] philosophers = new NaivePhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new NaivePhilosopher();
            philosophers[i].printState = printStatus;
        }
        simulator = new Simulator(philosophers);
        simulator.start();
    }
}
