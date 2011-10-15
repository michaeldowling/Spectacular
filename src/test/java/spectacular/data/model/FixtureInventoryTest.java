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


    @Test
    public void testCanMatchTextWithRegexGroups() throws Exception {

        Closure testerClosure = mock(Closure.class);
        when(testerClosure.toString()).thenReturn("tester");

        Closure devClosure = mock(Closure.class);
        when(devClosure.toString()).thenReturn("developer");

        FixtureInventory inventory = new FixtureInventory();
        inventory.addFixture("hello my (.*?) is tester", testerClosure);
        inventory.addFixture("^(.*?) my (.*?) is developer", devClosure);

        Closure tester = inventory.findClosureForText("hello my name is tester");
        assertNotNull(tester);
        assertEquals("tester", tester.toString());

        Closure developer = inventory.findClosureForText("hello my name is developer");
        assertNotNull(developer);
        assertEquals("developer", developer.toString());




    }


}
