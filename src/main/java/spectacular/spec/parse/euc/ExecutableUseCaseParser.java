package spectacular.spec.parse.euc;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.data.model.*;
import spectacular.spec.parse.SpecParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutableUseCaseParser implements SpecParser<UseCase> {


    private static Log LOGGER = LogFactory.getLog(ExecutableUseCaseParser.class);

    private static Pattern nameValuePattern = Pattern.compile("(.+)\\:(.+)");
    private static Pattern userStepPattern = Pattern.compile("(\\d+)\\. (.+)"); // looks for:  1. User does this...
    private static Pattern systemResponseStepPattern = Pattern.compile("\\((\\d+)\\) (.+)"); // looks for: (2) System does this...

    public UseCase parse(String spec) {

        // split into blocks
        // look for top to first PRIMARY FLOW - that is the name-value block
        UseCase useCase = new UseCase();
        String nameValueBlock = "";
        String primaryFlowBlock = "";
        List<String> alternateFlowBlocks = new LinkedList<String>();


        int startPrimaryBlock = spec.toUpperCase().indexOf(Flow.PRIMARY_FLOW_TAG, 0);
        int startFirstAlternateBlock = spec.toUpperCase().indexOf(Flow.ALTERNATE_FLOW_TAG, startPrimaryBlock);
        if (startPrimaryBlock > 0) {

            // go from start to the beginning of an "alternate flow" or the end of the doc
            if (startFirstAlternateBlock < 0) {
                primaryFlowBlock = spec.substring(startPrimaryBlock);
            } else {
                primaryFlowBlock = spec.substring(startPrimaryBlock, startFirstAlternateBlock);
            }

            Flow flow = parsePrimaryFlow(primaryFlowBlock);
            useCase.setPrimaryFlow(flow);

        }

        while (startFirstAlternateBlock >= 0) {

            int nextAltBlock = spec.toUpperCase().indexOf(Flow.ALTERNATE_FLOW_TAG, startFirstAlternateBlock + Flow.ALTERNATE_FLOW_TAG.length());
            if (nextAltBlock >= 0) {
                String altBlock = spec.substring(startFirstAlternateBlock, nextAltBlock);
                alternateFlowBlocks.add(altBlock);
                startFirstAlternateBlock = nextAltBlock;
            } else {
                String altBlock = spec.substring(startFirstAlternateBlock);
                alternateFlowBlocks.add(altBlock);
                startFirstAlternateBlock = -1;
            }

        }

        for (String block : alternateFlowBlocks) {
            Flow flow = parseAlternateFlow(block);
            useCase.addAlternativeFlow(flow);
        }


        if (startPrimaryBlock > 0) {
            nameValueBlock = spec.substring(0, startPrimaryBlock);
        } else {
            nameValueBlock = spec;
        }

        Map<String, String> nameValueData = parseNameValueBlock(nameValueBlock);
        for (String name : nameValueData.keySet()) {

            String value = nameValueData.get(name);
            if (name.toUpperCase().equals(UseCase.KEY_USE_CASE_TITLE)) {
                useCase.setUseCaseTitle(value.trim());
            } else if (name.toUpperCase().equals(UseCase.KEY_ACTORS)) {
                List<String> actors = splitByComma(value);
                useCase.setActors(actors);
            } else if (name.toUpperCase().equals(UseCase.KEY_PRECONDITIONS)) {
                List<String> preconditions = splitByComma(value);
                useCase.setPreconditions(preconditions);
            } else if (name.toUpperCase().equals(UseCase.KEY_POSTCONDITIONS)) {
                List<String> postconditions = splitByComma(value);
                useCase.setPostconditions(postconditions);
            }

        }

        return useCase;
    }

    private List<String> splitByComma(String value) {

        List<String> items = new LinkedList<String>();

        if (value.indexOf(",") >= 0) {
            String[] itemArr = value.split(",");
            for (String item : itemArr) {
                items.add(item.trim());
            }
        } else {
            items.add(value.trim());
        }


        return (items);
    }

    private Map<String, String> parseNameValueBlock(String nameValueBlock) {

        HashMap<String, String> nv = new HashMap<String, String>();
        String[] lines = nameValueBlock.split(System.getProperty("line.separator"));
        for (String line : lines) {

            Matcher matcher = nameValuePattern.matcher(line);
            if (matcher.matches()) {
                String name = matcher.group(1);
                String value = matcher.group(2);
                nv.put(name, value);
            }


        }

        return (nv);

    }

    private Flow parsePrimaryFlow(String primaryFlowBlock) {

        Flow flow = new Flow();
        flow.setFlowType(FlowType.PRIMARY);

        String primaryFlowTitle = "";
        List<Step> steps = new LinkedList<Step>();

        String[] lines = primaryFlowBlock.split(System.getProperty("line.separator"));
        for (String line : lines) {

            Matcher userStepMatcher = userStepPattern.matcher(line);
            Matcher systemResponseMatcher = systemResponseStepPattern.matcher(line);

            if (line.toUpperCase().contains(Flow.PRIMARY_FLOW_TAG)) {
                primaryFlowTitle = getPrimaryFlowTitle(line.trim());
                flow.setFlowTitle(primaryFlowTitle.trim());
            } else if (userStepMatcher.matches()) {

                String idStr = userStepMatcher.group(1);
                String step = userStepMatcher.group(2);

                Step userStep = new Step();
                userStep.setStepType(StepType.USER_ACTION);
                userStep.setId(idStr);
                userStep.setStepTitle(step);

                steps.add(userStep);

            } else if (systemResponseMatcher.matches()) {

                String idStr = systemResponseMatcher.group(1);
                String step = systemResponseMatcher.group(2);

                Step systemStep = new Step();
                systemStep.setStepType(StepType.SYSTEM_RESPONSE);
                systemStep.setId(idStr);
                systemStep.setStepTitle(step);

                steps.add(systemStep);

            }

        }

        flow.setSteps(steps);

        return (flow);

    }

    private Flow parseAlternateFlow(String alternativeFlowBlock) {

        Flow flow = new Flow();
        flow.setFlowType(FlowType.ALTERNATE);

        String alternateFlowTitle = "";
        List<Step> steps = new LinkedList<Step>();

        String[] lines = alternativeFlowBlock.split(System.getProperty("line.separator"));
        for (String line : lines) {

            Matcher userStepMatcher = userStepPattern.matcher(line);
            Matcher systemResponseMatcher = systemResponseStepPattern.matcher(line);

            if (line.toUpperCase().contains(Flow.ALTERNATE_FLOW_TAG)) {
                alternateFlowTitle = getAlternateFlowTitle(line.trim());
                flow.setFlowTitle(alternateFlowTitle.trim());
            } else if (userStepMatcher.matches()) {

                String idStr = userStepMatcher.group(1);
                String step = userStepMatcher.group(2);

                Step userStep = new Step();
                userStep.setStepType(StepType.USER_ACTION);
                userStep.setId(idStr);
                userStep.setStepTitle(step);

                steps.add(userStep);

            } else if (systemResponseMatcher.matches()) {

                String idStr = systemResponseMatcher.group(1);
                String step = systemResponseMatcher.group(2);

                Step systemStep = new Step();
                systemStep.setStepType(StepType.SYSTEM_RESPONSE);
                systemStep.setId(idStr);
                systemStep.setStepTitle(step);

                steps.add(systemStep);

            }

        }

        flow.setSteps(steps);

        return (flow);

    }

    private String getPrimaryFlowTitle(String line) {

        int begin = line.toUpperCase().indexOf(Flow.PRIMARY_FLOW_TAG);
        String title = line.substring(begin + Flow.PRIMARY_FLOW_TAG.length());
        return (title);

    }

    private String getAlternateFlowTitle(String line) {

        int begin = line.toUpperCase().indexOf(Flow.ALTERNATE_FLOW_TAG);
        String title = line.substring(begin + Flow.ALTERNATE_FLOW_TAG.length());
        return (title);

    }


}
