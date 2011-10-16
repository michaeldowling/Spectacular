package spectacular.spec.execution;


import groovy.lang.Closure;
import org.junit.Test;
import spectacular.data.model.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UseCaseExecutorTest {

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
        } catch(SpectacularException se) {
            System.out.println("Caught as expected:  " + se);
        }


    }

    @Test
    public void testWillExecuteUseCase() throws Exception {




    }


}
