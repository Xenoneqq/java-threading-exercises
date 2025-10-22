package org.example;

import org.example.modules.Buffer;
import org.example.modules.Consumer;
import org.example.modules.Producer;

public class Main {
    static void main() {

        int peopleCount = 1;

        Buffer buf;
        buf = new Buffer(5);

        for (int i = 0; i < peopleCount; i++) {
            new Producer(buf).start();
        }
        for (int i = 0; i < peopleCount; i++) {
            new Consumer(buf).start();
        }
    }
}
