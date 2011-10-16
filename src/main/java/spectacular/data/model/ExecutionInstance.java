package spectacular.data.model;


import groovy.lang.Closure;

import java.util.List;

public class ExecutionInstance {

    private Closure closure;
    private List<String> regexGroupMatches;

    public ExecutionInstance(Closure closure) {
        this.closure = closure;
    }

    public ExecutionInstance(Closure closure, List<String> regexGroupMatches) {
        this.closure = closure;
        this.regexGroupMatches = regexGroupMatches;
    }

    public Closure getClosure() {
        return closure;
    }

    public void setClosure(Closure closure) {
        this.closure = closure;
    }

    public List<String> getRegexGroupMatches() {
        return regexGroupMatches;
    }

    public void setRegexGroupMatches(List<String> regexGroupMatches) {
        this.regexGroupMatches = regexGroupMatches;
    }
}
