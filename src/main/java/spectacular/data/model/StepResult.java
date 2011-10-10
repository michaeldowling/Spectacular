package spectacular.data.model;


import java.util.LinkedList;
import java.util.List;

public class StepResult extends AbstractExecutionResult<Step> {

    private List<ActionResult> actionResults;


    public StepResult(Step execItem) {
        super(execItem);
        this.actionResults = new LinkedList<ActionResult>();
    }

    public List<ActionResult> getActionResults() {
        return actionResults;
    }

    public void setActionResults(List<ActionResult> actionResults) {
        this.actionResults = actionResults;
    }

    public void addActionResult(ActionResult result) {
        this.actionResults.add(result);
    }
}
