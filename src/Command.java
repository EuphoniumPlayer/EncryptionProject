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
    private static SettingsMenu settingsmenu;

    public static boolean isDark;
    private static String themeSettingsFilePath;
    private static String settingsFolderPath;
    private static String settingsPath;

    private static int bitlengthvalue;
    private static String publicPrefix, privatePrefix;

    public static void main(String[] args) {
        settingsFolderPath = System.getProperty("user.home") + "\\AppData\\Local\\EncryptionProject\\settings\\";
        themeSettingsFilePath = settingsFolderPath + "\\theme.conf";

        File folder = new File(settingsFolderPath);
        folder.mkdirs();

        settingsPath = settingsFolderPath += "\\settings.conf";

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

//        try{
//            loadSettings();
//        } catch (FileException e) {
//            bitlengthvalue = 512;
//            publicPrefix = "public-";
//            privatePrefix = "private-";
//            isDark = true;
//        }

        menu = new Menu();
        create = new CreateWindow();
        encryptor = new EncryptorWindow();
        fileErrorWindow = new FileIOError();
        settingsmenu = new SettingsMenu();

        try {
            readSettingsFile();
        } catch (FileException e) {
            displayFileError(e.getMessage() + "\nDefault values have been set.");
            bitlengthvalue = 512;
            publicPrefix = "public-";
            privatePrefix = "private-";
        }

        settingsmenu.updateValues();

        applyTheme();
        menu.visible();
    }//end main

    public void setMenuVisible(boolean state) {
        if (state) {
            menu.visible();
        } else {
            menu.invisible();
        }
    }//end setmenuvisible

    public void setCreateVisible(boolean state) {
        create.setVisible(state);
    }//end setcreatevisible

    public void setEncryptVisible(boolean state) {
        if (state) {
            encryptor.visible();
        } else {
            encryptor.invisible();
        }
    }

    public void setSettingsMenuVisible(boolean state) {
        settingsmenu.setVisible(state);
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
        //old method, had caching issues
//        SwingUtilities.updateComponentTreeUI(menu.getMenuFrame());
//        menu.getMenuFrame().revalidate();
//        menu.getMenuFrame().repaint();
//
//        SwingUtilities.updateComponentTreeUI(create.getFrame());
//        create.getFrame().revalidate();
//        create.getFrame().repaint();
//
//        SwingUtilities.updateComponentTreeUI(encryptor.getFrame());
//        encryptor.getFrame().revalidate();
//        encryptor.getFrame().repaint();
//
//        SwingUtilities.updateComponentTreeUI(fileErrorWindow.getFrame());
//        fileErrorWindow.getFrame().revalidate();
//        fileErrorWindow.getFrame().repaint();

        //SwingUtilities.updateComponentTreeUI(settingsmenu.getFrame());
        //settingsmenu.getFrame().revalidate();
        //settingsmenu.getFrame().repaint();

        menu.updateBGButton();
    }//end applyTheme

    public void updateTheme() {
        isDark = !isDark();
        try {
            fileIO.writeOneLineFile(themeSettingsFilePath, isDark ? "dark" : "light");
        } catch (Exception e) {
            displayFileError(new FileException(e));
        }
        restart();
    }

    private static void loadTheme() throws FileException {
        try {
            ArrayList<String> returned = fileIO.readFile(themeSettingsFilePath);
            if (returned.get(0).equals("light")) {
                isDark = false;
            } else {
                isDark = true;
            }
        } catch (Exception e) {
            throw new FileException(e);
        }
    }

    private static void readSettingsFile() throws FileException {
        try {
            ArrayList<String> settings = fileIO.readFile(settingsPath);
            bitlengthvalue = Integer.parseInt(settings.get(0));
            publicPrefix = settings.get(1);
            privatePrefix = settings.get(2);
        } catch (Exception e) {
            throw new FileException(e);
        }
    }

    public static boolean isDark() {
        return isDark;
    }
    public static int getBitLength() {
        return bitlengthvalue;
    }
    public static void displayFileError(FileException error) {
        fileErrorWindow.displayError(error);
    }
    public static void displayFileError(String error) {
        fileErrorWindow.displayError(error);
    }
    public static String getPublicPrefix() {return publicPrefix;}
    public static String getPrivatePrefix() {return privatePrefix;}

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
    }//end of restart
}