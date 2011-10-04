package spectacular.data.model;


import java.util.LinkedList;
import java.util.List;

public class UseCase {


    public static final String KEY_USE_CASE_TITLE = "USE CASE";
    public static final String KEY_PRECONDITIONS = "PRECONDITIONS";
    public static final String KEY_POSTCONDITIONS = "POSTCONDITIONS";
    public static final String KEY_ACTORS = "ACTORS";

    private String id;
    private String useCaseTitle;
    private List<String> preconditions;
    private List<UseCase> preconditionUseCases;
    private List<String> postconditions;
    private List<String> actors;

    private Flow primaryFlow;
    private List<Flow> alternativeFlows;


    public UseCase() {
        this.preconditions = new LinkedList<String>();
        this.preconditionUseCases = new LinkedList<UseCase>();
        this.postconditions = new LinkedList<String>();
        this.actors = new LinkedList<String>();
        this.alternativeFlows = new LinkedList<Flow>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseCaseTitle() {
        return useCaseTitle;
    }

    public void setUseCaseTitle(String useCaseTitle) {
        this.useCaseTitle = useCaseTitle;
    }

    public List<String> getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(List<String> preconditions) {
        this.preconditions = preconditions;
    }

    public void addPrecondition(String precondition) {
        this.preconditions.add(precondition);
    }

    public List<UseCase> getPreconditionUseCases() {
        return preconditionUseCases;
    }

    public void setPreconditionUseCases(List<UseCase> preconditionUseCases) {
        this.preconditionUseCases = preconditionUseCases;
    }

    public void addPreconditionUseCase(UseCase useCase) {
        this.preconditionUseCases.add(useCase);
    }

    public List<String> getPostconditions() {
        return postconditions;
    }

    public void setPostconditions(List<String> postconditions) {
        this.postconditions = postconditions;
    }

    public void addPostcondition(String postcondition) {
        this.postconditions.add(postcondition);
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void addActor(String actor) {
        this.actors.add(actor);
    }

    public Flow getPrimaryFlow() {
        return primaryFlow;
    }

    public void setPrimaryFlow(Flow primaryFlow) {
        this.primaryFlow = primaryFlow;
    }

    public List<Flow> getAlternativeFlows() {
        return alternativeFlows;
    }

    public void setAlternativeFlows(List<Flow> alternativeFlows) {
        this.alternativeFlows = alternativeFlows;
    }

    public void addAlternativeFlow(Flow altFlow) {
        this.alternativeFlows.add(altFlow);
    }
}
