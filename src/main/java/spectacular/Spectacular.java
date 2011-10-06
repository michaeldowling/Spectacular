package spectacular;


import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.apache.commons.cli.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;


public class Spectacular {

    private static Log LOGGER = LogFactory.getLog(Spectacular.class);
    private static final String DEFAULT_CONFIGURATION = "./spectacular.xml";


    public static void main(String[] args) {

        printSpectacularHeader();
        CommandLine commandLine = parseCommandLine(args);
        SpectacularConfiguration config = parseConfiguration(commandLine.getOptionValue("config", "./spectacular.xml"));
        printConfiguration(config);



    }

    private static void printConfiguration(SpectacularConfiguration config) {

        System.out.println( "Configuration: \n" +
                            "\tUse Case Base Location:  " + config.getUseCasesBaseLocation() + " \n" +
                            "\tUse Case File Filter:  " + config.getUseCasesBaseLocationIncludeFilter() + " \n");


    }

    private static SpectacularConfiguration parseConfiguration(String config) {

        File file = new File(config);
        if(!file.exists()) {
            LOGGER.fatal("Unable to find configuration file:  " + config);
            System.exit(1);
        }

        Configuration conf = null;
        try {
            conf = new XMLConfiguration(file);
        } catch(Exception e) {
            LOGGER.fatal("Unable to parse configuration.", e);
            System.exit(1);
        }

        SpectacularConfiguration specConfig = new SpectacularConfiguration();

        if(conf.containsKey("use-cases")) {

            if(conf.containsKey("use-cases.base-location")) {

                if(conf.containsKey("use-cases.base-location.path")) {
                    specConfig.setUseCasesBaseLocation(conf.getString("use-cases.base-location.path", "./"));
                }

                if(conf.containsKey("use-cases.base-location.include")) {
                    specConfig.setUseCasesBaseLocationIncludeFilter(conf.getString("use-cases.base-location.include", "*.usecase"));
                }
            }

        }

        return(specConfig);

    }

    private static CommandLine parseCommandLine(String[] args) {

        Options options = new Options();
        options.addOption("h", false, "Prints help documentation.");
        options.addOption("config", true, "Location of configuration for project.");

        CommandLineParser parser = new GnuParser();
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
        } catch(Exception e) {
            LOGGER.fatal("Unable to parse command line options.", e);
            System.exit(1);
        }

        if(line.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("spectacular [options]", options);
            System.exit(0);
        }


        return(line);

    }

    private static void printSpectacularHeader() {

        System.out.println( "Spectacular v2.0 \n" +
                            "Apache License \n");



    }


}
