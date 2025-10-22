package org.example.modules;

public class Producer extends Thread {
    private final Buffer buf;

    public Producer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                buf.put(i);
                System.out.println("Producent: " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
