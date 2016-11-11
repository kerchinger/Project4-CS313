package edu.luc.etl.cs313.android.countdowntimer.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 */

public class DefaultClockModel implements ClockModel {

    private Timer timer;
    private Timer recurring;

    private ClockListener listener;

    public void setClockListener(final ClockListener listener) {
        this.listener = listener;
    }


    @Override
    public void startTick(final int periodInSec) {
       // if(recurring != null) throw new IllegalStateException();
        if (recurring != null) {recurring.cancel();}
        recurring = new Timer();

        recurring.schedule(new TimerTask(){
            @Override
            public void run(){
                listener.onTick();
            }
        }, periodInSec * 1000, periodInSec * 1000);
    }

    @Override
    public void stopTick() {
    recurring.cancel();
    }

    // one-shot timer
    @Override
    public void restartTimeout(int i) { // it checks to see if timer is null, if it is not it cancels out the timer. and then creates a new timer, that will schedule a task after a 3 second delay
        if(timer != null) {
            timer.cancel();
        }

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onTimeout();

            }
        }, i *1000);

        }
}