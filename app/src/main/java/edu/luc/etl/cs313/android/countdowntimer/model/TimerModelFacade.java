package edu.luc.etl.cs313.android.countdowntimer.model;

import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIListener;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateSource;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public interface TimerModelFacade extends  TimerUIListener, TimerUIUpdateSource{
    void onStart();
}
