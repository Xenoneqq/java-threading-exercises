package org.editors;

import org.modules.List;

public class Reader extends Thread {
    private List list;
    private int maxValue = 5;
    private int operations = 250;

    public Reader(List list) {
        this.list = list;
    }

    public Reader(List list, int maxValue) {
        this.list = list;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        while(operations > 0) {
            int valueToCheck = (int)(Math.random() * maxValue);
            boolean contains = list.contains(new org.modules.Node(valueToCheck));
            System.out.println("Reader " + this.getName() + " checked for value: " + valueToCheck + " - Found: " + contains);
            operations--;
        }
    }
}
