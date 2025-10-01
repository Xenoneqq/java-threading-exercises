package org.example;

public class Simulation {
    public int threads = 0;
    public long totalTime = 0;
    public Counter counter;

    public int numberOfOperations = 2000000;
    public Runner.mode mode = Runner.mode.Synchronized;

    public Thread[] threadPool;

    public Simulation(){
        this(2);
    }

    public Simulation(int threads){
        this.threads = threads;
    }

    public void Start(){
        counter = new Counter();
        threadPool = new Thread[threads];
        long start = System.nanoTime();

        for(int i = 0; i < threads; i++) {
            Runner run = new Runner(counter, numberOfOperations,
                    Runner.operation.Increment, mode);
            if(i % 2 == 0){
                run.currentOperation = Runner.operation.Decrement;
            }
            threadPool[i] = new Thread(run);
            threadPool[i].start();
        }
        int saved = 0;
        try {
            for (int i = 0; i < threads; i++) {
                saved = i;
                threadPool[i].join();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Threads were interrupted at : " + saved);
        }

        long end = System.nanoTime();
        totalTime = end - start;
    }

    public void PrintDebugInformation(){
        System.out.println("======================================");
        if(mode == Runner.mode.Synchronized){
            System.out.println("Mode: Synchronized");
        }
        else {
            System.out.println("Mode: Unsynchronized");
        }
        System.out.println("Final value: " + counter.value);
        System.out.println("Total time (ns): " + totalTime);
    }
}
