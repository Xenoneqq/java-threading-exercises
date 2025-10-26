package org.example.simulation;

import org.example.models.Philosophers.HungryPhilosopher;
import org.example.models.Simulator;

/// Usually it is the slowest.

public class Hungry extends Thread{
    public boolean printStatus = true;
    public Simulator simulator;

    public static void main(String[] args) {
        Hungry hungrySimulation = new Hungry();
        hungrySimulation.runSimulation();
    }

    @Override
    public void run() {
        runSimulation();
    }

    public void setPrintStatus (boolean status) {
        printStatus = status;
    }

    public void runSimulation() {
        HungryPhilosopher[] philosophers = new HungryPhilosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new HungryPhilosopher();
            philosophers[i].printState = printStatus;
        }
        simulator = new Simulator(philosophers);
        simulator.start();
    }
}
