package org;

import org.simulations.Simulation;

public class Runner {
    public static void main(String[] args) {
        int numberOfWriters = 10;
        int numberOfReaders = 100;
        if(args.length == 2){
            numberOfWriters = Integer.parseInt(args[0]);
            numberOfReaders = Integer.parseInt(args[1]);
        }

        Simulation simulation = new Simulation(numberOfWriters, numberOfReaders);
        simulation.start();
    }
}
