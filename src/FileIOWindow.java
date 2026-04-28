import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FileIOWindow implements ActionListener {
    private JFrame window;
    private JTextArea directoryField;
    private JButton openFS,sendDirectory;

    public FileIOWindow() {
        window = new JFrame("File");
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 600);

        openFS = new JButton("Open Files");
        openFS.setFocusable(false);
        openFS.setFont(Tools.mainfont);
        openFS.addActionListener(this);
        openFS.setBounds(25, 25, 400, 30);

        directoryField = new JTextArea("");
        directoryField.setFocusable(true);
        directoryField.setEditable(true);
        directoryField.setFont(Tools.mainfont);
        directoryField.setLineWrap(false);

        sendDirectory = new JButton("Send");
        sendDirectory.setFocusable(false);
        sendDirectory.setFont(Tools.mainfont);
        sendDirectory.addActionListener(this);

        window.add(openFS);
        window.add(directoryField);
        window.add(sendDirectory);
    }

    public void visible() {
        window.setVisible(true);
    }
    public void invisible() {
        window.setVisible(false);
    }

    private void openFS() {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Key Files (*.keys)", "keys");
        chooser.setFileFilter(filter);

        int approved = chooser.showOpenDialog(window);
        if (approved == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            String filePath = selectedFile.getAbsolutePath();
            directoryField.setText(filePath);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == openFS) {
            openFS();
        }
    }
}
