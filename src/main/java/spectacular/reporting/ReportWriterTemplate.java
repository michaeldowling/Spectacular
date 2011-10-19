package spectacular.reporting;


public abstract class ReportWriterTemplate {

    private ReportSink reportSink;

    public ReportWriterTemplate(ReportSink sink) {
        this.reportSink = sink;
    }

    public ReportSink getReportSink() {
        return(this.reportSink);
    }


}
