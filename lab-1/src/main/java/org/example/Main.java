package org.example;

public class Main {
    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        simulation.mode = Runner.mode.Unsynchronized;
        simulation.Start();
        simulation.PrintDebugInformation();

        Simulation simulation2 = new Simulation();
        simulation2.mode = Runner.mode.Synchronized;
        simulation2.Start();
        simulation2.PrintDebugInformation();

//        // DOES NOT STOP PROGRAM USE AT YOUR OWN RISK!
//        MaxThreadsTest maxThreadsTest = new MaxThreadsTest();
//        maxThreadsTest.Start();

    }
}