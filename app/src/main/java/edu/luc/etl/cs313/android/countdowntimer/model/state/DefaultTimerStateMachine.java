package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.BoundedCounter;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class DefaultTimerStateMachine implements TimerStateMachine {
    public DefaultTimerStateMachine(TimeModel timeModel, ClockModel clockModel, BoundedCounter counter){
        this.clockModel = clockModel;
        this.timeModel = timeModel;
        this.counter = counter;
    }

    private final TimeModel timeModel;
    private final ClockModel clockModel;
    private final BoundedCounter counter;

    private TimerState state = new TimerState(this) {
        @Override
        public int getID() {
            throw new IllegalStateException();
        }
    };

    protected void setState(final TimerState nextState) {
        //takes previous state out and sets new state
        state.onExit();
        state = nextState;
        uiUpdateListener.updateState(state.getID());
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

    @Override public void updateUISeconds() { uiUpdateListener.updateTime(counter.get()); } // I am pretty sure these two are the same thing
    @Override
    public void updateUIRuntime() {uiUpdateListener.updateTime(timeModel.get());}


   //known states

    private final TimerState STOPPED = new TimerState(this) {
        @Override public void onEntry()       { timeModel.reset(); updateUIRuntime(); }
        @Override public void onButtonPress() {
            clockModel.restartTimeout(3 /* seconds */);
            timeModel.inc(); updateUIRuntime();
        }
        @Override public void onTimeout()     { setState(RUNNING); }
        @Override public int  getID()         { return R.string.STOPPED; }
    };

    private final TimerState RUNNING = new TimerState(this) {

         @Override public void onEntry() {
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
     };


    private final TimerState RINGING = new TimerState( this){
        @Override public void onEntry() {
            uiUpdateListener.ringAlarm(true);
        }
        @Override public void onExit() {
            uiUpdateListener.ringAlarm(false); // and here
        }
        @Override public void onButtonPress() {
           setState(STOPPED);
        }
        @Override public int getID() {return R.string.RINGING; }
    };

    //transitions
    @Override public void toRunningState() {setState(RUNNING);}
    @Override public void toStoppedState() {setState(STOPPED);}
    @Override public void toRingingState() {setState(RINGING);}

    //actions
    @Override public void increment() {
        counter.increment();
    }

    @Override public void decrement() {
        counter.decrement();
    }

    @Override public int get() {
        return counter.get();
    }

    @Override public boolean isFull() {
        return counter.isFull();
    }

    @Override public boolean isEmpty() {
        return counter.isEmpty();
    }
    //@Override public void actionInit() { toStoppedState(); }
    //@Override public void actionReset(){timeModel.reset();actionUpdateView();}
    //@Override public void actionStart() {clockModel.startTick(0);}
    //@Override public void actionStop() {clockModel.stopTick();}
    //@Override public void actionInc(){ timeModel.inc(); actionUpdateView(); }
    //@Override public void actionDec(){ timeModel.dec(); actionUpdateView(); }
    //@Override public void actionUpdateView() { state.updateView(); }

}
