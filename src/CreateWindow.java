import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.image.BufferedImage;

public class CreateWindow implements ActionListener {
    private JFrame creator;
    private JLabel plabel, qlabel, elabel, invalidplabel, invalidqlabel, invalidelabel, publicoutlabel, privateoutlabel, invalidcreate;
    private JTextField pfield, qfield, efield, publicout, privateout;
    private JButton createkey, savekeys, back, random;
    private Font mainfont = new Font("Arial",Font.PLAIN,20);
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
        createkey.setBounds(25, 400, 350, 40);

        random = new JButton(createDieIcon());
        random.setFocusable(false);
        random.addActionListener(this);
        random.setBounds(385,400,40,40);

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
        creator.add(random);
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
                pbigint = new BigInteger(pfield.getText());
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
                qbigint = new BigInteger(qfield.getText());
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
                    pbigint = new BigInteger(pfield.getText());
                    qbigint = new BigInteger(qfield.getText());
                    ebigint = new BigInteger(efield.getText());
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
                command.writeKeyFile(ebigint,dbigint,n);
            } catch (FileException error) {
                if (!error.getMessage().equals("ignore")) {
                    command.displayFileError(error);
                }
            }
        }//end savekeys

        if (event.getSource() == random) {
            this.pbigint = tools.randomPrime(512);
            this.pfield.setText(this.pbigint.toString());

            this.qbigint = tools.randomPrime(512);
            this.qfield.setText(this.qbigint.toString());

            this.ebigint = BigInteger.valueOf(65537);
            this.efield.setText(this.ebigint.toString());
        }
    }//end actionPerformed

    private ImageIcon createDieIcon() {
        BufferedImage img = new BufferedImage(24,24,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Border
        g.setColor(Color.BLACK);
        g.fillRect(0,0,24,24);

        //Background
        g.setColor(Color.WHITE);
        g.fillRect(1,1,22,22);

        //Five dot pattern
        g.setColor(Color.BLACK);
        g.fillRect(3,3,6,6);//top left
        g.fillRect(15,3,6,6);//top right
        g.fillRect(9,9,6,6);//center
        g.fillRect(3,15,6,6);//bottom left
        g.fillRect(15,15,6,6);//bottom right

        g.dispose();
        return new ImageIcon(img);
    }

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

    public JFrame getFrame() {
        return this.creator;
    }
}