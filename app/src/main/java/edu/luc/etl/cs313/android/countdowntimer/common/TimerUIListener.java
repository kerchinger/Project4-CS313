package edu.luc.etl.cs313.android.countdowntimer.common;

/**
 * A listener for countdown timer events coming from the UI.
 *
 * @author laufer
 */
public interface TimerUIListener {
    void onStart();
    void onButtonPress();

}
