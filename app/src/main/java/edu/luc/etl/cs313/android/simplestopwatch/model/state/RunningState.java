package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import java.util.Timer;

import edu.luc.etl.cs313.android.simplestopwatch.R;

abstract class RunningState extends TimerState {

    private final TimerState RUNNING = new TimerState(this) {
        @Override public void onEntry() {clockModel.startTick(1); }
        @Override public void onExit() {clockModel.stopTick(); }
        @Override public void onButtonPress() {setState(STOPPED);}
        @Override public void onTick() {
            timeModel.dec(); updateUIRuntime();
            if(timeModel.get() == 0) {setState(RINGING); }
        }
        @Override public int getID() {return R.string.RUNNING; }

    };

    public RunningState(TimerStateMachine sm) {
        super(sm);
    }

    /*public RunningState(final TimerSMStateView sm) {
            this.sm = sm;
        }

        private final TimerSMStateView sm;

        @Override
        public void onStartStop() {
            sm.actionStop();
            sm.toStoppedState();
        }

        @Override
        public void onLapReset() {
            sm.actionLap();
            sm.toLapRunningState();
        }

        @Override
        public void onTick() {
            sm.actionInc();
            sm.toRunningState();
        }

        @Override
        public void updateView() {
            sm.updateUIRuntime();
        }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
    */
}
