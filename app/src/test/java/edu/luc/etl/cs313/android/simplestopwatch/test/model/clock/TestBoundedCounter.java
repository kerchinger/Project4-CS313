package edu.luc.etl.cs313.android.simplestopwatch.test.model.clock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.model.clock.BoundedCounter;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.Counter;


/**
 * Created by Grazyna on 11/5/2016.
 */

public class TestBoundedCounter {

    private BoundedCounter boundedCounter;

    @Before
    public void setUp(){
        boundedCounter = new Counter();
    }

    @After
    public void tearDown() {
        boundedCounter = null;
    }

    @Test
    public void testIncrement() {
        decrementIfFull();
        assertFalse(boundedCounter.isFull());
        final int v = boundedCounter.get();
        boundedCounter.increment();
        assertEquals(v + 1, boundedCounter.get());
    }

    private void decrementIfFull(){
        if(boundedCounter.isFull()){
            boundedCounter.decrement();
        }
    }
}
