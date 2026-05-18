import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu implements ActionListener {
    //menu stuff
    private JFrame menu;
    private JButton create, encrypt, bgmode;
    private final Font menufont = new Font("Arial",Font.BOLD,15);

    //Command caller
    private static final Command command = new Command();

    Menu() {
        //menu
        menu = new JFrame("Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(500, 225);
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

        bgmode = new JButton();
        setBGButton();
        bgmode.setFont(menufont);
        bgmode.addActionListener(this);
        bgmode.setFocusable(false);
        bgmode.setBounds(360,150,125,35);

        menu.add(create);
        menu.add(encrypt);
        menu.add(bgmode);
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
        if (event.getSource() == bgmode) {
            command.updateTheme();
        }
    }//end actions

    public void visible() {
        menu.setVisible(true);
    }
    public void invisible() {
        menu.setVisible(false);
    }

    private void setBGButton() {
        if (command.isDark()) {
            bgmode.setText("Light Mode");
        } else {
            bgmode.setText("Dark Mode");
        }
    }

    public void updateBGButton() {
        setBGButton();
    }

    public JFrame getMenuFrame() {
        return this.menu;
    }

}//eop