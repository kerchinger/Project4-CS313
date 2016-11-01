package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class RingingState {

    private final TimerState RINGING = new TimerState(this){
        @Override public void onEntry() {uiUpdateListener.ringAlarm(true); }
        @Override public void onExit() {uiUpdateListener.ringAlarm(false); }
        @Override public void onButtonPress() {setState(STOPPED); }
        @Override public int getID() {return R.string.RINGING; }
    };
}
