package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.MsgBox;
import njfbrowser.misc.alertDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


class AddDBdialog extends JDialog
        implements ActionListener {
    public class AddDBdialogWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            parent.boolAddDBDlgOpen = false;
            dispose();
        }

        public AddDBdialogWinListener() {
        }
    }


    public AddDBdialog(adminApp aapsmall, String pbool) {
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
        dbTitleAAPLabel = new JLabel("Custom Title");

        dbchoicebox = new Choice();


        webfldrAAPTField = new JTextField("");
        usernameAAPTField = new JTextField("");
        adminpassAAPTField = new JTextField("");

        dbnameAAPTField = new JTextField("");
        dbTitleAAPTField = new JTextField("");
        // dbnameAAPTField.setEditable(false);

        JPanel dbTitleAAPPan = new JPanel(new BorderLayout(2, 2));
        dbTitleAAPPan.add("West", dbTitleAAPLabel);
        dbTitleAAPPan.add("Center", dbTitleAAPTField);

        JPanel webfolderAAPPan = new JPanel(new BorderLayout(2, 2));
        webfolderAAPPan.add("West", webfldrAAPLabel);
        webfolderAAPPan.add("Center", webfldrAAPTField);

        JPanel usernameAAPPan = new JPanel(new BorderLayout(1, 1));
        usernameAAPPan.add("West", usernameAAPLabel);
        usernameAAPPan.add("Center", usernameAAPTField);

        JPanel adminpassAAPPan = new JPanel(new BorderLayout(1, 1));
        adminpassAAPPan.add("West", adminpassAAPLabel);
        adminpassAAPPan.add("Center", adminpassAAPTField);


        encryptedUnameLabel = new JLabel("  " + parent.aplangstrings.getProperty("text303") + " ");
        encryptedPassLabel = new JLabel("  " + parent.aplangstrings.getProperty("text304") + " ");
        encryptedUnameTField = new JTextField("");
        encryptedPassTField = new JTextField("");

        JPanel encryptedUnamePan = new JPanel(new BorderLayout(1, 1));
        encryptedUnamePan.add("West", encryptedUnameLabel);
        encryptedUnamePan.add("Center", encryptedUnameTField);

        JPanel encryptedPassPan = new JPanel(new BorderLayout(1, 1));
        encryptedPassPan.add("West", encryptedPassLabel);
        encryptedPassPan.add("Center", encryptedPassTField);


        JPanel dbnameAAPPan = new JPanel(new BorderLayout(1, 1));
        dbnameAAPPan.add("West", dbnameAAPLabel);
        dbnameAAPPan.add("Center", dbnameAAPTField);
        //  dbnameAAPPan.add("East", btnaaSpEdit);


        JPanel aaSPBtnPan = new JPanel(new GridLayout(0, 2));
        aaSPBtnPan.add(btnsaveaaSprefs);
        // aaSPBtnPan.add(btnaaSpcancel);
        aaSPBtnPan.add(btnaaSpclose);


        JPanel pContxtpan = new JPanel(new GridLayout(7, 0, 3, 3));
        pContxtpan.add(dbTitleAAPPan);
        pContxtpan.add(webfolderAAPPan);
        pContxtpan.add(usernameAAPPan);
        pContxtpan.add(adminpassAAPPan);
        pContxtpan.add(encryptedUnamePan);
        pContxtpan.add(encryptedPassPan);
        pContxtpan.add(dbnameAAPPan);
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
        addWindowListener(new AddDBdialogWinListener());


        fillaaSpreffields();


        pack();
        resize(350, 260);
        // setResizable(false);
        setLocation(200, 200);
        show();


    }


    public void fillaaSpreffields() {
        dbTitleAAPTField.setText(pboolstring);
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
            parent.boolAddDBDlgOpen = false;
            dispose();
        }
    }


    public void confSavePrefs() {
        MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text100"), parent.aplangstrings.getProperty("text117"), true);
        requestFocus();
        if (message.id) {
            String TPdbTitle = dbTitleAAPTField.getText();
            TPdbTitle = TPdbTitle.trim().toLowerCase();
            TPdbTitle = adminApp.replaceString(TPdbTitle, " ", "-");

            String TPwebfolder = webfldrAAPTField.getText();
            String TPadminusername = usernameAAPTField.getText();
            String TPadminpassword = adminpassAAPTField.getText();
            String TPdbname = dbnameAAPTField.getText();

            message.dispose();
            saveAddDBPrefs(TPwebfolder, TPadminusername, TPadminpassword, TPdbname, TPdbTitle);
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
        String TPdbname = dbnameAAPTField.getText();


        String TPdbTitle = dbTitleAAPTField.getText();
        String cleanFileTitle = parent.getTitleToFile(TPdbTitle);


        String combostring = parent.aplangstrings.getProperty("text108") + parent.aplangstrings.getProperty("text114");

        if (TPdbTitle.length() < 2) {
            parent.setQstatus("Please Create a Custom Title For this Databae", false);
            return;
        }
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

        if (TPdbname.length() < 2) {
            parent.setQstatus("Please enter a Database Name", false);
            return;
        }

        String dbnameString = cleanFileTitle;
        File file;
        String list[];
        String dbsString = dbnameString + ".dat";
        System.out.println(" -:- " + dbsString);
        String hasOlderScheme = "no";
        int i = 0;
        try {
            file = new File(parent.getUfile("cbox/data/dbs/"));
            if (file.isDirectory()) {
                list = file.list();
                while (i < list.length) {
                    System.out.println(list[i] + " : " + dbsString);
                    // if(list[i].indexOf(dbsString) != -1) {
                    if (list[i].equals(dbsString)) {
                        hasOlderScheme = "yes";
                        System.out.println("--------------------|||||||||||||||");
                        // System.out.println("its true");
                    }
                    i++;
                }
            }
        } catch (Exception ex) {
            parent.setQstatus(parent.aplangstrings.getProperty("text529") + "\n\n" + ex.toString(), true);
        }
        if (hasOlderScheme.equals("yes")) {
            // dbTitleAAPTField.setText("");
            new alertDialog(parent, "This Title Exists. Please select Another");
            return;
        } else {
            saveAddDBPrefs(TPwebfolder, TPadminusername, TPadminpassword, TPdbname, cleanFileTitle);

            // parent.saveAddDBProps(TPdbTitle);
        }

//            confSavePrefs();
    }


    public void aaprfsdummy() {
        parent.setQstatus(parent.aplangstrings.getProperty("text014"), false);
    }


/// --------------------------------------
/// --------------------------------------
/// Database Scheme saving and getting
/// 


    public void saveDBtbleScheme(String dbSCname, String curtble, String curflds) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(parent.getUfile("cbox/data/dbtables/" + dbSCname + "---" + curtble + "---dbtbls.dat"));
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(curflds);
            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            parent.setQstatus(exception.toString(), true);
            return;
        }

    }


    public void parseDBscheme(String dbSCname) {
        String curtable = "";
        String curfields = "";
        try {
            FileInputStream fileinputstream = new FileInputStream(parent.getUfile("cbox/data/dbschemes/" + dbSCname + "-scheme.dat"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null) {
                if (s.indexOf("<tbl>") != -1) {
                    curtable = adminApp.replaceString(s, "<tbl>", "");
                } else if (s.indexOf("<ttok>") != -1) {
                    curfields = curfields.trim();
                    saveDBtbleScheme(dbSCname, curtable, curfields);
                    curfields = "";
                } else {
                    curfields = curfields + s + "\n";
                }

            }
            datainputstream.close();
            fileinputstream.close();
            // System.out.println("gotCatIDs");
            // loadDBSscheme();
        } catch (Exception _ex) {
            parent.setQstatus(_ex.toString(), true);
        }
        // getDBlistVector();
        // getDBQdbs(false);
        // loadDBSscheme(dbSCname);
        // dbnameDBQField.setText("");
        new alertDialog(parent, parent.aplangstrings.getProperty("text004"));
    }


    public void copyDBscheme(String dbSCname) {
        try {
            FileInputStream fis = new FileInputStream(parent.getUfile("cbox/data/dbschemes/demo-scheme.dat"));
            FileOutputStream fos = new FileOutputStream(parent.getUfile("cbox/data/dbschemes/" + dbSCname + "-scheme.dat"));
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            fis.close();
            fos.close();
        } catch (Exception exception) {
            parent.setQstatus(exception.toString(), true);
            return;
        }
        parseDBscheme(dbSCname);
    }


    public void saveAddDBPrefs(String a, String b, String c, String d, String e) {
        try {
            String dbIDString = adminApp.getTimeStamp();
            FileOutputStream fileoutputstream = new FileOutputStream(parent.getUfile("cbox/data/dbs/" + e + ".dat"));
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println("hostfolder = " + a);
            printstream.println("adminusername = " + b);
            printstream.println("adminpassword = " + c);
            printstream.println("webdbasename = " + d);
            printstream.println("encryptedUname = " + encryptedUnameTField.getText());
            printstream.println("encryptedPass = " + encryptedPassTField.getText());
            printstream.println("dbID = " + dbIDString);

            printstream.close();
            fileoutputstream.close();
            copyDBscheme(dbIDString);
            // parent.setQstatus(parent.aplangstrings.getProperty("text212"), false);
        } catch (Exception exception) {
            parent.setQstatus(exception.toString(), true);
            return;
        }
    }


    JLabel webfldrAAPLabel;
    JLabel usernameAAPLabel;
    JLabel adminpassAAPLabel;
    JLabel dbnameAAPLabel;
    JLabel dbTitleAAPLabel;


    JLabel encryptedUnameLabel;
    JLabel encryptedPassLabel;
    JTextField encryptedUnameTField;
    JTextField encryptedPassTField;


    JTextField webfldrAAPTField;
    JTextField usernameAAPTField;
    JTextField adminpassAAPTField;
    JTextField dbnameAAPTField;
    JTextField dbTitleAAPTField;

    JButton btnsaveaaSprefs;
    JButton btnaaSpcancel;
    JButton btnaaSpclose;
    JButton btnaaSpEdit;

    Choice dbchoicebox;
    adminApp parent;
    String pboolstring;


}
