package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;

public class StoppedState extends TimerState {

    public StoppedState(TimerSMStateView sm) {
        super(sm);
    }

    //private final TimerState STOPPED = new TimerState(this) {
    //    @Override public void onEntry() {
    //        //timeModel.reset(); updateUIRuntime();
    //    }
    //    @Override public void onButtonPress() {
    //        //clockModel.restartTimeout(3);
    //        //timeModel.inc(); updateUIRuntime();
    //    }
    //    @Override public void onTimeout() {
    //        //setState(RUNNING);
    //    }
//
    //    @Override public int getID() {return R.string.STOPPED; }
    //};

    @Override
    public int getID() {
        return R.string.STOPPED;
    }
}
