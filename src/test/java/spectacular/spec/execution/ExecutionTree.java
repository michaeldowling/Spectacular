package spectacular.spec.execution;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExecutionTree {

    private int nextIdentifier = 100;

    private Map<Integer, ExecutableSpec> idToExecutableIndex = new HashMap<Integer, ExecutableSpec>();
    private List<ExecutableSpec> tree = new LinkedList<ExecutableSpec>();



}
