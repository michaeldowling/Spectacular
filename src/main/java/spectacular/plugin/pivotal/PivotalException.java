package spectacular.plugin.pivotal;


public class PivotalException extends Exception {

    public PivotalException() {
    }

    public PivotalException(String s) {
        super(s);
    }

    public PivotalException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PivotalException(Throwable throwable) {
        super(throwable);
    }
}
