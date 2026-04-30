public class Command {
    private static final Menu menu = new Menu();
    private static final CreateWindow create = new CreateWindow();
    private static final EncryptorWindow encryptor = new EncryptorWindow();
    private static final FileIO fileIO = new FileIO();

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
    public String[] readFile() {
        try {
            return fileIO.readFile();
        } catch (FileException e) {
            throw e;
        }
    }
    public void writeFile(int e, int d, int m) {
        try {
            fileIO.writeFile(e,d,m);
        } catch (FileException error) {
            throw error;
        }
    }
}
