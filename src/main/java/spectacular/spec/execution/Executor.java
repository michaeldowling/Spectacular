package spectacular.spec.execution;


import groovy.lang.Closure;
import spectacular.data.model.Executable;
import spectacular.data.model.ExecutionResult;

import java.util.Map;

public interface Executor<T> {

    public void execute(Executable executable, Map<String, Closure> fixtureInventory, ExecutionResult result) throws SpectacularException;

}
