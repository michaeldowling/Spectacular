package spectacular.spec.finder;


import org.junit.Test;
import spectacular.data.model.SpecFile;

import java.util.List;

import static org.junit.Assert.*;

public class ExecutableUseCaseFinderTest {

    @Test
    public void testFindUseCasesWithBaseLocationAndFilter() throws Exception {

        ExecutableUseCaseFinder finder = new ExecutableUseCaseFinder();
        List<SpecFile> specFiles = finder.findSpecFiles("src/test/resources/specs/euc/findertest", "*.usecase");

        assertNotNull(specFiles);
        assertEquals(4, specFiles.size());

    }

    @Test
    public void testFindNoUseCasesWhenFilterIsWrong() throws Exception {

        ExecutableUseCaseFinder finder = new ExecutableUseCaseFinder();
        List<SpecFile> specFiles = finder.findSpecFiles("src/test/resources/specs/euc/findertest", "*.noexist");

        assertNotNull(specFiles);
        assertEquals(0, specFiles.size());


    }


}
