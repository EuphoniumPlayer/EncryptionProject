import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.BigInteger;

public class rsawindow implements ActionListener {
    creationtools tools = new creationtools();
    //menu stuff
    JFrame menu;
    JButton create, encrypt;
    Font menufont = new Font("Arial",Font.BOLD,15);

    //creator stuff
    JFrame creator;
    JLabel plabel, qlabel, elabel, invalidplabel, invalidqlabel, invalidelabel;
    JTextField pfield, qfield, efield, output;
    JButton checkp, checkq, checke;
    Font creatorfont = new Font("Arial",Font.PLAIN,20);
    int p,q,e;
    BigInteger pbigint, qbigint, ebigint;

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
        pfield.setBounds(25, 60, 400, 30);

        checkp = new JButton("Check");
        checkp.setFont(creatorfont);
        checkp.setFocusable(false);
        checkp.addActionListener(this);
        checkp.setBounds(25, 105, 100, 30);

        invalidplabel = new JLabel("");
        invalidplabel.setFont(creatorfont);
        invalidplabel.setFocusable(false);
        invalidplabel.setBounds(150, 105, 200, 30);

        qlabel = new JLabel("Enter a second prime number");
        qlabel.setFont(creatorfont);
        qlabel.setFocusable(false);
        qlabel.setBounds(25, 160, 400, 20);

        qfield = new JTextField();
        qfield.setFont(creatorfont);
        qfield.setEditable(true);
        qfield.setBounds(25, 195, 400, 30);

        checkq = new JButton("Check");
        checkq.addActionListener(this);
        checkq.setFocusable(false);
        checkq.setFont(creatorfont);
        checkq.setBounds(25, 240, 100, 30);

        invalidqlabel = new JLabel("");
        invalidqlabel.setFocusable(false);
        invalidqlabel.setFont(creatorfont);
        invalidqlabel.setBounds(150, 240, 200, 30);

        elabel = new JLabel("Enter a public encryption number");
        elabel.setFont(creatorfont);
        elabel.setFocusable(false);
        elabel.setBounds(25, 295, 400, 20);

        efield = new JTextField();
        efield.setFont(creatorfont);
        efield.setEditable(true);
        efield.setBounds(25, 330, 400, 30);

        checke = new JButton("Check");
        checke.addActionListener(this);
        checke.setFocusable(false);
        checke.setFont(creatorfont);
        checke.setBounds(25, 375, 100, 30);

        invalidelabel = new JLabel();
        invalidelabel.setFocusable(false);
        invalidelabel.setFont(creatorfont);
        invalidelabel.setBounds(150, 375, 300, 30);

        creator = new JFrame("Create a key");
        creator.setSize(500, 600);
        creator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creator.setLayout(null);

        creator.add(plabel);
        creator.add(pfield);
        creator.add(checkp);
        creator.add(invalidplabel);
        creator.add(qlabel);
        creator.add(qfield);
        creator.add(checkq);
        creator.add(invalidqlabel);
        creator.add(elabel);
        creator.add(efield);
        creator.add(checke);
        creator.add(invalidelabel);
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
        if (event.getSource() == checkp) {
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
                invalidplabel.setText("Invalid input");
                creator.repaint();
            }//end try
        }//end checkp
        if (event.getSource() == checkq) {
            try {
                q = Integer.parseInt(qfield.getText());
                qbigint = new BigInteger(String.valueOf(p));
                if (!tools.isprime(qbigint)) {
                    invalidqlabel.setText("Not prime");
                    creator.repaint();
                } else {
                    invalidqlabel.setText("Valid");
                    creator.repaint();
                }
            } catch (NumberFormatException error) {
                invalidqlabel.setText("Invalid input");
                creator.repaint();
            }//end try
        }//end checkq
        if (event.getSource() == checke) {
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
                    invalidelabel.setText("Invalid input(s)");
                    creator.repaint();
                    inputsvalid = false;
                }
                if (inputsvalid) {
                    if (!tools.isprime(pbigint) || !tools.isprime(qbigint)) {
                        invalidelabel.setText("Error: Primes not prime");
                    }
                }
            }
        }
    }//end actions
}//eop