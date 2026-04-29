import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.io.*;

public class FileIO {
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

    public String[] readFile() throws FileException {
        String e,d,m;
        String[] values = new String[3];
        try {
            BufferedReader in = new BufferedReader(new FileReader(getFilePath()));
            e = in.readLine();
            d = in.readLine();
            m = in.readLine();
            in.close();
            values[0] = e;
            values[1] = d;
            values[2] = m;
            return values;
        } catch (IOException ioException) {
            throw new FileException(ioException);
        } catch (Exception exception) {
            throw new FileException(exception.getMessage(), true);
        }
    }

    public void writeFile(Integer e, Integer d, Integer m) throws FileException {
        try {
            String path = getFilePath();
            if (!path.endsWith(".keys")) {
                path += ".keys";
            }
            BufferedWriter write = new BufferedWriter(new FileWriter(path));
            write.write(e.toString());
            write.newLine();
            write.write(d.toString());
            write.newLine();
            write.write(m.toString());
            write.close();
        } catch (FileException fileException) {
            throw fileException;
        } catch (IOException ioException) {
            throw new FileException(ioException);
        }
    }
}
