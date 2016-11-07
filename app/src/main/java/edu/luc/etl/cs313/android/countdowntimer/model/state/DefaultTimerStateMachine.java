package edu.luc.etl.cs313.android.countdowntimer.model.state;

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
    private final  ClockModel clockModel;

    //private TimerState state;

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
    public void onStart() { //TODO SHOULD THIS BE HERE?? NO!!!! i dont think is should

    }

    //UI thread or the timer thread
    @Override public  synchronized void onButtonPress() { state.onButtonPress(); }




    @Override public  synchronized void onTick() { state.onTick(); }
    @Override public  synchronized void onTimeout() { state.onTimeout(); }


    @Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.get()); }

   //known states
    private final TimerState STOPPED = new StoppedState(this);
    private final TimerState RUNNING = new RunningState(this);
    private final TimerState RINGING = new RingingState(this);

    //transitions
    @Override public void toRunningState() {setState(RUNNING);}
    @Override public void toStoppedState() {setState(STOPPED);}
    @Override public void toRingingState() {setState(RINGING);}

    //actions
    @Override public void actionInit() { toStoppedState(); }
    @Override public void actionReset(){timeModel.reset();actionUpdateView();}
    @Override public void actionStart() {clockModel.startTick(0);}
    @Override public void actionStop() {clockModel.stopTick();}
    @Override public void actionInc(){ timeModel.inc(); actionUpdateView(); }
    @Override public void actionDec(){ timeModel.dec(); actionUpdateView(); }
    @Override public void actionUpdateView() { state.updateView(); }

}
