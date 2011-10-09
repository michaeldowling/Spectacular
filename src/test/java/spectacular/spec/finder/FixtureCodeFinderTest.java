package spectacular.spec.finder;


import org.junit.Test;
import spectacular.data.model.SpecFile;

import java.util.List;

import static org.junit.Assert.*;

public class FixtureCodeFinderTest {

    @Test
    public void testCanFindMultipleFixturesInMultipleDirectories() throws Exception {

        FixtureCodeFinder finder = new FixtureCodeFinder();
        List<SpecFile> specs = finder.findSpecFiles("src/test/resources/fixtures/euc/complex", "*.groovy");

        assertNotNull(specs);
        assertEquals(6, specs.size());

    }

    @Test
    public void testCanFindSingleFixtureByName() throws Exception {

        FixtureCodeFinder finder = new FixtureCodeFinder();
        List<SpecFile> specs = finder.findSpecFiles("src/test/resources/fixtures/euc/complex", "complex_four.groovy");

        assertNotNull(specs);
        assertEquals(1, specs.size());


    }


}
