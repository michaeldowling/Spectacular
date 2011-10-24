package spectacular.plugin.pivotal;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PivotalAccessorTest {


    @Test
    public void testCanDoBasicAuthentication() throws Exception {

        PivotalAccessor pivotalAccessor = new PivotalAccessor("spectacular", "spectacular");
        assertTrue(pivotalAccessor.authenticate());

    }


    @Test
    public void testCanGetStoriesForTag() throws Exception {

        PivotalAccessor pivotalAccessor = new PivotalAccessor("spectacular", "spectacular");
        assertTrue(pivotalAccessor.authenticate());

        List<String> stories = pivotalAccessor.getStoryIdsByTagName("38811", "use case");
        assertNotNull(stories);
        assertEquals(1, stories.size());
        assertEquals("19922043", stories.get(0));

    }


}
