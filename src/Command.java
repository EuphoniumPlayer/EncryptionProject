import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

public class Command {
    private static Menu menu;
    private static CreateWindow create;
    private static EncryptorWindow encryptor;
    private static FileIO fileIO = new FileIO();
    private static FileIOError fileErrorWindow;

    public static boolean isDark;
    private static String settingsFilePath;

    public static void main(String[] args) {
        String folderPath = System.getProperty("user.home") + "\\AppData\\Local\\EncryptionProject\\settings\\";
        settingsFilePath = folderPath + "\\settings.conf";

        File folder = new File(folderPath);
        folder.mkdirs();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception err) {
            err.printStackTrace();
        }

        try {
            loadTheme();
        } catch (Exception e) {
            isDark = true;
        }

        menu = new Menu();
        create = new CreateWindow();
        encryptor = new EncryptorWindow();
        fileErrorWindow = new FileIOError();

        applyTheme();
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

    public String[] readKeyFile() throws FileException {
        try {
            return fileIO.readKeyFile();
        } catch (FileException error) {
            throw error;
        }
    }

    public void writeKeyFile(BigInteger e, BigInteger d, BigInteger m) throws FileException {
        try {
            fileIO.writeKeyFile(e, d, m);
        } catch (FileException error) {
            throw error;
        }
    }

    public ArrayList<String> readFile(String path) throws FileException {
        try {
            return fileIO.readFile(path);
        } catch (Exception e) {
            throw new FileException(e);
        }
    }

    private static void applyTheme() {
        if (isDark) {
            UIManager.put("nimbusBase", new Color(18,18,18));
            UIManager.put("nimbusBlueGrey", new Color(25,25,25));
            UIManager.put("control", new Color(20,20,20));//background
            UIManager.put("nimbusLightBackground", new Color(45,45,45));//text boxes
            UIManager.put("nimbusSelectionBackground", new Color(60,60,60));
            UIManager.put("text",Color.WHITE);
            UIManager.put("nimbusSelectedText", Color.WHITE);
            UIManager.put("nimbusDisabledText", new Color(100,100,100));
            UIManager.put("Button.disabled", new Color(50,50,50));
        } else {
            UIManager.put("nimbusBase", new Color(180,180,180));
            UIManager.put("nimbusBlueGrey", new Color(200,200,200));
            UIManager.put("control", new Color(220,220,220));
            UIManager.put("nimbusLightBackground", Color.WHITE);
            UIManager.put("nimbusSelectionBackground", new Color(100,149,237));
            UIManager.put("text", Color.BLACK);
            UIManager.put("nimbusSelectedText", Color.BLACK);
            UIManager.put("nimbusDisabledText", Color.GRAY);
            UIManager.put("Button.disabled", new Color(125,125,125));
        }
        SwingUtilities.updateComponentTreeUI(menu.getMenuFrame());
        menu.getMenuFrame().revalidate();
        menu.getMenuFrame().repaint();

        SwingUtilities.updateComponentTreeUI(create.getFrame());
        create.getFrame().revalidate();
        create.getFrame().repaint();

        SwingUtilities.updateComponentTreeUI(encryptor.getFrame());
        encryptor.getFrame().revalidate();
        encryptor.getFrame().repaint();

        SwingUtilities.updateComponentTreeUI(fileErrorWindow.getFrame());
        fileErrorWindow.getFrame().revalidate();
        fileErrorWindow.getFrame().repaint();

        menu.updateBGButton();
    }//end applyTheme

    public void updateTheme() {
        isDark = !isDark();
        try {
            fileIO.writeOneLineFile(settingsFilePath, isDark ? "dark" : "light");
        } catch (Exception e) {
            displayFileError(new FileException(e));
        }
        restart();
    }

    private static void loadTheme() throws FileException {
        try {
            ArrayList<String> returned = fileIO.readFile(settingsFilePath);
            if (returned.get(0).equals("light")) {
                isDark = false;
            } else {
                isDark = true;
            }
        } catch (Exception e) {
            throw new FileException(e);
        }
    }

    public boolean isDark() {
        return this.isDark;
    }

    public void displayFileError(FileException error) {
        fileErrorWindow.displayError(error);
    }
    public static void displayFileError(String error) {
        fileErrorWindow.displayError(error);
    }

    public static void restart() {
        String origin = ProcessHandle.current().info().command().orElseThrow();
        if (origin.endsWith(".exe") && !origin.contains("java")) {
            try {
                new ProcessBuilder(origin).inheritIO().start();
                System.exit(0);
            } catch (Exception e) {
                displayFileError(e.getMessage());
            }
        } else {
            String java = System.getProperty("java.home") + "/bin/java";
            String classpath = System.getProperty("java.class.path");
            try {
                new ProcessBuilder(java, "-cp", classpath, "Command").inheritIO().start();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}