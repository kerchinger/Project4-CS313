package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import java.util.Timer;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.TimerState;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.RingingState;

public class RunningState extends DefaultTimerStateMachine {

    ClockModel clockModel;
    TimeModel timeModel;


    private final TimerState RUNNING = new TimerState(this) {

        @Override public void onEntry() {clockModel.startTick(1); }
        @Override public void onExit() {clockModel.stopTick(); }
        @Override public void onButtonPress() {setState(STOPPED);}
        @Override public void onTick() {
            timeModel.dec(); sm.updateUIRuntime();
            if(timeModel.get() == 0) {setState(RINGING); }
        }
        @Override public int getID() {return R.string.RUNNING; }

    };

    public RunningState(TimeModel timeModel, ClockModel clockModel) {
        super(timeModel, clockModel);
    }


    @Override
    public int getID() {
        return R.string.RUNNING;
    }
}
