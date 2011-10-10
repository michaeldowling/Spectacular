package spectacular.data.model;


public interface ExecutionResult {

    public ExecutionResultStatus getStatus();
    public void setStatus(ExecutionResultStatus status);
    public String getStatusCommentary();
    public void setStatusCommentary(String commentary);

}
