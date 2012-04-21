package spectacular;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpectacularTest {


    @Test
    public void testCanReadXMLConfiguration() throws Exception {


        SpectacularConfiguration config = Spectacular.parseConfiguration("src/test/resources/config/SpectacularTestSimple.xml");

        assertNotNull(config);
        assertEquals("/base/path/usecase/001", config.getUseCasesBaseLocation());
        assertEquals("*.usecaseinclude", config.getUseCasesBaseLocationIncludeFilter());

        assertEquals("/base/path/stepactions/001", config.getStepActionBaseLocation());
        assertEquals("*.stepactioninclude", config.getStepActionBaseLocationIncludeFilter());

        assertEquals("/base/path/fixtures/001", config.getFixtureCodeBaseLocation());
        assertEquals("*.groovyfixtures", config.getFixtureCodeBaseLocationIncludeFilter());


        assertEquals("HTMLUNIT-aware", config.getSeleniumAwareDriver());


    }


}
