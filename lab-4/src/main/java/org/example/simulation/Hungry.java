package org.example.simulation;

import org.example.models.Philosophers.HungryPhilosopher;
import org.example.models.Simulator;

/// Usually it is the slowest.

public class Hungry {
    static void main(String args[]) {
        HungryPhilosopher[] philosophers = new HungryPhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new HungryPhilosopher();
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
