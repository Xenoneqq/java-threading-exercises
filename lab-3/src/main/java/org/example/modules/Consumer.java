package org.example.modules;

public class Consumer extends Thread {
    private final Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                int value = buf.get();
                System.out.println("Konsument: " + value);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
