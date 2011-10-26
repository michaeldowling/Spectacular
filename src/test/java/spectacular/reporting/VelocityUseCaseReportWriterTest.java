package spectacular.reporting;


import org.junit.Test;
import spectacular.data.model.Flow;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseResult;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VelocityUseCaseReportWriterTest {

    @Test
    public void testCanMergeBasicDocument() throws Exception {

        UseCase useCase = mock(UseCase.class);
        when(useCase.getUseCaseTitle()).thenReturn("TEST");

        UseCaseResult result = new UseCaseResult(useCase);

        VelocityUseCaseReportWriter writer = new VelocityUseCaseReportWriter("reporting/velocity/basic_template.vm");
        String gen = writer.generate(result);

        assertNotNull(gen);
        assertEquals("TEST", gen.trim());


    }

    @Test
    public void testCanMergeBasicForloopDocument() throws Exception {

        List<Flow> altFlows = new LinkedList<Flow>();
        for(int i = 0 ; i < 10 ; i++) {
            Flow flow = new Flow();
            flow.setFlowTitle("-" + i);
            altFlows.add(flow);
        }

        UseCase useCase = mock(UseCase.class);
        when(useCase.getUseCaseTitle()).thenReturn("TEST");
        when(useCase.getAlternativeFlows()).thenReturn(altFlows);

        UseCaseResult result = new UseCaseResult(useCase);

        VelocityUseCaseReportWriter writer = new VelocityUseCaseReportWriter("reporting/velocity/basic_forloop_template.vm");
        String gen = writer.generate(result);

        assertNotNull(gen);
        assertEquals("TEST-0-1-2-3-4-5-6-7-8-9", gen.trim());


    }


}
