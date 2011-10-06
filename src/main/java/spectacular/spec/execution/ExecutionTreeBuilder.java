package spectacular.spec.execution;


import spectacular.data.model.Executable;
import spectacular.data.model.UseCase;

import java.util.List;

public class ExecutionTreeBuilder {

    public static ExecutionTree buildUseCaseExecutionTree(List<UseCase> useCases) {

        ExecutionTree tree = new ExecutionTree();

        for(UseCase useCase : useCases) {

            buildTree(tree, useCase);

        }

        return(tree);
    }

    private static void buildTree(ExecutionTree tree, UseCase useCase) {

        // get upstreams
        List<String> preconditionList = useCase.getPreconditions();
        if(preconditionList == null) return;

        for(String precondition : preconditionList) {



        }

    }



}
