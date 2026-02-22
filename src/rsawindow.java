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
    JButton createkey;
    Font creatorfont = new Font("Arial",Font.PLAIN,20);
    int p,q,e;
    BigInteger pbigint, qbigint, ebigint, dbigint, n, tn;
    JTextField[] fields = new JTextField[3];

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
        plabel.setFont(creatorfont);
        plabel.setBounds(25, 25, 400, 20);

        pfield = new JTextField();
        pfield.setFont(creatorfont);
        pfield.setEditable(true);
        pfield.addActionListener(this);
        pfield.setBounds(25, 60, 400, 30);
        fields[0] = pfield;

        invalidplabel = new JLabel("No input");
        invalidplabel.setFont(creatorfont);
        invalidplabel.setFocusable(false);
        invalidplabel.setBounds(25, 105, 200, 20);

        qlabel = new JLabel("Enter a second prime number");
        qlabel.setFont(creatorfont);
        qlabel.setFocusable(false);
        qlabel.setBounds(25, 150, 400, 20);

        qfield = new JTextField();
        qfield.setEditable(true);
        qfield.addActionListener(this);
        qfield.setBounds(25, 185, 400, 30);
        fields[1] = qfield;

        invalidqlabel = new JLabel("No input");
        invalidqlabel.setFocusable(false);
        invalidqlabel.setFont(creatorfont);
        invalidqlabel.setBounds(25, 230, 200, 20);

        elabel = new JLabel("Enter a public encryption number");
        elabel.setFont(creatorfont);
        elabel.setFocusable(false);
        elabel.setBounds(25, 275, 400, 20);

        efield = new JTextField();
        efield.setEditable(true);
        efield.addActionListener(this);
        efield.setBounds(25, 310, 400, 30);
        fields[2] = efield;

        invalidelabel = new JLabel("No inputs");
        invalidelabel.setFocusable(false);
        invalidelabel.setFont(creatorfont);
        invalidelabel.setBounds(25, 355, 300, 20);

        createkey = new JButton("Create Keys");
        createkey.setFocusable(false);
        createkey.setFont(creatorfont);
        createkey.setEnabled(false);
        createkey.addActionListener(this);
        createkey.setBounds(25, 400, 400, 40);

        publicoutlabel = new JLabel("Public key pair");
        publicoutlabel.setFocusable(false);
        publicoutlabel.setFont(creatorfont);
        publicoutlabel.setBounds(25, 465, 150, 20);

        publicout = new JTextField("");
        publicout.setFocusable(true);
        publicout.setFont(creatorfont);
        publicout.setEditable(false);
        publicout.setBounds(25, 500, 400, 30);

        privateoutlabel = new JLabel("Private key pair");
        privateoutlabel.setFocusable(false);
        privateoutlabel.setFont(creatorfont);
        privateoutlabel.setBounds(25, 555, 400, 20);

        privateout = new JTextField();
        privateout.setEditable(false);
        privateout.setFocusable(true);
        privateout.setFont(creatorfont);
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
            fields[i].setFont(creatorfont);
            fields[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {fieldUpdate(number);}
                @Override
                public void removeUpdate(DocumentEvent e) {fieldUpdate(number);}
                @Override
                public void insertUpdate(DocumentEvent e) {fieldUpdate(number);}
            });
        }
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
    }//end actions

    public void fieldUpdate(int field) {
        createkey.setEnabled(false);
        if (field == 0) {
            pfield.postActionEvent();
        } else if (field == 1) {
            qfield.postActionEvent();
        }
        efield.postActionEvent();
        creator.repaint();
    }

}//eop