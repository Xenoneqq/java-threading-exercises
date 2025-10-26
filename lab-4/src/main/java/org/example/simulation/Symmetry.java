package org.example.simulation;
import org.example.models.Philosophers.SymmetricPhilosopher;
import org.example.models.Simulator;

/// This is usually the fastest approach. Prevents deadlock with smart fork picking rules.

public class Symmetry extends Thread {
    public boolean printStatus = true;
    public Simulator simulator;

    public static void main(String[] args) {
        Symmetry symmetrySimulation = new Symmetry();
        symmetrySimulation.runSimulation();
    }

    @Override
    public void run() {
        runSimulation();
    }

    public void setPrintStatus (boolean status) {
        printStatus = status;
    }

    public void runSimulation() {
        SymmetricPhilosopher[] philosophers = new SymmetricPhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new SymmetricPhilosopher();
            philosophers[i].printState = printStatus;
        }
        simulator = new Simulator(philosophers);
        simulator.start();
    }
}
