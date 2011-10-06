package spectacular.framework

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory


class ExecutableUseCaseFlow {

    private static Log LOGGER = LogFactory.getLog(ExecutableUseCaseFlow.class);
    Map<String, Closure> flows = new HashMap<String, Closure>();

    def static ExecutableUseCaseFlow loadActionImplementations(String path) {

        def flow = new ExecutableUseCaseFlow();

        def shell = new GroovyShell();
        shell.setVariable("step", flow);
        shell.run(new File(path));

        return(flow);

    }



    def Action(String pathToMatch, Closure closure) {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Adding Action:  " + pathToMatch);
        this.flows.put(pathToMatch, closure);


    }


}
