package org.example.modules;

public class Producer extends Thread {
    private static int globalID = 0;
    private int id = 0;
    private final Buffer buf;

    public Producer(Buffer buf) {
        this.buf = buf;
        id = globalID;
        globalID++;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                buf.put(i);
                System.out.println("Procuder " + id +  ": " + i);
                Thread.sleep(50);
            }
            System.out.println("Procuder " + id +  ": i am done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
