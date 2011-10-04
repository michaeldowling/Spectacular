package spectacular.spec.parse.euc;


import org.junit.Test;
import spectacular.data.model.StepActionChain;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;

import static org.junit.Assert.*;

public class StepActionParserTest {


    @Test
    public void testCanParseSingleActionStepExample() throws Exception {

        StepActionParser parser = new StepActionParser();
        String spec = loadExampleSpec("src/test/resources/specs/euc/SingleActionStepExample.actionsteps");

        List<StepActionChain> chain = parser.parse(spec);

        assertNotNull(chain);
        assertEquals("User does something to initiate use case", chain.get(0).getStepActionText());
        assertNotNull(chain.get(0).getStepActionPattern());
        assertTrue(chain.get(0).getStepActionPattern().matcher("User does something to initiate use case").matches());
        assertEquals(3, chain.get(0).getActions().size());

        assertEquals("Navigate to home page", chain.get(0).getActions().get(0).getActionText());
        assertEquals("Log in as \"admin\"", chain.get(0).getActions().get(1).getActionText());
        assertEquals("Go to user preferences page", chain.get(0).getActions().get(2).getActionText());


    }

    @Test
    public void testCanParseMultipleActionStepExample() throws Exception {

        StepActionParser parser = new StepActionParser();
        String spec = loadExampleSpec("src/test/resources/specs/euc/MultipleActionStepExample.actionsteps");

        List<StepActionChain> chain = parser.parse(spec);

        assertNotNull(chain);
        assertEquals(3, chain.size());

        assertEquals("User does something to initiate use case", chain.get(0).getStepActionText());
        assertNotNull(chain.get(0).getStepActionPattern());
        assertTrue(chain.get(0).getStepActionPattern().matcher("User does something to initiate use case").matches());
        assertEquals(3, chain.get(0).getActions().size());
        assertEquals("Navigate to home page", chain.get(0).getActions().get(0).getActionText());
        assertEquals("Log in as \"admin\"", chain.get(0).getActions().get(1).getActionText());
        assertEquals("Go to user preferences page", chain.get(0).getActions().get(2).getActionText());


        assertEquals("System does something funny", chain.get(1).getStepActionText());
        assertNotNull(chain.get(1).getStepActionPattern());
        assertTrue(chain.get(1).getStepActionPattern().matcher("System does something funny").matches());
        assertEquals(3, chain.get(1).getActions().size());
        assertEquals("insane in the membrain", chain.get(1).getActions().get(0).getActionText());
        assertEquals("INSANE IN THE BRAIN!", chain.get(1).getActions().get(1).getActionText());
        assertEquals("a space before this.", chain.get(1).getActions().get(2).getActionText());


        assertEquals("The kids are alright", chain.get(2).getStepActionText());
        assertNotNull(chain.get(2).getStepActionPattern());
        assertTrue(chain.get(2).getStepActionPattern().matcher("The kids are alright").matches());
        assertEquals(4, chain.get(2).getActions().size());
        assertEquals("one", chain.get(2).getActions().get(0).getActionText());
        assertEquals("two", chain.get(2).getActions().get(1).getActionText());
        assertEquals("three four five", chain.get(2).getActions().get(2).getActionText());
        assertEquals("six", chain.get(2).getActions().get(3).getActionText());


    }


    private String loadExampleSpec(String location) throws Exception {

        StringBuilder builder = new StringBuilder();
        LineNumberReader reader = new LineNumberReader(new FileReader(new File(location)));
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
            line = reader.readLine();
        }

        reader.close();
        return (builder.toString());


    }


}
