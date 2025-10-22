package org.example;

import org.example.modules.Buffer;
import org.example.modules.Consumer;
import org.example.modules.Producer;

public class Main {
    static void main() {
        Buffer buf;

        int operation = 0;

        if(operation == 0) {
            buf = new Buffer(10);
            Producer p = new Producer(buf);
            Consumer c = new Consumer(buf);

            p.start();
            c.start();
        }
        else {

            buf = new Buffer(5);

            for (int i = 0; i < 3; i++) {
                new Producer(buf).start();
            }
            for (int i = 0; i < 2; i++) {
                new Consumer(buf).start();
            }
        }
    }
}
