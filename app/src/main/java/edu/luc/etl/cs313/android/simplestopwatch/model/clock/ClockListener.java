package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public interface ClockListener {
    void onTick();
    void onTimeout();

}