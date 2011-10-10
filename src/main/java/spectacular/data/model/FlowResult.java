package spectacular.data.model;


import java.util.LinkedList;
import java.util.List;

public class FlowResult extends AbstractExecutionResult<Flow> {

    private List<StepResult> stepResults;

    public FlowResult(Flow execItem) {
        super(execItem);
        stepResults = new LinkedList<StepResult>();
    }

    public List<StepResult> getStepResults() {
        return stepResults;
    }

    public void setStepResults(List<StepResult> stepResults) {
        this.stepResults = stepResults;
    }

    public void addStepResult(StepResult stepResult) {
        this.stepResults.add(stepResult);
    }
}
