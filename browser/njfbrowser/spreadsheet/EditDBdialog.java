package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.MsgBox;
import njfbrowser.misc.alertDialog;
import njfbrowser.tasks.taskAAUpdateQcomms;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class EditDBdialog extends JDialog
        implements ActionListener {
    public class EditDBdialogWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            parent.boolAddDBDlgOpen = false;
            dispose();
        }

        public EditDBdialogWinListener() {
        }
    }


    public EditDBdialog(adminApp aapsmall, String pbool) {

        super(aapsmall, "Add/Edit Database Settings", true);

        parent = aapsmall;
        pboolstring = pbool;
        stringBoolBtnTitle = "";
        if (pboolstring.equals("noQvalue")) {
            setTitle(aapsmall.aplangstrings.getProperty("text016", "Add Database to List"));
        } else {
            setTitle(aapsmall.aplangstrings.getProperty("text315", "Edit Saved Settings") + ": " + pboolstring);
        }

        getContentPane().setLayout(new BorderLayout(1, 1));


        mbarEditDBDlg = new JMenuBar();
        mbarEditDBDlg.setBorderPainted(true);

        mnuEDBoxDB = new JMenu(parent.aplangstrings.getProperty("text324", "Tools"));
        mnuEDBoxDB.getPopupMenu().setLightWeightPopupEnabled(false);


        mbarEditDBDlg.add(mnuEDBoxDB);

        mnuiEDBoxUpdateQcomms = new JMenuItem(parent.aplangstrings.getProperty("text541", "Update qcomms file"));
        mnuEDBoxDB.add(mnuiEDBoxUpdateQcomms);
        mnuiEDBoxUpdateQcomms.addActionListener(this);

        btnsaveaaSprefs = new JButton(parent.aplangstrings.getProperty("text306"));
        btnaaSpcancel = new JButton(parent.aplangstrings.getProperty("text307"));
        btnaaSpclose = new JButton(parent.aplangstrings.getProperty("text507"));
        btnaaSpEdit = new JButton(parent.aplangstrings.getProperty("text066"));

        webfldrAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text302") + ":  ");
        usernameAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text312") + ":  ");
        adminpassAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text313") + ":  ");
        dbnameAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text305") + ":  ");
        dbTitleAAPLabel = new JLabel("  " + parent.aplangstrings.getProperty("text314") + ":  ");
        labelDBHost = new JLabel("  " + parent.aplangstrings.getProperty("text158") + ":  ");

        dbchoicebox = new Choice();


        webfldrAAPTField = new JTextField("", 34);
        usernameAAPTField = new JTextField("");
        // adminpassAAPTField = new JTextField("");
        adminpassAAPTField = new JPasswordField("");
        dbnameAAPTField = new JTextField("");
        dbTitleAAPTField = new JTextField("");
        tfieldDBHost = new JTextField("");
        // dbnameAAPTField.setEditable(false);


// !!!!!!!!!!!!!! Start of the progressBarAA

        progBarEditDBDlg = new JProgressBar(10, 100);
        Border pbBorder = progBarEditDBDlg.getBorder();
        Border spaceBelow = BorderFactory.createEmptyBorder(0, 0, 5, 0);
        progBarEditDBDlg.setBorder(BorderFactory.createCompoundBorder(spaceBelow, pbBorder));
        progBarEditDBDlg.setPreferredSize(new Dimension(100, 10));
        progBarEditDBDlg.setOpaque(true);
        progBarEditDBDlg.setForeground(getForeground());

// !!!!!!!!!!!!!! End of the progressBarAA


        JPanel panDBHost = new JPanel(new BorderLayout(1, 1));
        panDBHost.add("West", labelDBHost);
        panDBHost.add("Center", tfieldDBHost);


        JPanel dbTitleAAPPan = new JPanel(new BorderLayout(1, 1));
        dbTitleAAPPan.add("West", dbTitleAAPLabel);
        dbTitleAAPPan.add("Center", dbTitleAAPTField);
        dbTitleAAPPan.add("East", progBarEditDBDlg);

        JPanel webfolderAAPPan = new JPanel(new BorderLayout(1, 1));
        webfolderAAPPan.add("West", webfldrAAPLabel);
        webfolderAAPPan.add("Center", webfldrAAPTField);

        JPanel usernameAAPPan = new JPanel(new BorderLayout(1, 1));
        usernameAAPPan.add("West", usernameAAPLabel);
        usernameAAPPan.add("Center", usernameAAPTField);

        JPanel adminpassAAPPan = new JPanel(new BorderLayout(1, 1));
        adminpassAAPPan.add("West", adminpassAAPLabel);
        adminpassAAPPan.add("Center", adminpassAAPTField);


        encryptedUnameLabel = new JLabel("  " + parent.aplangstrings.getProperty("text317") + ":  ");
        encryptedPassLabel = new JLabel("  " + parent.aplangstrings.getProperty("text318") + ":  ");
        encryptedUnameTField = new JTextField("");
        encryptedPassTField = new JPasswordField("");

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


        Border dbqetchedBdr = BorderFactory.createEtchedBorder();
        Border dbqemptyBdr = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        Color dbqnrclr = new Color(180, 60, 0);
        Color dbqshclr = new Color(0, 60, 180);


        Border brdrEncrypPass = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text316") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border brdrFullEncrypPass = BorderFactory.createCompoundBorder(brdrEncrypPass, dbqemptyBdr);

        Border addPanTtlBdr = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text006") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border addCompoundBdr = BorderFactory.createCompoundBorder(addPanTtlBdr, dbqemptyBdr);

        Border brdrPanDBInfo = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text138") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border brdrCmpdDBInfo = BorderFactory.createCompoundBorder(brdrPanDBInfo, dbqemptyBdr);


        JPanel pContxtpan = new JPanel(new GridLayout(2, 0, 3, 3));
        pContxtpan.add(dbTitleAAPPan);
        pContxtpan.add(webfolderAAPPan);


        JPanel panDBInfo = new JPanel(new GridLayout(4, 0, 3, 3));
        panDBInfo.add(dbnameAAPPan);
        panDBInfo.add(panDBHost);
        panDBInfo.add(usernameAAPPan);
        panDBInfo.add(adminpassAAPPan);

        pContxtpan.setBorder(addCompoundBdr);
        panDBInfo.setBorder(brdrCmpdDBInfo);

        JPanel panEncryPass = new JPanel(new GridLayout(2, 0, 3, 3));
        panEncryPass.add(encryptedUnamePan);
        panEncryPass.add(encryptedPassPan);
        panEncryPass.setBorder(addCompoundBdr);
        panEncryPass.setBorder(brdrFullEncrypPass);

        JPanel aapCntrpan = new JPanel(new BorderLayout(3, 3));
        aapCntrpan.add("North", pContxtpan);
        aapCntrpan.add("Center", panDBInfo);
        aapCntrpan.add("South", panEncryPass);

        // aapCntrpan.add("South", dbchoicebox);

        JPanel aapMainpan = new JPanel(new BorderLayout(1, 1));

        aapMainpan.add("Center", aapCntrpan);
        aapMainpan.add("South", aaSPBtnPan);


        getContentPane().add(aapMainpan, BorderLayout.CENTER);
        btnsaveaaSprefs.addActionListener(this);
        btnaaSpcancel.addActionListener(this);
        btnaaSpclose.addActionListener(this);
        btnaaSpEdit.addActionListener(this);
        addWindowListener(new EditDBdialogWinListener());


        if (pboolstring.equals("noQvalue")) {
            System.out.println("not config");
        } else {
            dbTitleAAPTField.setText(pboolstring);
            dbTitleAAPTField.setEditable(false);
        }
// dbnameAAPTField.setEditable(false);
        fillaaSpreffields();
        setJMenuBar(mbarEditDBDlg);

        pack();
        // resize(350, 260);
        // setResizable(false);
        setLocation(200, 200);
        show();


    }


    public void fillaaSpreffields() {
        // dbTitleAAPTField.setText(pboolstring);
        webfldrAAPTField.setText(parent.hostfolder);
        usernameAAPTField.setText(parent.aausername);
        adminpassAAPTField.setText(parent.aapassword);
        encryptedUnameTField.setText(parent.encryptedUname);
        encryptedPassTField.setText(parent.encryptedPass);
        dbnameAAPTField.setText(parent.hostdbase);
        tfieldDBHost.setText(parent.currentDBHost);
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
        if (obj == mnuiEDBoxUpdateQcomms) {
            startUpdateQcomms();
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
        String strngDBHost = tfieldDBHost.getText();

        String TPdbTitle = dbTitleAAPTField.getText();
        String cleanFileTitle = parent.getTitleToFile(TPdbTitle);

        String hasValTitle = "yes";
        String allowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_- ";
        String dummyval = "noQ";


        String combostring = parent.aplangstrings.getProperty("text108") + parent.aplangstrings.getProperty("text114");

        for (int i = 0; i < TPdbTitle.length(); i++) {
            char c1 = TPdbTitle.charAt(i);
            if (allowedChars.indexOf(c1) != -1) {
                dummyval = "noQvalue";
            } else {
                hasValTitle = "no";
            }
        }

        if ((TPdbTitle.length() > 50) || (TPdbTitle.length() < 2) || (hasValTitle.equals("no"))) {
            parent.setQstatus(parent.aplangstrings.getProperty("text321"), false);
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
            parent.setQstatus(parent.aplangstrings.getProperty("text600"), false);
            return;
        }

        if (strngDBHost.length() < 2) {
            parent.setQstatus(parent.aplangstrings.getProperty("text159"), false);
            tfieldDBHost.setText("localhost");
            return;
        }

/* adding the db, confirm if not exists
*/
        if (pboolstring.equals("noQvalue")) {
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
            }
        } // end of if pboolstring = noQvalue


        saveAddDBPrefs(TPwebfolder, TPadminusername, TPadminpassword, TPdbname, cleanFileTitle);

    }


    public void aaprfsdummy() {
        parent.setQstatus(parent.aplangstrings.getProperty("text014"), false);
    }


    public void saveAddDBPrefs(String a, String b, String c, String d, String e) {
        String dbIDString = parent.currentDBID;
        if (pboolstring.equals("noQvalue")) {
            dbIDString = adminApp.getTimeStamp();
        } else {
            dbIDString = parent.currentDBID;
            parent.loadnewDBprefs(dbTitleAAPTField.getText().trim());
        }


        try {

            FileOutputStream fileoutputstream = new FileOutputStream(parent.getUfile("cbox/data/dbs/" + e + ".dat"));
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println("hostfolder = " + a);
            printstream.println("dbHost = " + tfieldDBHost.getText());
            printstream.println("adminusername = " + b);
            printstream.println("adminpassword = " + c);
            printstream.println("encryptedUname = " + encryptedUnameTField.getText());
            printstream.println("encryptedPass = " + encryptedPassTField.getText());
            printstream.println("webdbasename = " + d);
            printstream.println("dbID = " + dbIDString);

            printstream.close();
            fileoutputstream.close();

            if (pboolstring.equals("noQvalue")) {
                copyDBscheme(dbIDString);
            } else {
                parent.loadnewDBprefs(dbTitleAAPTField.getText().trim());
                parent.setQstatus(parent.aplangstrings.getProperty("text213"), false);
            }
            // copyDBscheme(dbIDString);

        } catch (Exception exception) {
            parent.setQstatus(exception.toString(), true);
            return;
        }
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
        parent.loadnewDBprefs(dbTitleAAPTField.getText().trim());


        if (pboolstring.equals("noQvalue")) {
            parent.loadNewDB(dbTitleAAPTField.getText().trim());
            parent.boolAddDBDlgOpen = false;
            dispose();
        }


        // new alertDialog(parent, parent.aplangstrings.getProperty("text004"));
    }


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

/**/


    public void startUpdateQcomms() {

        MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text541", "Update qcomms file") + "?", parent.aplangstrings.getProperty("text801", "PLEASE CONFIRM THAT") + ":\n" + parent.aplangstrings.getProperty("text802", "You are currently connected to internet") + ".", true);
        requestFocus();
        if (message.id) {
            message.dispose();

            progBarEditDBDlg.setIndeterminate(true);
            editDBDisableButtons();

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            try {
                taskAAUpdQcomms = new taskAAUpdateQcomms(this);
                taskAAUpdQcomms.execute();
            } catch (Exception exception) {
                parent.setQstatus("ERROR: startUpdateQcomms [587]: " + exception.toString(), true);
                return;
            }


        }
        if (!message.id) {
            message.dispose();
            System.out.println("Cancel pressed");
        }


    }

    public void stopUpdateQcomms() {

        parent.setDefaultCursor();
        setCursor(Cursor.getPredefinedCursor(0));


        try {
            progBarEditDBDlg.setIndeterminate(false);
            editDBEnableButtons();

            System.out.println("stopUpdateQcomms");

        } catch (Exception exception) {
            parent.setQstatus("ERROR: stopUpdateQcomms[611]: " + exception.toString(), true);
        }


    }

    public void doUpdateQcomms() {
        try {
            parent.updateQcomms();

            System.out.println("doUpdateQcomms");

        } catch (Exception exception) {
            parent.setQstatus("ERROR: doUpdateQcomms [626]: " + exception.toString(), true);
        }
    }


    public void editDBEnableButtons() {
        mbarEditDBDlg.setEnabled(true);
        encryptedUnameTField.setEditable(true);
        encryptedPassTField.setEditable(true);
        webfldrAAPTField.setEditable(true);
        tfieldDBHost.setEditable(true);
        usernameAAPTField.setEditable(true);
        adminpassAAPTField.setEditable(true);
        dbnameAAPTField.setEditable(true);
        btnsaveaaSprefs.setEnabled(true);
        btnaaSpcancel.setEnabled(true);
        btnaaSpclose.setEnabled(true);
        btnaaSpEdit.setEnabled(true);
    }


    public void editDBDisableButtons() {
        mbarEditDBDlg.setEnabled(false);
        encryptedUnameTField.setEditable(false);
        encryptedPassTField.setEditable(false);
        webfldrAAPTField.setEditable(false);
        tfieldDBHost.setEditable(false);
        usernameAAPTField.setEditable(false);
        adminpassAAPTField.setEditable(false);
        dbnameAAPTField.setEditable(false);
        btnsaveaaSprefs.setEnabled(false);
        btnaaSpcancel.setEnabled(false);
        btnaaSpclose.setEnabled(false);
        btnaaSpEdit.setEnabled(false);
    }


    JProgressBar progBarEditDBDlg;
    JMenuBar mbarEditDBDlg;
    JMenu mnuEDBoxDB;
    JMenuItem mnuiEDBoxUpdateQcomms;

    JLabel webfldrAAPLabel;
    JLabel usernameAAPLabel;
    JLabel adminpassAAPLabel;
    JLabel dbnameAAPLabel;
    JLabel labelDBHost;
    JLabel dbTitleAAPLabel;

    JLabel encryptedUnameLabel;
    JLabel encryptedPassLabel;
    JTextField encryptedUnameTField;
    JPasswordField encryptedPassTField;


    JTextField webfldrAAPTField;
    JTextField tfieldDBHost;
    JTextField usernameAAPTField;

    JPasswordField adminpassAAPTField;
    JTextField dbnameAAPTField;
    JTextField dbTitleAAPTField;

    JButton btnsaveaaSprefs;
    JButton btnaaSpcancel;
    JButton btnaaSpclose;
    JButton btnaaSpEdit;

    Choice dbchoicebox;
    adminApp parent;
    public String pboolstring;
    String stringBoolBtnTitle;
    taskAAUpdateQcomms taskAAUpdQcomms;
}
