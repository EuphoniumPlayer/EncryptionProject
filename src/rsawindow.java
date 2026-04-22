import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigInteger;

public class rsawindow implements ActionListener {
    tools tools = new tools();
    //menu stuff
    JFrame menu;
    JButton create, encrypt;
    Font menufont = new Font("Arial",Font.BOLD,15);

    //creator stuff
    JFrame creator;
    JLabel plabel, qlabel, elabel, invalidplabel, invalidqlabel, invalidelabel, publicoutlabel, privateoutlabel, invalidcreate;
    JTextField pfield, qfield, efield, publicout, privateout;
    JButton createkey, savekeys;
    Font font = new Font("Arial",Font.PLAIN,20);
    int p,q,e;
    BigInteger pbigint, qbigint, ebigint, dbigint, n, tn;
    JTextField[] fields = new JTextField[3];

    //encrypter stuff
    JFrame encryptor;
    JButton run, encryption, decryption;
    JLabel keylabel, moduluslabel, messagelabel, outputlabel, modelabel;
    JTextField keyfield, modulusfield;
    JTextArea messagefield, output;
    int key, modulus;
    BigInteger bigkey, bigmod;
    JTextField[] encryptfields = new JTextField[2];
    JTextArea[] encryptareas = new JTextArea[2];
    int mode;

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
        plabel = new JLabel("Enter a prime number");
        plabel.setFocusable(false);
        plabel.setFont(font);
        plabel.setBounds(25, 25, 400, 20);

        pfield = new JTextField();
        pfield.setFont(font);
        pfield.setEditable(true);
        pfield.addActionListener(this);
        pfield.setBounds(25, 60, 400, 30);
        fields[0] = pfield;

        invalidplabel = new JLabel("No input");
        invalidplabel.setFont(font);
        invalidplabel.setFocusable(false);
        invalidplabel.setBounds(25, 105, 200, 20);

        qlabel = new JLabel("Enter a second prime number");
        qlabel.setFont(font);
        qlabel.setFocusable(false);
        qlabel.setBounds(25, 150, 400, 20);

        qfield = new JTextField();
        qfield.setEditable(true);
        qfield.addActionListener(this);
        qfield.setBounds(25, 185, 400, 30);
        fields[1] = qfield;

        invalidqlabel = new JLabel("No input");
        invalidqlabel.setFocusable(false);
        invalidqlabel.setFont(font);
        invalidqlabel.setBounds(25, 230, 200, 20);

        elabel = new JLabel("Enter a public encryption number");
        elabel.setFont(font);
        elabel.setFocusable(false);
        elabel.setBounds(25, 275, 400, 20);

        efield = new JTextField();
        efield.setEditable(true);
        efield.addActionListener(this);
        efield.setBounds(25, 310, 400, 30);
        fields[2] = efield;

        invalidelabel = new JLabel("No inputs");
        invalidelabel.setFocusable(false);
        invalidelabel.setFont(font);
        invalidelabel.setBounds(25, 355, 300, 20);

        createkey = new JButton("Create Keys");
        createkey.setFocusable(false);
        createkey.setFont(font);
        createkey.setEnabled(false);
        createkey.addActionListener(this);
        createkey.setBounds(25, 400, 400, 40);

        publicoutlabel = new JLabel("Public key pair");
        publicoutlabel.setFocusable(false);
        publicoutlabel.setFont(font);
        publicoutlabel.setBounds(25, 465, 150, 20);

        publicout = new JTextField("");
        publicout.setFocusable(true);
        publicout.setFont(font);
        publicout.setEditable(false);
        publicout.setBounds(25, 500, 400, 30);

        privateoutlabel = new JLabel("Private key pair");
        privateoutlabel.setFocusable(false);
        privateoutlabel.setFont(font);
        privateoutlabel.setBounds(25, 555, 400, 20);

        privateout = new JTextField();
        privateout.setEditable(false);
        privateout.setFocusable(true);
        privateout.setFont(font);
        privateout.setBounds(25, 590, 400, 30);

        creator = new JFrame("Create a key");
        creator.setSize(500, 700);
        creator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creator.setLayout(null);

        creator.add(plabel);
        creator.add(pfield);
        creator.add(invalidplabel);
        creator.add(qlabel);
        creator.add(qfield);
        creator.add(invalidqlabel);
        creator.add(elabel);
        creator.add(efield);
        creator.add(invalidelabel);
        creator.add(createkey);
        creator.add(publicoutlabel);
        creator.add(publicout);
        creator.add(privateoutlabel);
        creator.add(privateout);

        for (int i=0;i<3;i++) {
            final int number = i;
            fields[i].setFont(font);
            fields[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {creatorFieldUpdate(number);}
                @Override
                public void removeUpdate(DocumentEvent e) {creatorFieldUpdate(number);}
                @Override
                public void insertUpdate(DocumentEvent e) {creatorFieldUpdate(number);}
            });
        }

        //encryption stuff
        keylabel = new JLabel("Key");
        keylabel.setFont(font);
        keylabel.setFocusable(false);
        keylabel.setBounds(25, 25, 400, 20);

        keyfield = new JTextField();
        keyfield.setFont(font);
        keyfield.setEditable(true);
        keyfield.setBounds(25, 60, 400, 30);
        encryptfields[0] = keyfield;

        moduluslabel = new JLabel("Modulus");
        moduluslabel.setFont(font);
        moduluslabel.setFocusable(false);
        moduluslabel.setBounds(25, 115, 400, 20);

        modulusfield = new JTextField();
        modulusfield.setFont(font);
        modulusfield.setEditable(true);
        modulusfield.setBounds(25, 150, 400, 30);
        encryptfields[1] = modulusfield;

        messagelabel = new JLabel("Message");
        messagelabel.setFont(font);
        messagelabel.setFocusable(false);
        messagelabel.setBounds(25, 205, 400, 20);

        messagefield = new JTextArea();
        messagefield.setFont(font);
        messagefield.setEditable(true);
        messagefield.setLineWrap(true);
        messagefield.setBounds(25, 240, 400, 90);
        encryptareas[0] = messagefield;

        encryption = new JButton("Encrypt");
        encryption.setFont(font);
        encryption.setFocusable(false);
        encryption.addActionListener(this);
        encryption.setBounds(25, 360, 200, 40);

        decryption = new JButton("Decrypt");
        decryption.setFont(font);
        decryption.setFocusable(false);
        decryption.addActionListener(this);
        decryption.setBounds(225, 360, 200, 40);

        run = new JButton("Run");
        run.setFont(font);
        run.setFocusable(false);
        run.setEnabled(false);
        run.addActionListener(this);
        run.setToolTipText("Blank fields");
        run.setBounds(25, 400, 400, 40);

        modelabel = new JLabel("Select a mode");
        modelabel.setHorizontalAlignment(SwingConstants.CENTER);
        modelabel.setFont(font);
        modelabel.setFocusable(false);
        modelabel.setBounds(25, 450, 400, 20);

        outputlabel = new JLabel("Output");
        outputlabel.setFont(font);
        outputlabel.setFocusable(false);
        outputlabel.setBounds(25, 500, 400, 20);

        output = new JTextArea();
        output.setFont(font);
        output.setFocusable(true);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setBounds(25, 535, 400, 90);
        encryptareas[1] = output;

        encryptor = new JFrame("Process a message");
        encryptor.setSize(500, 750);
        encryptor.setLayout(null);
        encryptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        encryptor.add(keylabel);
        encryptor.add(keyfield);
        encryptor.add(moduluslabel);
        encryptor.add(modulusfield);
        encryptor.add(messagelabel);
        encryptor.add(messagefield);
        encryptor.add(encryption);
        encryptor.add(decryption);
        encryptor.add(run);
        encryptor.add(modelabel);
        encryptor.add(outputlabel);
        encryptor.add(output);

//        back.setBounds(25,655,400,30);
//        encryptor.add(back);

        for (int i=0;i<2;i++) {
            encryptfields[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {encryptorUpdate();}
                @Override
                public void removeUpdate(DocumentEvent e) {encryptorUpdate();}
                @Override
                public void insertUpdate(DocumentEvent e) {encryptorUpdate();}
            });
        }

        messagefield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {encryptorUpdate();}
            @Override
            public void removeUpdate(DocumentEvent e) {encryptorUpdate();}
            @Override
            public void insertUpdate(DocumentEvent e) {encryptorUpdate();}
        });
    }//end constructor

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        rsawindow rsawindow = new rsawindow();
    }//end main

    // actions
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == create) {
            menu.setVisible(false);
            creator.setVisible(true);
            //window = 1;
        }//end create
        if (event.getSource() == pfield) {
            try {
                p = Integer.parseInt(pfield.getText());
                pbigint = new BigInteger(String.valueOf(p));
                if (!tools.isprime(pbigint)) {
                    invalidplabel.setText("Not prime");
                    creator.repaint();
                } else {
                    invalidplabel.setText("Valid");
                    creator.repaint();
                }
            } catch (NumberFormatException error) {
                if (pfield.getText().equals("")) {
                    invalidplabel.setText("No input");
                } else {
                    invalidplabel.setText("Invalid input");
                    creator.repaint();
                }
            }//end try
        }//end checkp
        if (event.getSource() == qfield) {
            try {
                q = Integer.parseInt(qfield.getText());
                qbigint = new BigInteger(String.valueOf(q));
                if (!tools.isprime(qbigint)) {
                    invalidqlabel.setText("Not prime");
                    creator.repaint();
                } else {
                    invalidqlabel.setText("Valid");
                    creator.repaint();
                }
            } catch (NumberFormatException error) {
                if (qfield.getText().equals("")) {
                    invalidqlabel.setText("No input");
                } else {
                    invalidqlabel.setText("Invalid input");
                    creator.repaint();
                }
            }//end try
        }//end checkq
        if (event.getSource() == efield) {
            boolean inputsvalid = false;
            if (pfield.getText().equals("") || qfield.getText().equals("")) {
                invalidelabel.setText("Enter two prime numbers");
                creator.repaint();
            } else {
                try {
                    p = Integer.parseInt(pfield.getText());
                    pbigint = new BigInteger(String.valueOf(p));
                    q = Integer.parseInt(qfield.getText());
                    qbigint = new BigInteger(String.valueOf(q));
                    e = Integer.parseInt(efield.getText());
                    ebigint = new BigInteger(String.valueOf(e));
                    inputsvalid = true;
                } catch (NumberFormatException error) {
                    if (efield.getText().equals("")) {
                        invalidelabel.setText("No input");
                    } else {
                        invalidelabel.setText("Invalid input");
                        creator.repaint();
                    }
                    inputsvalid = false;
                }
                if (inputsvalid) {
                    if (!tools.isprime(pbigint) || !tools.isprime(qbigint)) {
                        invalidelabel.setText("Error: Primes not prime");
                        creator.repaint();
                    } else {
                        n = pbigint.multiply(qbigint);
                        tn = (pbigint.subtract(BigInteger.ONE).multiply(qbigint.subtract(BigInteger.ONE)));
                        if (!tools.isgcd1(ebigint, tn) || ebigint.equals(BigInteger.ONE)) {
                            invalidelabel.setText("Not compatible");
                            creator.repaint();
                        } else {
                            invalidelabel.setText("Compatible");
                            createkey.setEnabled(true);
                            creator.repaint();
                        }//end isgcd1 check
                    }//end prime check
                }//end valid function
            }//end input check
        }//end checke
        if (event.getSource() == createkey) {
            dbigint = ebigint.modInverse(tn);
            publicout.setText("("+ebigint.toString()+", "+n.toString()+")");
            privateout.setText("("+dbigint.toString()+", "+n.toString()+")");
        }//end createkey
        if (event.getSource() == encrypt) {
            menu.setVisible(false);
            encryptor.setVisible(true);
        }// end encrypt
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
            encrypt.repaint();
        }
        if (event.getSource() == run) {
//            //TODO: update run
//            BigInteger key, mod;
//            String message;
//            key = new BigInteger(String.valueOf(keyfield.getText()));
//            mod = new BigInteger(String.valueOf(modulusfield.getText()));
//            message = messagefield.getText();
//            output.setText(tools.process(message, key, mod));
            BigInteger key, mod;
            String input;
            key = new BigInteger(String.valueOf(keyfield.getText()));
            mod = new BigInteger(String.valueOf(modulusfield.getText()));
            input = messagefield.getText();
            if (mode == 1) {
                //encrypt
                output.setText(tools.encrypt(key, mod, input));
            } else if (mode == 2) {
                //decrypt
                output.setText(tools.decrypt(key, mod, input));
            }
        }
    }//end actions

    public void creatorFieldUpdate(int field) {
        createkey.setEnabled(false);
        if (field == 0) {
            pfield.postActionEvent();
        } else if (field == 1) {
            qfield.postActionEvent();
        }
        efield.postActionEvent();
        creator.repaint();
    }//end creator updates

    public void encryptorUpdate() {
        int key, mod;
        BigInteger bigkey, bigmod;
        if (keyfield.getText().equals("") || modulusfield.getText().equals("") || messagefield.getText().equals("")) {
            run.setEnabled(false);
            run.setToolTipText("Blank field(s)");
            encryptor.repaint();
        } else {
            if (mode == 1 || mode == 2) {
                try {
                    key = Integer.parseInt(keyfield.getText());
                    bigkey = new BigInteger(String.valueOf(key));
                    mod = Integer.parseInt(modulusfield.getText());
                    bigmod = new BigInteger(String.valueOf(mod));
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
}//eop