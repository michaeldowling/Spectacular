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

    @Test
    public void testCanFindStepActionSpecificationsLifecycleStep() throws Exception {

        SpectacularConfiguration config = new SpectacularConfiguration();
        config.setStepActionBaseLocation("src/test/resources/specs/euc/findertest");
        config.setStepActionBaseLocationIncludeFilter("*.actionsteps");

        SpectacularSpine spine = new SpectacularSpine(config);
        List<SpecFile> stepActions = spine.findStepActions();

        assertNotNull(stepActions);
        assertEquals(2, stepActions.size());


    }


    @Test
    public void testCanFindTestFixturesLifecycleStep() throws Exception {

        SpectacularConfiguration config = new SpectacularConfiguration();
        config.setFixtureCodeBaseLocation("src/test/resources/fixtures/euc/complex");
        config.setFixtureCodeBaseLocationIncludeFilter("*.groovy");

        SpectacularSpine spine = new SpectacularSpine(config);
        List<SpecFile> fixtures = spine.findFixtureCode();

        assertNotNull(fixtures);
        assertEquals(6, fixtures.size());

    }


}
