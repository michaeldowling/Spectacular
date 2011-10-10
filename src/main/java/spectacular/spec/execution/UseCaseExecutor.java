package spectacular.spec.execution;


import groovy.lang.Closure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.data.model.Executable;
import spectacular.data.model.ExecutableType;
import spectacular.data.model.ExecutionResult;
import spectacular.data.model.UseCase;

import java.util.Map;

public class UseCaseExecutor implements Executor<UseCase> {

    private static Log LOGGER = LogFactory.getLog(UseCaseExecutor.class);


    public void execute(Executable useCaseExecutable, Map<String, Closure> fixtures, ExecutionResult result) throws SpectacularException {

        if(!useCaseExecutable.getExecutableType().equals(ExecutableType.USE_CASE)) {
            throw(new SpectacularException("Support for " + useCaseExecutable.getExecutableType() + " is not supported at this time."));
        }


        UseCase useCase = (UseCase) useCaseExecutable;
        if(LOGGER.isInfoEnabled()) LOGGER.info("Executing use case:  " + useCase.getUseCaseTitle());



    }
}
