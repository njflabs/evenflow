package njfbrowser.misc;


import njfbrowser.main.adminApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AAPrefsdialog extends JDialog
        implements ActionListener {
    public class AAPrefsdialogWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            parent.boolAAPrefsdlgOpen = false;
            dispose();
        }

        public AAPrefsdialogWinListener() {
        }
    }


    public AAPrefsdialog(adminApp aapsmall, String pbool) {
        super(aapsmall, "WebSite Settings");

        parent = aapsmall;
        pboolstring = pbool;
        getContentPane().setLayout(new BorderLayout(1, 1));

        btnsaveaaSprefs = new JButton(parent.aplangstrings.getProperty("text209"));
        btnaaSpcancel = new JButton(parent.aplangstrings.getProperty("text307"));
        btnaaSpclose = new JButton(parent.aplangstrings.getProperty("text507"));
        btnaaSpEdit = new JButton(parent.aplangstrings.getProperty("text066"));


        webfldrAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text302") + " ");
        usernameAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text303") + " ");
        adminpassAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text304") + " ");
        dbnameAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text305") + " ");

        dbchoicebox = new Choice();


        webfldrAAPTField = new JTextField("");
        usernameAAPTField = new JTextField("");
        adminpassAAPTField = new JTextField("");
        dbnameAAPTField = new JTextField("");
        dbnameAAPTField.setEditable(false);
        JPanel webfolderAAPPan = new JPanel(new BorderLayout(2, 2));
        webfolderAAPPan.add("West", webfldrAAPLabel);
        webfolderAAPPan.add("Center", webfldrAAPTField);

        JPanel usernameAAPPan = new JPanel(new BorderLayout(1, 1));
        usernameAAPPan.add("West", usernameAAPLabel);
        usernameAAPPan.add("Center", usernameAAPTField);

        JPanel adminpassAAPPan = new JPanel(new BorderLayout(1, 1));
        adminpassAAPPan.add("West", adminpassAAPLabel);
        adminpassAAPPan.add("Center", adminpassAAPTField);

        JPanel dbnameAAPPan = new JPanel(new BorderLayout());
        dbnameAAPPan.add("West", dbnameAAPLabel);
        dbnameAAPPan.add("Center", dbnameAAPTField);
        dbnameAAPPan.add("East", btnaaSpEdit);


        JPanel aaSPBtnPan = new JPanel(new GridLayout(0, 2));
        aaSPBtnPan.add(btnsaveaaSprefs);
        // aaSPBtnPan.add(btnaaSpcancel);
        aaSPBtnPan.add(btnaaSpclose);


        JPanel pContxtpan = new JPanel(new GridLayout(3, 0, 3, 3));
        pContxtpan.add(webfolderAAPPan);
        pContxtpan.add(usernameAAPPan);
        pContxtpan.add(adminpassAAPPan);
        // pContxtpan.add(dbnameAAPPan);
        JPanel aapCntrpan = new JPanel(new BorderLayout(3, 3));
        aapCntrpan.add("Center", pContxtpan);
        // aapCntrpan.add("South", dbchoicebox);

        JPanel aapMainpan = new JPanel(new BorderLayout(1, 1));

        aapMainpan.add("Center", aapCntrpan);
        aapMainpan.add("South", aaSPBtnPan);


        getContentPane().add(aapMainpan, BorderLayout.CENTER);
        btnsaveaaSprefs.addActionListener(this);
        btnaaSpcancel.addActionListener(this);
        btnaaSpclose.addActionListener(this);
        btnaaSpEdit.addActionListener(this);
        addWindowListener(new AAPrefsdialogWinListener());

        fillaaSpreffields();


        pack();
        resize(350, 160);
        // setResizable(false);
        setLocation(200, 200);
        show();


    }


    public void fillaaSpreffields() {
        webfldrAAPTField.setText(parent.hostfolder);
        usernameAAPTField.setText(parent.aausername);
        adminpassAAPTField.setText(parent.aapassword);
        dbnameAAPTField.setText(parent.hostdbase);

    }


    public static void main(String args[]) {
    }


    public void actionPerformed(ActionEvent actionevent) {

        Object obj = actionevent.getSource();
        if (obj == btnaaSpcancel) {
            dispose();
        }
        if (obj == btnsaveaaSprefs) {
            validatePrefs();
        }
        if (obj == btnaaSpclose) {
            parent.boolAAPrefsdlgOpen = false;
            dispose();
        }
    }


    public void confSavePrefs() {
        MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text100"), parent.aplangstrings.getProperty("text117"), true);
        requestFocus();
        if (message.id) {
            String TPwebfolder = webfldrAAPTField.getText();
            String TPadminusername = usernameAAPTField.getText();
            String TPadminpassword = adminpassAAPTField.getText();
            String TPdbname = dbnameAAPTField.getText();
            message.dispose();
            parent.saveAAPrefs(TPwebfolder, TPadminusername, TPadminpassword, TPdbname);
            // System.out.println("Ok pressed");
        }
        if (!message.id) {
            message.dispose();
            // System.out.println("Cancel pressed");
        }
    }


    public void validatePrefs() {
        String TPwebfolder = webfldrAAPTField.getText();
        String TPadminusername = usernameAAPTField.getText();
        String TPadminpassword = adminpassAAPTField.getText();
        String combostring = parent.aplangstrings.getProperty("text108") + parent.aplangstrings.getProperty("text114");

        if (TPwebfolder.length() < 2) {
            parent.setQstatus(combostring, false);
            return;
        }
        if (TPwebfolder.endsWith("/")) {
            System.out.println("ended ok");
        } else {
            parent.setQstatus(combostring, false);
            return;
        }

        if (TPadminusername.length() < 2) {
            parent.setQstatus(parent.aplangstrings.getProperty("text106"), false);
            return;
        }

        if (TPadminpassword.length() < 2) {
            parent.setQstatus(parent.aplangstrings.getProperty("text107"), false);
            return;
        }
        confSavePrefs();
    }


    public void aaprfsdummy() {
        parent.setQstatus(parent.aplangstrings.getProperty("text014"), false);
    }


    JLabel webfldrAAPLabel;
    JLabel usernameAAPLabel;
    JLabel adminpassAAPLabel;
    JLabel dbnameAAPLabel;

    JTextField webfldrAAPTField;
    JTextField usernameAAPTField;
    JTextField adminpassAAPTField;
    JTextField dbnameAAPTField;


    JButton btnsaveaaSprefs;
    JButton btnaaSpcancel;
    JButton btnaaSpclose;
    JButton btnaaSpEdit;

    Choice dbchoicebox;
    adminApp parent;
    String pboolstring;


}
