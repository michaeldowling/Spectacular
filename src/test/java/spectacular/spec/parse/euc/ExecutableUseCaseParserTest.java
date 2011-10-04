package spectacular.spec.parse.euc;


import org.junit.Test;
import spectacular.data.model.UseCase;
import spectacular.spec.parse.euc.ExecutableUseCaseParser;

import java.io.*;

import static org.junit.Assert.*;

public class ExecutableUseCaseParserTest {


    @Test
    public void testCanParseSpecSinglePrimaryFlowZeroAlternateFlow() throws Exception {

        ExecutableUseCaseParser parser = new ExecutableUseCaseParser();
        String spec = loadExampleSpec("src/test/resources/specs/euc/SinglePrimaryFlowZeroAlternateFlowsExample.usecase");

        UseCase useCase = parser.parse(spec);

        assertNotNull(useCase);
        assertEquals("Do something useful", useCase.getUseCaseTitle());
        assertEquals("User does something useful", useCase.getPrimaryFlow().getFlowTitle());
        assertEquals(0, useCase.getAlternativeFlows().size());

    }


    @Test
    public void testCanParseSpecSingleAlternateFlow() throws Exception {

        ExecutableUseCaseParser parser = new ExecutableUseCaseParser();
        String spec = loadExampleSpec("src/test/resources/specs/euc/SingleAlternateFlowExample.usecase");

        UseCase useCase = parser.parse(spec);

        assertNotNull(useCase);
        assertEquals("Do something useful", useCase.getUseCaseTitle());
        assertEquals("User does something useful", useCase.getPrimaryFlow().getFlowTitle());
        assertEquals(1, useCase.getAlternativeFlows().size());
        assertEquals("User using bad browser", useCase.getAlternativeFlows().get(0).getFlowTitle());

    }


    @Test
    public void testCanParseSpecMultipleAlternateFlows() throws Exception {

        ExecutableUseCaseParser parser = new ExecutableUseCaseParser();
        String spec = loadExampleSpec("src/test/resources/specs/euc/MultipleAlternateFlowsExample.usecase");

        UseCase useCase = parser.parse(spec);

        assertNotNull(useCase);
        assertEquals("Do something useful", useCase.getUseCaseTitle());
        assertEquals("User does something useful", useCase.getPrimaryFlow().getFlowTitle());
        assertEquals(2, useCase.getAlternativeFlows().size());
        assertEquals("User using bad browser type", useCase.getAlternativeFlows().get(0).getFlowTitle());
        assertEquals("User using bad username", useCase.getAlternativeFlows().get(1).getFlowTitle());

    }


    @Test
    public void testCanParseSpecWithNoFlows() throws Exception {

        ExecutableUseCaseParser parser = new ExecutableUseCaseParser();
        String spec = loadExampleSpec("src/test/resources/specs/euc/NoFlowExample.usecase");

        UseCase useCase = parser.parse(spec);

        assertNotNull(useCase);
        assertEquals("Do something useful", useCase.getUseCaseTitle());
        assertNull(useCase.getPrimaryFlow());
        assertEquals(0, useCase.getAlternativeFlows().size());


    }


    private String loadExampleSpec(String location) throws Exception {

        StringBuilder builder = new StringBuilder();
        LineNumberReader reader = new LineNumberReader(new FileReader(new File(location)));
        String line = reader.readLine();
        while(line != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
            line = reader.readLine();
        }

        reader.close();
        return(builder.toString());


    }




}
