package org.simulations;

import org.editors.Reader;
import org.modules.List;
import org.editors.Writer;

public class Simulation extends Thread {

    public Simulation(int numberOfWriters, int numberOfReaders) {
        this.numberOfWriters = numberOfWriters;
        this.numberOfReaders = numberOfReaders;
    }

    public Simulation(){}

    int numberOfWriters = 1;
    int numberOfReaders = 10;
    long finalTime = 0;

    @Override
    public void run() {

        System.out.println("--- Starting Simulation ---");
        System.out.println("Number of Writer Threads: " + numberOfWriters);
        System.out.println("Number of Reader Threads: " + numberOfReaders);

        List sharedList = new List();

        Thread[] writers = new Writer[numberOfWriters];
        for (int i = 0; i < numberOfWriters; i++) {
            Thread writer = new Writer(sharedList, 10);
            writers[i] = writer;
            writer.start();
        }

        Thread[] readers = new Reader[numberOfReaders];
        for (int i = 0; i < numberOfReaders; i++) {
            Thread reader = new Reader(sharedList, 10);
            readers[i] = reader;
            reader.start();
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfWriters; i++) {
            try {
                writers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < numberOfReaders; i++) {
            try {
                readers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\n--- Simulation Results ---");
        System.out.println("Final size of the list: " + sharedList.size());
        System.out.println("Simulation completed in " + (endTime - startTime) + " ms");

    }

    public long getFinalTime() {
        return finalTime;
    }
}
