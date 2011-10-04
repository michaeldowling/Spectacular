package spectacular.spec.parse.euc;


import spectacular.data.model.Action;
import spectacular.data.model.StepActionChain;
import spectacular.spec.parse.SpecParser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepActionParser implements SpecParser<List<StepActionChain>> {

    private Pattern stepActionPattern = Pattern.compile("\\/(.+)\\/");
    private Pattern actionPattern = Pattern.compile("\\.+");

    public List<StepActionChain> parse(String spec) {

        List<StepActionChain> chainList = new LinkedList<StepActionChain>();
        List<String> stepActionBlocks = splitIntoBlocks(spec);

        for(String block : stepActionBlocks) {

            StepActionChain chain = parseStepActionBlock(block);
            chainList.add(chain);

        }



        return(chainList);

    }

    private StepActionChain parseStepActionBlock(String block) {

        StepActionChain chain = new StepActionChain();

        String[] lines = block.split(System.getProperty("line.separator"));
        for(String line : lines) {

            // is it the step action or an action?
            Matcher stepActionMatcher = this.stepActionPattern.matcher(line.trim());
            // Matcher actionMatcher = this.actionPattern.matcher(line.trim());
            if(stepActionMatcher.matches()) {
                // get text
                String stepActionText = stepActionMatcher.group(1);
                chain.setStepActionText(stepActionText);
            } else if(line.trim().length() > 0) {
                Action action = new Action();
                action.setActionText(line.trim());
                chain.addAction(action);
            }




        }



        return(chain);

    }

    private List<String> splitIntoBlocks(String spec) {

        List<String> blockList = new LinkedList<String>();

        // find first StepAction
        Matcher matcher = this.stepActionPattern.matcher(spec);
        while(matcher.find()) {

            int start = matcher.start();
            int end = matcher.end();

            // look ahead for the next one
            String subSpec = spec.substring(end);
            Matcher nextOne = this.stepActionPattern.matcher(subSpec);
            if(nextOne.find()) {
                end = end + nextOne.start();
            } else {
                end = spec.length() - 1;
            }

            String block = spec.substring(start, end);
            blockList.add(block);


        }



        return(blockList);

    }
}
