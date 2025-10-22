package org.example.modules;

public class Consumer extends Thread {
    private static int globalID = 0;
    private int id = 0;
    private final Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
        id = globalID;
        globalID++;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                int value = buf.get();
                System.out.println("Consumer " + id +  ": " + value);
                Thread.sleep(100);
            }
            System.out.println("Consumer " + id +  ": i am done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
