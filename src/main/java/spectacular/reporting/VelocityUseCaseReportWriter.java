package spectacular.reporting;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import spectacular.data.model.UseCaseResult;
import spectacular.spec.execution.SpectacularException;

import java.io.StringWriter;

public class VelocityUseCaseReportWriter {

    public static final String USE_CASE_TEMPLATE = "reporting/use_case_template.vm";

    public static final String VELOCITY_RESOURCE_LOADER_KEY = "resource.loader";
    public static final String VELOCITY_RESOURCE_LOADER_CLASSPATH_VALUE = "class";

    public static final String VELOCITY_RESOURCE_LOADER_CLASS_KEY = VELOCITY_RESOURCE_LOADER_CLASSPATH_VALUE + ".resource.loader.class";
    public static final String VELOCITY_RESOURCE_LOADER_CLASS_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";




    private static Log LOGGER = LogFactory.getLog(VelocityUseCaseReportWriter.class);
    private String templatePath;

    public VelocityUseCaseReportWriter(String template) {
        this.templatePath = template;
    }

    public String generate(UseCaseResult result) throws SpectacularException {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Writing report:  " + result.getExecutableItem().getUseCaseTitle());
        Template useCaseTemplate = null;

        try {

            Velocity.setProperty(VELOCITY_RESOURCE_LOADER_KEY, VELOCITY_RESOURCE_LOADER_CLASSPATH_VALUE);
            Velocity.setProperty(VELOCITY_RESOURCE_LOADER_CLASS_KEY, VELOCITY_RESOURCE_LOADER_CLASS_VALUE);
            Velocity.init();
            useCaseTemplate = Velocity.getTemplate(this.templatePath);
        } catch(Exception e) {
            LOGGER.fatal("Unable to write report using Velocity:  " + e);
            throw(new SpectacularException(e));
        }

        VelocityContext context = new VelocityContext();
        context.put("useCase", result.getExecutableItem());
        context.put("result", result);

        StringWriter writer = new StringWriter();

        try {
            useCaseTemplate.merge(context, writer);
        } catch(Exception e) {
            LOGGER.fatal("Unable to merge velocity template:  " + e);
            throw(new SpectacularException(e));
        }

        return(writer.toString());

    }









}
