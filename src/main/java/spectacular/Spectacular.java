package spectacular;


import org.apache.commons.cli.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.spine.SpectacularSpine;

import java.io.File;


public class Spectacular {

    private static Log LOGGER = LogFactory.getLog(Spectacular.class);
    private static final String DEFAULT_CONFIGURATION = "./spectacular.xml";


    public static void main(String[] args) {

        printSpectacularHeader();
        CommandLine commandLine = parseCommandLine(args);
        SpectacularConfiguration config = parseConfiguration(commandLine.getOptionValue("config", "./spectacular.xml"));
        printConfiguration(config);

        SpectacularSpine spectacularSpine = new SpectacularSpine(config);

        try {
            spectacularSpine.executeFullLifecycle();
        } catch (Exception e) {
            LOGGER.fatal("Error executing spectacular lifecycle:  " + e);
            System.exit(255);
        }


    }

    private static void printConfiguration(SpectacularConfiguration config) {

        System.out.println("Configuration: \n" +
                "\tUse Case Base Location:  " + config.getUseCasesBaseLocation() + " \n" +
                "\tUse Case File Filter:  " + config.getUseCasesBaseLocationIncludeFilter() + " \n");


    }

    public static SpectacularConfiguration parseConfiguration(String config) {

        File file = new File(config);
        if (!file.exists()) {
            LOGGER.fatal("Unable to find configuration file:  " + config);
            System.exit(1);
        }

        Configuration conf = null;
        try {
            conf = new XMLConfiguration(file);
        } catch (Exception e) {
            LOGGER.fatal("Unable to parse configuration.", e);
            System.exit(1);
        }

        SpectacularConfiguration specConfig = new SpectacularConfiguration();

        if (conf.containsKey("use-cases.base-location.path")) {
            specConfig.setUseCasesBaseLocation(conf.getString("use-cases.base-location.path", "./"));
        }

        if (conf.containsKey("use-cases.base-location.include")) {
            specConfig.setUseCasesBaseLocationIncludeFilter(conf.getString("use-cases.base-location.include", "*.usecase"));
        }

        if (conf.containsKey("step-actions.base-location.path")) {
            specConfig.setStepActionBaseLocation(conf.getString("step-actions.base-location.path"));
        }

        if (conf.containsKey("step-actions.base-location.include")) {
            specConfig.setStepActionBaseLocationIncludeFilter(conf.getString("step-actions.base-location.include"));
        }

        if (conf.containsKey("fixtures.base-location.path")) {
            specConfig.setFixtureCodeBaseLocation(conf.getString("fixtures.base-location.path"));
        }

        if (conf.containsKey("fixtures.base-location.include")) {
            specConfig.setFixtureCodeBaseLocationIncludeFilter(conf.getString("fixtures.base-location.include"));
        }

        if (conf.containsKey("webdriver-aware.driver")) {
            specConfig.setSeleniumAwareDriver(conf.getString("webdriver-aware.driver", "HTMLUNIT"));
        }


        return (specConfig);

    }

    private static CommandLine parseCommandLine(String[] args) {

        Options options = new Options();
        options.addOption("h", false, "Prints help documentation.");
        options.addOption("config", true, "Location of configuration for project.");

        CommandLineParser parser = new GnuParser();
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
        } catch (Exception e) {
            LOGGER.fatal("Unable to parse command line options.", e);
            System.exit(1);
        }

        if (line.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("spectacular [options]", options);
            System.exit(0);
        }


        return (line);

    }

    private static void printSpectacularHeader() {

        System.out.println("Spectacular v2.0 \n" +
                "Apache License \n");


    }


}
