package spectacular.spec.execution;


import groovy.lang.Closure;
import org.junit.Test;
import spectacular.SpectacularConfiguration;
import spectacular.data.model.*;
import spectacular.spine.SpectacularSpine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UseCaseExecutorTest {


    @Test
    public void testFlushingAlternateFlows() throws Exception {

        Flow primaryFlow = new Flow();
        Flow altFlow = new Flow();

        List<Step> primarySteps = new LinkedList<Step>();
        for (int i = 1; i < 10; i++) {
            Step step = new Step();
            step.setId("" + i);
            step.setStepTitle("Step " + i);
            step.setStepType(StepType.USER_ACTION);

            primarySteps.add(step);
        }

        primaryFlow.setSteps(primarySteps);

        List<Step> altSteps = new LinkedList<Step>();

        Step stepThree = new Step();
        stepThree.setId("3");
        stepThree.setStepTitle("Alt Step 3");
        stepThree.setStepType(StepType.USER_ACTION);
        altSteps.add(stepThree);

        Step stepFour = new Step();
        stepFour.setId("4");
        stepFour.setStepTitle("Alt Step 4");
        stepFour.setStepType(StepType.USER_ACTION);
        altSteps.add(stepFour);

        altFlow.setSteps(altSteps);

        UseCaseExecutor useCaseExecutor = new UseCaseExecutor();
        Flow flushed = useCaseExecutor.flushAlternateFlow(primaryFlow, altFlow);

        assertNotNull(flushed);

        assertEquals("1", flushed.getSteps().get(0).getId());
        assertEquals("Step 1", flushed.getSteps().get(0).getStepTitle());

        assertEquals("2", flushed.getSteps().get(1).getId());
        assertEquals("Step 2", flushed.getSteps().get(1).getStepTitle());

        assertEquals("3", flushed.getSteps().get(2).getId());
        assertEquals("Alt Step 3", flushed.getSteps().get(2).getStepTitle());

        assertEquals("4", flushed.getSteps().get(3).getId());
        assertEquals("Alt Step 4", flushed.getSteps().get(3).getStepTitle());


    }


    @Test
    public void testRejectsNonUseCases() throws Exception {

        Executable exec = mock(Executable.class);
        ExecutionResult result = mock(ExecutionResult.class);
        Map<String, StepActionChain> chains = mock(Map.class);
        FixtureInventory inventory = mock(FixtureInventory.class);

        when(exec.getExecutableType()).thenReturn(ExecutableType.OTHER);

        UseCaseExecutor executor = new UseCaseExecutor();
        try {
            executor.execute(exec, chains, inventory, result);
            fail("Should have thrown exception for this type.");
        } catch (SpectacularException se) {
            System.out.println("Caught as expected:  " + se);
        }


    }

    @Test
    public void testWillExecuteUseCase() throws Exception {


    }


}
