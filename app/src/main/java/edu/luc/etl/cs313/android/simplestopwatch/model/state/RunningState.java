package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import java.util.Timer;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

public class RunningState extends TimerState {

    RunningState(TimeModel timeModel, ClockModel clockModel, TimerSMStateView sm){
        super(timeModel, clockModel);
    }

    ClockModel clockModel;
    TimeModel timeModel;

    private final TimerState RUNNING = new TimerState(this) {

        @Override public void onEntry() {
            clockModel.startTick(1); }
        @Override public void onExit() {clockModel.stopTick(); }
        @Override public void onButtonPress() {setState(STOPPED);}
        @Override public void onTick() {
            timeModel.dec(); updateUIRuntime();
            if(timeModel.get() == 0) {setState(RINGING); }
        }
        @Override public int getID() {return R.string.RUNNING; }

    };

    @Override public void onStartStop() {}



    @Override public int getID() {return R.string.RUNNING; }
}
