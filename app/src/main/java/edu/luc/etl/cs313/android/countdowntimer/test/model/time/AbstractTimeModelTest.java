package edu.luc.etl.cs313.android.countdowntimer.test.model.time;


import static edu.luc.etl.cs313.android.countdowntimer.common.Constants.SEC_PER_HOUR;
import static edu.luc.etl.cs313.android.countdowntimer.common.Constants.SEC_PER_TICK;
import static edu.luc.etl.cs313.android.countdowntimer.common.Constants.SEC_PER_MIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.luc.etl.cs313.android.countdowntimer.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime is initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.get());
    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrementRuntimeOne() {
        final int rt = model.get();
        model.inc();
        assertEquals((rt + SEC_PER_TICK) % SEC_PER_MIN, model.get());
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrementRuntimeMany() {
        final int rt = model.get();
        for (int i = 0; i < SEC_PER_HOUR; i ++) {
            model.inc();
        }
        assertEquals(rt, model.get());
    }
}
