import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EncryptorWindow implements ActionListener {
    private JFrame encryptor;
    private JButton run, encryption, decryption, back, loadFromFile, loadMessage, saveMessage, clearin, clearout, clearkey, clearmod;
    private JLabel keylabel, moduluslabel, messagelabel, outputlabel, modelabel;
    private JTextField keyfield, modulusfield;
    private JTextArea messagefield, output;
    private JTextField[] encryptfields = new JTextField[2];
    private JTextArea[] encryptareas = new JTextArea[2];
    private int mode;
    private JScrollPane inscroll, outscroll;

    private final Font mainfont = new Font("Arial", Font.PLAIN, 20);
    private final Tools tools = new Tools();
    private static final Command command = new Command();

    public EncryptorWindow() {
        keylabel = new JLabel("Key");
        keylabel.setFont(mainfont);
        keylabel.setFocusable(false);
        keylabel.setBounds(25, 25, 400, 25);

        keyfield = new JTextField();
        keyfield.setFont(mainfont);
        keyfield.setEditable(true);
        keyfield.setBounds(25, 60, 400, 30);
        encryptfields[0] = keyfield;

        moduluslabel = new JLabel("Modulus");
        moduluslabel.setFont(mainfont);
        moduluslabel.setFocusable(false);
        moduluslabel.setBounds(25, 115, 400, 25);

        modulusfield = new JTextField();
        modulusfield.setFont(mainfont);
        modulusfield.setEditable(true);
        modulusfield.setBounds(25, 150, 400, 30);
        encryptfields[1] = modulusfield;

        messagelabel = new JLabel("Message");
        messagelabel.setFont(mainfont);
        messagelabel.setFocusable(false);
        messagelabel.setBounds(25, 205, 400, 25);

        messagefield = new JTextArea();
        messagefield.setFont(mainfont);
        messagefield.setEditable(true);
        messagefield.setLineWrap(true);
        //messagefield.setBounds(25, 240, 400, 90);
        encryptareas[0] = messagefield;

        inscroll = new JScrollPane(messagefield);
        inscroll.setBounds(25, 240, 400, 90);

        loadMessage = new JButton("Load");
        loadMessage.setFont(mainfont);
        loadMessage.setFocusable(false);
        loadMessage.addActionListener(this);
        loadMessage.setBounds(267, 205, 80, 35);

        clearin = new JButton("Clear");
        clearin.setFont(mainfont);
        clearin.setFocusable(false);
        clearin.addActionListener(this);
        clearin.setBounds(345, 205, 80, 35);

        encryption = new JButton("Encrypt");
        encryption.setFont(mainfont);
        encryption.setFocusable(false);
        encryption.addActionListener(this);
        encryption.setBounds(25, 360, 200, 40);

        decryption = new JButton("Decrypt");
        decryption.setFont(mainfont);
        decryption.setFocusable(false);
        decryption.addActionListener(this);
        decryption.setBounds(225, 360, 200, 40);

        run = new JButton("Run");
        run.setFont(mainfont);
        run.setFocusable(false);
        run.setEnabled(false);
        run.addActionListener(this);
        run.setToolTipText("Blank fields");
        run.setBounds(25, 400, 400, 40);

        modelabel = new JLabel("Select a mode");
        modelabel.setHorizontalAlignment(SwingConstants.CENTER);
        modelabel.setFont(mainfont);
        modelabel.setFocusable(false);
        modelabel.setBounds(25, 450, 400, 20);

        outputlabel = new JLabel("Output");
        outputlabel.setFont(mainfont);
        outputlabel.setFocusable(false);
        outputlabel.setBounds(25, 500, 400, 25);

        saveMessage = new JButton("Save");
        saveMessage.setFont(mainfont);
        saveMessage.setFocusable(false);
        saveMessage.addActionListener(this);
        saveMessage.setEnabled(false);
        saveMessage.setToolTipText("There's nothing to save!");
        saveMessage.setBounds(267, 500, 80, 35);

        clearout = new JButton("Clear");
        clearout.setFont(mainfont);
        clearout.setFocusable(false);
        clearout.addActionListener(this);
        clearout.setBounds(345, 500, 80, 35);

        output = new JTextArea();
        output.setFont(mainfont);
        output.setFocusable(true);
        output.setEditable(false);
        output.setLineWrap(true);
        //output.setBounds(25, 535, 400, 90);
        encryptareas[1] = output;

        outscroll = new JScrollPane(output);
        outscroll.setBounds(25, 535, 400, 90);

        back = new JButton("Back");
        back.setFont(mainfont);
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(25, 705, 400, 40);

        loadFromFile = new JButton("Load Key File");
        loadFromFile.setFont(mainfont);
        loadFromFile.setFocusable(false);
        loadFromFile.addActionListener(this);
        loadFromFile.setBounds(25, 650, 400, 40);

        encryptor = new JFrame("Process a message");
        encryptor.setSize(500, 800);
        encryptor.setLayout(null);
        encryptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        encryptor.add(keylabel);
        encryptor.add(keyfield);
        encryptor.add(moduluslabel);
        encryptor.add(modulusfield);
        encryptor.add(messagelabel);
        encryptor.add(loadMessage);
        encryptor.add(clearin);
        encryptor.add(inscroll);
        encryptor.add(encryption);
        encryptor.add(decryption);
        encryptor.add(run);
        encryptor.add(modelabel);
        encryptor.add(outputlabel);
        encryptor.add(saveMessage);
        encryptor.add(clearout);
        encryptor.add(outscroll);
        encryptor.add(back);
        encryptor.add(loadFromFile);

//        back.setBounds(25,655,400,30);
//        encryptor.add(back);

        for (int i = 0; i < 2; i++) {
            encryptfields[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    encryptorUpdate();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    encryptorUpdate();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    encryptorUpdate();
                }
            });
        }

        messagefield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                encryptorUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                encryptorUpdate();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                encryptorUpdate();
            }
        });

        output.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                outputUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                outputUpdate();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                outputUpdate();
            }
        });
    }

    public void visible() {
        encryptor.setVisible(true);
    }

    public void invisible() {
        encryptor.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == encryption) {
            mode = 1;
            encryption.setEnabled(false);
            decryption.setEnabled(true);
            modelabel.setText("Encrypt");
            encryptorUpdate();
            encryptor.repaint();
        }
        if (event.getSource() == decryption) {
            mode = 2;
            decryption.setEnabled(false);
            encryption.setEnabled(true);
            modelabel.setText("Decrypt");
            encryptorUpdate();
            encryptor.repaint();
        }
        if (event.getSource() == run) {
            BigInteger key, mod;
            String input;
            key = new BigInteger(keyfield.getText());
            mod = new BigInteger(modulusfield.getText());
            input = messagefield.getText();
            if (mode == 1) {
                //encrypt
                output.setText(tools.encrypt(key, mod, input));
            } else if (mode == 2) {
                //decrypt
                output.setText(tools.decrypt(key, mod, input));
            }
            saveMessage.setEnabled(true);
            saveMessage.setToolTipText("");
        }
        if (event.getSource() == back) {
            invisible();
            command.setMenuVisible(true);
        }
        if (event.getSource() == loadFromFile) {
            try {
                String[] keys = new String[2];
                keys = command.readKeyFile();
                keyfield.setText(keys[0]);
                modulusfield.setText(keys[1]);
            } catch (FileException error) {
                if (!error.getMessage().equals("ignore")) {
                    command.displayFileError(error);
                }
            }
        }//end loadFromFile
        if (event.getSource() == loadMessage) {
            try {
                String text = command.readOneLine(null, "Encrypted Message File (*.emf)", "emf");
                messagefield.setText(text);
            } catch (FileException e) {
                command.displayFileError(e);
            }
        }
        if (event.getSource() == clearin) {
            messagefield.setText("");
        }
        if (event.getSource() == saveMessage) {
            try {
                String text = output.getText();
                command.writeOneLineFile(null, text, "Encrypted Message File (*.emf)", "emf");
            } catch (FileException e) {
                command.displayFileError(e);
            }
        }
        if (event.getSource() == clearout) {
            output.setText("");
            saveMessage.setEnabled(false);
            saveMessage.setToolTipText("There's nothing to save!");
        }
    }//end actionPerformed

    private void encryptorUpdate() {
        if (keyfield.getText().isEmpty() || modulusfield.getText().isEmpty() || messagefield.getText().isEmpty()) {
            run.setEnabled(false);
            run.setToolTipText("Blank field(s)");
            encryptor.repaint();
        } else {
            if (mode == 1 || mode == 2) {
                if (mode == 2 && !messagefield.getText().matches("[0-9,\\s]+")) {
                    run.setEnabled(false);
                    run.setToolTipText("Invalid decrypt input");
                    return;
                }
                try {
                    new BigInteger(keyfield.getText());
                    new BigInteger(modulusfield.getText());
                    run.setEnabled(true);
                    run.setToolTipText(null);
                    encryptor.repaint();
                } catch (NumberFormatException error) {
                    run.setEnabled(false);
                    run.setToolTipText("Invalid input(s)");
                    encryptor.repaint();
                }
            }
        }
    }//end encryptor updates

    private void outputUpdate() {
        if (output.getText().isEmpty()) {
            saveMessage.setEnabled(false);
            saveMessage.setToolTipText("There's nothing to save!");
        } else {
            saveMessage.setEnabled(true);
            saveMessage.setToolTipText(null);
        }
    }

    public JFrame getFrame() {
        return this.encryptor;
    }
}