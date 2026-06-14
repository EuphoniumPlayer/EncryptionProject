import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SettingsMenu implements ActionListener {
    private JFrame frame;
    private JButton save, mode, cancel;
    private JTextField bitlength, publicPrefix, privatePrefix;
    private JLabel bitlabel, pubPreLabel, privPreLabel;
    private JTextField[] textFields;

    private static final Command command = new Command();
    private final Tools tools = new Tools();

    public SettingsMenu() {
        frame = new JFrame("Settings");
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textFields = new JTextField[3];

        save = new JButton("Save");
        save.addActionListener(this);
        save.setFont(tools.mainfont);

        bitlabel = new JLabel("Random Prime Bit length:");
        bitlabel.setFont(tools.mainfont);
        bitlabel.setBounds(25,25,400,30);

        bitlength = new JTextField();
        bitlength.setFont(tools.mainfont);
        bitlength.setText(String.valueOf(Command.getBitLength()));
        bitlength.setBounds(25,65,100,30);
        textFields[0] = bitlength;

        pubPreLabel = new JLabel("Key 1 prefix:");
        pubPreLabel.setFont(tools.mainfont);
        pubPreLabel.setFocusable(false);
        pubPreLabel.setBounds(25, 110, 400, 30);

        publicPrefix = new JTextField();
        publicPrefix.setFont(tools.mainfont);
        publicPrefix.setText(Command.getPublicPrefix());
        publicPrefix.setBounds(25, 150, 400, 30);
        textFields[1] = publicPrefix;

        privPreLabel = new JLabel("Key 2 prefix:");
        privPreLabel.setFont(tools.mainfont);
        privPreLabel.setFocusable(false);
        privPreLabel.setBounds(25, 195, 400, 30);

        privatePrefix = new JTextField();
        privatePrefix.setFont(tools.mainfont);
        privatePrefix.setText(Command.getPrivatePrefix());
        privatePrefix.setBounds(25, 235, 400, 30);

//        for (JTextField field : textFields) {
//            field.getDocument().addDocumentListener(new DocumentListener() {
//                @Override
//                public void insertUpdate(DocumentEvent e) {}
//                @Override
//                public void removeUpdate(DocumentEvent e) {}
//                @Override
//                public void changedUpdate(DocumentEvent e) {}
//            });
//        }

        frame.add(bitlabel);
        frame.add(bitlength);
        frame.add(pubPreLabel);
        frame.add(publicPrefix);
        frame.add(privPreLabel);
        frame.add(privatePrefix);
        frame.setVisible(true);
    }//end of constructor


    public JFrame getFrame() {
        return frame;
    }//end of getFrame
    public void setVisible(boolean state) {
        frame.setVisible(state);
    }//end of setVisible
    public void updateValues() {
        bitlength.setText(String.valueOf(Command.getBitLength()));
        publicPrefix.setText(Command.getPublicPrefix());
        privatePrefix.setText(Command.getPrivatePrefix());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
    }//end of actionPerformed
}