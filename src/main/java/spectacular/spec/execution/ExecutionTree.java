package spectacular.spec.execution;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExecutionTree {

    /* key = use case name, value = use case */
    private Map<String, ExecutableSpec> idToExecutableIndex = new HashMap<String, ExecutableSpec>();

    /* Holds the tree */
    private List<ExecutableSpec> tree = new LinkedList<ExecutableSpec>();

    /* unresolved use cases */
    private Map<String, ExecutableSpec> unresolvedUseCases = new HashMap<String, ExecutableSpec>();

    public ExecutionTree() {

    }


    public void addExecutableSpec(ExecutableSpec spec) {




    }


}
