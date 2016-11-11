package edu.luc.etl.cs313.android.countdowntimer.model.clock;

/**
 * A listener for countdown timer events coming from the clock.
 *
 * Created by kyleerchinger on 11/1/16.
 */

public interface ClockListener {
    void onTick();
    void onTimeout();

}
