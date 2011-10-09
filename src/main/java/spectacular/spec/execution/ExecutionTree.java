package spectacular.spec.execution;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.data.model.UseCase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExecutionTree {

    private static Log LOGGER = LogFactory.getLog(ExecutionTree.class);
    private List<UseCase> executionPath;
    private int currentUseCasePointer = -2;

    public static ExecutionTree build(UseCase useCase, final Map<String, UseCase> useCaseInventory) {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Building execution tree for (" + useCase.getUseCaseTitle() + ")");
        ExecutionTree tree = new ExecutionTree();

        tree.addUseCase(useCase);

        /*
        List<String> preconditions = useCase.getPreconditions();
        for(String pc : preconditions) {

            UseCase uc = useCaseInventory.get(pc);
            if(uc == null) {
                if(LOGGER.isInfoEnabled()) LOGGER.info("There is a precondition specified for (" + pc + ") but found no use case by that title.  Skipping.");
            }

            ExecutionTree recursiveTree = ExecutionTree.build(uc, useCaseInventory);
            UseCase recursiveUseCase = recursiveTree.getNext();
            while(recursiveUseCase != null) {
                tree.addUseCase(recursiveUseCase);
                recursiveUseCase = recursiveTree.getNext();
            }

        }
        */

        return(tree);

    }

    private void addUseCase(UseCase recursiveUseCase) {
        this.executionPath.add(recursiveUseCase);
    }


    protected ExecutionTree() {
        this.executionPath = new LinkedList<UseCase>();
    }


    public int getTotalExecutions() {
        return(this.executionPath.size());
    }

    public UseCase getNext() {

        if(this.currentUseCasePointer == -2) reset();
        if(this.currentUseCasePointer == -1) return null;

        UseCase useCase = this.executionPath.get(this.currentUseCasePointer);
        this.currentUseCasePointer--;

        return(useCase);

    }

    public void reset() {
        this.currentUseCasePointer = this.executionPath.size() -1;

    }
}
