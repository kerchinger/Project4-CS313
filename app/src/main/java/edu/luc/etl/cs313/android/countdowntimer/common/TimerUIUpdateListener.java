package edu.luc.etl.cs313.android.countdowntimer.common;

/**
 * A listener for UI update notifications.
 * This interface is typically implemented by the adapter, with the
 * notifications coming from the model.
 *
 * @author laufer
 */
public interface TimerUIUpdateListener {
    void updateTime(int timeValue);
    void updateState(int stateId);
    void updateButton(int buttonId);
    void ringAlarm(boolean b);
    void ringNotification();
}
