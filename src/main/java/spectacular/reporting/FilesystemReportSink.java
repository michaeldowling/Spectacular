package spectacular.reporting;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseResult;

import java.io.File;

public class FilesystemReportSink implements ReportSink {

    private static Log LOGGER = LogFactory.getLog(FilesystemReportSink.class);


    public FilesystemReportSink() {
        this(".");
    }

    public FilesystemReportSink(String baseDirectory) {

        File baseDir = new File(baseDirectory);
        if (baseDir.exists()) {
            try {
                FileUtils.forceDelete(baseDir);
            } catch (Exception e) {
                LOGGER.fatal("Unable to force delete directory for reporting:  " + e);
                return;
            }
        }

        try {
            FileUtils.forceMkdir(baseDir);
        } catch (Exception e) {
            LOGGER.fatal("Unable to create directory for reporting:  " + e);
            return;
        }

    }

    public void write(Object sourceOfData, String data) {

        if (!(sourceOfData instanceof UseCaseResult)) {
            LOGGER.fatal("Wrong source of data passed in.");
            return;
        }

        UseCaseResult result = (UseCaseResult) sourceOfData;


    }

    public String generateFilename(UseCase useCase) {

        String title = useCase.getUseCaseTitle();
        title = title.replaceAll("\\W", "_") + ".html";
        return (title);


    }
}
