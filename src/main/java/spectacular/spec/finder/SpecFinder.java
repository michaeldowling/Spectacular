package spectacular.spec.finder;


import spectacular.data.model.SpecFile;

import java.util.List;

public interface SpecFinder {

    List<SpecFile> findSpecFiles(String baseLocation, String fileFilter);


}
