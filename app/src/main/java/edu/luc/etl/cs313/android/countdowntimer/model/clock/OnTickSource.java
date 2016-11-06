package edu.luc.etl.cs313.android.countdowntimer.model.clock;

/**
 * A source of onTick events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface OnTickSource {
    void setClockListener(ClockListener listener);

}
