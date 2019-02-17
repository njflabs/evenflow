package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.JUtil;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RecEditPanel extends Frame
        implements ActionListener, ItemListener, KeyListener, MouseListener {
    public class RecEditPanelWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public RecEditPanelWindowListener() {
        }
    }


    public void saveRecordToFile() {
        changeitem();
        String s = "";
        int i = listrows.getItemCount();
        int j = i - 1;
        listrows.select(j);
        try {
            for (int k = 0; k < i; k++)
                if (k == j)
                    s = s + listrows.getItem(k);
                else
                    s = s + listrows.getItem(k) + "\t";

        } catch (Exception exception) {
            System.out.println(exception);
        }
        listrows.select(i);
        insertIntoFile(s);
    }

    public void setRecstatus(String s, boolean flag) {
        RecAlertDialog recalertdialog = new RecAlertDialog(s, this);
        recalertdialog = null;
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent mouseevent) {
    }

    public void changeitem() {
        String s = recordEdit.getText();
        if (listrows.getSelectedItem() != null) {
            if (s.length() < 1)
                listrows.replaceItem("noQvalue", oldInt);
            System.out.println(recordEdit.getText());
            if (s.length() > 0 && (s.compareTo(oldString) > 0 || s.compareTo(oldString) < 0))
                listrows.replaceItem(s, oldInt);
        }
        listrows.select(-1);
    }

    public RecEditPanel(adminApp myslic, String s) {
        setTitle("Record Edit");
        parent = myslic;
        System.out.println("got dyna gui1: ");
        qline = s;
        ilinenum = "";
        recordEditLabel = new Label("Edit Line:");
        recordEditLabel.setBackground(new Color(225, 225, 225));
        QcancelRecEdit = new RecEditLabel(this, "Cancel", "cancel");
        QsubmitRecEdit = new RecEditLabel(this, "Save", "saverecpan");
        searchRep = new RecEditLabel(this, "Search-Replace", "searchreplace");
        saveRecToFile = new RecEditLabel(this, "Save to File", "saverectofile");
        recordEdit = new TextField("");
        listrows = new List();
        listfieldvals = new Choice();
        qline = replaceString(qline, "\t\t", "\tnoQvalue\t");
        String as[] = readmessTokens(qline, "\t");
        int i = 0;
        try {
            while (i < as.length) {
                listrows.addItem(as[i]);
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
            System.out.println(arrayindexoutofboundsexception + "\n" + ":\n" + as[i]);
        }
        buttonAdd = new Button("Add a button");
        buttonAdd.addActionListener(this);
        buttonRemove = new Button("Remove a button");
        buttonRemove.addActionListener(this);
        Panel panel = new Panel(new BorderLayout());
        panel.add("West", searchRep);
        panel.add("Center", saveRecToFile);
        panel.add("East", QsubmitRecEdit);
        Panel panel1 = new Panel(new BorderLayout());
        panel1.add("Center", panel);
        panel1.add("East", QcancelRecEdit);
        Panel panel2 = new Panel(new BorderLayout());
        panel2.add("Center", recordEdit);
        panel2.add("West", recordEditLabel);
        Panel panel3 = new Panel(new BorderLayout());
        panel3.add("Center", listrows);
        Panel panel4 = new Panel(new BorderLayout());
        panel4.add("North", panel2);
        panel4.add("Center", panel3);
        panel4.add("South", panel1);
        addWindowListener(new RecEditPanelWindowListener());
        listrows.select(1);
        listrows.addItemListener(this);
        listrows.addActionListener(this);
        recordEdit.addActionListener(this);
        recordEdit.setText(listrows.getItem(0));
        oldString = listrows.getItem(0);
        add(panel4);
        resize(450, 350);
        show();
        setVisible(true);
        setLocation(60, 60);
        ontop();
    }

    public void itemStateChanged(ItemEvent itemevent) {
        Object obj = itemevent.getSource();
        String s = recordEdit.getText();
        if (listrows.getSelectedItem() != null) {
            if (s.length() < 1)
                listrows.replaceItem("noQvalue", oldInt);
            System.out.println(recordEdit.getText());
            if (s.length() > 0 && (s.compareTo(oldString) > 0 || s.compareTo(oldString) < 0))
                listrows.replaceItem(s, oldInt);
            if (obj == listrows) {
                intListrows = listrows.getSelectedIndex();
                recordEdit.setText(listrows.getItem(intListrows));
                oldString = listrows.getItem(intListrows);
                oldInt = intListrows;
                listrows.requestFocus();
            }
        }
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyPressed(KeyEvent keyevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void ontop() {
        JUtil.setWindowAlwaysOnTop(JUtil.getHwnd(getTitle()), true);
    }

    public static String replaceString(String s, String s1, String s2) {
        String s3 = s;
        if (s3 != null && s3.length() > 0) {
            int i = 0;
            do {
                int j = s3.indexOf(s1, i);
                if (j == -1)
                    break;
                s3 = s3.substring(0, j) + s2 + s3.substring(j + s1.length());
                i = j + s2.length();
            } while (true);
        }
        return s3;
    }

    public void searchRecAndRep(String s, String s1) {
        String s2 = "";
        int i = listrows.getItemCount();
        int j = 0;
        try {
            while (j < i) {
                String s3 = listrows.getItem(j);
                s2 = s2 + s3 + "\n";
                j++;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        listrows.removeAll();
        String s4 = replaceString(s2, s, s1);
        for (java.util.StringTokenizer stringtokenizer = new java.util.StringTokenizer(s4, "\n"); stringtokenizer.hasMoreTokens(); listrows.addItem(stringtokenizer.nextToken()))
            ;
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == recordEdit)
            changeitem();
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void mouseEntered(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
    }

    public void setWaitCursor() {
        setCursor(3);
    }

    public void searchAndRep() {
        new RecEditSearchReplace(this, recordEdit.getText());
    }

    public void saveRecord() {
        changeitem();
        String s = "";
        int i = listrows.getItemCount();
        int j = i - 1;
        listrows.select(j);
        try {
            for (int k = 0; k < i; k++)
                if (k == j)
                    s = s + listrows.getItem(k);
                else
                    s = s + listrows.getItem(k) + "\t";

        } catch (Exception exception) {
            System.out.println(exception);
        }
        listrows.select(i);
        parent.procsRecord(s, s, s);
        setRecstatus("Record Added to PendQ List.", true);
    }

    public static String[] readmessTokens(String s, String s1) {
        String s2 = s;
        String s3 = s1;
        java.util.StringTokenizer stringtokenizer = new java.util.StringTokenizer(s2, s3);
        int i = stringtokenizer.countTokens();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
            as[j] = stringtokenizer.nextToken();

        return as;
    }

    public void setHandCursor() {
        setCursor(12);
    }

    public void setDefaultCursor() {
        setCursor(0);
    }

    public void insertIntoFile(String s) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("./data/dprefs/pending_qs.txt", true);
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(s);
            printstream.close();
            fileoutputstream.close();
            setRecstatus("Record Saved", true);
            return;
        } catch (Exception exception) {
            System.out.println(exception);
            setRecstatus(exception.toString(), true);
            return;
        }
    }

    adminApp parent;
    public static final String ADD = "Add a button";
    public static final String REMOVE = "Remove a button";
    public static final String BUTTONGRID = "Button Grid";
    public static final String CONTROLPANEL = "Control Panel";
    String oldString;
    int oldInt;
    int intListrows;
    int intListfieldvals;
    String ilinenum;
    List listrows;
    Choice listfieldvals;
    RecEditLabel QcancelRecEdit;
    RecEditLabel QsubmitRecEdit;
    Label recordEditLabel;
    TextField recordEdit;
    Panel panelDynamic;
    Panel panelTopLevel;
    Button buttonAdd;
    Button buttonRemove;
    java.util.Vector fieldsvec;
    int activePanel;
    String qline;
    RecEditLabel searchRep;
    RecEditLabel saveRecToFile;
}
