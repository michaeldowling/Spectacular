package spectacular.data.model;


public class AbstractExecutionResult<T> implements ExecutionResult {

    protected ExecutionResultStatus status;
    protected T executableItem;
    protected String commentary;

    protected AbstractExecutionResult(T execItem) {
        this.executableItem = execItem;
    }


    public ExecutionResultStatus getStatus() {
        return(this.status);
    }

    public void setStatus(ExecutionResultStatus status) {
        this.status = status;
    }

    public String getStatusCommentary() {
        return(this.commentary);
    }

    public void setStatusCommentary(String commentary) {
       this.commentary = commentary;
    }

    public T getExecutableItem() {
        return(this.executableItem);
    }
}
