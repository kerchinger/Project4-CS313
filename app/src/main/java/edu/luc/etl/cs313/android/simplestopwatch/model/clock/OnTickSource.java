package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

import edu.luc.etl.cs313.android.simplestopwatch.model.state.TimerStateMachine;

/**
 * A source of onTick events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface OnTickSource {
    void setClockListener(TimerStateMachine listener);
}
