package org.example;

import java.util.ArrayList;
import java.util.List;

public class MaxThreadsTest {

    private final List<Thread> threads = new ArrayList<>();

    public void Start() {
        int count = 0;

        try {
            while (true) {
                Thread t = new Thread(() -> {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException ignored) {}
                });

                t.start();
                threads.add(t);
                count++;

                if (count % 100 == 0) {
                    System.out.println("Threads created: " + count);
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("==================================");
            System.out.println("Done – Max Thread Count: " + count);
            e.printStackTrace();
        }
    }
}
