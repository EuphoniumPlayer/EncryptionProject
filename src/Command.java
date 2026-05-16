import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class Command {
    private static Menu menu;
    private static CreateWindow create;
    private static EncryptorWindow encryptor;
    private static FileIO fileIO;
    private static FileIOError fileErrorWindow;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase",new Color(18,18,18));
            UIManager.put("nimbusBlueGrey",new Color(25,25,25));
            UIManager.put("control", new Color(28,28,28));
            UIManager.put("text", Color.WHITE);
            UIManager.put("nimbusLightBackground", new Color(20,20,20));
            UIManager.put("nimbusSelectedText", Color.WHITE);
            UIManager.put("nimbusSelectionBackground", new Color(60,60,60));
        } catch (Exception err) {
            err.printStackTrace();
        }

        menu = new Menu();
        create = new CreateWindow();
        encryptor = new EncryptorWindow();
        fileIO = new FileIO();
        fileErrorWindow = new FileIOError();

        menu.visible();
    }

    public void setMenuVisible(boolean state) {
        if (state) {
            menu.visible();
        } else {
            menu.invisible();
        }
    }//end setmenuvisible
    public void setCreateVisible(boolean state) {
        if (state) {
            create.visible();
        } else {
            create.invisible();
        }
    }//end setcreatevisible
    public void setEncryptVisible(boolean state) {
        if (state) {
            encryptor.visible();
        } else {
            encryptor.invisible();
        }
    }
    public String[] readFile() throws FileException {
        try {
            return fileIO.readFile();
        } catch (FileException error) {
            throw error;
        }
    }
    public void writeFile(BigInteger e, BigInteger d, BigInteger m) throws FileException {
        try {
            fileIO.writeFile(e,d,m);
        } catch (FileException error) {
            throw error;
        }
    }
    public void displayFileError(FileException error) {
        fileErrorWindow.displayError(error);
    }
    public void displayFileError(String error) {
        fileErrorWindow.displayError(error);
    }
}