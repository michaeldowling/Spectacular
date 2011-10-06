package spectacular.spec.execution;


import spectacular.data.model.Executable;
import spectacular.data.model.UseCase;

import java.util.List;

public class ExecutionTreeBuilder {

    public static ExecutionTree buildUseCaseExecutionTree(List<UseCase> useCases) {

        ExecutionTree tree = new ExecutionTree();

        for(UseCase useCase : useCases) {

            ExecutableSpec spec = new ExecutableSpec(tree.getNextIdentifier(), useCase);


        }

        return(tree);
    }



}
