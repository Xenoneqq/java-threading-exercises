package org.example;

public class Runner implements Runnable {

    public long time = -1;

    public Counter counter = null;
    public int additions = 0;
    public operation currentOperation = operation.Increment;
    public mode currentMode = mode.Synchronized;

    public enum operation{
        Increment,
        Decrement
    }

    public enum mode{
        Synchronized,
        Unsynchronized
    }

    public Runner(Counter counter, int additions, operation operation, mode mode){
        this.counter = counter;
        this.additions = additions;
        currentMode = mode;
        currentOperation = operation;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        while(additions > 0){
            if(currentOperation == operation.Increment){
                if(currentMode == mode.Synchronized){
                    counter.IncrementSynchronized();
                }
                else if(currentMode == mode.Unsynchronized) {
                    counter.IncrementUnsynchronized();
                }
            }
            else if(currentOperation == operation.Decrement){
                if(currentMode == mode.Synchronized){
                    counter.DecrementSynchronized();
                }
                else if(currentMode == mode.Unsynchronized) {
                    counter.DecrementUnsynchronized();
                }
            }
            additions--;
        }
        long end = System.nanoTime();
        time = end - start;
    }
}
