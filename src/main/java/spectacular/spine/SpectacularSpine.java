package spectacular.spine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.SpectacularConfiguration;

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

    public void execute() throws Exception {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Finding use cases.");
        findUseCases();



    }

    private void findUseCases() {


    }


}
