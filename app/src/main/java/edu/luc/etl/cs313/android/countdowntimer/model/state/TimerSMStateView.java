package edu.luc.etl.cs313.android.countdowntimer.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface TimerSMStateView {
    // transitions
    void toRunningState();
    void toStoppedState();
    void toRingingState();

    // state-dependent UI updates
    void updateUISeconds();
}
