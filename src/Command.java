public class Command {
    private static final Menu menu = new Menu();
    private static final CreateWindow create = new CreateWindow();
    private static final EncryptorWindow encryptor = new EncryptorWindow();
    private static final FileIO fileIO = new FileIO();
    private static final FileIOError fileErrorWindow = new FileIOError();

    public static void main(String[] args) {
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
    public void writeFile(int e, int d, int m) throws FileException {
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
