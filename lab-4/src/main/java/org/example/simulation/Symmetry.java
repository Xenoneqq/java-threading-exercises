package org.example.simulation;
import org.example.models.Philosophers.SymmetricPhilosopher;
import org.example.models.Simulator;

/// This is usually the fastest approach. Prevents deadlock with smart fork picking rules.

public class Symmetry {
    static void main(String args[]) {
        SymmetricPhilosopher[] philosophers = new SymmetricPhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new SymmetricPhilosopher();
        }
        Simulator simulator = new Simulator(philosophers);
        simulator.start();
    }
}
