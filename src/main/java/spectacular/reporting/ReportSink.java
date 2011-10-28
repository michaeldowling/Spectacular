package spectacular.reporting;

public interface ReportSink {

    public void write(Object dataSource, String data);

}
