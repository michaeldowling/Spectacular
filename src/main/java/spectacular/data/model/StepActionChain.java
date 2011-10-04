package spectacular.data.model;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class StepActionChain {

    private String id;
    private Pattern stepActionPattern;
    private String stepActionText;

    private List<Action> actions;


    public StepActionChain() {
        this.actions = new LinkedList<Action>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pattern getStepActionPattern() {
        return stepActionPattern;
    }

    public void setStepActionPattern(Pattern stepActionPattern) {
        this.stepActionPattern = stepActionPattern;
    }

    public String getStepActionText() {
        return stepActionText;
    }

    public void setStepActionText(String stepActionText) {
        this.stepActionText = stepActionText;
        setStepActionPattern(Pattern.compile(this.stepActionText));
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }
}
