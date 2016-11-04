package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateSource;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockListener;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public interface TimerStateMachine extends TimerUIListener, ClockListener, TimerUIUpdateSource, TimerSMStateView {}
