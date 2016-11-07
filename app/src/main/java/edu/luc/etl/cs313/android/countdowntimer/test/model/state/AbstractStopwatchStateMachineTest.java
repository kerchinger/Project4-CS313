package edu.luc.etl.cs313.android.countdowntimer.test.model.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.BoundedCounter;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockListener;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.countdowntimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.countdowntimer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;

/**
 * Testcase superclass for the stopwatch state machine model. Unit-tests the state
 * machine in fast-forward mode by directly triggering successive tick events
 * without the presence of a pseudo-real-time clock. Uses a single unified mock
 * object for all dependencies of the state machine model.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractStopwatchStateMachineTest {

    private static final int MAX_TIME = 99;// Max time should be 99 because that is as high as we can go
    private TimerStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        dependency = null;
    }

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimerStateMachine model) {
        this.model = model;
        if (model == null)
            return;
        this.model.setUIUpdateListener(dependency);
        this.model.onStart();
    }

    protected UnifiedMockDependency getDependency() {
        return dependency;
    }

    /**
     * Verifies that we're initially in the stopped state (and told the listener
     * about it).
     */
    @Test
    public void testPreconditions() {
        assertEquals(R.string.STOPPED, dependency.getState());
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5.
     */
    @Test
    public void testScenarioRun() {
        assertTimeEquals(0);
        // directly invoke the button press event handler methods
        model.onStart();
        onTickRepeat(5);
        assertTimeEquals(5);
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */
    @Test
    public void testScenarioRun2(){
        assertEquals(R.string.STOPPED,dependency.getState());
        model.onButtonPress();
        assertTimeEquals(1);
        assertEquals(R.string.STOPPED,dependency.getState());
        onButtonRepeat(MAX_TIME*2);
        assertTimeEquals(MAX_TIME);
        model.onTimeout();
        assertEquals(R.string.RUNNING,dependency.getState());
        onTickRepeat(50);
        assertTimeEquals(MAX_TIME-50);
        onTickRepeat(49);
        assertTimeEquals(0);
        assertEquals(R.string.RINGING,dependency.getState());
        assertTrue(dependency.isRinging());
        onTickRepeat(3);
        assertEquals(R.string.RINGING,dependency.getState());
        assertTrue(dependency.isRinging());
        model.onButtonPress();
        assertFalse(dependency.isRinging());
        assertEquals(R.string.STOPPED,dependency.getState());
    }

    private void onButtonRepeat(int t) {
        for(int i =0; i< t; i++){
            model.onButtonPress(); // TODO check idk how accureate this, it may be more like the onTickRepear() which is below
        }
    }


    /**
     * Sends the given number of tick events to the model.
     *
     *  @param n the number of tick events
     */
    protected void onTickRepeat(final int n) {
        for (int i = 0; i < n; i++)
            model.onTick();
    }

    /**
     * Checks whether the model has invoked the expected time-keeping
     * methods on the mock object.
     */
    protected void assertTimeEquals(final int t) {
        assertEquals(t, dependency.getTime());
    }
}

/**
 * Manually implemented mock object that unifies the three dependencies of the
 * stopwatch state machine model. The three dependencies correspond to the three
 * interfaces this mock object implements.
 *
 * @author laufer
 */
class UnifiedMockDependency implements TimeModel, ClockModel, TimerUIUpdateListener, BoundedCounter {

    private int timeValue = -1, stateId = -1;

    private int runningTime = 0, idleTime = -1;

    private boolean started = false, ringing = false;

    //theses are the instance variables and the getters enable to tesst whether the SUT produced the expedcted changes in the mock Object
    public int getTime() {
        return timeValue;
    }
    public int getState() {
        return stateId;
    }
    public boolean isStarted() {
        return started;
    } // method is used to don't worry about it
    public boolean isRinging() {
        return ringing;
    }

    // TimeUIUpdateListener
    @Override
    public void updateTime(final int tv) {
        this.timeValue = tv;
    }
    @Override
    public void updateState(final int stateId) {
        this.stateId = stateId;
    }
    @Override
    public void ringAlarm(final boolean b) {
        ringing = b;
    }

    // ClockModel
    @Override
    public void setClockListener(final ClockListener listener) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void startTick(final int period) {
        started = true;
    }
    @Override
    public void stopTick() {
        started = false;
    }
    @Override
    public void restartTimeout(final int period) {
    }

    // TimeModel
    @Override
    public void reset() {
        runningTime = 0;
    }
    @Override
    public void inc() {
        if (runningTime != 99) {
            runningTime++;
        }
    }
    @Override
    public void dec() {
        if (runningTime != 0) {
            runningTime--;
        }
    }
    @Override
    public int get() {
        return runningTime;
    }

    @Override public void increment() {

    }

    @Override public void decrement() {

    }

    //@Override public int get() {
    //    return 0;
    //}

    @Override public boolean isFull() {
        return false;
    }

    @Override public boolean isEmpty() {
        return false;
    }

}
