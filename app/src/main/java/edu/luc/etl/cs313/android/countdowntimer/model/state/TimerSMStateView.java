package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.model.clock.BoundedCounter;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface TimerSMStateView extends BoundedCounter {
    // transitions
    void toRunningState();
    void toStoppedState();
    void toRingingState();

    // actions
    //void actionInit();
    //void actionReset();
    //void actionStart();
    //void actionStop();
    //void actionInc();
    //void actionDec();
    //void actionUpdateView();

    // state-dependent UI updates
    void updateUISeconds();
}
