import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.io.File;

public class FileIO {
    private String path;
    private static String getFile() {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Key Files (*.keys)", "keys");
        chooser.setFileFilter(filter);

        int approved = chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        return file.getAbsolutePath();
    }
}
