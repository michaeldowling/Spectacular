package spectacular.data.model;

import spectacular.framework.SpecFileUtils;


public class StepActionSpecFile implements SpecFile {

    private String path = null;

    public StepActionSpecFile(String location) {
        this.path = location;
    }

    public String getPath() {
        return this.path;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getContents() {

        String spec = SpecFileUtils.loadFileContents(getPath());
        return(spec);

    }
}
