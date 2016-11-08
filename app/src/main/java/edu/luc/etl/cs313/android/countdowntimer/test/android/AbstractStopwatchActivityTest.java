package edu.luc.etl.cs313.android.countdowntimer.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.android.TimerAdapter;

import static edu.luc.etl.cs313.android.countdowntimer.common.Constants.SEC_PER_MIN;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractStopwatchActivityTest {

    private static final long STOPPED = Long.parseLong(null); // also don;t know how correct these two are
    private static final long RUNNING = Long.parseLong(null);
    private static final long RINNGING = Long.parseLong(null);


    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(() -> assertEquals(0, getDisplayedValue()));
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds, expect time 5.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRun() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
        });
        Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            assertEquals(5, getDisplayedValue());
            assertTrue(getButton().performClick());
        });
    }



    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */

    // deleted the one that was previously here and added the one from the Programmatic system testing of the app section
    @Test
    public void testScenarioRun2() throws Throwable{
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(STOPPED, getStateValue());
            assertEquals(0, getDisplayedValue());
            for(int i= 0; i <5; i++){
                assertTrue(getButton().performClick());
            }
        }});
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable(){ @Override public void run(){
            assertEquals(5, getDisplayedValue());
        }});
        Thread.sleep(3200);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable(){@Override public void run(){
            assertEquals(RUNNING, getStateValue());
            assertEquals(5, getDisplayedValue());
        }});
        Thread.sleep(3200);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable(){@Override public void run(){
            assertEquals(RUNNING, getStateValue());
            assertEquals(2, getDisplayedValue());
            assertTrue(getButton().performClick());
        }});
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run(){
            assertEquals(STOPPED, getStateValue());
        }});

    }

    // auxiliary methods for easy access to UI widgets

    protected abstract TimerAdapter getActivity();

    protected int tvToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return SEC_PER_MIN * tvToInt(ts);
    }

    protected String getStateValue(){
        final TextView ts1 = (TextView) getActivity().findViewById(R.id.stateName);
        return ts1.toString(); // I don't know how correct this is
    }

    protected Button getButton() {
        return (Button) getActivity().findViewById(R.id.counterButton);
    }


    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}
