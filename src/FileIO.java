import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private String filepath="";
    private String error="";

    public FileIO (String file) {
        this.filepath = file;
    }

    private ArrayList<String> fileScanner(String directory) throws NotFoundException {
        ArrayList<String> keyfiles = new ArrayList<>();
        try {
            File f = new File(directory);
            for (String file : f.list()) {
                if (file.toLowerCase().endsWith(".keys")) {
                    keyfiles.add(file);
                }
            }
        } catch (NullPointerException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            error += "Error: " + e.getMessage();
        }
        return keyfiles;
    }
    public String getFiles() {
        ArrayList<String> files;
        try {
            files = fileScanner(filepath);
            StringBuilder builder = new StringBuilder();
            for (String file : files) builder.append(file).append("\n");
            return new String(builder);


        } catch (NotFoundException nfe) {
            error += "File not found error: " + nfe.getMessage();
        } catch (Exception e) {
            error += "Error: " + e.getMessage();
        }
        return "Error";
    }
    public String getErrorLog() {
        if (error.isEmpty()) {
            return "No errors encountered. ";
        } else {
            return error;
        }
    }
}