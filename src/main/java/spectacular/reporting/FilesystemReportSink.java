package spectacular.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseResult;

public class FilesystemReportSink implements ReportSink {

    private static Log LOGGER = LogFactory.getLog(FilesystemReportSink.class);

    public void write(Object sourceOfData, String data) {

        if(!(sourceOfData instanceof UseCaseResult)) {
            LOGGER.fatal("Wrong source of data passed in.");
            return;
        }

        UseCaseResult result = (UseCaseResult) sourceOfData;


    }

    public String generateFilename(UseCase useCase) {

        String title = useCase.getUseCaseTitle();
        title = title.replaceAll("\\W", "_") + ".html";
        return(title);


    }
}
