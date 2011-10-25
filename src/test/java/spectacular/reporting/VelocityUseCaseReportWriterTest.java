package spectacular.reporting;


import org.junit.Test;
import spectacular.data.model.UseCase;
import spectacular.data.model.UseCaseResult;

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
        assertEquals("TEST", gen);


    }


}
