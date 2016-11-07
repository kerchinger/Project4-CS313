package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;

public class StoppedState extends TimerState {
    //ClockModel clockModel;

    public StoppedState(TimerSMStateView sm) {
        super((TimerStateMachine) sm);
    }

    @Override
    public void onButtonPress() {
        //clockModel.restartTimeout(3);
        sm.increment();
        sm.updateUISeconds();
    }

    @Override
    public void onTimeout() {
        sm.toRunningState();
    }

    @Override
    public int getID() {
        return R.string.STOPPED;
    }
}
