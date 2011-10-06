package spectacular.spec.finder;


import org.junit.Test;
import spectacular.data.model.SpecFile;

import java.util.List;

import static org.junit.Assert.*;

public class StepActionFinderTest {

    @Test
    public void testCanFindStepActionSpecsWithBaseLocationAndFilter() throws Exception {

        StepActionFinder finder = new StepActionFinder();
        List<SpecFile> specFiles = finder.findSpecFiles("src/test/resources/specs/euc/findertest", "*.actionsteps");

        assertNotNull(specFiles);
        assertEquals(2, specFiles.size());


    }


}
