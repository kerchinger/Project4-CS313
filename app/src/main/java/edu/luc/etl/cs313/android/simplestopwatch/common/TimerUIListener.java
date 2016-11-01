package edu.luc.etl.cs313.android.simplestopwatch.common;

import edu.luc.etl.cs313.android.simplestopwatch.android.TimerAdapter;

/**
 * A listener for stopwatch events coming from the UI.
 *
 * @author laufer
 */
public interface TimerUIListener {
    void onStartStop();

    //void onLapReset();
}
