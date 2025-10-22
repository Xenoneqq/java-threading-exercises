package org.example.models;

import java.util.ArrayList;

public class Philosopher extends Thread {
    public static int globalID = 0;
    public int id;
    public int mealsToEat = 10;
    public ArrayList<Float> consumptionTime = new ArrayList<Float>();

    public Fork leftFork, rightFork;
    private boolean leftForkObtained = false;
    private boolean rightForkObtained = false;

    public Philosopher() {
        id = globalID++;
    }

    @Override
    public void run() {
        try {
            while (mealsToEat > 0) {
                think();
                float startTime = System.currentTimeMillis();
                getRightFork();
                getLeftFork();
                Consume();
                float endTime = System.currentTimeMillis();
                consumptionTime.add(endTime - startTime);
                mealsToEat--;
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " interrupted.");
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking...");
        Thread.sleep((int) (Math.random() * 1000));
    }

    public void getLeftFork() {
        try {
            leftFork.acquire();
            leftForkObtained = true;
            System.out.println("Philosopher " + id + " obtained left fork.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getRightFork() {
        try {
            rightFork.acquire();
            rightForkObtained = true;
            System.out.println("Philosopher " + id + " obtained right fork.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void Consume() {
        try {
            if (leftForkObtained && rightForkObtained) {
                System.out.println("Philosopher " + id + " is consuming.");
                Thread.sleep(1000);
                System.out.println("Philosopher " + id + " has finished consuming.");
                leftForkObtained = false;
                rightForkObtained = false;
                leftFork.release();
                rightFork.release();
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
