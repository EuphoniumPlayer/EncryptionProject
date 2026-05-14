import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreateWindow implements ActionListener {
    private JFrame creator;
    private JLabel plabel, qlabel, elabel, invalidplabel, invalidqlabel, invalidelabel, publicoutlabel, privateoutlabel, invalidcreate;
    private JTextField pfield, qfield, efield, publicout, privateout;
    private JButton createkey, savekeys, back;
    private Font mainfont = new Font("Arial",Font.PLAIN,20);
    private int p,q,e;
    private BigInteger pbigint, qbigint, ebigint, dbigint, n, tn;
    private JTextField[] fields = new JTextField[3];

    private Tools tools = new Tools();

    //Command caller
    private static final Command command = new Command();

    public CreateWindow() {
        plabel = new JLabel("Enter a prime number");
        plabel.setFocusable(false);
        plabel.setFont(mainfont);
        plabel.setBounds(25, 25, 400, 20);

        pfield = new JTextField();
        pfield.setFont(mainfont);
        pfield.setEditable(true);
        pfield.addActionListener(this);
        pfield.setBounds(25, 60, 400, 30);
        fields[0] = pfield;

        invalidplabel = new JLabel("No input");
        invalidplabel.setFont(mainfont);
        invalidplabel.setFocusable(false);
        invalidplabel.setBounds(25, 105, 200, 20);

        qlabel = new JLabel("Enter a second prime number");
        qlabel.setFont(mainfont);
        qlabel.setFocusable(false);
        qlabel.setBounds(25, 150, 400, 20);

        qfield = new JTextField();
        qfield.setEditable(true);
        qfield.addActionListener(this);
        qfield.setBounds(25, 185, 400, 30);
        fields[1] = qfield;

        invalidqlabel = new JLabel("No input");
        invalidqlabel.setFocusable(false);
        invalidqlabel.setFont(mainfont);
        invalidqlabel.setBounds(25, 230, 200, 20);

        elabel = new JLabel("Enter a public encryption number");
        elabel.setFont(mainfont);
        elabel.setFocusable(false);
        elabel.setBounds(25, 275, 400, 20);

        efield = new JTextField();
        efield.setEditable(true);
        efield.addActionListener(this);
        efield.setBounds(25, 310, 400, 30);
        fields[2] = efield;

        invalidelabel = new JLabel("No inputs");
        invalidelabel.setFocusable(false);
        invalidelabel.setFont(mainfont);
        invalidelabel.setBounds(25, 355, 300, 20);

        createkey = new JButton("Create Keys");
        createkey.setFocusable(false);
        createkey.setFont(mainfont);
        createkey.setEnabled(false);
        createkey.addActionListener(this);
        createkey.setBounds(25, 400, 400, 40);

        publicoutlabel = new JLabel("Public key pair");
        publicoutlabel.setFocusable(false);
        publicoutlabel.setFont(mainfont);
        publicoutlabel.setBounds(25, 465, 150, 20);

        publicout = new JTextField("");
        publicout.setFocusable(true);
        publicout.setFont(mainfont);
        publicout.setEditable(false);
        publicout.setBounds(25, 500, 400, 30);

        privateoutlabel = new JLabel("Private key pair");
        privateoutlabel.setFocusable(false);
        privateoutlabel.setFont(mainfont);
        privateoutlabel.setBounds(25, 555, 400, 20);

        privateout = new JTextField();
        privateout.setEditable(false);
        privateout.setFocusable(true);
        privateout.setFont(mainfont);
        privateout.setBounds(25, 590, 400, 30);

        savekeys = new JButton("Save Keys to File");
        savekeys.setFont(mainfont);
        savekeys.setFocusable(false);
        savekeys.addActionListener(this);
        savekeys.setBounds(25, 645, 400, 40);
        savekeys.setEnabled(false);

        back = new JButton("Back");
        back.setFont(mainfont);
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(25, 700, 400, 40);

        creator = new JFrame("Create a key");
        creator.setSize(500, 800);
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
        creator.add(savekeys);
        creator.add(back);

        for (int i=0;i<3;i++) {
            final int number = i;
            fields[i].setFont(mainfont);
            fields[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {creatorFieldUpdate(number);}
                @Override
                public void removeUpdate(DocumentEvent e) {creatorFieldUpdate(number);}
                @Override
                public void insertUpdate(DocumentEvent e) {creatorFieldUpdate(number);}
            });
        }
    }//end constructor

    public void visible() {
        creator.setVisible(true);
    }
    public void invisible() {
        creator.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
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
            savekeys.setEnabled(true);
        }//end createkey
        if (event.getSource() == back) {
            invisible();
            command.setMenuVisible(true);
        }//end back
        if (event.getSource() == savekeys) {
            try {
                String es,ds,ms;
                es = ebigint.toString();
                ds = dbigint.toString();
                ms = n.toString();
                int e,d,m;
                e = Integer.parseInt(es);
                d = Integer.parseInt(ds);
                m = Integer.parseInt(ms);
                command.writeFile(e,d,m);
            } catch (FileException error) {
                if (!error.getMessage().equals("ignore")) {
                    command.displayFileError(error);
                }
            }
        }//end savekeys
    }//end actionPerformed

    private void creatorFieldUpdate(int field) {
        createkey.setEnabled(false);
        if (field == 0) {
            pfield.postActionEvent();
        } else if (field == 1) {
            qfield.postActionEvent();
        }
        efield.postActionEvent();
        creator.repaint();
    }//end creator updates
}
