package spectacular.data.model;

import java.util.LinkedList;
import java.util.List;

public class UseCaseResult extends AbstractExecutionResult<UseCase> {

    private FlowResult primaryFlow;
    private List<FlowResult> alternateFlows;

    public UseCaseResult(UseCase execItem) {
        super(execItem);
        this.alternateFlows = new LinkedList<FlowResult>();
    }

    public FlowResult getPrimaryFlow() {
        return primaryFlow;
    }

    public void setPrimaryFlow(FlowResult primaryFlow) {
        this.primaryFlow = primaryFlow;
    }

    public List<FlowResult> getAlternateFlows() {
        return alternateFlows;
    }

    public void setAlternateFlows(List<FlowResult> alternateFlows) {
        this.alternateFlows = alternateFlows;
    }

    public void addAlternateFlow(FlowResult alt) {
        this.alternateFlows.add(alt);
    }

}
