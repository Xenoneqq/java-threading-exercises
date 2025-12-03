package org.editors;

import org.modules.List;

public class Writer extends Thread {

    private List list;
    private int maxValue = 5;
    private int operations = 250;

    public Writer(List list) {
        this.list = list;
    }

    public Writer(List list, int maxValue) {
        this.list = list;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        while(operations > 0) {
            if(list.size() == 0){
                addValue();
            }
            else{
                if(Math.random() < 0.7){
                    addValue();
                }
                else{
                    removeValue();
                }
            }
            operations--;
        }
    }

    private void addValue(){
        int valueToAdd = (int)(Math.random() * maxValue);
        list.add(valueToAdd);
        //System.out.println("Writer " + this.getName() + " added value: " + valueToAdd);
    }

    public void removeValue(){
        int valueToRemove = (int)(Math.random() * maxValue);
        list.remove(valueToRemove);
        //System.out.println("Writer " + this.getName() + " removed value: " + valueToRemove);
    }


}
