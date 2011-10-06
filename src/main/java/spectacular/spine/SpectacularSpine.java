package spectacular.spine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.SpectacularConfiguration;
import spectacular.data.model.SpecFile;
import spectacular.data.model.UseCase;
import spectacular.spec.finder.ExecutableUseCaseFinder;
import spectacular.spec.finder.SpecFinder;
import spectacular.spec.parse.euc.ExecutableUseCaseParser;

import java.util.LinkedList;
import java.util.List;

/**
 * The "spine" of spectacular - in other words, this is how the lifecycle
 * of spectacular is executed
 */
public class SpectacularSpine {

    private static Log LOGGER = LogFactory.getLog(SpectacularSpine.class);
    private SpectacularConfiguration configuration;

    private ExecutableUseCaseParser executableUseCaseParser;


    private List<SpecFile> specFileList = null;
    private List<UseCase> useCaseList = null;


    public SpectacularSpine(SpectacularConfiguration configuration) {
        this.configuration = configuration;
        this.executableUseCaseParser = new ExecutableUseCaseParser();
        this.useCaseList = new LinkedList<UseCase>();
    }

    public void executeFullLifecycle() throws Exception {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Finding use case specifications.");
        this.specFileList = findUseCaseSpecifications();

        if(LOGGER.isInfoEnabled()) LOGGER.info("Parsing specifications.");
        for(SpecFile specFile : this.specFileList) {
            UseCase uc = parseUseCaseSpecification(specFile);
            if(uc != null) {
                if(LOGGER.isInfoEnabled()) LOGGER.info("Use Case:  " + uc.getUseCaseTitle());
                this.useCaseList.add(uc);
            }
        }

        // findStepActions();


        // foreach use case
            // execute use case steps against actions



    }

    public List<SpecFile> findUseCaseSpecifications() {

        SpecFinder finder = new ExecutableUseCaseFinder();
        List<SpecFile> specs = finder.findSpecFiles(this.configuration.getUseCasesBaseLocation(), this.configuration.getUseCasesBaseLocationIncludeFilter());

        return(specs);
    }

    public UseCase parseUseCaseSpecification(SpecFile specFile) {

        UseCase useCase = null;
        try {
            useCase = this.executableUseCaseParser.parse(specFile.getContents());
        } catch(Exception e) {
            LOGGER.error("Unable to parse use case:  " + specFile.getPath(), e);
        }

        return(useCase);

    }


}
