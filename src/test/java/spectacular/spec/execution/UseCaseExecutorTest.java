package spectacular.spec.execution;


import groovy.lang.Closure;
import org.junit.Test;
import spectacular.data.model.Executable;
import spectacular.data.model.ExecutableType;
import spectacular.data.model.ExecutionResult;
import spectacular.data.model.UseCase;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UseCaseExecutorTest {

    @Test
    public void testRejectsNonUseCases() throws Exception {

        Executable exec = mock(Executable.class);
        ExecutionResult result = mock(ExecutionResult.class);
        Map<String, Closure> inventory = mock(Map.class);
        when(exec.getExecutableType()).thenReturn(ExecutableType.OTHER);

        UseCaseExecutor executor = new UseCaseExecutor();
        try {
            executor.execute(exec, inventory, result);
            fail("Should have thrown exception for this type.");
        } catch(SpectacularException se) {
            System.out.println("Caught as expected:  " + se);
        }


    }


}
