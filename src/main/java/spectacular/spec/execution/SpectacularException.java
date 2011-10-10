package spectacular.spec.execution;


public class SpectacularException extends Exception {

    public SpectacularException() {
    }

    public SpectacularException(String s) {
        super(s);
    }

    public SpectacularException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SpectacularException(Throwable throwable) {
        super(throwable);
    }
}
