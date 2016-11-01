package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateSource;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public interface TimerModelFacade extends  TimerUIListener, TimerUIUpdateSource{
    void onStart();
}
/*public interface StopwatchModelFacade extends TimerUIListener, TimerUIUpdateSource {
    void onStart();
}*/
