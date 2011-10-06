package spectacular.spine;


import org.junit.Test;
import spectacular.SpectacularConfiguration;
import spectacular.data.model.SpecFile;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseSpecFile;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class SpectacularSpineTest {

    @Test
    public void testCanFindExecutableUseCaseSpecificationsLifecycleStep() throws Exception {

        SpectacularConfiguration config = new SpectacularConfiguration();
        config.setUseCasesBaseLocation("src/test/resources/specs/euc/findertest");
        config.setUseCasesBaseLocationIncludeFilter("*.usecase");

        SpectacularSpine spine = new SpectacularSpine(config);
        List<SpecFile> specs = spine.findUseCaseSpecifications();

        assertNotNull(specs);
        assertEquals(4, specs.size());

    }

    @Test
    public void testCanParseExecutableUseCaseSpecLifecycleStep() throws Exception {

        SpectacularConfiguration config = new SpectacularConfiguration();

        SpectacularSpine spine = new SpectacularSpine(config);
        UseCase useCase = spine.parseUseCaseSpecification(new UseCaseSpecFile(new File("src/test/resources/specs/euc/findertest/MultipleAlternateFlowsExample.usecase").getAbsolutePath()));

        assertNotNull(useCase);
        assertEquals("Do something useful", useCase.getUseCaseTitle());

    }





}
