import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

public class Command {
    //Declaration of the various objects
    private static Menu menu;
    private static CreateWindow create;
    private static EncryptorWindow encryptor;
    private static FileIO fileIO = new FileIO();
    private static FileIOError fileErrorWindow;
    private static SettingsMenu settingsmenu;

    //Declaration of the various setting related variables
    private static boolean isDark;
    private static String themeSettingsFilePath;
    private static String settingsFolderPath;
    private static String settingsPath;
    private static int bitlengthvalue;
    private static String publicPrefix, privatePrefix;

    //Sets up everything to run
    public static void main(String[] args) {
        //Obtains the user's home directory and digs into the AppData directory to store settings files
        //Works to create the baseline
        settingsFolderPath = System.getProperty("user.home") + "\\AppData\\Local\\EncryptionProject\\settings\\";
        //Ensures the directory for settings exists
        File folder = new File(settingsFolderPath);
        folder.mkdirs();
        //Sets the theme settings file path value
        themeSettingsFilePath = settingsFolderPath + "\\theme.conf";
        //Sets the settings file path value
        settingsPath = settingsFolderPath += "\\settings.conf";

        //Tries to set default theme to Nimbus so colors can be altered
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception err) {
            err.printStackTrace();
        }

        //Tries to load the theme value from file. Otherwise, defaults to dark
        //TODO: Update for settings menu
        try {
            loadTheme();
        } catch (Exception e) {
            isDark = true;
        }

        //Initializes the objects now that the theme has been set so it applies when
        //they are created
        menu = new Menu();
        create = new CreateWindow();
        encryptor = new EncryptorWindow();
        fileErrorWindow = new FileIOError();
        settingsmenu = new SettingsMenu();

        //Applies the theme
        applyTheme();

        //Tries to read the saved settings. Otherwise, sets default values to avoid
        //issues.
        try {
            readSettingsFile();
        } catch (FileException e) {
            displayFileError("Error reading settings file. Default values have been set.");
            bitlengthvalue = 512;
            publicPrefix = "public-";
            privatePrefix = "private-";
        }

        //Updates settings menu with the newly read values
        //TODO: Consider changing logic
        settingsmenu.updateValues();
        //Sets the menu visible
        menu.setVisible(true);
    }//end main

    //Set menu visibility
    public void setMenuVisible(boolean state) {menu.setVisible(state);}//end setmenuvisible

    //Set key creator visibility
    public void setCreateVisible(boolean state) {create.setVisible(state);}

    //Set Encryptor visibility
    public void setEncryptVisible(boolean state) {
        if (state) {
            encryptor.visible();
        } else {
            encryptor.invisible();
        }
    }

    //Set SettingsMenu visibility
    public void setSettingsMenuVisible(boolean state) {
        settingsmenu.setVisible(state);
    }

    //Reads .keys files
    public String[] readKeyFile() throws FileException {
        try {
            return fileIO.readKeyFile();
        } catch (FileException error) {
            throw error;
        }
    }

    //Writes .keys files
    public void writeKeyFile(BigInteger e, BigInteger d, BigInteger m) throws FileException {
        try {
            fileIO.writeKeyFile(e, d, m);
        } catch (FileException error) {
            throw error;
        }
    }

    //Applies theme based on whether the current theme is supposed to be dark or light
    private static void applyTheme() {
        //Checks if isDark is true or false
        if (isDark) {
            //Set to the dark theme
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
            //Set to the light theme
            UIManager.put("nimbusBase", new Color(180, 180, 180));
            UIManager.put("nimbusBlueGrey", new Color(200, 200, 200));
            UIManager.put("control", new Color(220, 220, 220));
            UIManager.put("nimbusLightBackground", Color.WHITE);
            UIManager.put("nimbusSelectionBackground", new Color(100, 149, 237));
            UIManager.put("text", Color.BLACK);
            UIManager.put("nimbusSelectedText", Color.BLACK);
            UIManager.put("nimbusDisabledText", Color.GRAY);
            UIManager.put("Button.disabled", new Color(125, 125, 125));
        }//End of the theme-setting logic

        //Updates the theme button
        //TODO: Readjust for new settings menu
        menu.updateBGButton();
    }//end applyTheme

    //Update the theme value
    //TODO: Update for new settings menu
    public void updateTheme() {
        //Toggles the isDark value
        isDark = !isDark();
        try {
            //Writes the new theme setting to file
            fileIO.writeOneLineFile(themeSettingsFilePath, isDark ? "dark" : "light");
        } catch (Exception e) {
            displayFileError(new FileException(e));
        }
        //Restart to apply theme
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