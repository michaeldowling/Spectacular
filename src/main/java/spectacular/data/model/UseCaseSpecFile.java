package spectacular.data.model;

public class UseCaseSpecFile implements SpecFile {

    private String path;

    public UseCaseSpecFile(String path) {
        this.path = path;
    }



    public String getPath() {
        return this.path;
    }
}
