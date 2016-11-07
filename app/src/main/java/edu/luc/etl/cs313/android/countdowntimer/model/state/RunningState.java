package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;

public class RunningState extends TimerState {

   public RunningState(TimerSMStateView sm) {
        super(sm);
    }

    ClockModel clockModel;
    TimeModel timeModel;
    TimerState STOPPED;
    TimerState RINGING;


    private final TimerState RUNNING = new TimerState(this) {

        @Override public void onEntry() {
            clockModel.startTick(1); }
        @Override public void onExit() {clockModel.stopTick(); }
        @Override public void onButtonPress() {
            setState(STOPPED);
        }


        @Override public void onTick() {
            timeModel.dec();
            updateUIRuntime();
            if(timeModel.get() == 0) {
                setState(RINGING);
            }
        }

        @Override public int getID() {return R.string.RUNNING; }

    };


    @Override
    public int getID() {return R.string.RUNNING; }
}
