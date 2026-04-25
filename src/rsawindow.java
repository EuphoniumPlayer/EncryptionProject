import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigInteger;

public class rsawindow implements ActionListener {
    protected tools tools = new tools();
    //menu stuff
    private JFrame menu;
    private JButton create, encrypt;
    private final Font menufont = new Font("Arial",Font.BOLD,15);

    //creator stuff
    private final Font mainfont = new Font("Arial",Font.PLAIN,20);
    private final CreateWindow creator = new CreateWindow();

    //encrypter stuff
    EncryptorWindow encryptor = new EncryptorWindow();

    rsawindow () {
        //menu
        menu = new JFrame("Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(500, 200);
        menu.setLayout(null);

        create = new JButton("Create a key");
        create.setFont(menufont);
        create.setBounds(25, 25, 200, 100);
        create.addActionListener(this);
        create.setFocusable(false);

        encrypt = new JButton("Encrypt/Decrypt");
        encrypt.setFont(menufont);
        encrypt.setBounds(250, 25, 200, 100);
        encrypt.addActionListener(this);
        encrypt.setFocusable(false);

        menu.add(create);
        menu.add(encrypt);
        menu.setVisible(true);

        //creator


        //encryption stuff


    }//end constructor

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        rsawindow menu = new rsawindow();
    }//end main

    // actions
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == create) {
            this.invisible();
            creator.visible();
            //window = 1;
        }//end create
        if (event.getSource() == encrypt) {
            this.invisible();
            encryptor.visible();
        }
    }//end actions

    public void visible() {
        menu.setVisible(true);
    }
    public void invisible() {
        menu.setVisible(false);
    }
}//eop