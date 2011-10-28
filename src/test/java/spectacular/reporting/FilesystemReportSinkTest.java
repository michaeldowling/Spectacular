package spectacular.reporting;


import org.junit.Test;
import spectacular.data.model.UseCase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FilesystemReportSinkTest {

    @Test
    public void testCanGenerateCorrectFilename() throws Exception {

        UseCase uc = mock(UseCase.class);
        when(uc.getUseCaseTitle()).thenReturn("I'm here to do something-useful: lol");

        FilesystemReportSink reportSink = new FilesystemReportSink();
        String filename = reportSink.generateFilename(uc);

        assertNotNull(filename);
        assertEquals("I_m_here_to_do_something_useful__lol.html", filename);


    }


}
