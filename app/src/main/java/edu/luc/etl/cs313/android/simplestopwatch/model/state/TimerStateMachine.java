package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

// TODO add the states to this
public interface TimerStateMachine {
    void setUIUpdateListener(TimerUIUpdateListener uiUpdateListener);

    // UI thread or the timer thread
    void onButtonPress();

    void onTick();

    void onTimeout();

    //TODO change
    void updateUIRuntime();

    //transitions
    void toRunningState();

    void toStoppedState();

    void toRingingState();

    //actions
    void actionInit();

    void actionReset();

    void actionStart();

    void actionStop();

    //@Override public void actionInc(){ timeModel.incRuntime(); actionUpdateView(); } // dont know if we need
    void actionUpdateView();
}
//how it should sort of look public interface StopwatchStateMachine extends TimerUIListener, OnTickListener, TimerUIUpdateSource, TimerSMStateView { }