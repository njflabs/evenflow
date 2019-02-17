package njfbrowser.spreadsheet;

import njfbrowser.main.adminApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SmartAddRows extends JDialog
        implements ActionListener {
    public class SmartAddRowsWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public SmartAddRowsWinListener() {
        }
    }


    public SmartAddRows(adminApp admnaap) {
        super(admnaap, " Add Rows", false);
        parent = admnaap;
        getContentPane().setLayout(new BorderLayout());
        numrows = new JTextField("10");
        JLabel numrowsLabel = new JLabel("  Add ");
        JLabel therowsLabel = new JLabel("  Rows ");

        JPanel inputpan = new JPanel(new GridLayout(0, 3));
        inputpan.add(numrowsLabel);
        inputpan.add(numrows);
        inputpan.add(therowsLabel);

        okido = new JButton("Add");
        okido.setBackground(new Color(225, 180, 180));
        okido.setForeground(new Color(0, 0, 180));

        nogo = new JButton("Cancel");
        nogo.setBackground(new Color(225, 180, 180));
        nogo.setForeground(new Color(0, 0, 180));


        JPanel btnpan = new JPanel(new GridLayout(0, 2));
        btnpan.add(okido);
        btnpan.add(nogo);

        JPanel mainpanel = new JPanel(new BorderLayout());
        mainpanel.add("Center", inputpan);
        mainpanel.add("South", btnpan);

        getContentPane().add(mainpanel, "Center");
        addWindowListener(new SmartAddRowsWinListener());
        okido.addActionListener(this);
        nogo.addActionListener(this);
        numrows.addActionListener(this);
        pack();


        setResizable(false);
        setForeground(new Color(0, 0, 120));
        setLocation(220, 220);
        show();
        // setred();
    }

    public static void main(String args[]) {
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == okido) {
            pumprowstoP();
        }
        if (obj == nogo) {
            dispose();
        }
        if (obj == numrows) {
            pumprowstoP();
        }
    }

    public void pumprowstoP() {
        int ii = 0;
        try {
            ii = Integer.parseInt(numrows.getText());
        } catch (Exception e) {
            parent.setQstatus(e.toString(), false);
            return;
        }
        setVisible(false);
        parent.addSmartRows(ii);
    }

    JButton okido;
    JButton nogo;
    adminApp parent;
    JTextField numrows;
}
