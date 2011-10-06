package spectacular;


public class SpectacularConfiguration {

    private String useCasesBaseLocation = "./";
    private String useCasesBaseLocationIncludeFilter = "*.usecase";
    private String stepActionBaseLocation = "./";
    private String stepActionBaseLocationIncludeFilter = "*.groovy";

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
}
