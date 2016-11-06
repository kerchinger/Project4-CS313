package edu.luc.etl.cs313.android.countdowntimer.test.model.state;

import org.junit.After;
import org.junit.Before;

import edu.luc.etl.cs313.android.countdowntimer.model.state.DefaultTimerStateMachine;

/**
 * Concrete testcase subclass for the default stopwatch state machine
 * implementation.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public class DefaultStopwatchStateMachineTest extends AbstractStopwatchStateMachineTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setModel(new DefaultTimerStateMachine(getDependency(), getDependency())); // have this problem somewhere else too
    }

    @After
    public void tearDown() {
        setModel(null);
        super.tearDown();
    }
}
