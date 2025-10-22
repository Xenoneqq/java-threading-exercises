package org.example.simulation;

import org.example.models.Philosopher;
import org.example.models.Philosophers.HungryPhilosopher;
import org.example.models.Philosophers.NaivePhilosopher;
import org.example.models.Simulator;

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
