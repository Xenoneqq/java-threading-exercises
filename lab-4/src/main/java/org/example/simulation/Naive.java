package org.example.simulation;

import org.example.models.Philosophers.NaivePhilosopher;
import org.example.models.Simulator;

/// This is the simplest implementation, but can lead to deadlock.

public class Naive {
    static void main(String args[]) {
        NaivePhilosopher[] philosophers = new NaivePhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new NaivePhilosopher();
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
