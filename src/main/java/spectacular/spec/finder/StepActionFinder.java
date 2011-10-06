package spectacular.spec.finder;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spectacular.data.model.SpecFile;
import spectacular.data.model.StepActionSpecFile;
import spectacular.data.model.UseCaseSpecFile;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StepActionFinder implements SpecFinder {

    private static Log LOGGER = LogFactory.getLog(StepActionFinder.class);

    public List<SpecFile> findSpecFiles(String baseLocation, String fileFilter) {

        List<SpecFile> specFiles = new LinkedList<SpecFile>();
        Collection<File> files = FileUtils.listFiles(new File(baseLocation), new WildcardFileFilter(fileFilter), TrueFileFilter.INSTANCE);
        for(File file : files) {

            if(LOGGER.isInfoEnabled()) LOGGER.info("Found:  " + file.getAbsolutePath());
            SpecFile sf = new StepActionSpecFile(file.getAbsolutePath());
            specFiles.add(sf);

        }

        return(specFiles);

    }
}
