package edu.luc.etl.cs313.android.countdowntimer.model.clock;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel extends OnTickSource {
    void setClockListener(ClockListener listener);


    void startTick(int periodInSec);
    void stopTick();

    void restartTimeout(int i);

}
