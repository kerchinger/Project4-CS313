package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.DefaultTimerStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class RingingState extends DefaultTimerStateMachine{



    private final TimerState RINGING = new TimerState(this){
        @Override public void onEntry() {uiUpdateListener.ringAlarm(true); }
        @Override public void onExit() {uiUpdateListener.ringAlarm(false); }
        @Override public void onButtonPress() {setState(STOPPED); }
        @Override public int getID() {return R.string.RINGING; }
    };

    public RingingState(TimeModel timeModel, ClockModel clockModel) {
        super(timeModel, clockModel);
    }

    @Override
    public int getID() {
        return R.string.RINGING;}

}
