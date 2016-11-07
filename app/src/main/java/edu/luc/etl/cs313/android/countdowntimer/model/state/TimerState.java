package edu.luc.etl.cs313.android.countdowntimer.model.state;

import java.util.Timer;

import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockListener;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

abstract class TimerState implements TimerUIListener, ClockListener{

     public TimerState(final TimerStateMachine sm) {
         this.sm = sm;}


    protected final TimerStateMachine sm;


    @Override
    public void onStart() { onEntry();}
    public void onEntry() {}
    public void onExit() {}
    public void onButtonPress() {}
    public void onTick() {}
    public void onTimeout() {}
    public abstract int getID();


    public void updateView() {  // these is the only spot this makes sense
    }


}

