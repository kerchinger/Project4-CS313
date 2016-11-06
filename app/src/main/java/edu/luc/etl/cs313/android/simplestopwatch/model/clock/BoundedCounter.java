package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

/**
 * Created by Grazyna on 11/5/2016.
 */

public interface BoundedCounter {
    void increment();
    void decrement();
    int get();
    boolean isFull();
    boolean isEmpty();
}
