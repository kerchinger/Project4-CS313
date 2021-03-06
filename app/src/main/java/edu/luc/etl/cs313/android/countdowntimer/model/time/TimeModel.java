package edu.luc.etl.cs313.android.countdowntimer.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void dec();
    int get();
    void inc();
    void reset();
}
