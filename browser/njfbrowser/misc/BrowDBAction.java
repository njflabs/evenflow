package njfbrowser.misc;


// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrowDBAction.java

import njfbrowser.main.adminApp;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Vector;

public class BrowDBAction extends JDialog
        implements ActionListener, KeyListener, ItemListener, MouseListener {
    public class BrowDBActionWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            adminApp.qlinksframe1 = false;
            dispose();
        }

        public BrowDBActionWindowListener() {
        }
    }


    public void jbodummy() {

        if (isbolder) {
            resize(300, 500);
            setLocation(320, 20);
            isbolder = false;
            return;
        } else {
            parent.setQstatus(parent.aplangstrings.getProperty("text014"), false);
        }

    }


    public void ItemListener(ItemEvent itemevent) {
    }

    public void keepitSmaller() {
        resize(280, 53);
        setLocation(300, 15);
        isbolder = true;
    }

    public void showlinkOns() {
        httpconfig = new Properties();
        try {
            inifile = parent.udString + "/" + "cbox/slic/01exe.ini";
            FileInputStream fileinputstream = new FileInputStream(parent.udString + "/" + "cbox/slic/01exe.ini");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            httpconfig.load(fileinputstream);
            String s = URLEncoder.encode(httpconfig.getProperty("search1"));
            String s1;
            while ((s1 = datainputstream.readLine()) != null)
                searchChoice.addItem(s);
            datainputstream.close();
            fileinputstream.close();
            searchChoice.addItem(s);
            return;
        } catch (Exception _ex) {
            new Messdialog1(parent, parent.aplangstrings.getProperty("text045"));
        }
    }


    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == addchan) {
            saveActionlist();
        }
        if (mouseevent.getSource() == searchbut) {
            System.out.println("searchit() deleted");
        }
        if (mouseevent.getSource() == chandelete) {
            delDBAAction();
        }
        if (mouseevent.getSource() == btnbrowlaunch) {
            String s = txtfldBrwToDBUrl.getText();
            if (s.trim().length() < 1 || s == "quickLinks")
                new Messdialog1(parent, parent.aplangstrings.getProperty("text111"));
            else
                parent.favs(s);
        }
        if (mouseevent.getSource() == bold) {
            isitBolder();
        }
    }


    public BrowDBAction(adminApp myslic, String urlstring, String dbString, String tblString, String dbactnq, int browint) {

        super(myslic, "BrowserToDB Actions Box");
        parent = myslic;

        getContentPane().setLayout(new BorderLayout());
        thebrow = browint;
        dbactionQ = dbactnq;
        allActionsString = "";
        currDbaDBstring = dbString;
        currDbaTblstring = tblString;
        brwtodbStringVector = new Vector();
        brwtodbDBVector = new Vector();
        brwtodbTblVector = new Vector();
        brwtodbUrlVector = new Vector();
        m_menu = new PopupMenu();
        m_red = new MenuItem("Paste");
        m_menu.add(m_red);


        tracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage(parent.udString + "/" + "cbox/images/newmess.gif");
        img5 = Toolkit.getDefaultToolkit().getImage(parent.udString + "/" + "cbox/images/delchann.gif");
        img6 = Toolkit.getDefaultToolkit().getImage(parent.udString + "/" + "cbox/images/addchann.gif");
        img7 = Toolkit.getDefaultToolkit().getImage(parent.udString + "/" + "cbox/images/minimiz.gif");
        img8 = Toolkit.getDefaultToolkit().getImage(parent.udString + "/" + "cbox/images/jbrowlaunch.gif");
        img12 = Toolkit.getDefaultToolkit().getImage(parent.udString + "/" + "cbox/images/searchbut.gif");
        tracker.addImage(img1, 1);
        tracker.addImage(img5, 5);
        tracker.addImage(img6, 6);
        tracker.addImage(img7, 7);
        tracker.addImage(img8, 8);
        tracker.addImage(img12, 12);
        try {
            tracker.waitForID(1);
            tracker.waitForID(5);
            tracker.waitForID(6);
            tracker.waitForID(7);
            tracker.waitForID(8);
            tracker.waitForID(12);
        } catch (InterruptedException _ex) {
        }
        bugReport = new ImageButton("Visite our Sponsors", img1);
        chandelete = new ImageButton("Delete Channel", img5);
        addchan = new ImageButton("Add Channel", img6);
        bold = new ImageButton("Minimize", img7);
        btnbrowlaunch = new ImageButton("Help", img8);
        searchbut = new ImageButton("searchbut", img12);

        jbtnCloseBDBA = new JButton(parent.aplangstrings.getProperty("text507"));
        jbtnAddBDBAction = new JButton(parent.aplangstrings.getProperty("text030"));
        jbtnDeleteBDBAction = new JButton(parent.aplangstrings.getProperty("text031"));
        tiplabel1 = new JLabel(parent.aplangstrings.getProperty("text211") + ": <");
        JLabel lblBrwToDBUrl = new JLabel(parent.aplangstrings.getProperty("text211") + ": ");
        JLabel lblBrwToDBList = new JLabel(parent.aplangstrings.getProperty("text033") + ": ");
        JLabel lblBrwToDBString = new JLabel(parent.aplangstrings.getProperty("text089") + ": ");
        JLabel lblAdminAppVer = new JLabel(parent.aplangstrings.getProperty("text000"), 2);

        lblDBtoApply = new JLabel();
        lblActionUrls = new JLabel(parent.aplangstrings.getProperty("text042") + ": ");


        txtfldBrwToDBUrl = new JTextField(urlstring);
        txtFldBrwDBAString = new JTextField(dbactionQ);
        txtFldQBrwString = new JTextField("");

        quicklist = new java.awt.List(6);


        searchChoice = new Choice();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(225, 225, 225));
        panel.setForeground(new Color(225, 225, 225));
        panel.add("West", addchan);
        panel.add("Center", chandelete);
        panel.add("East", btnbrowlaunch);

        JPanel panelBrwToDBUrl = new JPanel(new BorderLayout());
        panelBrwToDBUrl.add("North", lblBrwToDBUrl);
        panelBrwToDBUrl.add("Center", txtfldBrwToDBUrl);
        // panelBrwToDBUrl.add("East", panel);
        //  panelBrwToDBUrl.add("East", jbtnAddBDBAction);


        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add("Center", panelBrwToDBUrl);

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.add("North", lblBrwToDBString);
        panel5.add("Center", txtFldBrwDBAString);
        panel5.add("East", jbtnAddBDBAction);


        JPanel newrulePan = new JPanel(new BorderLayout());
        newrulePan.add("North", panelBrwToDBUrl);
        newrulePan.add("Center", panel5);
        Border etchedBdr = BorderFactory.createEtchedBorder();
        Border emptyBdr = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        Color nrclr = new Color(180, 60, 0);
        Color shclr = new Color(0, 60, 180);
        Border newrulePanTtlBdr = BorderFactory.createTitledBorder(etchedBdr, parent.aplangstrings.getProperty("text105") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), nrclr);
        Border compoundBdr = BorderFactory.createCompoundBorder(newrulePanTtlBdr, emptyBdr);
        newrulePan.setBorder(compoundBdr);


        JPanel qlistPan = new JPanel(new BorderLayout());
        qlistPan.add("North", lblActionUrls);
        qlistPan.add("Center", quicklist);
        qlistPan.add("South", lblDBtoApply);

        JPanel shwRulesPan = new JPanel(new BorderLayout());
        shwRulesPan.add("North", qlistPan);
        shwRulesPan.add("Center", txtFldQBrwString);
        shwRulesPan.add("East", jbtnDeleteBDBAction);


        Border shwRulePanTtlBdr = BorderFactory.createTitledBorder(etchedBdr, parent.aplangstrings.getProperty("text040") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), shclr);
        Border shwCompoundBdr = BorderFactory.createCompoundBorder(shwRulePanTtlBdr, emptyBdr);
        shwRulesPan.setBorder(shwCompoundBdr);


        JPanel mainPan = new JPanel(new BorderLayout());
        mainPan.add("North", newrulePan);
        mainPan.add("Center", shwRulesPan);

        JPanel botPan = new JPanel(new BorderLayout(2, 2));
        botPan.add("West", lblAdminAppVer);
        botPan.add("East", jbtnCloseBDBA);


        JPanel mainMMPan = new JPanel(new BorderLayout());
        mainMMPan.add("North", tiplabel1);
        mainMMPan.add("Center", mainPan);
        mainMMPan.add("South", botPan);

        getContentPane().add(mainMMPan);


        searchChoice.setBackground(new Color(235, 235, 255));
        tiplabel1.setForeground(new Color(0, 0, 180));
        txtFldBrwDBAString.setForeground(new Color(0, 0, 180));
        txtfldBrwToDBUrl.setForeground(new Color(0, 0, 180));
        lblAdminAppVer.setFont(new Font("ARIAL", 0, 11));
        lblAdminAppVer.setForeground(new Color(0, 0, 180));
        quicklist.setBackground(new Color(252, 252, 255));
        quicklist.setForeground(new Color(0, 0, 80));

        jbtnCloseBDBA.addActionListener(this);
        jbtnAddBDBAction.addActionListener(this);
        jbtnDeleteBDBAction.addActionListener(this);

        bugReport.addActionListener(this);
        bugReport.addMouseListener(this);
        btnbrowlaunch.addActionListener(this);
        btnbrowlaunch.addMouseListener(this);
        addchan.addActionListener(this);
        addchan.addMouseListener(this);
        chandelete.addActionListener(this);
        chandelete.addMouseListener(this);
        bold.addActionListener(this);
        bold.addMouseListener(this);
        quicklist.addItemListener(this);
        quicklist.addMouseListener(this);
        quicklist.addActionListener(this);
        searchbut.addActionListener(this);
        searchbut.addMouseListener(this);
        txtfldBrwToDBUrl.addActionListener(this);


        addWindowListener(new BrowDBActionWindowListener());
        setLocation(50, 50);
        loadWatchlist();
        resize(650, 375);
        setVisible(true);
        isbolder = false;
        // showlinkOns();
        setResizable(false);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyPressed(KeyEvent keyevent) {
    }


    public void mouseReleased(MouseEvent mouseevent) {
    }


    public void itemStateChanged(ItemEvent itemevent) {
        Object obj = itemevent.getSource();
        if (obj == quicklist) {
            showdaquickie();
        }
    }


    public void sortMyquickList(String s1) {
        int j = 0;
        for (int k = quicklist.countItems() - 1; j < k; ) {
            int l = (j + k) / 2;
            if (s1.toLowerCase().compareTo(quicklist.getItem(l).toLowerCase()) > 0)
                j = l + 1;
            else
                k = l;
        }

        quicklist.addItem(s1, j);
    }

    public void showdaquickie() {
        int i = quicklist.getSelectedIndex();
        lblDBtoApply.setText(brwtodbDBVector.elementAt(i).toString() + " : " + brwtodbStringVector.elementAt(i));
        txtFldQBrwString.setText(brwtodbStringVector.elementAt(i).toString());
        // txtfldBrwToDBUrl.setText(quicklist.getSelectedItem());
        txtfldBrwToDBUrl.requestFocus();
    }


    public void loadWatchlist() {

        quicklist.removeAll();
        allActionsString = "";
        brwtodbDBVector.removeAllElements();
        brwtodbStringVector.removeAllElements();
        brwtodbUrlVector.removeAllElements();
        try {
            FileInputStream fileinputstream = new FileInputStream(parent.udString + "/" + "cbox/data/dbcomms/brwtodb.prcd");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null) {
                // String tstring[] = parent.readmessTokens(s, "[|]");
                // System.out.println(tstring[0] + " : " + tstring[1] + " : " + tstring[2]);
                // brwtodbDBVector.addElement(tstring[1]);
                //  brwtodbStringVector.addElement(tstring[2]);
                allActionsString = allActionsString + s + "\n";
                sortMyquickList(s);
            }
            allActionsString = allActionsString.trim();
            datainputstream.close();
            fileinputstream.close();
        } catch (Exception _ex) {
            parent.setQstatus(_ex.toString(), false);
            return;
        }
        try {
            for (int i = 0; i < quicklist.getItemCount(); i++) {
                String qcstring = quicklist.getItem(i);
                String tstring[] = adminApp.readmessTokens(qcstring, "[|]");
                System.out.println(i + " | " + tstring[0] + " : " + tstring[1] + " : " + tstring[2] + " : " + tstring[3]);
                brwtodbUrlVector.addElement(tstring[0]);
                brwtodbDBVector.addElement(tstring[1]);
                brwtodbTblVector.addElement(tstring[2]);
                brwtodbStringVector.addElement(tstring[3]);
                quicklist.replaceItem(tstring[0], i);
            }
        } catch (Exception _ex) {
            parent.setQstatus(_ex.toString(), false);
            return;
        }

    }

    public void savenewlist(String s) {
        String trimmedS = s.trim();
        FileOutputStream fileoutputstream = null;
        DataOutputStream dataoutputstream = null;
        try {
            fileoutputstream = new FileOutputStream(parent.udString + "/" + "cbox/data/dbcomms/brwtodb.prcd");
            BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(fileoutputstream);
            dataoutputstream = new DataOutputStream(bufferedoutputstream);
        } catch (IOException ioexception) {
            parent.setQstatus(ioexception.toString(), false);
        }
        try {
            dataoutputstream.writeBytes(trimmedS);
            dataoutputstream.flush();
            fileoutputstream.close();

        } catch (IOException ioexception1) {
            parent.setQstatus(ioexception1.toString(), false);
        }

        loadWatchlist();
        // parent.setQstatus(parent.aplangstrings.getProperty("text067"), false);
    }

    // to delete ??
    public void saveWatchlist() {
        try {
            String s = txtfldBrwToDBUrl.getText();
            quicklist.addItem(s);
            FileOutputStream fileoutputstream = new FileOutputStream(parent.udString + "/" + "cbox/iecanvaz/links.ini");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int i = 0; i < quicklist.getItemCount(); i++)
                printstream.println(quicklist.getItem(i));

            printstream.close();
            fileoutputstream.close();
            new Messdialog1(parent, parent.aplangstrings.getProperty("text051"));
        } catch (Exception exception) {
            System.out.println(exception);
        }
        loadWatchlist();
    }


    // end to delete ??
/*
    public static void main(String args[])
    {
    }
*/
    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == txtfldBrwToDBUrl) {
            parent.gotoit(txtfldBrwToDBUrl.getText(), thebrow);
        }

        if (obj == jbtnCloseBDBA) {
            dispose();
        }
        if (obj == jbtnAddBDBAction) {
            saveActionlist();
        }
        if (obj == jbtnDeleteBDBAction) {
            delDBAAction();
        }

    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void mouseEntered(MouseEvent mouseevent) {
        Component component = mouseevent.getComponent();
        if (component == btnbrowlaunch) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text196"));
            return;
        }
        if (component == searchbut) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text047"));
            return;
        }
        if (component == chandelete) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text052"));
            return;
        }
        if (component == addchan) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text053"));
            return;
        }
        if (component == bugReport) {
            tiplabel1.setText("Fale connosco");
            return;
        }
        if (component == bold) {
            if (!isbolder) {
                tiplabel1.setText(parent.aplangstrings.getProperty("text020"));
                return;
            } else {
                tiplabel1.setText(parent.aplangstrings.getProperty("text021"));
                return;
            }
        } else {
            return;
        }
    }

    public void mouseExited(MouseEvent mouseevent) {
        tiplabel1.setText("BrowserToDB Actions Box");
    }

    public void isitBolder() {
        if (!isbolder) {
            resize(300, 48);
            setLocation(200, 2);
            isbolder = true;
            return;
        }
        if (isbolder) {
            resize(300, 500);
            setLocation(320, 20);
            isbolder = false;
            return;
        } else {
            return;
        }
    }


    public void dbqdummy() {
        if (!isbolder) {
            parent.setQstatus(parent.aplangstrings.getProperty("text005"), false);
        }
        if (isbolder) {
            resize(300, 500);
            setLocation(320, 20);
            isbolder = false;
            return;
        } else {
            return;
        }
    }

    public void delDBAAction() {
        String s1 = quicklist.getSelectedItem();
        int i = quicklist.getSelectedIndex();
        if (s1 == null) {
            parent.setQstatus(parent.aplangstrings.getProperty("text048"), true);
            return;
        } else {
            String actionToGO = brwtodbUrlVector.elementAt(i).toString() + "[|]" + brwtodbDBVector.elementAt(i).toString() + "[|]" + brwtodbTblVector.elementAt(i).toString() + "[|]" + brwtodbStringVector.elementAt(i).toString();
            System.out.println("delDBAAction: " + i + " | " + actionToGO);
            String actionToGOA = actionToGO + "\n";
            allActionsString = adminApp.replaceString(allActionsString, actionToGOA, "");
            String actiongosa = adminApp.replaceString(allActionsString, "actionToGO", "");
            // quicklist.remove(i);
            System.out.println("its deleted: " + actiongosa);
            savenewlist(actiongosa);
        }
    }


    public void saveActionlist() {
        String st = txtfldBrwToDBUrl.getText().trim();
        int qt = st.length();
        String bdaQstring = txtFldBrwDBAString.getText().trim();
        int qi = 0;
        if ((st.trim().length() < 1) || (st.indexOf("about:blank") != -1)) {
            parent.setQstatus(parent.aplangstrings.getProperty("text043"), false);
            return;
        }
        for (int i = 0; i < quicklist.getItemCount(); i++) {
            String qselstring = quicklist.getItem(i).trim();
            qi = qselstring.length();
            if (st.indexOf(qselstring) != -1) {
                if (qi == qt) {
                    parent.setQstatus(parent.aplangstrings.getProperty("text059"), false);
                    return;
                }
            }
        }

        if (bdaQstring.indexOf("Browser Token") != -1) {
            if (st.lastIndexOf("=") != -1) {
                st = st.substring(0, st.lastIndexOf("=") + 1);
            } else {
                parent.setQstatus(parent.aplangstrings.getProperty("text128") + "\n" + parent.aplangstrings.getProperty("text129"), false);
                return;
            }
        }

        String aa = parent.aplangstrings.getProperty("text028") + parent.aplangstrings.getProperty("text086") + "?";
        String ee = parent.aplangstrings.getProperty("text028") + parent.aplangstrings.getProperty("text086") + "?";
        ee = ee + "\n\n" + parent.aplangstrings.getProperty("text211") + " (or variant):\n" + st;
        ee = ee + "\n\n" + parent.aplangstrings.getProperty("text079") + ": " + currDbaDBstring;
        ee = ee + "\n\n" + parent.aplangstrings.getProperty("text089") + ":\n" + bdaQstring;
        ee = ee + "\n\n" + parent.aplangstrings.getProperty("text133");

        MsgBox message = new MsgBox(parent, aa, ee, true);
        requestFocus();
        if (message.id) {
            message.dispose();

            try {
                String s = allActionsString + "\n" + st + "[|]" + currDbaDBstring + "[|]" + currDbaTblstring + "[|]" + txtFldBrwDBAString.getText();
                savenewlist(s);
            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else {
            message.dispose();
            System.out.println("Cancel pressed");
        }
    }


    Vector brwtodbStringVector;
    Vector brwtodbDBVector;
    Vector brwtodbUrlVector;
    Vector brwtodbTblVector;

    String currDbaDBstring;
    String currDbaTblstring;
    String inifile;
    String dbactionQ;
    String allActionsString;


    JLabel lblActionUrls;
    JLabel tiplabel1;
    JLabel lblDBtoApply;
    JButton jbtnCloseBDBA;
    JButton jbtnAddBDBAction;
    JButton jbtnDeleteBDBAction;
    java.awt.List quicklist;
    Choice searchChoice;
    JTextField txtFldBrwDBAString;
    JTextField txtFldQBrwString;
    JTextField txtfldBrwToDBUrl;
    protected boolean isbolder;
    Image img1;
    Image img5;
    Image img6;
    Image img7;
    Image img8;
    Image img12;
    ImageButton chandelete;
    ImageButton addchan;
    ImageButton bold;
    ImageButton btnbrowlaunch;
    ImageButton quicklinks;
    ImageButton searchbut;
    ImageButton bugReport;


    Properties httpconfig;
    adminApp parent;


    MediaTracker tracker;
    PopupMenu m_menu;
    private MenuItem m_red;
    int thebrow;

}
