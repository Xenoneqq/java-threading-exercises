package org.modules;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class List {
    private Node head;
    private int count = 0;

    public List(){
        head = new Node(-1); // Dummy head node
        count = 0;
    }

    public Node getHead() {
        return head;
    }

    public int size() {
        return count;
    }

    public boolean contains(Node node){
        Node current = head.getNext();
        current.lock();
        while(current != null){
            if(current == node){
                current.unlock();
                return true;
            }

            if(current.getNext() != null) current.getNext().lock();
            current.unlock();

            current = current.getNext();
        }
        return false;
    }

    public void add(int value){
        Node newNode = new Node(value);
        Node current = head;
        current.lock();
        while(current.getNext() != null){
            current.getNext().lock();
            current.unlock();
            current = current.getNext();
        }
        current.setNext(newNode);
        count++;
        current.unlock();
    }

    public boolean remove(int value){
        Node current = head;
        current.lock();
        int currentID = -1;
        while(current.getNext() != null){
            current.getNext().lock();
            if(current.getNext().getValue() == value){
                if(current.getNext().getNext() != null){
                    current.getNext().getNext().lock();
                }
                current.setNext(current.getNext().getNext());
                count--;
                if(current.getNext() != null) current.getNext().unlock();
                current.unlock();
                return true;
            }
            current.unlock();
            current = current.getNext();
        }
        current.unlock();
        return false;
    }
}
