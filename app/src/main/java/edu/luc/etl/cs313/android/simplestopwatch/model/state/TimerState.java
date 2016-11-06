package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

abstract class TimerState implements TimerUIListener, ClockListener{
    public TimerState(final TimerState sm) {this.sm = sm;}

    protected final TimerState sm;


    @Override
    public final void onStart() { onEntry();}
    public void onEntry() {}
    public void onExit() {}
    public void onButtonPress() {}
    public void onTick() {}
    public void onTimeout() {}
    public abstract int getID();


    public void updateView() {} // I beleive this is where this goes
}

