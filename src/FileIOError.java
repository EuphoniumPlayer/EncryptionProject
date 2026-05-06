import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FileIOError implements ActionListener {
    private JFrame errorWindow;
    private JButton close,retry;
    private JLabel errorOccuredLabel,error;
    private String exceptionText="";

    private static final Font font = new Font("Arial",Font.PLAIN,15);
    private static final Font bold = new Font("Arial",Font.BOLD,15);

    private static Command command = new Command();

    public FileIOError() {
        errorWindow = new JFrame("Error");
        errorWindow.setLayout(null);
        errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorWindow.setSize(500,255);

        close = new JButton("Close");
        close.setFont(bold);
        close.setFocusable(false);
        close.addActionListener(this);
        close.setBounds(390,175,85,30);

        retry = new JButton("Retry");
        retry.setFont(bold);
        retry.setFocusable(false);
        retry.addActionListener(this);
        retry.setBounds(300,175,85,30);

        errorOccuredLabel = new JLabel("An error occurred:");
        errorOccuredLabel.setFont(font);
        errorOccuredLabel.setFocusable(true);
        errorOccuredLabel.setBounds(20, 20, 400, 10);

        error = new JLabel("");
        error.setFont(font);
        error.setFocusable(true);
        setErrorLabel();
        error.setBounds(20,35,400,60);

        errorWindow.add(close);
        //errorWindow.add(retry);
        errorWindow.add(errorOccuredLabel);
        errorWindow.add(error);
        errorWindow.setVisible(false);
    }//end of Constructor

    private void setErrorLabel() {
        if (this.exceptionText.isEmpty()) {
            this.error.setText("Unknown error");
        } else {
            this.error.setText(this.exceptionText);
        }
    }//end setErrorLabel

    public void displayError(String error) {
        this.exceptionText = error;
        setErrorLabel();
        this.errorWindow.setVisible(true);
    }

    public void displayError(FileException e) {
        this.exceptionText = e.getMessage();
        setErrorLabel();
        this.errorWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == close) {
            this.exceptionText = "";
            this.errorWindow.setVisible(false);
            this.error.setText("");
        }
    }
}