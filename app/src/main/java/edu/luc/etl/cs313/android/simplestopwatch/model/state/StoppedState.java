package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

abstract class StoppedState extends TimerState {

    private final TimerState STOPPED = new TimerState(this) {
        @Override public void onEntry() {timeModel.reset(); updateUIRuntime();}
        @Override public void onButtonPress() {
            clockModel.restartTimeout(3);
            timeModel.inc();
            updateUIRuntime();
        }
        @Override public void onTimeout() {setState(RUNIING); }
        @Override public int getID() {return R.string.STOPPED; }

    };

    public StoppedState(TimerStateMachine sm) {
        super(sm);
    }


   /* public StoppedState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.toRunningState();
    }

    @Override
    public void onLapReset() {
        sm.actionReset();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
    */
}
