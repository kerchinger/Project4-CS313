package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

import edu.luc.etl.cs313.android.simplestopwatch.common.ClockListener;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel extends OnTickSource {
    void setOnTickListener(OnTickListener listener);


    void startTick(int periodInSec);
    void stopTick();
    //void start();
    //void stop();

    void restartTimeout(int i); // TODO

    void setClockListener(ClockListener clockListener);
}
