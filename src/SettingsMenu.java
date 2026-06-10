import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SettingsMenu implements ActionListener {
    private JFrame frame;
    private JButton save, mode, cancel;
    private JTextField bitlength;
    private JLabel bitlabel;

    private static final Command command = new Command();
    private final Tools tools = new Tools();

    public SettingsMenu() {
        frame = new JFrame("Settings");
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save = new JButton("Save");
        save.addActionListener(this);
        save.setFont(tools.mainfont);

        bitlabel = new JLabel("Random Prime Bit length:");
        bitlabel.setFont(tools.mainfont);
        bitlabel.setBounds(25,25,400,30);

        bitlength = new JTextField();
        bitlength.setFont(tools.mainfont);
        bitlength.setEditable(true);
        bitlength.setText(String.valueOf(command.getBitLength()));
        bitlength.setBounds(25,65,100,30);

        frame.add(bitlabel);
        frame.add(bitlength);
        frame.setVisible(true);
    }//end of constructor


    public JFrame getFrame() {
        return frame;
    }//end of getFrame
    public void setVisible(boolean state) {
        frame.setVisible(state);
    }//end of setVisible

    @Override
    public void actionPerformed(ActionEvent event) {
        
    }//end of actionPerformed
}