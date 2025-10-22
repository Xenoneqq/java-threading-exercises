package org.example.models;

import java.util.ArrayList;

public class Philosopher extends Thread {
    public static int globalID = 0;
    public int id;
    public int mealsToEat = 10;

    public Fork leftFork, rightFork;
    public boolean leftForkObtained = false;
    public boolean rightForkObtained = false;

    public Philosopher() {
        id = globalID++;
    }

    public void think() throws InterruptedException {
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
                Thread.sleep(100);
                System.out.println("Philosopher " + id + " has finished consuming.");
                mealsToEat--;
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
