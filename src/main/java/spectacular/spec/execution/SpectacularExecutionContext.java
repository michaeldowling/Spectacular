package spectacular.spec.execution;


import java.util.HashMap;
import java.util.Map;

public class SpectacularExecutionContext {

    private Map<String, Object> contextAttributes;



    public SpectacularExecutionContext() {
        this.contextAttributes = new HashMap<String, Object>();
    }

    public Object getAttribute(String key) {
        return(this.contextAttributes.get(key));
    }

    public void setAttribute(String key, Object value) {
        this.contextAttributes.put(key, value);
    }




}
