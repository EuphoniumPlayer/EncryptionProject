import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu implements ActionListener {
    //menu stuff
    private JFrame menu;
    private JButton create, encrypt;
    private final Font menufont = new Font("Arial",Font.BOLD,15);

    //Command caller
    private static final Command command = new Command();

    Menu() {
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

    }//end constructor

    // actions
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == create) {
            this.invisible();
            command.setCreateVisible(true);
        }//end create button
        if (event.getSource() == encrypt) {
            this.invisible();
            command.setEncryptVisible(true);
        }//end encrypt button
    }//end actions

    public void visible() {
        menu.setVisible(true);
    }
    public void invisible() {
        menu.setVisible(false);
    }
}//eop