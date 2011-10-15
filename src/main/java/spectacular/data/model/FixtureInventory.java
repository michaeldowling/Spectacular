package spectacular.data.model;


import groovy.lang.Closure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FixtureInventory {

    private Map<String, Closure> fixtures;
    private Map<Pattern, String> fixturePatterns;

    public FixtureInventory() {
        this.fixtures = new HashMap<String, Closure>();
        this.fixturePatterns = new HashMap<Pattern, String>();
    }


    public void setFixtures(Map<String, Closure> fixtures) {
        this.fixtures = fixtures;
    }

    public void addFixture(String matchText, Closure fixture) {
        this.fixtures.put(matchText, fixture);
        createPattern(matchText);
    }

    public Closure findClosureForText(String text) {

        Closure closure = null;
        for(Pattern pattern : this.fixturePatterns.keySet()) {

            Matcher matcher = pattern.matcher(text);
            if(matcher.matches())
                return this.fixtures.get(this.fixturePatterns.get(pattern));


        }

        return(closure);

    }

    protected void setFixturePatterns(Map<Pattern, String> fixturePatterns) {
        this.fixturePatterns = fixturePatterns;
    }

    protected void addFixturePattern(String matchText, Pattern pattern) {
        this.fixturePatterns.put(pattern, matchText);
    }

    protected void createPattern(String text) {
        Pattern pattern = Pattern.compile(text);
        addFixturePattern(text, pattern);
    }
}
