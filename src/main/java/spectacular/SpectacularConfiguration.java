package spectacular;


public class SpectacularConfiguration {

    private String useCasesBaseLocation = "./";
    private String useCasesBaseLocationIncludeFilter = "*.usecase";
    private String stepActionBaseLocation = "./";
    private String stepActionBaseLocationIncludeFilter = "*.stepaction";
    private String fixtureCodeBaseLocation = "./";
    private String fixtureCodeBaseLocationIncludeFilter = "*.groovy";

    private String seleniumAwareDriver = "HTMLUNIT";

    public String getUseCasesBaseLocation() {
        return useCasesBaseLocation;
    }

    public void setUseCasesBaseLocation(String useCasesBaseLocation) {
        this.useCasesBaseLocation = useCasesBaseLocation;
    }

    public String getUseCasesBaseLocationIncludeFilter() {
        return useCasesBaseLocationIncludeFilter;
    }

    public void setUseCasesBaseLocationIncludeFilter(String useCasesBaseLocationIncludeFilter) {
        this.useCasesBaseLocationIncludeFilter = useCasesBaseLocationIncludeFilter;
    }

    public String getStepActionBaseLocation() {
        return stepActionBaseLocation;
    }

    public void setStepActionBaseLocation(String stepActionBaseLocation) {
        this.stepActionBaseLocation = stepActionBaseLocation;
    }

    public String getStepActionBaseLocationIncludeFilter() {
        return stepActionBaseLocationIncludeFilter;
    }

    public void setStepActionBaseLocationIncludeFilter(String stepActionBaseLocationIncludeFilter) {
        this.stepActionBaseLocationIncludeFilter = stepActionBaseLocationIncludeFilter;
    }

    public String getFixtureCodeBaseLocation() {
        return fixtureCodeBaseLocation;
    }

    public void setFixtureCodeBaseLocation(String fixtureCodeBaseLocation) {
        this.fixtureCodeBaseLocation = fixtureCodeBaseLocation;
    }

    public String getFixtureCodeBaseLocationIncludeFilter() {
        return fixtureCodeBaseLocationIncludeFilter;
    }

    public void setFixtureCodeBaseLocationIncludeFilter(String fixtureCodeBaseLocationIncludeFilter) {
        this.fixtureCodeBaseLocationIncludeFilter = fixtureCodeBaseLocationIncludeFilter;
    }

    public String getSeleniumAwareDriver() {
        return seleniumAwareDriver;
    }

    public void setSeleniumAwareDriver(String seleniumAwareDriver) {
        this.seleniumAwareDriver = seleniumAwareDriver;
    }
}
