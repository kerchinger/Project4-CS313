package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIListener;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateSource;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public interface TimerStateMachine extends TimerUIListener, ClockListener, TimerUIUpdateSource, TimerSMStateView {
    void updateUIRuntime();
}
