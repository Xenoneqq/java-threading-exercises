package org.example.simulation;

import org.example.models.Philosophers.PolitePhilosopher;
import org.example.models.Simulator;
import org.example.models.Waiter;

/// Slightly slower than Naive approach because of the waiter, but guarantees no deadlock.

public class Polite extends Thread {
    public boolean printStatus = true;
    public Simulator simulator;

    public static void main() {
        Polite politeSimulation = new Polite();
        politeSimulation.runSimulation();
    }

    @Override
    public void run() {
        runSimulation();
    }

    public void setPrintStatus (boolean status) {
        printStatus = status;
    }

    public void runSimulation() {
        Waiter waiter = new Waiter(4);
        PolitePhilosopher[] philosophers = new PolitePhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new PolitePhilosopher(waiter);
            philosophers[i].printState = printStatus;
        }
        simulator = new Simulator(philosophers);
        simulator.start();
    }

}
