package spectacular.data.model;

import spectacular.framework.SpecFileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class UseCaseSpecFile implements SpecFile {

    private String path;

    public UseCaseSpecFile(String path) {
        this.path = path;
    }


    public String getContents() {

        return(SpecFileUtils.loadFileContents(getPath()));


    }

    public String getPath() {
        return this.path;
    }
}
