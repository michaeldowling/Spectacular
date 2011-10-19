package spectacular.reporting;


import spectacular.data.model.FlowResult;
import spectacular.data.model.StepResult;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseResult;

import java.util.List;

public abstract class ReportWriterTemplate {

    private ReportSink reportSink;

    public ReportWriterTemplate(ReportSink sink) {
        this.reportSink = sink;
    }

    public ReportSink getReportSink() {
        return(this.reportSink);
    }

    public void reportUseCaseResult(UseCaseResult result) {

        beginUseCaseResult(result);
        writeUseCaseTitle(result.getExecutableItem().getUseCaseTitle());

        String useCaseResult = result.getStatus().toString();
        if(result.getStatusCommentary() != null) {
            useCaseResult += " - ";
            useCaseResult += result.getStatusCommentary();
        }
        writeUseCaseResult(useCaseResult);

        beginMetadata();
        writeMetadata(result.getExecutableItem());
        endMetadata();

        beginPrimaryFlowResults();
        writePrimaryFlowTitle(result.getExecutableItem().getPrimaryFlow().getFlowTitle());

        beginFlowStepTable();
        FlowResult flowResult = result.getPrimaryFlow();
        boolean altRow = true;
        for(StepResult stepResult : flowResult.getStepResults()) {
            writeStepResultRow(stepResult, altRow);
            altRow = (altRow) ? false : true;
        }
        endFlowStepTable();

        List<FlowResult> flowResults = result.getAlternateFlows();
        for(FlowResult altFlowResult : flowResults) {

            beginFlowStepTable();
            altRow = true;
            for(StepResult stepResult : altFlowResult.getStepResults()) {
                writeStepResultRow(stepResult, altRow);
                altRow = (altRow) ? false : true;
            }
            endFlowStepTable();
        }


        endUseCaseResult(result);

    }


    protected abstract void beginUseCaseResult(UseCaseResult result);
    protected abstract void writeUseCaseTitle(String title);
    protected abstract void writeUseCaseResult(String useCaseResult);
    protected abstract void beginMetadata();
    protected abstract void writeMetadata(UseCase useCase);
    protected abstract void endMetadata();
    protected abstract void beginPrimaryFlowResults();
    protected abstract void writePrimaryFlowTitle(String title);
    protected abstract void beginFlowStepTable();
    protected abstract void writeStepResultRow(StepResult stepResult, boolean isAlternateRow);
    protected abstract void endFlowStepTable();
    protected abstract void endUseCaseResult(UseCaseResult useCaseResult);




}
