package edu.luc.etl.cs313.android.simplestopwatch.common;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public interface UIThreadScheduler {
    void runOnUiThread(Runnable r);
}
