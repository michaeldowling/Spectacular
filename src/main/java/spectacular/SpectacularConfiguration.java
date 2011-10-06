package spectacular;


public class SpectacularConfiguration {

    private String useCasesBaseLocation = "./";
    private String useCasesBaseLocationIncludeFilter = "*.usecase";
    private String actionScripts = "*.groovy";

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

    public String getActionScripts() {
        return actionScripts;
    }

    public void setActionScripts(String actionScripts) {
        this.actionScripts = actionScripts;
    }
}
