package spectacular.framework;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public final class SpecFileUtils {

    public static String loadFileContents(String path) {

        StringBuilder builder = new StringBuilder();

        try {

            LineNumberReader reader = new LineNumberReader(new FileReader(new File(path)));
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException ioe) {

        }

        return (builder.toString());


    }


}
