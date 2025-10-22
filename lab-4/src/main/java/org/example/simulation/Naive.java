package org.example.simulation;

import org.example.models.Philosopher;
import org.example.models.Philosophers.NaivePhilosopher;
import org.example.models.Simulator;

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
