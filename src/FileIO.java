import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private String filepath = "";

    public FileIO (String file) {
        this.filepath = file;
    }

    public String fileScanner(String directory) throws DirectoryNotFoundException {
        try {
            File f = new File(directory);
            for (String file : f.list()) {

            }
        } catch (NullPointerException e) {
            throw new DirectoryNotFoundException("Specified directory not found");
        }
    }
}
