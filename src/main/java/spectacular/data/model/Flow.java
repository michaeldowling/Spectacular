package spectacular.data.model;


import java.util.List;

public class Flow {

    public static final String PRIMARY_FLOW_TAG = "PRIMARY FLOW:";
    public static final String ALTERNATE_FLOW_TAG = "ALTERNATE FLOW:";

    private String id;
    private String flowTitle;
    private FlowType flowType;
    private List<Step> steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowTitle() {
        return flowTitle;
    }

    public void setFlowTitle(String flowTitle) {
        this.flowTitle = flowTitle;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
