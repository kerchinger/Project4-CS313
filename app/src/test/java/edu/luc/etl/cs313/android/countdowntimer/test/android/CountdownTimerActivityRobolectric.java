package edu.luc.etl.cs313.android.countdowntimer.test.android;

import edu.luc.etl.cs313.android.countdowntimer.android.TimerAdapter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Concrete Robolectric test subclass. For the Gradle unitTest task to work,
 * the Robolectric dependency needs to be isolated here instead of being present in src/main.
 *
 * @author laufer
 * @see http://pivotal.github.com/robolectric
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk=18)
public class CountdownTimerActivityRobolectric extends AbstractCountdownTimerActivityTest {

    private static String TAG = "stopwatch-android-act1vity-robolectric";

    private TimerAdapter activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(TimerAdapter.class).create().start().visible().get();
    }

    @Override
    protected TimerAdapter getActivity() {
        return activity;
    }

    @Override
    protected void runUiThreadTasks() {
        // Robolectric requires us to run the scheduled tasks explicitly!
        Robolectric.runUiThreadTasks();
    }
}
