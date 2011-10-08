package spectacular.spec.execution;


import org.junit.Test;
import spectacular.data.model.UseCase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExecutionTreeTest {

    @Test
    public void testCanBuildTreeFromSimplePrecondition() throws Exception {

        // create use cases
        UseCase primaryUseCase = new UseCase();
        primaryUseCase.setUseCaseTitle("use case 1");

        UseCase useCaseTwo = new UseCase();
        useCaseTwo.setUseCaseTitle("use case 2 - before use case 1");

        List<String> preconditions = new LinkedList<String>();
        preconditions.add("use case 2 - before use case 1");
        primaryUseCase.setPreconditions(preconditions);

        Map<String, UseCase> inventory = new HashMap<String, UseCase>();
        inventory.put(primaryUseCase.getUseCaseTitle(), primaryUseCase);
        inventory.put(useCaseTwo.getUseCaseTitle(), useCaseTwo);

        ExecutionTree tree = ExecutionTree.build(primaryUseCase, inventory);

        assertNotNull(tree);
        assertEquals(2, tree.getTotalExecutions());
        assertEquals("use case 2 - before use case 1", tree.getNext().getUseCaseTitle());
        assertEquals("use case 1", tree.getNext().getUseCaseTitle());
        assertNull(tree.getNext());

    }

    @Test
    public void testResetWorks() throws Exception {

        // create use cases
        UseCase primaryUseCase = new UseCase();
        primaryUseCase.setUseCaseTitle("use case 1");

        UseCase useCaseTwo = new UseCase();
        useCaseTwo.setUseCaseTitle("use case 2 - before use case 1");

        List<String> preconditions = new LinkedList<String>();
        preconditions.add("use case 2 - before use case 1");
        primaryUseCase.setPreconditions(preconditions);

        Map<String, UseCase> inventory = new HashMap<String, UseCase>();
        inventory.put(primaryUseCase.getUseCaseTitle(), primaryUseCase);
        inventory.put(useCaseTwo.getUseCaseTitle(), useCaseTwo);

        ExecutionTree tree = ExecutionTree.build(primaryUseCase, inventory);

        assertNotNull(tree);
        assertEquals(2, tree.getTotalExecutions());
        assertEquals("use case 2 - before use case 1", tree.getNext().getUseCaseTitle());
        assertEquals("use case 1", tree.getNext().getUseCaseTitle());
        assertNull(tree.getNext());

        tree.reset();
        assertEquals("use case 2 - before use case 1", tree.getNext().getUseCaseTitle());
        assertEquals("use case 1", tree.getNext().getUseCaseTitle());
        assertNull(tree.getNext());


    }


}
