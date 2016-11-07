package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;

public class StoppedState extends TimerState {

    public StoppedState(TimerSMStateView sm) {
        super(sm);
    }


    ClockModel clockModel;
    TimeModel timeModel;



   /private final TimerState STOPPED = new TimerState((TimerSMStateView) this) {
        @Override public void onEntry() {
            timeModel.reset(); updateUIRuntime();
        }
        @Override public void onButtonPress() {
            clockModel.restartTimeout(3);
            timeModel.inc(); updateUIRuntime();
       }
        @Override public void onTimeout() {
            setState(RUNNING);
        }

        @Override public int getID() {return R.string.STOPPED; }
    };

    @Override
    public int getID() {
        return R.string.STOPPED;
    }
}
