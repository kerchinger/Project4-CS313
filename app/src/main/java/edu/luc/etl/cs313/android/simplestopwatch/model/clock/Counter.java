package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

/**
 * Created by Grazyna on 11/5/2016.
 */

public class Counter implements BoundedCounter {

    private int clockCounter;


    @Override
    public void increment() {
        if(!isFull()){
            clockCounter++;
        }
    }

    @Override
    public void decrement() {
        if(!isEmpty()) {
            clockCounter--;
        }
    }

    @Override
    public int get(){
        return clockCounter;
    }

    @Override
    public boolean isFull(){
        if(clockCounter == 99){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty(){
        if(clockCounter == 0){
            return true;
        } else {
            return false;
        }
    }
}
