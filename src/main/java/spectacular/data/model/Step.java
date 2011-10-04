package spectacular.data.model;


public class Step {

    private String id;
    private String stepTitle;
    private StepType stepType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public StepType getStepType() {
        return stepType;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;



    }
}
