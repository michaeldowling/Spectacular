package spectacular.spec.execution;


import groovy.lang.Closure;
import spectacular.data.model.Executable;
import spectacular.data.model.ExecutionResult;
import spectacular.data.model.StepActionChain;

import java.util.List;
import java.util.Map;

public interface Executor<T> {

    public void execute(Executable executable, Map<String, StepActionChain> stepActionChains, Map<String, Closure> fixtureInventory, ExecutionResult result) throws SpectacularException;

}
