import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
    private String path;
    private static String getFilePath() throws FileException {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Key Files (*.keys)", "keys");
        chooser.setFileFilter(filter);

        int approved = chooser.showOpenDialog(null);
        if (approved == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                return file.getAbsolutePath();
            } catch (Exception e) {
                throw new FileException(e.getMessage(), true);
            }
        } else {
            throw new FileException(FileException.FILE_EXPLORER_ERROR);
        }
    }

    private static String[] readFile() {
        int e=0,m=0,d=0;
        String[] values = new String[3];
        try {
            BufferedReader in = new BufferedReader(new FileReader(getFilePath()));
        }
    }
}
