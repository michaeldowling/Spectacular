package spectacular.framework

import org.junit.Test
import static org.junit.Assert.*

class ExecutableUseCaseFlowMixinTest {


    @Test
    def void testMixinExecutableUseCaseFlowAndCallAction() throws Exception {

        ExecutableUseCaseFlow flow = ExecutableUseCaseFlow.loadActionImplementations("src/test/groovy/spectacular/framework/ClassToMixinWith.groovy");

        assertNotNull(flow);
        assertEquals(1, flow.flows.keySet().size());
        assertTrue(flow.flows.get("This is my path") instanceof Closure);


    }



}
