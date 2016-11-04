package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    private int runningTime = 0;

    @Override
    public void dec() {
        runningTime = (runningTime - SEC_PER_TICK) % SEC_PER_HOUR;
    }

    @Override
    public int get() {
        return runningTime;
    }

    @Override
    public void inc() {runningTime = (runningTime + SEC_PER_TICK) % SEC_PER_HOUR;}

    @Override
    public void reset() {
        runningTime = 0;
    @Override
    public int get() {return 0;}

    @Override
    public void inc(){}
}