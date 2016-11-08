package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class DefaultTimerStateMachine implements TimerStateMachine {
    public DefaultTimerStateMachine(TimeModel timeModel, ClockModel clockModel){
        this.clockModel = clockModel;
        this.timeModel = timeModel;
    }

    private final TimeModel timeModel;
    private final ClockModel clockModel;

    private TimerState state = new TimerState(this) {
        @Override
        public int getID() {
            throw new IllegalStateException();
        }

        @Override
        public int getButtonID() {
            throw new IllegalStateException();
        }
    };

    protected void setState(final TimerState nextState) {
        //takes previous state out and sets new state
        state.onExit();
        state = nextState;
        uiUpdateListener.updateState(state.getID());
        uiUpdateListener.updateButton(state.getButtonID());
        state.onEntry();
    }

    public TimerUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    @Override
    public void onStart() {
        toStoppedState();
    }

    //UI thread or the timer thread
    @Override public  synchronized void onButtonPress() { state.onButtonPress(); }

    @Override public  synchronized void onTick() { state.onTick(); }
    @Override public  synchronized void onTimeout() { state.onTimeout(); }

    @Override public void updateUISeconds() { uiUpdateListener.updateTime(timeModel.get()); } // I am pretty sure these two are the same thing
    @Override
    public void updateUIRuntime() {uiUpdateListener.updateTime(timeModel.get());}


   //known states

    private final TimerState STOPPED = new TimerState(this) {
        @Override public void onEntry() {
            timeModel.reset();
            updateUIRuntime();
        }

        //this will set the boundary from 0 to 99
        @Override public void onButtonPress() {
            timeModel.inc();
            updateUIRuntime();
            if(timeModel.get() < 99){
                clockModel.restartTimeout(3 /* seconds */);

            } else {
                setState(RUNNING);
            }
        }
        @Override public void onTimeout()     { setState(RUNNING); }
        @Override public int  getID()         { return R.string.STOPPED; }
        @Override public int getButtonID() {return R.string.BUTTONIDSTOPPED;}
    };

    private final TimerState RUNNING = new TimerState(this) {
        @Override public void onEntry() {
            uiUpdateListener.ringNotification(); // rings notifcation to indicate 3 second pause for a new alarm
            clockModel.startTick(1);}
        @Override public void onExit() {clockModel.stopTick(); }
        @Override public void onButtonPress() {
             setState(STOPPED); }
        @Override public void onTick() {
             timeModel.dec();
             updateUIRuntime();
             if(timeModel.get() == 0) {
                 setState(RINGING);
            }
        }
        @Override public int getID() {return R.string.RUNNING; }
        @Override public int getButtonID() {return R.string.BUTTONIDRUNNING;}
     };


    private final TimerState RINGING = new TimerState(this){
        @Override public void onEntry() {
            uiUpdateListener.ringAlarm(true);
        }
        @Override public void onExit() {
            uiUpdateListener.ringAlarm(false); // and here
        }
        @Override public void onButtonPress() {
           setState(STOPPED);
        }
        @Override public int getID() { return R.string.RINGING; }
        @Override public int getButtonID() { return R.string.BUTTONIDRINGING; }
    };

    //transitions
    @Override public void toRunningState() {setState(RUNNING);}
    @Override public void toStoppedState() {setState(STOPPED);}
    @Override public void toRingingState() {setState(RINGING);}
}
