package spectacular.spine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.SpectacularConfiguration;
import spectacular.data.model.SpecFile;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseSpecFile;
import spectacular.spec.finder.ExecutableUseCaseFinder;
import spectacular.spec.finder.SpecFinder;

import java.util.List;

/**
 * The "spine" of spectacular - in other words, this is how the lifecycle
 * of spectacular is executed
 */
public class SpectacularSpine {

    private static Log LOGGER = LogFactory.getLog(SpectacularSpine.class);
    private SpectacularConfiguration configuration;


    public SpectacularSpine(SpectacularConfiguration configuration) {
        this.configuration = configuration;
    }

    public void executeFullLifecycle() throws Exception {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Finding use case specifications.");
        findUseCaseSpecifications();



    }

    public List<SpecFile> findUseCaseSpecifications() {

        SpecFinder finder = new ExecutableUseCaseFinder();
        List<SpecFile> specs = finder.findSpecFiles(this.configuration.getUseCasesBaseLocation(), this.configuration.getUseCasesBaseLocationIncludeFilter());

        return(specs);
    }


}
