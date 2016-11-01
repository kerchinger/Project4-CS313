package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class DefaultTimerStateMachine implements TimerStateMachine {

    public DefaultTimerStateMachine(final TimeModel timeModel, final ClockModel clockModel){
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }
    private final TimeModel timeModel;
    private final ClockModel clockModel;

    private TimerState state;

    protected setState(final TimerState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId()); //TODO gonna need to change this
    }

    // TODO CHANge
        private TimerUIUpdateListener uiUpdateListener;

        @Override
        public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {
            this.uiUpdateListener = uiUpdateListener;
        }
   //end of change


    // UI thread or the timer thread
    @Override public synchronized void onButtonPress() {state.onButtonPress(); }
    @Override public  synchronized void onTick() {state.onTick();}
    @Override public  synchronized void onTimeout() {state.onTimeout(); }


    //TODO change
        @Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.getRuntime()); }
        @Override public void updateUILaptime() { uiUpdateListener.updateTime(timeModel.getLaptime()); }
    //end of Change

    //known states
    private final TimerState STOPPED = new StoppedState(this);
    private final TimerState RUNNING = new RunningState(this);
    private final TimerState RINGING = new RingingState(this);

    //transitions
    @Override public void toRunningState() {setState(RUNNING);}
    @Override public void toStoppedState() {setState(STOPPED);}
    @Override public void toRingingState() {setState(RINGING);}

    //actions
    @Override public void actionInit() {toStoppedState();; actionReset();}
    @Override public void actionReset(){timeModel.resetRuntime();actionUpdateView();}
    @Override public void actionStart() {clockModel.start();}
    @Override public void actionStop() {clockModel.stop();}
    //@Override public void actionInc(){ timeModel.incRuntime(); actionUpdateView(); } // dont know if we need
    @Override public void actionUpdateView() { state.updateView(); }



}
