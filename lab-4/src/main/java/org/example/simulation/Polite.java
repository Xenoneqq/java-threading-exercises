package org.example.simulation;

import org.example.models.Philosophers.PolitePhilosopher;
import org.example.models.Simulator;
import org.example.models.Waiter;

/// Slightly slower than Naive approach because of the waiter, but guarantees no deadlock.

public class Polite {
    static void main(String args[]) {
        Waiter waiter = new Waiter(4);
        PolitePhilosopher[] philosophers = new PolitePhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new PolitePhilosopher(waiter);
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
