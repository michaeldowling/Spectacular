package spectacular.spine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.SpectacularConfiguration;
import spectacular.data.model.SpecFile;
import spectacular.data.model.StepActionChain;
import spectacular.data.model.UseCase;
import spectacular.spec.finder.ExecutableUseCaseFinder;
import spectacular.spec.finder.SpecFinder;
import spectacular.spec.finder.StepActionFinder;
import spectacular.spec.parse.euc.ExecutableUseCaseParser;
import spectacular.spec.parse.euc.StepActionParser;

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
    private StepActionParser stepActionParser;


    private List<SpecFile> specFileList = null;
    private List<UseCase> useCaseList = null;

    private List<SpecFile> stepActionList = null;
    private List<StepActionChain> stepActionChainList = null;


    public SpectacularSpine(SpectacularConfiguration configuration) {
        this.configuration = configuration;
        this.executableUseCaseParser = new ExecutableUseCaseParser();
        this.stepActionParser = new StepActionParser();
        this.useCaseList = new LinkedList<UseCase>();
        this.specFileList = new LinkedList<SpecFile>();
        this.stepActionList = new LinkedList<SpecFile>();
        this.stepActionChainList = new LinkedList<StepActionChain>();
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

        if(LOGGER.isInfoEnabled()) LOGGER.info("Finding step actions.");
        this.stepActionList = findStepActions();

        if(LOGGER.isInfoEnabled()) LOGGER.info("Parsing step actions.");
        for(SpecFile specFile : this.stepActionList) {

            List<StepActionChain> chains = parseStepActionSpecification(specFile);
            if(chains != null) {
                for(StepActionChain chain : chains) {
                    if(LOGGER.isInfoEnabled()) LOGGER.info("Step Action Chain: " + chain.getStepActionText());
                    this.stepActionChainList.add(chain);
                }
            }

        }


        // foreach use case
        for(UseCase useCase : this.useCaseList) {

            if(LOGGER.isInfoEnabled()) LOGGER.info("Building execution path for use case \"" + useCase.getUseCaseTitle() + "\"");



        }
            // execute use case steps against actions



    }

    public List<StepActionChain> parseStepActionSpecification(SpecFile specFile) {

        List<StepActionChain> chains = null;
        try {
            chains = this.stepActionParser.parse(specFile.getContents());

        } catch(Exception e) {
            LOGGER.error("Unable to parse Step Action:  " + specFile, e);
        }

        return(chains);

    }

    public List<SpecFile> findStepActions() {

        SpecFinder finder = new StepActionFinder();
        List<SpecFile> specs = finder.findSpecFiles(this.configuration.getStepActionBaseLocation(), this.configuration.getStepActionBaseLocationIncludeFilter());

        return(specs);


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
