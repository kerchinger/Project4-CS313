package edu.luc.etl.cs313.android.countdowntimer.model;

import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.BoundedCounter;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.state.DefaultTimerStateMachine;
import edu.luc.etl.cs313.android.countdowntimer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.countdowntimer.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.Counter;

/**
 * Created by kyleerchinger on 11/1/16.
 */

public class ConcreteTimerModelFacade implements TimerModelFacade {
    private TimerStateMachine stateMachine;

    private ClockModel clockModel;
    private TimeModel timeModel;
    private BoundedCounter counter;

    public ConcreteTimerModelFacade() {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        counter = new Counter();
        stateMachine = new DefaultTimerStateMachine(timeModel, clockModel, counter);
        clockModel.setClockListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.onStart();
    }

    @Override
    public void onButtonPress() {
        stateMachine.onButtonPress();
    }

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

}
