package njfbrowser.spreadsheet;
 
 

import njfbrowser.main.adminApp;
import njfbrowser.misc.HelpWindow;
import njfbrowser.misc.ImageButton;
import njfbrowser.misc.MsgBox;
import njfbrowser.misc.alertDialog;
import njfbrowser.tasks.taskDBQBoxQuery;
import njfbrowser.tasks.taskDBQBoxReloadDB;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

public class DBQueryBox extends JDialog
        implements ActionListener, KeyListener, ItemListener, MouseListener {

    public class DBQueryBoxWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            // parent.boolDBQBoxOpen = false;

            hide();
            // dispose();
            // System.exit(0);
        }


        public DBQueryBoxWindowListener() {
        }
    }


    public void keyPressed(KeyEvent keyevent) {
    }

    public void keyTyped(KeyEvent keyevent) {
        //  if(keyevent.getSource() == dbCriteriaAfield) {

    }

    public void keyReleased(KeyEvent keyevent) {
        if ((keyevent.getSource() == dbCriteriaAfield) || (keyevent.getSource() == dbCriteriaBfield) || (keyevent.getSource() == dbCriteriaCfield) || (keyevent.getSource() == fieldResultLimit)) {
            //  if((keyevent.getSource() == dbCriteriaAfield) || (keyevent.getSource() == dbCriteriaBfield) || (keyevent.getSource() == dbCriteriaCfield))   {

            giveThequery(true, false);
        }
    }


    public void getLangString(String s) {
        if (s == "blank") {
            setTipLable(parent.aplangstrings.getProperty("text079", "Database") + ": " + dbsDBQChoice.getSelectedItem() + "   ->   " + parent.aplangstrings.getProperty("text032", "Table") + ": " + dbTablesAchoice.getSelectedItem());

        } else {
            dbQtiplabel.setText(" " + parent.aplangstrings.getProperty(s));
        }
    }

    public void setTipLable(String s) {

        dbQtiplabel.setText(s);

    }

    public void delTheRecords() {
        String aa = parent.aplangstrings.getProperty("text131") + "?";
        String ee = QQTextArea.getText();
        MsgBox message = new MsgBox(parent, aa, ee, true);
        requestFocus();
        if (message.id) {
            message.dispose();
            parent.writeFile(dbsDBQChoice.getSelectedItem(), ee);
//       parent.setDBQQuery(ee, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem());
        }
        if (!message.id) {
            message.dispose();
            System.out.println("Cancel pressed");
        }

    }


    public void runTAreaQ() {
        // System.out.println("runTAreaQ()");
        // parent.runTAreaQ();
    }


    public void setDBChoice(String choicedDB, String theCallBack) {
        System.out.println("XX_setDBChoice: 147 - choicedDB: " + choicedDB + " - theCallBack: " + theCallBack);

        dbsDBQChoice.removeAll();
        File file;
        String list[];
        dbListVector.removeAllElements();
	currStrDBTitles = "";
        try {
            file = new File(parent.getUfile("cbox/data/dbs/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    String dbsString = parent.getFileToTitle(list[i]);

                    dbsDBQChoice.addItem(dbsString);
			  currStrDBTitles += dbsString + "::";

                }
            }


        } catch (Exception ex) {
            parent.setQstatus("Error 163B [DBQueryBox]: \n" + ex.toString(), false);
            System.out.println(ex.toString());
        } finally {
            try {
                selectDBChoice(choicedDB, theCallBack);

            } catch (Exception ex) {
                parent.setQstatus("Error 173B [DBQueryBox]: \n" + ex.toString(), false);
            }
        }

    }


    public void selectDBChoice(String choicedDB, String theCallBack) {
        System.out.println("XX_selectDBChoice: 182 - choicedDB: " + choicedDB + " - theCallBack: " + theCallBack);
        try {
            dbsDBQChoice.select(choicedDB);
            loadDBSscheme(choicedDB);

        } catch (Exception ex) {
            parent.setQstatus("Error 187C [DBQueryBox]: \n" + ex.toString(), false);
        } finally {
            try {
                // selectDBChoice(choicedDB, theCallBack);
                System.out.println("aa");
            } catch (Exception ex) {
                parent.setQstatus("Error 174B [DBQueryBox]: \n" + ex.toString(), false);
            }
        }


    }


    public void getDBQdbs(boolean justdbs) {
        System.out.println("XX_getDBQdbs: 209 - justdbs: " + justdbs);
        dbsDBQChoice.removeAll();
        File file;
        String list[];
        dbListVector.removeAllElements();
	  currStrDBTitles = "";
        try {
            file = new File(parent.getUfile("cbox/data/dbs/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    String dbsString = parent.getFileToTitle(list[i]);

                    dbsDBQChoice.addItem(dbsString);
			  currStrDBTitles += dbsString + "::";
                }
            }
        } catch (Exception ex) {
            parent.setQstatus("Error 156A [DBQueryBox]: \n" + ex.toString(), false);
            ex.printStackTrace();
            return;
        }
 
            loadDBSscheme(dbsDBQChoice.getSelectedItem());
 
 
    }


    public void isitDBQBolder() {
        System.out.println("XX_isitDBQBolder: 241 - boolDbQBold: " + boolDbQBold);

        if (!boolDbQBold) {
            hide();
            boolDbQBold = true;
            return;
          /*
            resize(300, 48);
            setLocation(320, 4);
		*/

        }
        if (boolDbQBold) {
            show();
            // resize(550, 350);
            //  setLocation(50, 100);
            //   pack();
            boolDbQBold = false;
            return;
        } else {
            return;
        }
    }


    public void dbqdummy() {
        if (boolDbQBold) {
            show();
            // resize(550, 350);
            setLocation(50, 100);
            pack();
            boolDbQBold = false;
            return;
        } else {
            show();
            // parent.setQstatus(parent.aplangstrings.getProperty("text014"), false);
        }
    }


    public void doQ(String qstr) {
        checkCurrentDB();
        QQTextArea.setText(qstr);
        runTextQ();
    }


    public void setTheDefaultCursor() {
        setCursor(java.awt.Cursor.getPredefinedCursor(0));

    }

    public void setTheHandCursor() {
        setCursor(java.awt.Cursor.getPredefinedCursor(12));

    }

    public void setTheQstatus(String s) {
        parent.setQstatus(s, false);
    }


    public void setTheWaitCursor() {
        // parent.setWaitCursor();
        setCursor(java.awt.Cursor.getPredefinedCursor(3));

    }


    public void dloadProgressChange(int i, int j) {
        // dloadBar.start();
    }


    public String getOpsChoice(String s, String s1) {
        String opsCstring = s;
        String qCritstring = s1;
        String transOpsString = "";


        if (opsCstring == parent.aplangstrings.getProperty("text605")) {
            transOpsString = " like " + "'%" + qCritstring + "%'";
        }
        if (opsCstring == parent.aplangstrings.getProperty("text606")) {
            transOpsString = " = " + "'" + qCritstring + "'";
        }
        if (opsCstring == parent.aplangstrings.getProperty("text607")) {
            transOpsString = " like " + "'" + qCritstring + "%'";
        }
        if (opsCstring == parent.aplangstrings.getProperty("text608")) {
            transOpsString = " like " + "'%" + qCritstring + "'";
        }
        if (opsCstring == parent.aplangstrings.getProperty("text609")) {
            transOpsString = " not like " + "'" + qCritstring + "'";
        }
        if (opsCstring == parent.aplangstrings.getProperty("text610")) {
            transOpsString = " > " + "'" + qCritstring + "'";
        }
        if (opsCstring == parent.aplangstrings.getProperty("text611")) {
            transOpsString = " < " + "'" + qCritstring + "'";
        }

	/*
       if(opsCstring == "Contains") {
       transOpsString = " like " + "'%" + qCritstring + "%'";
       }
       if(opsCstring == "Equals") {
       transOpsString = " = " + "'" + qCritstring + "'";
       }
       if(opsCstring == "Starts With") {
       transOpsString = " like " + "'" + qCritstring + "%'";
       }
       if(opsCstring == "Ends With") {
       transOpsString = " like " + "'%" + qCritstring + "'";
       }
       if(opsCstring == "Unlike") {
       transOpsString = " not like " + "'" + qCritstring + "'";
       }
       if(opsCstring == "Greater Then") {
       transOpsString = " > " + "'" + qCritstring + "'";
       }
       if(opsCstring == "Less Then") {
       transOpsString = " < " + "'" + qCritstring + "'";
       }     
	*/
        return transOpsString;
    }


    public String getFieldsBoolChoice(String s) {
        String transFBstring = "and";
        String fldsBoolstring = s;
        if (fldsBoolstring == parent.aplangstrings.getProperty("text613")) {
            transFBstring = "or";
        }
        return transFBstring;
    }


    public void giveThequery(boolean addel, boolean showrun) {

        System.out.println("XX_giveThequery: 355 - addel: " + addel + " - showrun: " + showrun);

        String dbCritAfldString = dbCriteriaAfield.getText();
        String dbCritBfldString = dbCriteriaBfield.getText();
        String dbCritCfldString = dbCriteriaCfield.getText();
        String strResultLimit = fieldResultLimit.getText();
        String dummyString = "no";

        try {
            Integer.parseInt(strResultLimit);
        } catch (Exception e) {
            fieldResultLimit.setText("50");
        }

        strResultLimit = fieldResultLimit.getText();

        parent.fieldname = dbFieldsAchoice.getSelectedItem();
        String dbqString = "";
        if (addel) {
            dbqString = "select * from " + dbTablesAchoice.getSelectedItem();
        } else {
            dbqString = "delete from " + dbTablesAchoice.getSelectedItem();
        }


        QQTextArea.setText(dbqString + " limit " + strResultLimit + ";");

        if (dbCritAfldString.length() < 1) {


	  /* must start with first table field criteria or screws up SQL statement */

            if ((dbCritBfldString.length() > 0) || (dbCritCfldString.length() > 0)) {
                dbCriteriaBfield.setText("");
                dbCriteriaCfield.setText("");
                dbCriteriaAfield.requestFocus();
                return;
            }


        } else {
            dbqString = dbqString + " where " + dbFieldsAchoice.getSelectedItem();
            dbqString = dbqString + getOpsChoice(dbFieldsOpsAchoice.getSelectedItem(), dbCritAfldString);
        }
        if (dbCritBfldString.length() > 0) {
            dbqString = dbqString + " " + getFieldsBoolChoice(dbFieldsBoolsBchoice.getSelectedItem()) + " " + dbFieldsBchoice.getSelectedItem();
            dbqString = dbqString + getOpsChoice(dbFieldsOpsBchoice.getSelectedItem(), dbCritBfldString);
        }

        if (dbCritCfldString.length() > 0) {
            dbqString = dbqString + " " + getFieldsBoolChoice(dbFieldsBoolsCchoice.getSelectedItem()) + " " + dbFieldsCchoice.getSelectedItem();
            dbqString = dbqString + getOpsChoice(dbFieldsOpsCchoice.getSelectedItem(), dbCritCfldString);
        }
        dbqString = dbqString + " order by " + choiceSortByA.getSelectedItem() + " " + choiceSortOrderA.getSelectedItem() + " limit " + strResultLimit;

        dbqString = dbqString + ";";
        QQTextArea.setText(dbqString);
        if (showrun) {
            if (addel) {
                runTextQ();
                // parent.setDBQQuery(dbqString, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem());

            } else {
                delTheRecords();
            }
        }
    }

    public void runTextQ() {
        String mBoxtitle = parent.aplangstrings.getProperty("text036") + "?!";
        String mBoxtext = "";
        int idb = dbsDBQChoice.getItemCount();
        if ((idb <= 1) || (parent.currentDBID.equals("555"))) {
            parent.setQstatus(parent.aplangstrings.getProperty("text326"), false);
            return;
        }

// downloadBar.start();
// setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        System.out.println("XX_runTextQ: 409");
        String rQstring = QQTextArea.getText();
        if (rQstring.length() < 5) {
            parent.setQstatus(parent.aplangstrings.getProperty("text199"), false);
            QQTextArea.requestFocus();
            return;
        } else if (rQstring.toLowerCase().startsWith("select")) {
/* TODO: here is where you would add do not ask for below select message box */
/* add it as Menu Item */
            String dummyval = "Im stumped";
            mBoxtext = rQstring;
        } else if (rQstring.toLowerCase().startsWith("qry")) {
            String dummyval = rQstring.substring(3, rQstring.length());
            mBoxtext = dummyval;
            rQstring = mBoxtext;
        } else {

            String s5 = adminApp.replaceString(rQstring, "\n", " ");
            String s4 = adminApp.replaceString(s5, ";", "\n");
            parent.startComtextQuery(s4);
            giveThequery(true, false);
            return;
        }


            startDBQBoxQuery(rQstring, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem());

/* not using  confirm
        MsgBox message = new MsgBox(parent, mBoxtitle, mBoxtext, true);
        requestFocus();
        if (message.id) {
            message.dispose();
            startDBQBoxQuery(rQstring, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem());

//parent.startDBQBoxQuery(rQstring, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem());
        } else {
            message.dispose();
        }
*/


    }

    public void confSavePrefs() {
        MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text100"), parent.aplangstrings.getProperty("text117"), true);
        requestFocus();
        if (message.id) {
            message.dispose();
            saveDBS();
            System.out.println("Ok pressed");
        }
        if (!message.id) {
            message.dispose();
            System.out.println("Cancel pressed");
        }
    }


    public void getmyqsfile() {
        myquerylist.removeAll();
        try {
            FileInputStream fileinputstream = new FileInputStream("cbox/savedqs/myqs.txt");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null)
                sortmyqList(s);
            datainputstream.close();
            fileinputstream.close();
            return;
        } catch (Exception exception) {
            parent.setQstatus(parent.aplangstrings.getProperty("text532"), false);
        }
    }

    public void savemyqsfile() {
        try {
            parent.currentDB = dbnameDBQField.getText();
            parent.tablename = Qtablefield.getText();
            parent.fieldname = Qcolumnfield.getText();
            parent.criterianame = Qcriteriafield.getText();
            String s = parent.currentDB + "\t" + parent.tablename + "\t" + parent.fieldname + "\t" + parent.criterianame;
            myquerylist.addItem(s);
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/data/dbs/myqs.txt");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int i = 0; i < myquerylist.getItemCount(); i++)
                printstream.println(myquerylist.getItem(i));

            printstream.close();
            fileoutputstream.close();
            parent.setQstatus(parent.aplangstrings.getProperty("text518"), false);
        } catch (Exception exception) {
            System.out.println(exception);
            parent.setQstatus("Error 402A [DBQueryBox]: \n" + exception.toString(), false);
        }
        getmyqsfile();
    }


    public void sortmyqList(String s) {
        int i = 0;
        for (int j = myquerylist.countItems() - 1; i < j; ) {
            int k = (i + j) / 2;
            if (s.toLowerCase().compareTo(myquerylist.getItem(k).toLowerCase()) > 0)
                i = k + 1;
            else
                j = k;
        }

        myquerylist.addItem(s, i);
    }


    public void runMyqlist() {
        parent.setWaitCursor();
        String s = myquerylist.getSelectedItem();
        myquerylist.getSelectedIndex();
        if (s == null) {
            parent.setQstatus("No Saved Query Selected....", true);
            return;
        }
        dbnameDBQField.setText("");
        Qtablefield.setText("");
        Qcolumnfield.setText("");
        Qcriteriafield.setText("");
        try {
            for (StringTokenizer stringtokenizer = new StringTokenizer(s, "\t"); stringtokenizer.hasMoreTokens(); Qcriteriafield.setText(stringtokenizer.nextToken())) {
                dbnameDBQField.setText(stringtokenizer.nextToken());
                Qtablefield.setText(stringtokenizer.nextToken());
                Qcolumnfield.setText(stringtokenizer.nextToken());
            }

        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Limited Array");
            parent.QstatusTextArea.setText("STS639EX: Limited Array");
        }
        if (dbnameDBQField.getText().length() < 1) {
            parent.setQstatus("Enter a Database!", true);
            return;
        }
        if (Qtablefield.getText().length() < 1) {
            parent.setQstatus("Enter a Table!", true);
            return;
        } else {
            parent.currentDB = dbnameDBQField.getText();
            parent.tablename = Qtablefield.getText();
            parent.fieldname = Qcolumnfield.getText();
            parent.criterianame = Qcriteriafield.getText();
            parent.QqueryTextArea.setText("");
            // parent.setQuery(parent.dbasename, parent.tablename, parent.fieldname, parent.criterianame);

            parent.setDefaultCursor();
            return;
        }
    }


    public void savemynewQ() {
        String s = myquerylist.getSelectedItem();
        myquerylist.getSelectedIndex();
        if (s.length() < 0) {
            parent.setQstatus(parent.aplangstrings.getProperty("text048"), false);
            return;
        }
        int i = myquerylist.getSelectedIndex();
        myquerylist.remove(i);
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/data/dbs/myqs.txt");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int j = 0; j < myquerylist.getItemCount(); j++)
                printstream.println(myquerylist.getItem(j));

            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            System.out.println(exception);
            parent.QstatusTextArea.setText("STS836EX: " + exception.toString());
        }
        parent.setQstatus(parent.aplangstrings.getProperty("text519"), false);
        getmyqsfile();
    }


    public void getcleanQfromfile(String s, String s1) {
        parent.QqueryTextArea.append("getcleanQfromfile" + s + s1);
    }


    public void addQuery() {
        savemyqsfile();
    }
/*

    public void getBrwsrDBActnPan()
    {
    mainBrDBPan = new JPanel(new BorderLayout());
    lblBrDBActName = new JLabel(parent.aplangstrings.getProperty("text207")); 
    txtfldBrDBActName = new JTextField("");
    lblBrDBUrl = new JLabel(parent.aplangstrings.getProperty("text211"));
    txtfldBrDBUrl = new JTextField("");
    
    
    }
*/

    public DBQueryBox(adminApp admindbapp, String s) {
        super(admindbapp, "");


        parent = admindbapp;

        setTitle(parent.aplangstrings.getProperty("text322", "DataBase Query Box "));
        strngDBQBTempDB = "";
        strngDBQBTempTable = "";
        strngDBQBTempQStrng = ""; // temp query string
        tempDBQBoxResString = ""; // the actual connection stream results given back
	  currStrDBTitles = "";
        getContentPane().setLayout(new BorderLayout());
        boolDbQBold = false;
        boolminChck = false;
        defaultDB = "";
        defaultDBname = "";
        tracker = new MediaTracker(this);
        img1DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/dbqrundbq.gif"));
        img2DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/getdbqbold.gif"));
        img3DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/reloaddb.gif"));
        img4DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/deletedb.gif"));
        img5DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/adddb.gif"));
        img6DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/deldbqs.gif"));
        img7DBQ = Toolkit.getDefaultToolkit().getImage(parent.getUfile("cbox/images/editdbinfo.gif"));

        tracker.addImage(img1DBQ, 1);
        tracker.addImage(img2DBQ, 2);
        tracker.addImage(img3DBQ, 3);
        tracker.addImage(img4DBQ, 4);
        tracker.addImage(img5DBQ, 5);
        tracker.addImage(img6DBQ, 6);
        tracker.addImage(img7DBQ, 7);
        try {
            tracker.waitForAll();
        } catch (InterruptedException _ex) {
            parent.setStatusText(_ex.toString());
        }


        mbarQbx = new JMenuBar();
        mbarQbx.setBorderPainted(true);
        mnuQbxTools = new JMenu(parent.aplangstrings.getProperty("text139", "File"));

        jmnuHelp = new JMenu(parent.aplangstrings.getProperty("text012", "Help"));
        jmnuHelp.setForeground(new Color(0, 0, 255)); //XXX windows lnf?
        // mnuQbxTools.setMnemonic((int)(new String("File").charAt(0)));
        mnuQbxCurrDB = new JMenu(parent.aplangstrings.getProperty("text079", "Database"));
        // mnuQbxCurrDB.setMnemonic('D');


        mnuQbxTools.getPopupMenu().setLightWeightPopupEnabled(false);
        jmnuHelp.getPopupMenu().setLightWeightPopupEnabled(false);
        mnuQbxCurrDB.getPopupMenu().setLightWeightPopupEnabled(false);


        mbarQbx.add(mnuQbxTools);
        mbarQbx.add(mnuQbxCurrDB);
        mbarQbx.add(jmnuHelp);


        mnuiQbxDBsettings = new JMenuItem(parent.aplangstrings.getProperty("text006", "DataBase Settings"));
        // mnuiQbxDBsettings.setToolTipText("Database Settings");
        mnuiQbxDBdelete = new JMenuItem(parent.aplangstrings.getProperty("text503", "Remove DataBase Information"));
        mnuiQbxDBreload = new JMenuItem(parent.aplangstrings.getProperty("text504", "Reload Database Information"));

        mnuiQbxDBadd = new JMenuItem(parent.aplangstrings.getProperty("text130", "Add Database"));
        mnuiQbxQuit = new JMenuItem("Exit");


        mnuiHelpTopics = new JMenuItem(parent.aplangstrings.getProperty("text414", "Help Topics"));
        mnuiHelpWThis = new JMenuItem(parent.aplangstrings.getProperty("text415", "Whats This?"), new ImageIcon(parent.getUfile("cbox/images/iconHelp.gif")));
        jmnuHelp.add(mnuiHelpTopics);
        jmnuHelp.addSeparator();
        jmnuHelp.add(mnuiHelpWThis);


        mnuQbxCurrDB.add(mnuiQbxDBsettings);
        mnuQbxCurrDB.add(mnuiQbxDBreload);
        mnuQbxCurrDB.addSeparator();
        mnuQbxCurrDB.add(mnuiQbxDBadd);
        mnuQbxCurrDB.addSeparator();
        mnuQbxCurrDB.add(mnuiQbxDBdelete);


        mnuQbxTools.addSeparator();
        mnuQbxTools.add(mnuiQbxQuit);

        mnuiQbxDBsettings.addActionListener(this);
        mnuiQbxDBreload.addActionListener(this);
        mnuiQbxDBdelete.addActionListener(this);
        mnuiQbxDBadd.addActionListener(this);
        mnuiQbxQuit.addActionListener(this);

        mnuiHelpTopics.addActionListener(this);
        mnuiHelpWThis.addActionListener(this);


        // parent.setIconImage(img1DBQ);
        minChckBx = new JCheckBox(parent.aplangstrings.getProperty("text070"));
        minChckBx.addActionListener(this);


        progressBar = new JProgressBar(10, 100);

        Border pbBorder = progressBar.getBorder();

        Border spaceBelow = BorderFactory.createEmptyBorder(0, 0, 5, 0);

        progressBar.setBorder(BorderFactory.createCompoundBorder(spaceBelow, pbBorder));

        progressBar.setPreferredSize(new Dimension(100, 15));
        progressBar.setOpaque(true);
        // !!xbug blip.setBorder(new CompoundBorder(new CompoundBorder(new EmptyBorder(new Insets(1, 1, 1, 1)), new LineBorder(getForeground())), new EmptyBorder(new Insets(1, 1, 1, 1))));
        progressBar.setForeground(getForeground());
        JPanel pnlDloadBar = new JPanel(new FlowLayout(0));
        // pnlDloadBar.add(downloadBar);
        pnlDloadBar.add(progressBar);


        ButDBQRunDBQ = new ImageButton("Get databases", img1DBQ);
        ButDBQRunDBQ.addActionListener(this);
        ButDBQRunDBQ.addMouseListener(this);

        ButDBQBold = new ImageButton("MaxMin", img2DBQ);
        ButDBQBold.addActionListener(this);
        ButDBQBold.addMouseListener(this);

        ButDBQReloadDB = new ImageButton("Reload DB", img3DBQ);
        ButDBQReloadDB.addActionListener(this);
        ButDBQReloadDB.addMouseListener(this);

        ButDBQDeleteDB = new ImageButton("Delete DB", img4DBQ);
        ButDBQDeleteDB.addActionListener(this);
        ButDBQDeleteDB.addMouseListener(this);


        ButDBQAddDB = new ImageButton("Add DB", img5DBQ);
        ButDBQAddDB.addActionListener(this);
        ButDBQAddDB.addMouseListener(this);

        ButDelDBQs = new ImageButton("Del Records", img6DBQ);
        ButDelDBQs.addActionListener(this);
        ButDelDBQs.addMouseListener(this);

        ButEditDB = new ImageButton("Edit Database Info", img7DBQ);
        ButEditDB.addActionListener(this);
        ButEditDB.addMouseListener(this);


//      this stuff is not used.


        QqueryInfolabel = new JLabel(" Query Info:");
        Qstatus = new JLabel("Query-Command Status:");

        Qdbaselabel = new JLabel("  " + parent.aplangstrings.getProperty("text079", "Database") + ": ");
        Qtablelabel = new JLabel(" Table: ", 2);
        Qcolumnlabel = new JLabel(" Field: ");
        Qcriterialabel = new JLabel(" Criteria: ");


        Qtablefield = new JTextField();
        Qcolumnfield = new JTextField();
        Qcriteriafield = new JTextField();
        Qcriteriafield.addActionListener(this);

// 
        dbListVector = new Vector();


        addDbsLabel = new JLabel("  " + parent.aplangstrings.getProperty("text016") + " ");
        dbnameLabel = new JLabel("  " + parent.aplangstrings.getProperty("text305") + " ");
        dbnameDBQField = new JTextField();

        JPanel addDBInptPan = new JPanel(new BorderLayout(1, 1));
        JPanel addDBPan = new JPanel(new BorderLayout(1, 1));


        addDBInptPan.add("West", dbnameLabel);
        addDBInptPan.add("Center", dbnameDBQField);

        addDBPan.add("West", addDbsLabel);
        addDBPan.add("Center", addDBInptPan);
        // delete this!!  addDBPan.add("East", btnAddDB);


        Border dbqetchedBdr = BorderFactory.createEtchedBorder();
        Border dbqemptyBdr = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        Color dbqnrclr = new Color(180, 60, 0);
        Color dbqshclr = new Color(0, 60, 180);

        Border addPanTtlBdr = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text016") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border addCompoundBdr = BorderFactory.createCompoundBorder(addPanTtlBdr, dbqemptyBdr);
        addDBPan.setBorder(addCompoundBdr);


        btnDBsettings = new JButton(parent.aplangstrings.getProperty("text006"));
        btnDBsettings.setToolTipText("Database Settings");
        btnDBreload = new JButton(parent.aplangstrings.getProperty("text504", "Reload Database Information"));
        btnDBreload.setToolTipText(parent.aplangstrings.getProperty("text504", "Reload Database Information"));


        btnaaSpclose = new JButton(parent.aplangstrings.getProperty("text507"));

        dbsDBQChoice = new Choice();


	  /* !! start deleting this stuff
	  */
        QrunQs = new DBQBoxCommLabel(this, "Run Query", "runquery");
        QrunTextQ = new DBQBoxCommLabel(this, "Run Query", "runtextquery");
        deleteQs = new DBQBoxCommLabel(this, "Delete Record(s)", "deleterecords");
        minmaxCommLbl = new DBQBoxCommLabel(this, "Min-Max(imize)", "minmax");
        savethisQ = new DBQBoxCommLabel(this, "Save Criteria", "savethequery");
        lblReloadDB = new DBQBoxCommLabel(this, "Reload DataBase", "reloadDB");
        browDBaction = new DBQBoxCommLabel(this, parent.aplangstrings.getProperty("text086"), "brwDBaction");


        myquerylist = new java.awt.List(4, false);
        myquerylist.addActionListener(this);
        myquerylist.addItemListener(this);


        JPanel dbqbtmPanel = new JPanel(new BorderLayout());
        dbqbtmPanel.add("East", btnaaSpclose);
        // dbqbtmPanel.add("East", minmaxCommLbl);
        dbqbtmPanel.add("West", minChckBx);
        // dbqbtmPanel.add("North", addDBPan);


        JPanel dbdbDBQBtnPan = new JPanel(new GridLayout(0, 3));
        dbdbDBQBtnPan.add(ButDBQReloadDB);
        // dbdbDBQBtnPan.add(ButDBQAddDB);
        dbdbDBQBtnPan.add(ButDBQDeleteDB);
        dbdbDBQBtnPan.add(ButEditDB);


        btnAddDB = new JButton(parent.aplangstrings.getProperty("text130"));
        btnAddDB.setToolTipText(parent.aplangstrings.getProperty("text016"));
        btnAddDB.addActionListener(this);


        JPanel panBtnsDBEdit = new JPanel(new BorderLayout());
        // panBtnsDBEdit.add("Center", dbdbDBQBtnPan);
        panBtnsDBEdit.add("Center", btnAddDB);
        panBtnsDBEdit.add("East", btnDBreload);

        JPanel dbdbDBQPanel = new JPanel(new BorderLayout());
        dbdbDBQPanel.add("West", Qdbaselabel);
        dbdbDBQPanel.add("Center", dbsDBQChoice);
        // dbdbDBQPanel.add("East", panBtnsDBEdit);
        dbdbDBQPanel.add("East", btnDBsettings);

        JPanel dbdbDBQMPan = new JPanel(new BorderLayout());
        dbdbDBQMPan.add("West", dbdbDBQPanel);
        // dbdbDBQMPan.add("East", btnDBreload);
        // dbdbDBQMPan.add("East", dbdbDBQBtnPan);
        dbdbDBQMPan.add("Center", panBtnsDBEdit);
        dbdbDBQMPan.add("East", pnlDloadBar);

        dbTablesAchoice = new Choice();
        JPanel dbTableChoicePan = new JPanel(new BorderLayout(1, 1));
        dbTableChoicePan.add("West", new JLabel(" " + parent.aplangstrings.getProperty("text088") + ": "));
        dbTableChoicePan.add("Center", dbTablesAchoice);


        jeditPaneTables = new JEditorPane();
        jeditPaneTables.setEditable(false);

        HTMLEditorKit kitA = (HTMLEditorKit) jeditPaneTables.getEditorKitForContentType("text/html");
        jeditPaneTables.setEditorKit(kitA);
        //anonymous inner listener
        jeditPaneTables.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent ev) {
                try {

                    if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        System.out.println("URL getSourceElement: " + ev.getSourceElement());
                        System.out.println("URL: getDescription" + ev.getDescription());
                        String uEstring = ev.getDescription();
                        String newUEstring = "aa_view-";
                        if (uEstring.startsWith("aa_view-")) {
                            newUEstring = adminApp.replaceString(uEstring, "aa_view-", "");
                            uEstring = newUEstring;
                        }
                        dbTablesAchoice.select(uEstring);
                        String loadedDBID = parent.getDBID(dbsDBQChoice.getSelectedItem());
                        loadDBtblflds(loadedDBID, uEstring);
                        if (newUEstring.equals(uEstring)) {
                            giveThequery(true, true);
                        }
                    }
                } catch (Exception ex) {
                    //put message in window
                    ex.printStackTrace();
                }
            }
        });


        JScrollPane jscrollPaneTableList = new JScrollPane(jeditPaneTables);
        jscrollPaneTableList.setPreferredSize(new Dimension(250, 175));


        dbTablesAchoice.addMouseListener(this);

        dbFieldsOrderchoice = new Choice();

        QQTextArea = new JTextArea("", 3, 65);
        JScrollPane qqScrollPane = new JScrollPane(QQTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
// QQTextArea.setFont(new Font("Serif", 10));
        QQTextArea.setLineWrap(true);
        QQTextArea.setWrapStyleWord(true);
        whereLabel = new JLabel(" " + parent.aplangstrings.getProperty("text543", "Fields") + ": ");

        dbQtiplabel = new JLabel("               " + parent.aplangstrings.getProperty("text214", "Search Database") + "             ", JLabel.CENTER);
        dbQtiplabel.setForeground(new Color(0, 0, 165));


        dbFieldsAchoice = new Choice();
        dbFieldsBchoice = new Choice();
        dbFieldsCchoice = new Choice();

        labelOrderBy = new JLabel(" " + parent.aplangstrings.getProperty("text542", "Order by") + ": ");
        labelOrderBy.setToolTipText("Order the results by field in ascending or descending order");
        labelResultLimit = new JLabel("     " + parent.aplangstrings.getProperty("text544", "Number of Results") + ": ");
        fieldResultLimit = new JTextField(parent.prefsNumResults, 7);
        fieldResultLimit.setToolTipText(parent.aplangstrings.getProperty("text502", "Enter the number of results [rows] to return"));
        choiceSortByA = new Choice();

        choiceSortOrderA = new Choice();
        choiceSortOrderA.addItem("desc");
        choiceSortOrderA.addItem("asc");

        btnRunTquery = new JButton(parent.aplangstrings.getProperty("text505", "Run Query"));

        JPanel panelSortBy = new JPanel(new GridLayout(0, 5));
        // panelSortBy.add(labelOrderBy);
        // panelSortBy.add(choiceSortByA);
        // panelSortBy.add(choiceSortOrderA);
        //  panelSortBy.add(labelResultLimit);
        //  panelSortBy.add(fieldResultLimit);
        // panelSortBy.add(QrunTextQ);
        // panelSortBy.add(btnRunTquery);


        JPanel panelFullSortBy = new JPanel(new BorderLayout(1, 1));
        // panelFullSortBy.add("West", panelSortBy);
        // panelFullSortBy.add("Center", dbQtiplabel);

        JPanel dbFieldsPan = new JPanel(new GridLayout(4, 0, 1, 1));
        dbFieldsPan.add(dbFieldsAchoice);
        dbFieldsPan.add(dbFieldsBchoice);
        dbFieldsPan.add(dbFieldsCchoice);
        dbFieldsPan.add(choiceSortByA);

        dbFieldsOpsAchoice = new Choice();
        dbFieldsOpsBchoice = new Choice();
        dbFieldsOpsCchoice = new Choice();

        JPanel dbFieldsOpsPan = new JPanel(new GridLayout(4, 0, 1, 1));
        dbFieldsOpsPan.add(dbFieldsOpsAchoice);
        dbFieldsOpsPan.add(dbFieldsOpsBchoice);
        dbFieldsOpsPan.add(dbFieldsOpsCchoice);

        dbFieldsOpsPan.add(choiceSortOrderA);


        dbFieldsBoolsBchoice = new Choice();
        dbFieldsBoolsBchoice.addItem(parent.aplangstrings.getProperty("text612", "and"));
        dbFieldsBoolsBchoice.addItem(parent.aplangstrings.getProperty("text613", "or"));

        dbFieldsBoolsCchoice = new Choice();
        dbFieldsBoolsCchoice.addItem(parent.aplangstrings.getProperty("text612", "and"));
        dbFieldsBoolsCchoice.addItem(parent.aplangstrings.getProperty("text613", "or"));

        JPanel dbFieldsBoolsPan = new JPanel(new GridLayout(4, 0, 1, 1));
        dbFieldsBoolsPan.add(whereLabel);
        dbFieldsBoolsPan.add(dbFieldsBoolsBchoice);
        dbFieldsBoolsPan.add(dbFieldsBoolsCchoice);
        dbFieldsBoolsPan.add(labelOrderBy);


        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text605", "Contains"));
        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text606", "Equals"));
        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text607", "Starts With"));
        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text608", "Ends With"));
        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text609", "Unlike"));
        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text610", "Greater Then"));
        dbFieldsOpsAchoice.addItem(parent.aplangstrings.getProperty("text611", "Less Then"));

        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text605", "Contains"));
        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text606", "Equals"));
        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text607", "Starts With"));
        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text608", "Ends With"));
        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text609", "Unlike"));
        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text610", "Greater Then"));
        dbFieldsOpsBchoice.addItem(parent.aplangstrings.getProperty("text611", "Less Then"));


        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text605", "Contains"));
        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text606", "Equals"));
        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text607", "Starts With"));
        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text608", "Ends With"));
        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text609", "Unlike"));
        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text610", "Greater Then"));
        dbFieldsOpsCchoice.addItem(parent.aplangstrings.getProperty("text611", "Less Then"));

        dbCriteriaAfield = new JTextField(25);
        dbCriteriaBfield = new JTextField(25);
        dbCriteriaCfield = new JTextField(25);

        JPanel dbqClickPan = new JPanel(new BorderLayout());
        // dbqClickPan.add("West", ButDBQRunDBQ);
        dbqClickPan.add("Center", QrunQs);


        JPanel dbqMClickPan = new JPanel(new BorderLayout());
        // dbqMClickPan.add("West", ButDelDBQs);
        dbqMClickPan.add("Center", deleteQs);

        JPanel dbqMMClickPan = new JPanel(new GridLayout(0, 5));
        dbqMMClickPan.add(new JLabel("Action: "));
        dbqMMClickPan.add(browDBaction);
        dbqMMClickPan.add(dbqMClickPan);
        // dbqMMClickPan.add(QrunTextQ);
        dbqMMClickPan.add(dbqClickPan);


        JPanel pnlResLimit = new JPanel(new BorderLayout(0, 0));
        pnlResLimit.add("West", labelResultLimit);


        pnlResLimit.add("Center", fieldResultLimit);
        // pnlResLimit.add("East", new JLabel("       "));

        JPanel pnlFullResLimit = new JPanel(new BorderLayout(0, 0));
        pnlFullResLimit.add("West", pnlResLimit);
        // pnlFullResLimit.add("Center", dbQtiplabel);


        JPanel dbCritFieldsPan = new JPanel(new GridLayout(4, 0, 1, 1));

        dbCritFieldsPan.add(dbCriteriaAfield);
        dbCritFieldsPan.add(dbCriteriaBfield);
        dbCritFieldsPan.add(dbCriteriaCfield);
        dbCritFieldsPan.add(pnlFullResLimit); // had to fit both result limit label and field in this box


        JPanel topRow = new JPanel(new BorderLayout(1, 1));
        topRow.add("North", dbdbDBQMPan);
        // topRow.add("Center", dbTableChoicePan);

        JPanel firstRow = new JPanel(new BorderLayout(1, 1));
        firstRow.add("West", dbFieldsBoolsPan);
        firstRow.add("Center", dbFieldsPan);

        JPanel secondRow = new JPanel(new BorderLayout(1, 1));

        secondRow.add("West", dbFieldsOpsPan);
        secondRow.add("Center", dbCritFieldsPan);

        JPanel fullRow = new JPanel(new BorderLayout(1, 1));

        fullRow.add("West", firstRow);
        fullRow.add("Center", secondRow);
        fullRow.add("South", panelFullSortBy);

        JPanel pnlTextArea = new JPanel(new BorderLayout(1, 1));
        pnlTextArea.add("North", new JLabel(parent.aplangstrings.getProperty("text215", "SQL Query Text") + ": "));
        pnlTextArea.add("Center", qqScrollPane);
        pnlTextArea.add("East", btnRunTquery);


        JPanel dbqMMClickQQPan = new JPanel(new BorderLayout(6, 6));
        // dbqMMClickQQPan.add("North", new JLabel(" " + parent.aplangstrings.getProperty("text215") + ": "));        // dbqMMClickQQPan.add("North", new JLabel(" " + parent.aplangstrings.getProperty("text215") + ": "));
        dbqMMClickQQPan.add("North", fullRow);
        dbqMMClickQQPan.add("Center", pnlTextArea);


        JPanel panelTableSearch = new JPanel(new BorderLayout(6, 6));
        panelTableSearch.add("Center", jscrollPaneTableList);
        panelTableSearch.add("North", dbTableChoicePan);

        JPanel panelFullTableSearch = new JPanel(new BorderLayout(6, 6));
        panelFullTableSearch.add("West", panelTableSearch);
        panelFullTableSearch.add("Center", dbqMMClickQQPan);
        panelFullTableSearch.add("North", dbQtiplabel);
        Border dbqMMCQQBdr = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text300") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border dbqMMCQQCMPDBdr = BorderFactory.createCompoundBorder(dbqMMCQQBdr, dbqemptyBdr);
        panelFullTableSearch.setBorder(dbqMMCQQCMPDBdr);


        JPanel panel13 = new JPanel(new BorderLayout(6, 6));

        panel13.add("South", dbqbtmPanel);
        panel13.add("Center", panelFullTableSearch);
        // panel13.add("West", jscrollPaneTableList);


        JPanel panel18 = new JPanel(new BorderLayout());
        panel18.add("North", topRow);
        // panel18.add("Center", dbQtiptop);


        Border frowPanTtlBdr = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text214") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border frowCompoundBdr = BorderFactory.createCompoundBorder(frowPanTtlBdr, dbqemptyBdr);
        topRow.setBorder(frowCompoundBdr);


        JPanel panel19 = new JPanel(new BorderLayout());
        panel19.add("North", panel18);
        // panel19.add("Center", fullRow);

        panel19.add("South", panel13);
        // loadDBQProps();
        btnDBsettings.addActionListener(this);
        btnDBreload.addActionListener(this);
        dbTablesAchoice.addItemListener(this);
        btnaaSpclose.addActionListener(this);
        btnRunTquery.addActionListener(this);
        dbsDBQChoice.addItemListener(this);


        dbFieldsAchoice.addItemListener(this);
        dbFieldsBchoice.addItemListener(this);
        dbFieldsCchoice.addItemListener(this);
        dbFieldsOpsAchoice.addItemListener(this);
        dbFieldsOpsBchoice.addItemListener(this);
        dbFieldsOpsCchoice.addItemListener(this);
        dbFieldsBoolsBchoice.addItemListener(this);
        dbFieldsBoolsCchoice.addItemListener(this);
        choiceSortByA.addItemListener(this);
        choiceSortOrderA.addItemListener(this);


        dbCriteriaAfield.addActionListener(this);
        dbCriteriaBfield.addActionListener(this);
        dbCriteriaCfield.addActionListener(this);
        fieldResultLimit.addActionListener(this);


        dbCriteriaAfield.addKeyListener(this);
        dbCriteriaBfield.addKeyListener(this);
        dbCriteriaCfield.addKeyListener(this);
        fieldResultLimit.addKeyListener(this);


        getContentPane().add(panel19, "Center");
        setJMenuBar(mbarQbx);

        pack();
        // resize(670, this.getHeight());
        // setResizable(false);
        // setLocation(50, 100);

        loadDBQProps();
        // show();
        addWindowListener(new DBQueryBoxWindowListener());
        System.out.println("loading parent currentDB with DBQueryBox: " + parent.currentDB);
        // checkCurrentDB();
        checkBeginner();
    }


    public void checkCurrentDB() {
        System.out.println("XX_checkCurrentDB: 1070");
        try {
            dbsDBQChoice.select(parent.currentDB);
            loadDBSscheme(parent.currentDB);
            dbTablesAchoice.select(parent.tablename);
            String loadedDBID = parent.getDBID(parent.currentDB);
            loadDBtblflds(loadedDBID, parent.tablename);
            giveThequery(true, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();

/*
        if(obj == Qcriteriafield && Qcriteriafield.getText().length() > 0) {
            parent.givequery();
        }

*/


        if (obj == mnuiQbxDBsettings) {
            editDBinfo();
        }
        if (obj == mnuiQbxDBreload) {
            startDBQBReloadDB(dbsDBQChoice.getSelectedItem());
        }
        if (obj == mnuiQbxDBadd) {
            parent.getEditDBPrefs("noQvalue");
        }
        if (obj == mnuiQbxDBdelete) {
            deleteDB();
        }
        if (obj == mnuiQbxQuit) {
            hide();
        }

        if (obj == mnuiHelpTopics) {
            new HelpWindow("help", "index_body.html", parent.prefsUserLang);
        }
        if (obj == mnuiHelpWThis) {
            new HelpWindow("adminApp - Search Box Help", "help-window_db_search_box.html", parent.prefsUserLang);
        }


        if ((obj == dbCriteriaAfield) || (obj == dbCriteriaBfield) || (obj == dbCriteriaCfield) || (obj == fieldResultLimit)) {

            giveThequery(true, true);

        }

        if (obj == btnAddDB) {
            // saveDBS();
            // parent.getAddDBPrefs();
            parent.getEditDBPrefs("noQvalue");
        }
        if (obj == btnRunTquery) {
// setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            // saveDBS();
            // parent.getAddDBPrefs();
            runTextQ();
            // parent.getEditDBPrefs("noQvalue");
        }


        if (obj == btnaaSpclose) {
            hide();
            // parent.boolDBQBoxOpen = false;
            // dispose();
            // System.exit(0);
        }
        if (obj == btnDBreload) {
            startDBQBReloadDB(dbsDBQChoice.getSelectedItem());
        }
        if (obj == btnDBsettings) {
            editDBinfo();
        }
        if (obj == minChckBx) {
            saveQMinMaxProp();
        }
    }


    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == ButDBQRunDBQ) {
            //  giveThequery(true, true);
        }
        if (mouseevent.getSource() == ButDBQBold) {
            isitDBQBolder();
        }
        if (mouseevent.getSource() == ButDBQDeleteDB) {
            deleteDB();
        }
        if (mouseevent.getSource() == ButEditDB) {
            editDBinfo();
        }
        if (mouseevent.getSource() == ButDBQReloadDB) {
            startDBQBReloadDB(dbsDBQChoice.getSelectedItem());
        }
        if (mouseevent.getSource() == ButDelDBQs) {
            // giveThequery(false, true);
        }


    }


    public void mouseEntered(MouseEvent mouseevent) {

        if (mouseevent.getSource() == ButDBQRunDBQ) {
            setTheHandCursor();
            getLangString("text510");
            return;
        }

        if (mouseevent.getSource() == ButDBQAddDB) {
            setTheHandCursor();
            getLangString("text016");
            return;
        }

        if (mouseevent.getSource() == ButDBQDeleteDB) {
            setTheHandCursor();
            getLangString("text027");
            return;
        }
        if (mouseevent.getSource() == ButEditDB) {
            setTheHandCursor();
            getLangString("Edit Selected DB Info");
            return;
        }
        if (mouseevent.getSource() == ButDBQReloadDB) {
            setTheHandCursor();
            getLangString("text155");
            return;
        }

        if (mouseevent.getSource() == ButDelDBQs) {
            setTheHandCursor();
            getLangString("text132");
            return;
        }

        if (mouseevent.getSource() == ButDBQBold) {
            setTheHandCursor();
            if (!boolDbQBold) {
                getLangString("text020");
                return;
            } else {
                getLangString("text021");
                return;
            }
        }
    }


    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
        setTheDefaultCursor();
        setTipLable(parent.aplangstrings.getProperty("text079", "Database") + ": " + dbsDBQChoice.getSelectedItem() + "   ->   " + parent.aplangstrings.getProperty("text032", "Table") + ": " + dbTablesAchoice.getSelectedItem());
    }

    public void itemStateChanged(ItemEvent itemevent) {
        if (itemevent.getSource() == dbTablesAchoice) {
            String loadedDBID = parent.getDBID(dbsDBQChoice.getSelectedItem());
            loadDBtblflds(loadedDBID, dbTablesAchoice.getSelectedItem());
        } else if (itemevent.getSource() == dbsDBQChoice) {
            parent.loadnewDBprefs(dbsDBQChoice.getSelectedItem());
            loadDBSscheme(dbsDBQChoice.getSelectedItem());
            parent.setQstatus(dbsDBQChoice.getSelectedItem() + " " + parent.aplangstrings.getProperty("text531"), false);
        } else {

	/*
        if((itemevent.getSource() == choiceSortByA) || (itemevent.getSource() == choiceSortOrderA))
        {
    		giveThequery(true, false);
        }
	
	*/
            giveThequery(true, false);

        }


    }


    public void loadDBSscheme(String dbSCname) {
        System.out.println("XX_loadDBSscheme: 1261 - dbSCname: " + dbSCname);
        dbTablesAchoice.removeAll();
        parent.setStatusText("Loading DB Scheme");
        String loadedDBID = parent.getDBID(dbSCname);
        File file;
        String list[];


        String strngTableMenu = "<span style=\"font-family: Arial; font-size: small;\">";
        String dbstbles = loadedDBID + "---";
        try {
            file = new File(parent.getUfile("cbox/data/dbtables/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    // String dbsString = parent.replaceString(list[i], "", "");
                    if (list[i].startsWith(dbstbles)) {
                        String dbsString = adminApp.replaceString(list[i], dbstbles, "");
                        dbsString = adminApp.replaceString(dbsString, "---dbtbls.dat", "");
                        dbTablesAchoice.addItem(dbsString);
                        strngTableMenu += "<a href=aa_view-" + dbsString + "><font color=\"#008080\" style=\"text-decoration: none\" title=\"Show records\">&#x25BA;</font></a>&nbsp;<a href=" + dbsString + ">" + dbsString + "</a><br>";
                    }
                }
            }
            strngTableMenu += "</span>";
            jeditPaneTables.setText(strngTableMenu);

            // parent.setCurrentDB(dbSCname);
            loadDBtblflds(loadedDBID, dbTablesAchoice.getSelectedItem());
        } catch (Exception ex) {
            parent.setQstatus("Error 1302A [DBQueryBox] loadDBSscheme(): \n" + ex.toString(), true);
            return;
        }
    }


    public void loadDBtblflds(String dbTLoadID, String curtble) {
        // hide();
        dbFieldsAchoice.removeAll();
        dbFieldsBchoice.removeAll();
        dbFieldsCchoice.removeAll();
        choiceSortByA.removeAll();
        dbCriteriaAfield.setText("");
        dbCriteriaBfield.setText("");
        dbCriteriaCfield.setText("");
 	  if(dbTLoadID.equals("555")) { curtble = "DemoTableA"; } // adhoc fix
        System.out.println("XX_loadDBtblflds: 1291 - dbTLoadID: " + dbTLoadID + " :: curtble: " + curtble);

        try {
            FileInputStream fileinputstream = new FileInputStream(parent.getUfile("cbox/data/dbtables/" + dbTLoadID + "---" + curtble + "---dbtbls.dat"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null) {
                dbFieldsAchoice.addItem(s);
                dbFieldsBchoice.addItem(s);
                dbFieldsCchoice.addItem(s);
                choiceSortByA.addItem(s);
            }
            datainputstream.close();
            fileinputstream.close();
        } catch (Exception _ex) {
             System.out.println("loadDBtblflds.ERROR: " + _ex.toString());
 

            //   parent.setQstatus("Error 1060A [DBQueryBox]: \n" + _ex.toString(), false);
             // show();
            return;
        }
        // show();
        giveThequery(true, false);
        parent.loadnewDBprefs(dbsDBQChoice.getSelectedItem());
        setTipLable("Database: " + dbsDBQChoice.getSelectedItem() + "   ->   Table: " + dbTablesAchoice.getSelectedItem());

        // parent.setCurrentTbl(curtble);
        // pack();
        getLangString("blank");
    }








    public void reloadSQLDB(String dbSCname) {
            String sQa = "Error";
            parent.setQstatus("its sqllight reloadSQLDB", true);
		sQa = parent.dbMSQLA.getTblsQ();
                if (sQa.startsWith("Error: ")) {
                    parent.setQstatus(sQa, false);
                    setDBChoice(parent.currentDBTitle, "noQvalue");
                    setTheDefaultCursor();
                    return;
                } else {
                    deleteOldDBtbls(parent.currentDBID);
                    saveDBscheme(parent.currentDBID, sQa);
                    setTheDefaultCursor();
                    return;
                } 
    }



    public void reloadDB(String dbSCname) {
		if(parent.hostfolder.startsWith("jdbc")) {
            reloadSQLDB(dbSCname);
		} else {
            reloadAppDB(dbSCname);
		}
    }


    public void reloadAppDB(String dbSCname) {
        System.out.println("XX_reloadDB: 1330 - dbSCname: " + dbSCname);
        Authenticator.setDefault(new MyAuthenticator());
        MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text319"), parent.aplangstrings.getProperty("text320"), true);

        //   MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text155"), parent.aplangstrings.getProperty("text156"), true);
        requestFocus();
        if (message.id) {
            message.dispose();
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            progressBar.setIndeterminate(true);
            //  setTheWaitCursor();
            long startTime0 = System.currentTimeMillis();

            String s = parent.hostfolder + "qcomms/" + "qcomms.php?" + parent.userpassString + "&action=refreshDB";
            System.out.println("reloadDB: " + s);
            parent.setStatusText("Reloading DB: \n" + s);
            String s1 = "";
            StringBuffer sb;


            StringBuffer dbqxmlStr = null;
            String dbqstr = null;
            GZIPInputStream dbqgzip = null;
            BufferedReader dbqzipReader = null;


            try {

                URL url = new URL(s);

                java.io.InputStream inputstream = url.openStream();
                String s2;


                dbqgzip = new GZIPInputStream(inputstream);
                dbqzipReader = new BufferedReader(new InputStreamReader(dbqgzip));
                char chars[] = new char[1024];
                int len = 0;
                dbqxmlStr = new StringBuffer();
                //Write chunks of characters to the StringBuffer
                while ((len = dbqzipReader.read(chars, 0, chars.length)) >= 0) {
                    dbqxmlStr.append(chars, 0, len);
                }
                chars = null;
                s1 = dbqxmlStr.toString();
                // System.out.println("heeeeeeeeeeee " +  s1);

                dbqgzip.close();
                dbqzipReader.close();
                if (s1.startsWith("Error: ")) {
                    parent.setQstatus(s1, false);
                    setDBChoice(parent.currentDBTitle, "noQvalue");
                    setTheDefaultCursor();
                    return;
                } else {
                    deleteOldDBtbls(parent.currentDBID);
                    // s1 = adminApp.replaceString(s1, "<lbk>", "\n");
                    saveDBscheme(parent.currentDBID, s1);
                    setTheDefaultCursor();
                    return;
                }


            } catch (Exception exception) {

                String fullErrorString = getFullErrString(exception.toString()) + "Output: \nTry loading this url browser:\n" + s;

                parent.setQstatus(fullErrorString, false);
                setDBChoice(parent.currentDBTitle, "noQvalue");
                setTheDefaultCursor();
                return;
            }


        } else {
            // database exists, dont warn below that they must refresh the demo tables
            if (dbsDBQChoice.getSelectedItem().equals(parent.currentDBTitle)) {

            } else {
                setDBChoice(parent.currentDBTitle, "noQvalue");
                parent.setQstatus(parent.aplangstrings.getProperty("text157"), false);
            }

            message.dispose();
            System.out.println("Cancel pressed");

        }
    }


    public void saveDBS() {
        System.out.println("XX_saveDBS: 1425");
        String dbnameString = dbnameDBQField.getText().trim();
        if (dbnameString.length() < 1) {
            new alertDialog(parent, parent.aplangstrings.getProperty("text600"));
            return;
        }
        if (dbnameString.indexOf(" ") != -1) {
            new alertDialog(parent, parent.aplangstrings.getProperty("text601"));
            return;
        }

        checkForOlderDB();
    }

    public void checkForOlderDB() {
        System.out.println("XX_checkForOlderDB: 1441");
        String dbnameString = dbnameDBQField.getText().trim();
        File file;
        String list[];
        String dbsString = dbnameString + "---.dat";
        String hasOlderScheme = "no";
        int i = 0;
        try {
            file = new File("cbox/data/dbs/");
            if (file.isDirectory()) {
                list = file.list();
                while (i < list.length) {
                    // System.out.println(list[i] + " : " + dbsString);
                    // if(list[i].indexOf(dbsString) != -1) {
                    if (list[i] == dbsString) {
                        hasOlderScheme = "yes";
                        // System.out.println("its true");
                    }
                    i++;
                }
            }
        } catch (Exception ex) {
            parent.setQstatus("Error 1204A [DBQueryBox]: \n" + parent.aplangstrings.getProperty("text529") + "\n\n" + ex.toString(), true);
        }
        if (hasOlderScheme == "yes") {
            dbnameDBQField.setText("");
            new alertDialog(parent, parent.aplangstrings.getProperty("text602"));
            return;
        } else {
            saveDBProps(dbnameString);
        }
    }


    public void saveDBProps(String dbSCname) {
        System.out.println("XX_saveDBProps: 1475 - dbSCname: " + dbSCname);
        System.out.println("method SaveDBProps: " + dbSCname);
        parent.setStatusText("Saving DBProps: " + dbSCname);
        try {

            String dbTitleString = parent.getTitleToFile(dbsDBQChoice.getSelectedItem());
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/data/dbs/" + dbTitleString + "---.dat");
            PrintStream printstream = new PrintStream(fileoutputstream);

            printstream.println("hostfolder = " + parent.hostfolder);
            printstream.println("adminusername = " + parent.aausername);
            printstream.println("adminpassword = " + parent.aapassword);
            // printstream.println("webdbasename = " + parent.dbnameString);
            printstream.close();
            fileoutputstream.close();
            // new alertDialog(parent, parent.aplangstrings.getProperty("text119"));
        } catch (Exception exception) {
            parent.setQstatus("Error 1237A [DBQueryBox]: \n" + exception.toString(), true);
            return;

        }
        copyDBscheme(dbnameDBQField.getText().trim());

    }


    public void copyDBscheme(String dbSCname) {
        System.out.println("XX_copyDBscheme: 1507 - dbSCname: " + dbSCname);
        try {
            FileInputStream fis = new FileInputStream("cbox/data/dbschemes/demo-scheme.dat");
            FileOutputStream fos = new FileOutputStream("cbox/data/dbschemes/" + dbSCname + "---dbs.dat");
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            fis.close();
            fos.close();
        } catch (Exception exception) {
            parent.setQstatus("Error 1263A [DBQueryBox]: \n" + exception.toString(), true);
            return;
        }
        parseDBscheme(dbSCname);
    }


    public void saveDBscheme(String dbSCname, String dbSCstring) {
        System.out.println("XX_saveDBscheme: 1530 - dbSCname: " + dbSCname + " - dbSCstring: " + dbSCstring);
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/data/dbschemes/" + dbSCname + "---dbs.dat");
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(dbSCstring);
            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            parent.setQstatus("Error 1283A [DBQueryBox]: \n" + exception.toString(), true);
            return;
        }
        parseDBscheme(dbSCname);
    }


    public void parseDBscheme(String dbSCname) {
        System.out.println("XX_parseDBscheme: 1548 - dbSCname: " + dbSCname);
        String curtable = "";
        String curfields = "";
        String canContinue = "yes";
        try {
            FileInputStream fileinputstream = new FileInputStream(parent.getUfile("cbox/data/dbschemes/" + dbSCname + "---dbs.dat"));
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
            parent.setQstatus("Error 1319A [DBQueryBox]: \n" + _ex.toString(), true);
        }
        // getDBlistVector();
        setDBChoice(parent.currentDBTitle, "noQvalue");
 
        dbnameDBQField.setText("");
        new alertDialog(parent, parent.aplangstrings.getProperty("text004"));
    }


    public void saveDBtbleScheme(String dbSCname, String curtble, String curflds) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(parent.getUfile("cbox/data/dbtables/" + dbSCname + "---" + curtble + "---dbtbls.dat"));
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(curflds);
            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            parent.setQstatus("Error 1343A [DBQueryBox]: \n" + exception.toString(), true);
            return;
        }

    }


    public void deleteDB() {
        String dbnameString = dbsDBQChoice.getSelectedItem();
        String dbstbles = dbnameString + "---";
        boolean dbTblfileToDelBool;
        int idb = dbsDBQChoice.getItemCount();
        // System.out.println(String.valueOf(idb));
        if ((idb <= 1) || (parent.currentDBID.equals("555"))) {
            parent.setQstatus(parent.aplangstrings.getProperty("text604"), false);
            return;
        }
        System.out.println("dbnameString: " + dbnameString + " :" + "  -  " + ": " + defaultDB);

        dbnameString = dbnameString.trim();
        defaultDB = defaultDB.trim();

        MsgBox message = new MsgBox(parent, parent.aplangstrings.getProperty("text027"), parent.aplangstrings.getProperty("text029"), true);
        requestFocus();
        if (message.id) {
            message.dispose();
            try {
                File dbfileToDel = new File(parent.getUfile("cbox/data/dbs/" + dbnameString + ".dat"));

                dbfileToDel.delete();
 

            } catch (Exception ex) {
                parent.setQstatus("Error 1383A [DBQueryBox]: \n" + ex.toString(), true);
 
            }

            try {


                File dbshemeToDel = new File(parent.getUfile("cbox/data/dbschemes/" + dbnameString + "---dbs.dat"));
                // File dbtblshemeToDel = new File("cbox/data/dbtables/");


                boolean dbshemeToDelBool = dbshemeToDel.delete();
                // System.out.print(dbshemeToDel);
                //  System.out.println(dbshemeToDel + "  deleted " + dbshemeToDelBool);

                File file = new File(parent.getUfile("cbox/data/dbtables/"));
                if (file.isDirectory()) {
                    String list[] = file.list();
                    for (int i = 0; i < list.length; i++) {
                        if (list[i].indexOf(dbstbles) != -1) {
                            File delfile = new File(parent.getUfile("cbox/data/dbtables/" + list[i]));
                            dbTblfileToDelBool = delfile.delete();
                            // System.out.print(dbTblfileToDelBool);
                            // System.out.println(list[i] + "  deleted " + dbTblfileToDelBool);
                        }
                    }
                }
            } catch (Exception ex) {
                parent.setQstatus("Error 1411A [DBQueryBox]: \n" + ex.toString(), true);
                // return;
            }
            System.out.println("  deleted al files");
            // getDBlistVector();
            getDBQdbs(false);
            // loadDBSscheme(dbsDBQChoice.getSelectedItem());


            // saveDBS();
            System.out.println("Ok pressed");
        } else {
            message.dispose();
            System.out.println("Cancel pressed");
        }
    }


    public void deleteOldDBtbls(String theDB) {
        String dbstbles = theDB + "---";
        boolean dbTblfileToDelBool;
        File delfile;
        try {
            File file = new File(parent.getUfile("cbox/data/dbtables/"));
            if (file.isDirectory()) {
                String list[] = file.list();
                for (int i = 0; i < list.length; i++) {
                    if (list[i].indexOf(dbstbles) != -1) {
                        delfile = new File(parent.getUfile("cbox/data/dbtables/" + list[i]));
                        dbTblfileToDelBool = delfile.delete();
                        System.out.print(dbTblfileToDelBool);
                        System.out.println(list[i] + "  deleted " + dbTblfileToDelBool);
                    }
                }
            }
        } catch (Exception ex) {
            parent.setQstatus("Error 1449A [DBQueryBox]: \n" + ex.toString(), true);
        }

    }

    public void saveQMinMaxProp() {
        System.out.println("XX_saveQMinMaxProp: 1759 : defaultDB: " + defaultDB + " parent.currentDB: " + parent.currentDB);
        try {
            Point p = this.getLocation();
            int pLocX = p.x;
            int pLocY = p.y;
            int fW = this.getWidth();
            int fH = this.getHeight();

            String chckbxstring = "";
            String defdbString = "";
            boolean isSel = minChckBx.isSelected();
            if (isSel) {
                chckbxstring = "yes";
                boolminChck = true;
            } else {
                chckbxstring = "no";
                boolminChck = false;
            }


            parent.aamainprefs.setProperty("defaultdb", parent.currentDBID);
            parent.aamainprefs.setProperty("defaultDBname", dbsDBQChoice.getSelectedItem());
            parent.aamainprefs.setProperty("minonquery", chckbxstring);

            parent.aamainprefs.setProperty("propDBQBWidth", String.valueOf(fW));
            parent.aamainprefs.setProperty("propDBQBHeight", String.valueOf(fH));
            parent.aamainprefs.setProperty("propDBQBLocationX", String.valueOf(pLocX));
            parent.aamainprefs.setProperty("propDBQBLocationY", String.valueOf(pLocY));
            parent.aamainprefs.setProperty("prefsDefTableName", dbTablesAchoice.getSelectedItem());
            parent.aamainprefs.setProperty("prefsNumResults", fieldResultLimit.getText());

 

        } catch (Exception exception) {
            parent.setQstatus("Error 1525A [DBQueryBox]: \n" + exception.toString(), true);
	    	exception.printStackTrace();
            return;
        }

    }


    public void loadDBQProps() {
        System.out.println("loadDBQProps: 1842");
        try {
            String ckbx = "";

            defaultDB = parent.aamainprefs.getProperty("defaultdb").trim();
            defaultDBname = parent.aamainprefs.getProperty("defaultDBname").trim();
            ckbx = parent.aamainprefs.getProperty("minonquery").trim();

            String propDBQBWidth = parent.aamainprefs.getProperty("propDBQBWidth").trim();
            String propDBQBHeight = parent.aamainprefs.getProperty("propDBQBHeight").trim();
            String propDBQBLocationX = parent.aamainprefs.getProperty("propDBQBLocationX").trim();
            String propDBQBLocationY = parent.aamainprefs.getProperty("propDBQBLocationY").trim();


            setLocation(Integer.parseInt(propDBQBLocationX), Integer.parseInt(propDBQBLocationY));
            resize(Integer.parseInt(propDBQBWidth), this.getHeight());


            if (ckbx.indexOf("yes") != -1) {
                boolminChck = true;
                minChckBx.setSelected(true);
            } else {
                boolminChck = false;
            }

        } catch (Exception exception) {
            // defaultDB = "noQvalue";
            // checkCurrentDB();
            parent.setQstatus("Error 1613A [DBQueryBox]: defaultDBname: \n" + defaultDBname + " :: " + exception.toString(), false);
        }

        if (defaultDB.length() > 1) {

            try {
                loadDefaultDB(defaultDBname, parent.prefsDefTableName);
            } catch (Exception e) {
                System.out.println("setDefaultDB " + e.toString());
            }


        } else {
            getDBQdbs(false);
        }
        System.out.println("loadDBQProps: defaultDBname: \n" + defaultDBname + " :: " + defaultDB);

    }


    public void old_to_delete_loadDBQProps() {
        System.out.println("loadDBQProps: 1842");
        try {
            String ckbx = "";
            dbqProps = new Properties();
            FileInputStream fileinputstream = new FileInputStream(parent.getUfile("cbox/prefs/DBQBox.prfs"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            dbqProps.load(fileinputstream);
            defaultDB = dbqProps.getProperty("defaultdb").trim();
            defaultDBname = dbqProps.getProperty("defaultDBname").trim();
            ckbx = dbqProps.getProperty("minonquery").trim();

            String propDBQBWidth = dbqProps.getProperty("propDBQBWidth").trim();
            String propDBQBHeight = dbqProps.getProperty("propDBQBHeight").trim();
            String propDBQBLocationX = dbqProps.getProperty("propDBQBLocationX").trim();
            String propDBQBLocationY = dbqProps.getProperty("propDBQBLocationY").trim();


            fileinputstream.close();
            datainputstream.close();


            setLocation(Integer.parseInt(propDBQBLocationX), Integer.parseInt(propDBQBLocationY));
            resize(Integer.parseInt(propDBQBWidth), this.getHeight());


            if (ckbx.indexOf("yes") != -1) {
                boolminChck = true;
                minChckBx.setSelected(true);
            } else {
                boolminChck = false;
            }

        } catch (Exception exception) {
            // defaultDB = "noQvalue";
            // checkCurrentDB();
            parent.setQstatus("Error 1613A [DBQueryBox]: defaultDBname: \n" + defaultDBname + " :: " + exception.toString(), false);
        }

        if (defaultDB.length() > 1) {
            // System.out.println("its default");
            // System.out.println("he:" + defaultDB + ":EH");
            // loadDBSscheme(defaultDBname);
            setDefaultDB(defaultDBname);
            //  getDBQdbs(true);
        } else {
            getDBQdbs(false);
        }

    }


    public void paint(Graphics g) {
        super.paint(g);
    }

    public void getQueryMinMax() {
        // System.out.println("getQueryMinMax()");
        if ((boolminChck) && (!boolDbQBold)) {
            isitDBQBolder();
        }
    }

    public void getBrowDBA() {
        String actnCritString = dbCriteriaAfield.getText();
        String actnUrlString = parent.maddress.getText();

        if (actnCritString.length() < 1) {
            dbCriteriaAfield.setText("Browser Token");
        }
        giveThequery(true, false);
        String purlstring = parent.maddress.getText();
        if (purlstring.lastIndexOf("&osCsid=") != -1) {
            purlstring = purlstring.substring(0, purlstring.lastIndexOf("&osCsid=") - 1);
        }
//       new BrowDBAction(parent, purlstring, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem(), QQTextArea.getText(), 0);
    }

    public void editDBinfo() {
        parent.getEditDBPrefs(dbsDBQChoice.getSelectedItem());
    }


 

   /*
    * Authentication Class for password protected URLS
   */

    public class MyAuthenticator extends Authenticator {
        // This method is called when a password-protected URL is accessed
        protected PasswordAuthentication getPasswordAuthentication() {
            // Get information about the request
            String promptString = getRequestingPrompt();
            String hostname = getRequestingHost();
            InetAddress ipaddr = getRequestingSite();
            int port = getRequestingPort();

            // Get the username from the user...
            String username = parent.encryptedUname;

            // Get the password from the user...
            String password = parent.encryptedPass;

            // Return the information
            return new PasswordAuthentication(username, password.toCharArray());
        }
    }






 

/* !!to delete
 
     public void run()
    {
        try
        {
// threadDBQBox.yield();
                    //setDBQBoxQuery(strngDBQBTempQStrng, strngDBQBTempDB, strngDBQBTempTable);
                     // threadDBQBox.sleep(11115000);
        }
        catch(Exception exception)
        {
 
            parent.setQstatus(exception.toString(), true);

            
            return;
        }
    }
 
*/


    public void startDBQBoxQuery(String s, String dname, String tname) {
        int idb = dbsDBQChoice.getItemCount();
        if ((idb <= 1) || (parent.currentDBID.equals("555"))) {
            parent.setQstatus(parent.aplangstrings.getProperty("text326"), false);
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dbqbDisableButtons();
        progressBar.setIndeterminate(true);
        strngDBQBTempDB = dname;
        strngDBQBTempTable = tname;
        strngDBQBTempQStrng = s;
        try {
            //   setDBQBoxQuery(strngDBQBTempQStrng, strngDBQBTempDB, strngDBQBTempTable);
            taskDBQBQ = new taskDBQBoxQuery(this, strngDBQBTempQStrng, strngDBQBTempDB, strngDBQBTempTable);
            //  task = new Task();
            //   task.addPropertyChangeListener(this);
            taskDBQBQ.execute();
            // threadDBQBox = new Thread(this);
            //   threadDBQBox.start();

        } catch (Exception exception) {
            stopDBQBoxQuery();
            parent.setQstatus(exception.toString(), true);


            return;
        }

    }


    public void stopDBQBoxQuery() {
        setTheDefaultCursor();
        dbqbEnableButtons();
        progressBar.setIndeterminate(false);

        try {
            System.out.println("stopDBQBoxQuery");


        } catch (Exception exception) {
            parent.setQstatus(exception.toString(), true);
        }
        tempDBQBoxResString = null;

    }

    public void killTheThread() {
        if (threadDBQBox != null) {
            threadDBQBox.stop();
            threadDBQBox = null;
        }
    }








    public void setDBQBoxQuery(String s, String dname, String tname) {

        // dbqueryBox.downloadBar.start();
        // setWaitCursor();
        // dbqueryBox.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        long startTime0 = System.currentTimeMillis();

        String isDone = "no";


        String senced = URLEncoder.encode(s);

        String s1 = parent.hostfolder + "qcomms/qcomms.php?" + parent.userpassString + "&action=runQuery&tablename=" + tname + "&qstring=" + senced;
        try {



		if(parent.hostfolder.startsWith("jdbc")) {
            
            parent.setQstatus("its sqllight", true);
		tempDBQBoxResString = parent.dbMSQLA.setTabsQ(s);
		// parent.setCellQuery(tempDBQBoxResString);
            // stopDBQBoxQuery();
		} else {



        parent.setStatusText(s1);
        String s5 = "";
        String s7 = "";
        parent.setStatusText(s1);
        // parent.QstatusTextArea.setText("STS563SQ: " + s1);

        int i = 0;
        InputStream inputstream;

        StringBuffer xmlStr = null;
        String str = null;
        GZIPInputStream gzip = null;
        BufferedReader zipReader = null;
        inputstream = null;
        Authenticator.setDefault(new MyAuthenticator());


            URL url = new URL(s1);
            // setBasicAuthentication("aausername", "aapassword", url);
            inputstream = url.openStream();
            gzip = new GZIPInputStream(inputstream);
            zipReader = new BufferedReader(new InputStreamReader(gzip));
            char chars[] = new char[1024];
            int len = 0;
            int ttlen = 0;


            xmlStr = new StringBuffer();
            //Write chunks of characters to the StringBuffer 
            while ((len = zipReader.read(chars, 0, chars.length)) >= 0) {
                ttlen++;
                // System.out.println("chars.length: " + String.valueOf(chars.length) +  "chars: " + chars + "len: " + String.valueOf(len));
                xmlStr.append(chars, 0, len);
                // dbqueryBox.blipProgressChange(ttlen, len);
                //  dbqueryBox.progressBar.setValue(ttlen);
                // dbqueryBox.setTipLable(String.valueOf(ttlen));
            }
            chars = null;

            gzip.close();
            zipReader.close();
            tempDBQBoxResString = xmlStr.toString();



	   }  // end of else not jdbc



            if (tempDBQBoxResString.startsWith("Error: ") || tempDBQBoxResString.startsWith("Alert: ")) {

                parent.setQstatus(tempDBQBoxResString, false);
                // setDefaultCursor();
                stopDBQBoxQuery();
                return;


            } else {

                if (tempDBQBoxResString.startsWith("NoRecords:")) {
                    String newDBQBoxResString = "";
                    for (int j = 0; j < dbFieldsAchoice.getItemCount(); j++) {
                        if (j == (dbFieldsAchoice.getItemCount() - 1)) {
                            newDBQBoxResString += dbFieldsAchoice.getItem(j);
                        } else {
                            newDBQBoxResString += dbFieldsAchoice.getItem(j) + "\t";
                        }
                    }
                    tempDBQBoxResString = newDBQBoxResString + "\n" + newDBQBoxResString;
                }
                parent.setCellQuery(tempDBQBoxResString);
                // System.out.println("tempQResultsString: " + tempQResultsString);
                // tempQResultsString = replaceString(tempQResultsString, "<nr>", "\n");
                // parent.QqueryTextlabel.setForeground(new Color(255, 0, 0));
                // parent.QqueryTextlabel.setText("RecordSum: " + i);

                parent.tempQResultsString = tempDBQBoxResString;

                // System.out.println(tempQResultsString);
                parent.tablename = tname;
                long endTime0 = System.currentTimeMillis();
                // System.out.println("Time taken for creation of String literals : " + (endTime0 - startTime0) + " milli seconds" );

                parent.setCurrentDB(dname);
                parent.setCurrentTbl(tname);
                isDone = "yes";

                if (parent.boolDBQBoxOpen) {
                    getQueryMinMax();
                }
                parent.getDBapp();

                stopDBQBoxQuery();
                return;
            }


        } catch (Exception exception) {

            String writefstring = "select * from " + tname + ";";
            String fullErrorString = getFullErrString(exception.toString()) + "Output: \n" + tempDBQBoxResString + "\nTry loading this url browser:\n" + s1;
            // why do this? parent.writeFile(dname, writefstring);

            // fullErrorString += "\n\nOUTPUT:" + xmlStr;
            parent.setQstatus(fullErrorString, true);
            stopDBQBoxQuery();
            return;
        }


    }

    public void startDBQBReloadDB(String dname) {

        int idb = dbsDBQChoice.getItemCount();
        //  if((idb <= 0) || (parent.currentDBID.equals("555"))) {
        if (parent.currentDBID.equals("555")) { // trying to reload the demo database, give error
            parent.setQstatus(parent.aplangstrings.getProperty("text326"), false);
            return;
        } else {

            // progressBar.setIndeterminate(true);
            strngDBQBTempDB = dname;

            try {
                dbqbDisableButtons();
                taskDBQBRDB = new taskDBQBoxReloadDB(this, strngDBQBTempDB);

                taskDBQBRDB.execute();


            } catch (Exception exception) {

                parent.setQstatus(exception.toString(), true);
                return;
            }
        } // end of else
    }

    public void stopDBQBReloadDB() {
        setTheDefaultCursor();
        progressBar.setIndeterminate(false);
        dbqbEnableButtons();
    }


    public void loadDefaultDB(String tempDefDBName, String tempDefTblName) {
        System.out.println("XX_loadDefaultDB: 2411 - tempDefDBName: " + tempDefDBName);
        dbsDBQChoice.removeAll();
        File file;
        String list[];
        String hasDefDB = "no";
        currStrDBTitles = "";
        dbListVector.removeAllElements();
        try {
            file = new File(parent.getUfile("cbox/data/dbs/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    String dbsString = parent.getFileToTitle(list[i]);
                    if (dbsString.equals(tempDefDBName)) {
                        hasDefDB = "yes";
                    }
                    dbsDBQChoice.addItem(dbsString);
			  currStrDBTitles += dbsString + "::";
                }
            }
        } catch (Exception ex) {
            parent.setQstatus("Error 2613A [DBQueryBox] loadDefaultDB: \n" + ex.toString(), false);
            // System.out.println(ex.toString());
            return;
        }
        if (hasDefDB.equals("yes")) {
            dbsDBQChoice.select(tempDefDBName);

        } else {
            System.out.println("XX_loadDefaultDB: 2436 - tempDefDBName - noDefDB: " + tempDefDBName);
        }
        loadDefDBSscheme(dbsDBQChoice.getSelectedItem(), tempDefTblName);
    }

    public void loadDefDBSscheme(String dbSCname, String tmpDfTblName) {
        System.out.println("XX_loadDefDBSscheme: 1261 - dbSCname: " + dbSCname);
        dbTablesAchoice.removeAll();
        parent.setStatusText("Loading DB Scheme");
        String loadedDBID = parent.getDBID(dbSCname);
        File file;
        String list[];


        String strngTableMenu = "<span style=\"font-family: Arial; font-size: small;\">";
        String dbstbles = loadedDBID + "---";
        try {
            file = new File(parent.getUfile("cbox/data/dbtables/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    // String dbsString = parent.replaceString(list[i], "", "");
                    if (list[i].startsWith(dbstbles)) {
                        String dbsString = adminApp.replaceString(list[i], dbstbles, "");
                        dbsString = adminApp.replaceString(dbsString, "---dbtbls.dat", "");
                        dbTablesAchoice.addItem(dbsString);
                        strngTableMenu += "<a href=aa_view-" + dbsString + "><font color=\"#008080\" style=\"text-decoration: none\" title=\"Show records\">&#x25BA;</font></a>&nbsp;<a href=" + dbsString + ">" + dbsString + "</a><br>";
                    }
                }
            }
            strngTableMenu += "</span>";
            jeditPaneTables.setText(strngTableMenu);

            // parent.setCurrentDB(dbSCname);

            dbTablesAchoice.select(tmpDfTblName);
            loadDBtblflds(loadedDBID, tmpDfTblName);
        } catch (Exception ex) {
            parent.setQstatus("Error 2659A [DBQueryBox] loadDefDBSscheme(): \n" + ex.toString(), true);
            return;
        }
    }


    public void setDefaultDB(String tempDefDBName) {
        System.out.println("XX_setDefaultDB: 2411 - tempDefDBName: " + tempDefDBName);
        dbsDBQChoice.removeAll();
        File file;
        String list[];
        String hasDefDB = "no";
        dbListVector.removeAllElements();
	  currStrDBTitles = "";
        try {
            file = new File(parent.getUfile("cbox/data/dbs/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    String dbsString = parent.getFileToTitle(list[i]);
                    if (dbsString.equals(tempDefDBName)) {
                        hasDefDB = "yes";
                    }
                    dbsDBQChoice.addItem(dbsString);
			  currStrDBTitles += dbsString + "::";
                }
            }
        } catch (Exception ex) {
            parent.setQstatus("Error 156A [DBQueryBox]: \n" + ex.toString(), false);
            // System.out.println(ex.toString());
            return;
        }
        if (hasDefDB.equals("yes")) {
            dbsDBQChoice.select(tempDefDBName);

        } else {
            System.out.println("XX_setDefaultDB: 2436 - tempDefDBName - noDefDB: " + tempDefDBName);
        }
        loadDBSscheme(dbsDBQChoice.getSelectedItem());
    }


    public String getFullErrString(String theError) {

 
        String fullErrorString = parent.aplangstrings.getProperty("text800", "We have an error in trying to contact the qcomms.php file") + "\n";
        fullErrorString += parent.aplangstrings.getProperty("text801", "PLEASE CONFIRM THAT") + ":\n";
        fullErrorString += "1. " + parent.aplangstrings.getProperty("text802", "You are currently connected to internet") + ".\n";
        fullErrorString += "2. " + parent.aplangstrings.getProperty("text803", "Your qcomms.php file is located here") + ": " + parent.hostfolder + "qcomms/qcomms.php\n";
        fullErrorString += "3. " + parent.aplangstrings.getProperty("text804", "The [dbqs] folder is in the [qcomms] folder and is chommed to 777") + "\n\n";
        fullErrorString += parent.aplangstrings.getProperty("text805", "If you get a [Server redirected] error, check your protected folder username and password") + ".\n\n";
        fullErrorString += parent.aplangstrings.getProperty("text806", "If you are adding or refreshing database") + ", ";
        fullErrorString += parent.aplangstrings.getProperty("text807", "once confirming steps above") + ", ";
        fullErrorString += parent.aplangstrings.getProperty("text808", "use the DataBase -> Reload Database menu to try again") + ".\n\n";
        fullErrorString += "Error 2303A [DBQueryBox]: \n" + theError + "\n\n";
        return fullErrorString;
    }






    public void checkBeginner() {
        int idb = dbsDBQChoice.getItemCount();
        try {
            if (idb <= 0) {
                parent.setQstatus(parent.aplangstrings.getProperty("text142") + "\n" + parent.aplangstrings.getProperty("text016") + "\n" + parent.aplangstrings.getProperty("text123"), false);
		    // show();
            }
        } catch (Exception cbExecption) {
            System.out.println("cbExecption: " + cbExecption.toString());
        }

    }


    public String giveStrDBTitles() {
		String tmpCDBTstr = currStrDBTitles.substring(0, currStrDBTitles.length() - 2);
        return tmpCDBTstr;

    }
    public void dbqbEnableButtons() {
        btnDBreload.setEnabled(true);
        btnDBsettings.setEnabled(true);
        btnaaSpclose.setEnabled(true);
        btnAddDB.setEnabled(true);
        btnRunTquery.setEnabled(true);
        dbsDBQChoice.setEnabled(true);
        dbTablesAchoice.setEnabled(true);
        dbCriteriaAfield.setEditable(true);
        dbCriteriaBfield.setEditable(true);
        dbCriteriaCfield.setEditable(true);
        fieldResultLimit.setEditable(true);
        parent.aaEnableButtons();
    }


    public void dbqbDisableButtons() {
        btnDBreload.setEnabled(false);
        btnDBsettings.setEnabled(false);
        btnaaSpclose.setEnabled(false);
        btnAddDB.setEnabled(false);
        btnRunTquery.setEnabled(false);
        dbsDBQChoice.setEnabled(false);
        dbTablesAchoice.setEnabled(false);
        dbCriteriaAfield.setEditable(false);
        dbCriteriaBfield.setEditable(false);
        dbCriteriaCfield.setEditable(false);
        fieldResultLimit.setEditable(false);
        parent.aaDisableButtons();
    }


    boolean boolDbQBold;
    boolean boolminChck;

    java.awt.List myquerylist;

    DBQBoxCommLabel QrunQs;
    DBQBoxCommLabel QrunTextQ;
    DBQBoxCommLabel deleteQs;
    DBQBoxCommLabel minmaxCommLbl;
    DBQBoxCommLabel savethisQ;
    DBQBoxCommLabel lblReloadDB;
    DBQBoxCommLabel browDBaction;

    JLabel QqueryInfolabel;
    JLabel Qstatus;
    JLabel Qdbaselabel;
    JLabel Qtablelabel;
    JLabel Qcolumnlabel;
    JLabel Qcriterialabel;
    JLabel whereLabel;
    JLabel orderbyLabel;
    JLabel dbQtiplabel;
    JLabel labelOrderBy;
    JLabel labelResultLimit;


    Choice dbsDBQChoice;
    Choice dbTablesAchoice;
    Choice dbFieldsAchoice;
    Choice dbFieldsBchoice;
    Choice dbFieldsCchoice;
    Choice dbFieldsOpsAchoice;
    Choice dbFieldsOpsBchoice;
    Choice dbFieldsOpsCchoice;
    Choice dbFieldsBoolsBchoice;
    Choice dbFieldsBoolsCchoice;
    Choice dbFieldsOrderchoice;
    Choice choiceSortByA;
    Choice choiceSortOrderA;


    JTextField dbCriteriaAfield;
    JTextField dbCriteriaBfield;
    JTextField dbCriteriaCfield;
    JTextField dbnameDBQField;
    JTextField Qtablefield;
    JTextField Qcolumnfield;
    JTextField Qcriteriafield;
    JTextField fieldResultLimit;

    JProgressBar progressBar;
    JTextArea QQTextArea;
    adminApp parent;

    Image img1DBQ;
    Image img2DBQ;
    Image img3DBQ;
    Image img4DBQ;
    Image img5DBQ;
    Image img6DBQ;
    Image img7DBQ;

    ImageButton ButDBQRunDBQ;
    ImageButton ButDBQBold;
    ImageButton ButDBQReloadDB;
    ImageButton ButDBQAddDB;
    ImageButton ButDBQDeleteDB;
    ImageButton ButDelDBQs;
    ImageButton ButEditDB;


    JButton btnDBreload;
    JButton btnDBsettings;
    JButton btnaaSpclose;

    String defaultDBname;
    String defaultDB;
    JButton btnAddDB;
    JButton btnRunTquery;
    JLabel addDbsLabel;
    JLabel dbnameLabel;

    Properties dbqProps;

    Vector dbListVector;
    MediaTracker tracker;
    JCheckBox minChckBx;

    taskDBQBoxReloadDB taskDBQBRDB;
    taskDBQBoxQuery taskDBQBQ;

    JMenuBar mbarQbx;
    JMenu mnuQbxTools;
    JMenu mnuQbxCurrDB;
    JMenu jmnuHelp;

    JMenuItem mnuiQbxDBsettings;
    JMenuItem mnuiQbxDBdelete;
    JMenuItem mnuiQbxDBreload;
    JMenuItem mnuiHelpTopics;
    JMenuItem mnuiHelpWThis;

    JMenuItem mnuiQbxDBadd;
    JMenuItem mnuiQbxQuit;
    Thread threadDBQBox;

    String strngDBQBTempDB;
    String strngDBQBTempTable;
    String strngDBQBTempQStrng;
    String tempDBQBoxResString;
    String currStrDBTitles;
    JEditorPane jeditPaneTables;

}
