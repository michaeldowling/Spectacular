package spectacular.spine;

import groovy.lang.Closure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.SpectacularConfiguration;
import spectacular.data.model.*;
import spectacular.framework.ExecutableUseCaseFlow;
import spectacular.spec.execution.ExecutionTree;
import spectacular.spec.execution.UseCaseExecutor;
import spectacular.spec.finder.ExecutableUseCaseFinder;
import spectacular.spec.finder.FixtureCodeFinder;
import spectacular.spec.finder.SpecFinder;
import spectacular.spec.finder.StepActionFinder;
import spectacular.spec.parse.euc.ExecutableUseCaseParser;
import spectacular.spec.parse.euc.StepActionParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    private Map<String, UseCase> useCaseInventory = null;

    private List<SpecFile> stepActionList = null;

    private Map<String, StepActionChain> stepActionChainInventory = null;
    private Map<Pattern, StepActionChain> stepActionChainPatternInventory;

    private FixtureInventory fixtureInventory = null;


    public SpectacularSpine(SpectacularConfiguration configuration) {
        this.configuration = configuration;
        this.executableUseCaseParser = new ExecutableUseCaseParser();
        this.stepActionParser = new StepActionParser();
        this.useCaseInventory = new HashMap<String, UseCase>();
        this.specFileList = new LinkedList<SpecFile>();
        this.stepActionList = new LinkedList<SpecFile>();

        this.stepActionChainInventory = new HashMap<String, StepActionChain>();
        this.stepActionChainPatternInventory = new HashMap<Pattern, StepActionChain>();
        this.fixtureInventory = new FixtureInventory();

    }

    public void executeFullLifecycle() throws Exception {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Finding use case specifications.");
        this.specFileList = findUseCaseSpecifications();

        if(LOGGER.isInfoEnabled()) LOGGER.info("Parsing specifications.");
        for(SpecFile specFile : this.specFileList) {
            UseCase uc = parseUseCaseSpecification(specFile);
            if(uc != null) {
                if(LOGGER.isInfoEnabled()) LOGGER.info("Use Case:  " + uc.getUseCaseTitle());
                this.useCaseInventory.put(uc.getUseCaseTitle(), uc);
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
                    this.stepActionChainInventory.put(chain.getStepActionText(), chain);
                }
            }

        }

        if(LOGGER.isInfoEnabled()) LOGGER.info("Indexing automation code.");
        List<SpecFile> fixtures = findFixtureCode();
        for(SpecFile fixtureFile : fixtures) {

            ExecutableUseCaseFlow fixtureCode = ExecutableUseCaseFlow.loadActionImplementations(fixtureFile.getPath());
            this.fixtureInventory.setFixtures(fixtureCode.getFlows());

        }


        // foreach use case
        for(String useCaseTitle : this.useCaseInventory.keySet()) {

            if(LOGGER.isInfoEnabled()) LOGGER.info("Building execution path for use case \"" + useCaseTitle + "\"");
            UseCase useCase = this.useCaseInventory.get(useCaseTitle);
            ExecutionTree tree = ExecutionTree.build(useCase, this.useCaseInventory);

            if(LOGGER.isInfoEnabled()) LOGGER.info("Executing tree");
            UseCase nextUseCase = tree.getNext();
            while(nextUseCase != null) {

                if(LOGGER.isInfoEnabled()) LOGGER.info("Executing: " + nextUseCase.getUseCaseTitle());
                UseCaseResult useCaseResult = executeUseCase(nextUseCase, this.fixtureInventory, this.stepActionChainInventory);

                nextUseCase = tree.getNext();


            }



        }
            // execute use case steps against actions



    }

    public UseCaseResult executeUseCase(UseCase nextUseCase, FixtureInventory inventory, Map<String, StepActionChain> stepActions) {

        UseCaseExecutor executor = new UseCaseExecutor();
        UseCaseResult result = new UseCaseResult(nextUseCase);
        try {
            executor.execute(nextUseCase, stepActions, inventory, result);
        } catch(Exception e) {
            LOGGER.error("Unable to execute use case:  " + e);
            result.setStatus(ExecutionResultStatus.FAIL);
            result.setStatusCommentary(e.toString());
        }

        return(result);

    }

    public List<SpecFile> findFixtureCode() {

        SpecFinder finder = new FixtureCodeFinder();
        List<SpecFile> specs = finder.findSpecFiles(this.configuration.getFixtureCodeBaseLocation(), this.configuration.getFixtureCodeBaseLocationIncludeFilter());
        return(specs);


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
