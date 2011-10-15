package spectacular.data.model;


import groovy.lang.Closure;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FixtureInventoryTest {

    @Test
    public void testCanMatchSimpleText() throws Exception {

        Closure testerClosure = mock(Closure.class);
        when(testerClosure.toString()).thenReturn("tester");

        Closure devClosure = mock(Closure.class);
        when(devClosure.toString()).thenReturn("developer");

        FixtureInventory inventory = new FixtureInventory();
        inventory.addFixture("hello my name is tester", testerClosure);
        inventory.addFixture("hello my name is developer", devClosure);

        Closure tester = inventory.findClosureForText("hello my name is tester");
        assertNotNull(tester);
        assertEquals("tester", tester.toString());


    }


}
