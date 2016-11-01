package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */
public class DefaultClockModel implements ClockModel {

    // TODO make accurate by keeping track of partial seconds when canceled etc.

    private Timer timer;

    private OnTickListener listener;

    @Override
    public void setOnTickListener(final OnTickListener listener) {
        this.listener = listener;
    }

   /* @Override
    public void start() {
        timer = new Timer();

        // The clock model runs onTick every 1000 milliseconds
        timer.schedule(new TimerTask() {
            @Override public void run() {
                // fire event
                listener.onTick();
            }
        }, /*initial delay*/ //1000, /*periodic delay*/ 1000);
    //}

    //added by me and I beleive this is the right spot to add this
    @Override
    public void startTick(final int periodInSec){
        if(recurring != null) throw new IllegalStateException();

        recurring = new Timer():

        recurring.schedule(new TimerTask(){
            @)Override
                    public void run() {
                listener.onTick();
            }
        }, periodInSec * 1000, periodInSec * 1000);
    }

    @Override
    public void stop() {
        timer.cancel();
    }
}