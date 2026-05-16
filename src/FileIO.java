import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.io.*;
import java.math.BigInteger;

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
                throw new FileException(e.getMessage());
            }
        } else if (approved != JFileChooser.CANCEL_OPTION) {
            throw new FileException(FileException.FILE_EXPLORER_ERROR);
        } else {
            throw new FileException("ignore");
        }
    }

    public String[] readFile() throws FileException {
        String k,m;
        String[] values = new String[2];
        try {
            BufferedReader in = new BufferedReader(new FileReader(getFilePath()));
            k = in.readLine();
            m = in.readLine();
            in.close();
            if (m.isEmpty() && k.isEmpty()) {
                throw new FileException("Invalid file: File is empty");
            } else if (m.isEmpty()) {
                throw new FileException("Invalid file: Only one piece of data present (two required)");
            }
            try {
                new BigInteger(k);
                new BigInteger(m);
                values[0] = k;
                values[1] = m;
                return values;
            } catch (NumberFormatException e) {
                throw new FileException("Invalid file data: Non-integers");
            }
        } catch (Exception exception) {
            throw new FileException(exception);
        }
    }

    public void writeFile(BigInteger e, BigInteger d, BigInteger m) throws FileException {
        try {
            String path = getFilePath();
            if (!path.endsWith(".keys")) {
                path += ".keys";
            }
            File file = new File(path);
            File publicFile = new File(file.getParent(),"public-" + file.getName());
            File privateFile = new File(file.getParent(), "private-" + file.getName());

            //public
            BufferedWriter writepub = new BufferedWriter(new FileWriter(publicFile));
            writepub.write(e.toString());
            writepub.newLine();
            writepub.write(m.toString());
            writepub.close();

            //private
            BufferedWriter writepriv = new BufferedWriter(new FileWriter(privateFile));
            writepriv.write(d.toString());
            writepriv.newLine();
            writepriv.write(m.toString());
            writepriv.close();
        } catch (FileException fileException) {
            throw fileException;
        } catch (IOException ioException) {
            throw new FileException(ioException);
        }
    }//end writeFile
}
