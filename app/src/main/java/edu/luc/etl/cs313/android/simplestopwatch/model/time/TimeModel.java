package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void resetRuntime();
    void incRuntime();
    int getRuntime();
    void setLaptime();
    int getLaptime();

    void dec(); // TODO Make sure these are implemented in proper class
    int get();
    void inc();
    void reset();
}
