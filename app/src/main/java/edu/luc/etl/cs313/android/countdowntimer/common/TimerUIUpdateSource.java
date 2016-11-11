package edu.luc.etl.cs313.android.countdowntimer.common;

/**
 * A source of UI update events for the countdown timer.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface TimerUIUpdateSource {
    void setUIUpdateListener(TimerUIUpdateListener listener);
}
