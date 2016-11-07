package edu.luc.etl.cs313.android.countdowntimer.model.state;

import edu.luc.etl.cs313.android.countdowntimer.R;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class RingingState extends TimerState {

    public RingingState(TimerSMStateView sm) {
        super(sm);
    }
    
 /*  private final TimerState RINGING = new TimerState(this){
        @Override public void onEntry() {
            //uiUpdateListener.ringAlarm(true);
        }
        @Override public void onExit() {
            //uiUpdateListener.ringAlarm(false);
        }
        @Override public void onButtonPress() {
           // setState(STOPPED);
        }
        @Override public int getID() {return R.string.RINGING; }
    };
*/
    @Override
    public int getID() { return R.string.RINGING; }



}
