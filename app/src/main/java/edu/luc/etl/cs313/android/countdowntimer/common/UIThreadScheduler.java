package edu.luc.etl.cs313.android.countdowntimer.common;

/**
 * A scheduler for a UI thread
 *
 * Created by kyleerchinger on 11/1/16.
 */

public interface UIThreadScheduler {
    void runOnUiThread(Runnable r);
}
