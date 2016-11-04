package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class DefaultTimerStateMachine implements TimerStateMachine {



    public DefaultTimerStateMachine(TimeModel timeModel, ClockModel clockModel){
            this.clockModel = clockModel;
            this.timeModel = timeModel;
            this.STOPPED = new TimerState(new StoppedState(timeModel, clockModel));
            this.RUNNING = new TimerState(new RunningState(timeModel, clockModel, null));
            this.RINGING = new TimerState(new RingingState(timeModel, clockModel));
    }

    private final TimeModel timeModel;
    private final ClockModel clockModel;

    private TimerState nextState;
    private TimerState state;

    protected void setState(TimerState state) {
     state.onExit();
        state = nextState;
        uiUpdateListener.updateState(state.getID());
        state.onEntry();
    }

    // TODO CHANge
    public TimerUIUpdateListener uiUpdateListener;

        @Override
        public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {
            this.uiUpdateListener = uiUpdateListener;
        }
   //end of change


    @Override
    public void onStart() { //TODO SHOULD THIS BE HERE?? NO!!!! i dont think is should

    }

    // UI thread or the timer thread
    @Override public  synchronized void onButtonPress() {state.onButtonPress(); }
    @Override public  synchronized void onTick() {state.onTick();}
    @Override public  synchronized void onTimeout() {state.onTimeout(); }


    @Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.get()); }

    //@Override public void updateUILaptime() { uiUpdateListener.updateTime(timeModel.getLaptime()); }
    //end of Change

   //known states
    public TimerState STOPPED;
    public TimerState RUNNING;
    public TimerState RINGING;

    //transitions
    @Override public void toRunningState() {setState(RUNNING);}
    @Override public void toStoppedState() {setState(STOPPED);}
    @Override public void toRingingState() {setState(RINGING);}

    //actions
    @Override public void actionInit() {toStoppedState();; actionReset();}
    @Override public void actionReset(){timeModel.reset();actionUpdateView();}
    @Override public void actionStart() {clockModel.startTick(0);}
    @Override public void actionStop() {clockModel.stopTick();}
    @Override public void actionInc(){ timeModel.inc(); actionUpdateView(); } // THERE MAY BE AN ActionDec() idk actually i think there is also an INC
    @Override public void actionUpdateView() { }

}
