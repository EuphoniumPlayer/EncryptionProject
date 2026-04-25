public class Command {
    private static final Menu menu = new Menu();
    private static final CreateWindow create = new CreateWindow();
    private static final EncryptorWindow encryptor = new EncryptorWindow();

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
}
