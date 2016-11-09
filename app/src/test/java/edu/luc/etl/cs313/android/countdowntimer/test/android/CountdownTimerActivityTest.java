package edu.luc.etl.cs313.android.countdowntimer.test.android;

import android.test.ActivityInstrumentationTestCase2;

import edu.luc.etl.cs313.android.countdowntimer.android.TimerAdapter;


/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see //http://developer.android.com/tools/testing/activity_testing.html
 */
public class CountdownTimerActivityTest extends ActivityInstrumentationTestCase2<TimerAdapter> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the
     * {@link //SkeletonActivity} activity.
     */
    public CountdownTimerActivityTest() {
        super(TimerAdapter.class);
        actualTest = new AbstractCountdownTimerActivityTest() {
            @Override
            protected TimerAdapter getActivity() {
                // return activity instance provided by instrumentation test
                return CountdownTimerActivityTest.this.getActivity();
            }
        };
    }

    private AbstractCountdownTimerActivityTest actualTest;

    public void testActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
    }

    public void testActivityScenarioRun() throws Throwable {
        actualTest.testActivityScenarioRun();
    }

    public void testActivityScenarioRunLapReset() throws Throwable {
        //actualTest.testActivityScenarioRunLapReset();
    }
}
