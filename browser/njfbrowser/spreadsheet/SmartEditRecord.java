package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.HelpWindow;
import njfbrowser.misc.ImageButton;
import njfbrowser.misc.JlsTokenizer;
import njfbrowser.misc.MsgBox;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

public class SmartEditRecord extends JDialog
        implements HyperlinkListener, ActionListener, KeyListener, ItemListener, MouseListener, ListSelectionListener {
    public class SmartEditRecordWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            closeSer();
        }

        public SmartEditRecordWindowListener() {
        }
    }

    public void closeSer() {
        adminApp.qlinksframe1 = false;
        dispose();
    }

    public void ItemListener(ItemEvent itemevent) {
    }

    public void keepitSmaller() {
        resize(280, 53);
        setLocation(300, 15);
        isbolder = true;
    }


    public void searchit() {


        String s = searchinput.getText();
        String s1 = replaceinput.getText();
        if (s1.length() < 1 || s.trim().length() < 1) {
            parent.setQstatus(parent.aplangstrings.getProperty("text047"), false);
            return;
        } else {
            searchItemAndRep(s, s1);
            return;
        }

    }

    public void keyPressed(KeyEvent keyevent) {
    }

    public void itemStateChanged(ItemEvent itemevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public static String replaceString(String s, String s1, String s2) {
        int i = 0;
        String s3 = s;
        if (s3 != null && s3.length() > 0) {
            int j = 0;
            do {
                int k = s3.indexOf(s1, j);
                if (k == -1)
                    break;
                s3 = s3.substring(0, k) + s2 + s3.substring(k + s1.length());
                j = k + s2.length();
                i++;
            } while (true);
        }
        return s3;
    }


    public static void main(String args[]) {
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == searchinput) {
            searchit();
        }
        if (obj == btnApplyTfieldVal) {
            changeitem();
        }
        if (obj == btnEditTfieldVal) {
            serDisableButtons();
            setTareaFieldVal();
        }


        if (obj == btnApplyTareaVal) {
            serEnableButtons();
            getprodlist();
        }

        if (obj == btnCancelTareaVal) {
            getCardLaytPanel("mainPan");
            serEnableButtons();
        }
        if (obj == tfieldFieldEdit) {
            changeitem();
        }

        if (obj == mnuiSERuloadRec) {
            uploadSelectedRecord();
        }
        if (obj == mnuiSEReditField) {
            setTareaFieldVal();
        }
        if (obj == mnuiSERaddToPending) {
            saveProdRecord();
        }
        if (obj == mnuiSERexit) {
            closeSer();
        }
        if (obj == btnCloseSer) {
            closeSer();
        }
        if (obj == btnSERuloadRec) {
            uploadSelectedRecord();
        }
        if (obj == btnSERaddToPending) {
            saveProdRecord();
        }

        if (obj == mnuiSERhelptopics) {
            new HelpWindow("adminApp Help", parent.getUfile("docs/index_body.html"), parent.prefsUserLang);
        }
        if (obj == mnuiSERhelpwthis) {
            new HelpWindow("adminApp - Edit Record Help", parent.getUfile("docs/help-window_edit_record.html"), parent.prefsUserLang);

        } 



/*
        // fix this, have to be carefull with this
		// user may delete records that he shouldnt
         if(obj == mnuiSERdeleteRec) {
            delSerRecord();
        }
*/

    }

    public void mouseEntered(MouseEvent mouseevent) {
        //  Component component = mouseevent.getComponent();

    }

    public void mouseExited(MouseEvent mouseevent) {
        // setCursor(Cursor.getPredefinedCursor(0));
        lblTipLabel.setText("  " + parent.aplangstrings.getProperty("text096"));
    }

    private Frame getParentFrame() {
        Container container;
        for (container = getParent(); !(container instanceof Frame); container = container.getParent()) ;
        return (Frame) container;
    }

    public String countRepsString(String s, String s1, String s2) {
        int i = 0;
        String s3 = s;
        if (s3 != null && s3.length() > 0) {
            int j = 0;
            do {
                int k = s3.indexOf(s1, j);
                if (k == -1)
                    break;
                s3 = s3.substring(0, k) + s2 + s3.substring(k + s1.length());
                j = k + s2.length();
                i++;
            } while (true);
        }
        if (i > 0)
            parent.setQstatus(i + " Replacement(s) made.", true);
        else
            parent.setQstatus("No Replacement(s) found.", true);
        return s3;
    }

    public void showdaquickie() {
        tfieldFieldEdit.setText(modelDataList.getElementAt(dataList.getSelectedIndex()).toString());
        tfieldFieldEdit.requestFocus();
    }

    public void mouseClicked(MouseEvent mouseevent) {
        Component component = mouseevent.getComponent();

        if ((mouseevent.getClickCount() == 2) && (component == dataList)) {

            String s = modelDataList.getElementAt(dataList.getSelectedIndex()).toString();
            tfieldFieldEdit.setText("" + s + "");
            tareaFieldEdit.setText(s);
            // htmlPane.setText(s);
            // getdesctarea();
            serDisableButtons();
            getCardLaytPanel("desctAreaPan");
            // parent.gotoit(tfieldFieldEdit.getText());
            return;
        }

    }

    public void mousePressed(MouseEvent mouseevent) {
        setCursor(Cursor.getPredefinedCursor(3));
        if (mouseevent.getSource() == addchan)
            saveProdRecord();
        if (mouseevent.getSource() == searchbut)
            searchit();
        if (mouseevent.getSource() == Butshowprod)
            // BrowserControl.displayURL(parent.hostfolder + "index.php?productID=" + prodfieldsList.getItem(0));
            if (mouseevent.getSource() == chandelete) {
                delSerRecord();
            }

        if (mouseevent.getSource() == bold)
            isitBolder();

        if (mouseevent.getSource() == ButCatProds)
            parent.getCatProds(catChoice.getSelectedItem());
        setCursor(Cursor.getPredefinedCursor(0));
    }


    public SmartEditRecord(adminApp myslic, String s, String[] hstring) {
        // super(myslic, "Record-Edit - " + hstring[0] + "...");
        super(myslic, "", true);
        popupTable = new Hashtable();
        parent = myslic;
        setTitle(parent.aplangstrings.getProperty("text096", "Record-Edit"));
        getContentPane().setLayout(new BorderLayout());
        qitem = "";
        currentDBPrnt = parent.currentDB;
        currentTblPrnt = parent.tablename;


        mbarSER = new JMenuBar();
        mbarSER.setBorderPainted(true);

        mnuSERrecord = new JMenu(parent.aplangstrings.getProperty("text310", "Record"));
        mnuSERrecord.getPopupMenu().setLightWeightPopupEnabled(false);
        mnuSERhelp = new JMenu(parent.aplangstrings.getProperty("text012", "Help"));
        mnuSERhelp.getPopupMenu().setLightWeightPopupEnabled(false);

        mbarSER.add(mnuSERrecord);
        // !! the HelpWindow dialog stays hidden because this is in modal mode
        // mbarSER.add(mnuSERhelp);

        mnuiSERhelptopics = new JMenuItem(parent.aplangstrings.getProperty("text414", "Help Topics"));
        mnuiSERhelpwthis = new JMenuItem(parent.aplangstrings.getProperty("text415", "Whats This?"), new ImageIcon(parent.getUfile("cbox/images/iconHelp.gif")));


        mnuiSERuloadRec = new JMenuItem(parent.aplangstrings.getProperty("text549", "Upload Record to Database"));
        mnuiSERdeleteRec = new JMenuItem(parent.aplangstrings.getProperty("text550", "Delete Record from Database"));
        mnuiSEReditField = new JMenuItem(parent.aplangstrings.getProperty("text526", "Edit Field"));
        mnuiSERaddToPending = new JMenuItem(parent.aplangstrings.getProperty("text520", "Add to Pending Uploads"));
        mnuiSERexit = new JMenuItem(parent.aplangstrings.getProperty("text507", "Close"));

        mnuSERhelp.add(mnuiSERhelptopics);
        mnuSERhelp.addSeparator();
        mnuSERhelp.add(mnuiSERhelpwthis);


        mnuSERrecord.add(mnuiSEReditField);
        mnuSERrecord.addSeparator();
        mnuSERrecord.add(mnuiSERuloadRec);
        //  mnuSERrecord.add(mnuiSERdeleteRec);
        mnuSERrecord.addSeparator();
        mnuSERrecord.add(mnuiSERaddToPending);
        mnuSERrecord.addSeparator();
        mnuSERrecord.add(mnuiSERexit);


        mnuiSERhelptopics.addActionListener(this);
        mnuiSERhelpwthis.addActionListener(this);
        mnuiSEReditField.addActionListener(this);
        mnuiSERuloadRec.addActionListener(this);
        mnuiSERdeleteRec.addActionListener(this);
        mnuiSERaddToPending.addActionListener(this);
        mnuiSERexit.addActionListener(this);








/* to delete
        catChoice = new Choice();
*/
        Border raisedbvl = BorderFactory.createEmptyBorder(1, 1, 1, 1);

        tracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage("cbox/images/newmess.gif");
        img2 = Toolkit.getDefaultToolkit().getImage("cbox/images/searchprod.gif");
        img3 = Toolkit.getDefaultToolkit().getImage("cbox/images/showprod.gif");
        img5 = Toolkit.getDefaultToolkit().getImage("cbox/images/deletedb.gif");
        img6 = Toolkit.getDefaultToolkit().getImage("cbox/images/adddata.gif");
        img7 = Toolkit.getDefaultToolkit().getImage("cbox/images/minimiz.gif");
        img8 = Toolkit.getDefaultToolkit().getImage("cbox/images/upsell.gif");
        img9 = Toolkit.getDefaultToolkit().getImage("cbox/images/imgupload.gif");
        img10 = Toolkit.getDefaultToolkit().getImage("cbox/images/listcatitems.gif");
        img12 = Toolkit.getDefaultToolkit().getImage("cbox/images/searchbut.gif");
        tracker.addImage(img1, 1);
        tracker.addImage(img2, 2);
        tracker.addImage(img3, 3);
        tracker.addImage(img5, 5);
        tracker.addImage(img6, 6);
        tracker.addImage(img7, 7);
        tracker.addImage(img8, 8);
        tracker.addImage(img9, 9);
        tracker.addImage(img10, 10);
        tracker.addImage(img12, 12);
        try {
            tracker.waitForID(1);
            tracker.waitForID(2);
            tracker.waitForID(3);
            tracker.waitForID(5);
            tracker.waitForID(6);
            tracker.waitForID(7);
            tracker.waitForID(8);
            tracker.waitForID(9);
            tracker.waitForID(10);
            tracker.waitForID(12);
        } catch (InterruptedException _ex) {
        }
        bugReport = new ImageButton("Visite our Sponsors", img1);
        Butsearchprod = new ImageButton("Search Product", img2);
        Butshowprod = new ImageButton("Show Product", img3);
        chandelete = new ImageButton("Delete Channel", img5);
        addchan = new ImageButton("Add Channel", img6);
        bold = new ImageButton("Minimize", img7);
        UsellButton = new ImageButton("Help", img8);
        ButImgUpload = new ImageButton("Get Cats", img9);
        ButCatProds = new ImageButton("List Cat Items", img10);
        searchbut = new ImageButton("searchbut", img12);


/*
* bottom buttons and panel
*/
        btnSERuloadRec = new JButton(parent.aplangstrings.getProperty("text549", "Upload Record to Database"));
        btnSERuloadRec.addActionListener(this);

        btnSERaddToPending = new JButton(parent.aplangstrings.getProperty("text520", "Add to Pending Uploads"));
        btnSERaddToPending.addActionListener(this);

        btnCloseSer = new JButton(parent.aplangstrings.getProperty("text507"));
        btnCloseSer.addActionListener(this);

        JPanel panBtnsBottom = new JPanel(new BorderLayout(1, 1));
        panBtnsBottom.add("West", btnSERuloadRec);
        panBtnsBottom.add("Center", btnSERaddToPending);
        panBtnsBottom.add("South", btnCloseSer);

/*
*  end of bottom buttons and panel
*/


        lblTipLabel = new JLabel(" " + parent.aplangstrings.getProperty("text096"));
        lblTipLabel.setForeground(new Color(0, 0, 165));
        labelFieldTitle = new JLabel(" " + hstring[0]);
        labelFieldTitle.setForeground(new Color(0, 0, 240));
        // JLabel labelFieldName = new JLabel(" " + parent.aplangstrings.getProperty("text056"));


        labelFieldName = new JLabel(" " + parent.aplangstrings.getProperty("text099") + ": ");
        labelFieldName.setForeground(Color.black);
        topLabel = new JLabel(" " + parent.aplangstrings.getProperty("text096"));
        topLabel.setForeground(new Color(0, 0, 165));


        JLabel labelFieldEdit = new JLabel(" " + parent.aplangstrings.getProperty("text527", "Field Value") + ": ");

        tfieldFieldEdit = new JTextField("");
        tfieldFieldEdit.addActionListener(this);
        tfieldFieldEdit.addMouseListener(this);
        tfieldFieldEdit.setForeground(new Color(0, 0, 80));

        searchinput = new JTextField("");
        replaceinput = new JTextField("");

        searchinputLabel = new JLabel(" " + parent.aplangstrings.getProperty("text524"));
        searchinputLabel.setForeground(new Color(0, 0, 0));
        searchrepLabel = new JLabel(" " + parent.aplangstrings.getProperty("text513"));
        searchrepLabel.setForeground(new Color(0, 0, 180));
        replaceinputLabel = new JLabel(" " + parent.aplangstrings.getProperty("text525"));
        replaceinputLabel.setForeground(new Color(0, 0, 0));
        searchinput.setForeground(new Color(0, 0, 180));

	/* !!!!       remove the prodfieldslist
        prodfieldsList = new java.awt.List(12);
        prodfieldsList.setBackground(new Color(252, 252, 255));
        prodfieldsList.setForeground(new Color(0, 0, 80));
	*/


        qitem = s;
        qitem = replaceString(qitem, "\n", "");
        String as[] = readmessTokens(qitem, "\t");
        headerfields = hstring;
        int i = 0;

        modelDataList = new DefaultListModel();


        dataList = new JList(modelDataList);
        for (int iSA = 0; iSA < as.length; iSA++) {
            modelDataList.add(iSA, as[iSA]);
        }
        // The value of the JList model property is an object that provides
        // a read-only view of the data.  It was constructed automatically.

        for (int ixx = 0; ixx < dataList.getModel().getSize(); ixx++) {
            System.out.println(dataList.getModel().getElementAt(ixx));
        }

        fnList = new JList(headerfields);
        fnList.setEnabled(false);
        // The value of the JList model property is an object that provides
        // a read-only view of the data.  It was constructed automatically.

        for (int ixxa = 0; ixxa < dataList.getModel().getSize(); ixxa++) {
            System.out.println(dataList.getModel().getElementAt(ixxa));
        }

        JScrollPane fieldValScrollPane = new JScrollPane();
        fieldValScrollPane.getViewport().setView(dataList);

        JPanel panFullList = new JPanel(new BorderLayout(1, 1));
        panFullList.add("West", fnList);
        panFullList.add("Center", fieldValScrollPane);


        JScrollPane dbqbscrollPane = new JScrollPane();
        dbqbscrollPane.getViewport().setView(panFullList);

        dbqbscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dbqbscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        fieldValScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //fieldValScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);






	  /*
	  * here goes the browser panel
	  */


        JPanel htmlPanel = new JPanel(new BorderLayout());
        htmlPane = new JEditorPane();
        htmlPane.setEditable(true);
        // 	 htmlPane.setEditable(true);

        //  htmlPane.addHyperlinkListener(this);

        htmlPane.addMouseListener(this);

        HTMLEditorKit kit = (HTMLEditorKit) htmlPane.getEditorKitForContentType("text/html");
        // htmlPane.setEditorKit(kit);
        // HTMLDocument document = (HTMLDocument) htmlPane.getDocument();

        JPanel sourcePanel = new JPanel(new BorderLayout());
        sourcePanel.add(new JScrollPane(htmlPane));

        // BorderLayout.CENTER);

        // )
        // borderEmpty = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        // borderTitled = new TitledBorder(appName);
        // sourcePanel.setBorder(BorderFactory.createCompoundBorder(borderEmpty, borderTitled));
        // sourcePanel.setBorder(borderTitled);
        // sourcePanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));


        htmlPanel.add(sourcePanel, BorderLayout.CENTER);



/*
* end of the browser code
*/


        btnCancelTareaVal = new JButton(parent.aplangstrings.getProperty("text307", "Cancel"));
        btnCancelTareaVal.addActionListener(this);

        btnApplyTareaVal = new JButton(parent.aplangstrings.getProperty("text209", "Apply"));
        btnApplyTareaVal.addActionListener(this);


        tareaFieldEdit = new JTextArea();
        tareaFieldEdit.setForeground(new Color(0, 0, 80));
        tareaFieldEdit.setLineWrap(true);
        tareaFieldEdit.setWrapStyleWord(true);

        JPanel panelBtnTAreaPanel = new JPanel(new GridLayout(1, 2));
        panelBtnTAreaPanel.add(btnApplyTareaVal);
        panelBtnTAreaPanel.add(btnCancelTareaVal);


        JPanel descTpanel = new JPanel(new BorderLayout());
        descTpanel.add("Center", new JScrollPane(tareaFieldEdit));
        // descTpanel.add("Center", htmlPanel);

        descTpanel.add("North", panelBtnTAreaPanel);


        // new JPanel();
        JPanel panelBtnPanel = new JPanel(new BorderLayout(1, 1));
        panelBtnPanel.add("West", addchan);
        panelBtnPanel.add("Center", chandelete);
        // panel.add("East", Butshowprod);


        btnApplyTfieldVal = new JButton(parent.aplangstrings.getProperty("text209", "Apply"));
        btnApplyTfieldVal.addActionListener(this);

        btnEditTfieldVal = new JButton(parent.aplangstrings.getProperty("text010", "Edit"));
        btnEditTfieldVal.addActionListener(this);

        JPanel panelBtnsTField = new JPanel(new BorderLayout(1, 1));
        panelBtnsTField.add("Center", btnApplyTfieldVal);
        panelBtnsTField.add("East", btnEditTfieldVal);


        JPanel panelFieldTitle = new JPanel(new BorderLayout(1, 1));
        panelFieldTitle.add("West", labelFieldName);
        panelFieldTitle.add("Center", labelFieldTitle);


        JPanel panelFieldEdit = new JPanel(new BorderLayout(1, 1));
        panelFieldEdit.add("West", labelFieldEdit);
        panelFieldEdit.add("Center", tfieldFieldEdit);
        panelFieldEdit.add("East", panelBtnsTField);
        panelFieldEdit.setBorder(raisedbvl);


        JPanel panel3 = new JPanel(new BorderLayout(1, 1));
        panel3.setBorder(raisedbvl);

        JPanel panel4 = new JPanel(new BorderLayout(1, 1));
        panel4.add("Center", panelFieldEdit);
        panel4.add("North", panelFieldTitle);


        JPanel panel5 = new JPanel(new BorderLayout(1, 1));

        panel5.setForeground(new Color(0, 0, 80));
        panel5.add("North", searchinput);
        panel5.add("Center", replaceinput);
        panel5.add("East", searchbut);
        JPanel panel6 = new JPanel(new BorderLayout(1, 1));


        panel6.setForeground(new Color(0, 0, 80));
        panel6.add("North", searchinputLabel);
        panel6.add("Center", replaceinputLabel);


        JPanel panelTipLabel = new JPanel(new BorderLayout(1, 1));
        panelTipLabel.add("East", bold);
        panelTipLabel.add("Center", lblTipLabel);

        JPanel panelSrchReplace = new JPanel(new BorderLayout(1, 1));

        panelSrchReplace.setForeground(new Color(225, 225, 225));
        panelSrchReplace.add("North", searchrepLabel);
        panelSrchReplace.add("Center", panel5);
        panelSrchReplace.add("West", panel6);


        JPanel panel9 = new JPanel(new BorderLayout(1, 1));
        panel9.add("Center", dbqbscrollPane);
        panel9.add("North", panel4);

        JPanel panel10 = new JPanel(new BorderLayout(1, 1));
        labelFieldEdit.setForeground(new Color(0, 0, 80));

        JTextField textfield = new JTextField("");
        textfield.setForeground(new Color(0, 0, 180));

        cardsSmall = new JPanel();
        cardsSmall.setLayout(new CardLayout(0, 0));

        cardsSmall.add("mainPan", panel9);
        cardsSmall.add("desctAreaPan", descTpanel);


        Border dbqetchedBdr = BorderFactory.createEtchedBorder();
        Border dbqemptyBdr = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        Color dbqnrclr = new Color(180, 60, 0);
        Color dbqshclr = new Color(0, 60, 180);

        Border borderMainPan = BorderFactory.createTitledBorder(dbqetchedBdr, parent.aplangstrings.getProperty("text096") + ": ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), dbqshclr);
        Border borderCompoundBdr = BorderFactory.createCompoundBorder(borderMainPan, dbqemptyBdr);


        JPanel panel20 = new JPanel(new BorderLayout(3, 3));
        // panel20.add("North", panelTipLabel);
        panel20.add("Center", cardsSmall);
        panel20.add("South", panBtnsBottom);
        // panel20.add("Center", panel19);
        panel20.setBorder(borderCompoundBdr);


        getContentPane().add(panel20, "Center");
        setJMenuBar(mbarSER);

        bugReport.addActionListener(this);
        bugReport.addMouseListener(this);
        UsellButton.addActionListener(this);
        UsellButton.addMouseListener(this);
        addchan.addActionListener(this);
        addchan.addMouseListener(this);
        chandelete.addActionListener(this);
        chandelete.addMouseListener(this);
        bold.addActionListener(this);
        bold.addMouseListener(this);

        searchbut.addActionListener(this);
        searchbut.addMouseListener(this);
        searchinput.addMouseListener(this);
        replaceinput.addMouseListener(this);


        textfield.addActionListener(this);
        textfield.addMouseListener(this);
        Butsearchprod.addActionListener(this);
        Butsearchprod.addMouseListener(this);


        addWindowListener(new SmartEditRecordWindowListener());


        dataList.addListSelectionListener(this);
        dataList.addMouseListener(this);
        try {
            oldString = modelDataList.getElementAt(0).toString();
            tfieldFieldEdit.setText(oldString);

            setLocation(250, 80);
            resize(470, 575);
            setVisible(true);
            isbolder = false;

            // setResizable(false);
            dataList.setSelectedIndex(0);
        } catch (Exception e) {
            parent.setQstatus(parent.aplangstrings.getProperty("text092"), false);
        }
    }


    public void keyTyped(KeyEvent keyevent) {
    }

    public void EDmnuCopy_actionPerformed(ActionEvent actionevent) {
        toClipboard();
    }

    public void searchItemAndRep(String s, String s1) {
        String s2 = "";
        int i = prodfieldsList.getItemCount();
        int j = 0;
        try {
            while (j < i) {
                String s3 = prodfieldsList.getItem(j);

                String nstring = replaceString(s3, s, s1);
                prodfieldsList.replaceItem(nstring, j);

                s2 = s2 + s3 + "\n";
                j++;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        //  prodfieldsList.removeAll();
        String s4 = countRepsString(s2, s, s1);
        //  for(StringTokenizer stringtokenizer = new StringTokenizer(s4, "\n"); stringtokenizer.hasMoreTokens(); prodfieldsList.addItem(stringtokenizer.nextToken()));
    }

    void setHash(Component component, PopupMenu popupmenu) {
        popupTable.put(component, popupmenu);
    }


    PopupMenu getHash(Component component) {
        return (PopupMenu) popupTable.get(component);
    }

    public void keyReleased(KeyEvent keyevent) {
    }


    public static String[] readmessTokens(String s, String s1) {
        String s2 = s;
        String s3 = s1;
        JlsTokenizer stringtokenizer = new JlsTokenizer(s2, s3);
        int i = stringtokenizer.countTokens();
        int k = i - 1;
        String as[] = new String[k];
        for (int j = 0; j < k; j++)
            as[j] = stringtokenizer.nextToken();

        return as;
    }


    public static String[] readmessyTokens(String s, String s1) {
        String s2 = s;
        String s3 = s1;
        StringTokenizer stringtokenizer = new StringTokenizer(s2, s3);
        int i = stringtokenizer.countTokens();
        String as[] = new String[i];

        for (int j = 0; j < i; j++) {
            as[j] = stringtokenizer.nextToken();
            if (as[j].length() < 0) {
                as[j] = "isnull";
            }
            System.out.println("asj: " + j + " is: " + as[j]);
        }
        return as;
    }


    public void isitBolder() {
        if (!isbolder) {
            resize(320, 48);
            setLocation(450, 10);
            isbolder = true;
            return;
        }
        if (isbolder) {
            resize(320, 375);
            setLocation(250, 80);
            isbolder = false;
            return;
        } else {
            return;
        }
    }


    public void toClipboard() {
        SecurityManager securitymanager = System.getSecurityManager();
        if (securitymanager != null)
            try {
                securitymanager.checkSystemClipboardAccess();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        Transferable transferable = clipboard.getContents(this);
        try {
            String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            if (popobject == prodimgUrlTArea)
                prodimgUrlTArea.setText(s);
            if (popobject == thumbimgUrlTArea)
                thumbimgUrlTArea.setText(s);
            if (popobject == searchinput)
                searchinput.setText(s);
            if (popobject == replaceinput)
                replaceinput.setText(s);
            if (popobject == prodIDfield)
                prodIDfield.setText(s);
            if (popobject == tfieldFieldEdit)
                tfieldFieldEdit.setText(s);
            System.out.println(s);
            return;
        } catch (Throwable throwable) {
            System.err.println(throwable);
        }
    }

    public void getCardLaytPanel(String panName) {
        ((CardLayout) cardsSmall.getLayout()).show(cardsSmall, panName);
    }

    public void getdesctarea() {
        ((CardLayout) cardsSmall.getLayout()).show(cardsSmall, "desctAreaPan");
    }


    public void valueChanged(ListSelectionEvent e) {
        Object obj = e.getSource();
        if (obj == dataList) {
            String s = tfieldFieldEdit.getText();
            if (dataList.getSelectedValue() != null) {
                if (s.length() < 1) {

                    modelDataList.set(oldInt, "isnull");
                    labelFieldTitle.setText("Defaults to blank on Upload");
                }
                System.out.println(tfieldFieldEdit.getText());
                if (s.length() > 0 && (s.compareTo(oldString) > 0 || s.compareTo(oldString) < 0)) {
                    modelDataList.set(oldInt, s);
                }

                intprodfieldsList = dataList.getSelectedIndex();
                labelFieldTitle.setText(" " + headerfields[intprodfieldsList]);
                tfieldFieldEdit.setText(modelDataList.getElementAt(intprodfieldsList).toString());
                searchinput.setText(tfieldFieldEdit.getText());
                oldString = modelDataList.getElementAt(intprodfieldsList).toString();
                oldInt = intprodfieldsList;
                dataList.requestFocus();

            }
        }

    }

    public void searchRecAndRep(String s, String s1) {
        String s2 = "";
        int i = dataList.getModel().getSize();
        int j = 0;
        try {
            while (j < i) {
                String s3 = modelDataList.getElementAt(j).toString();

                String nstring = replaceString(s3, s, s1);
                modelDataList.set(j, nstring);
                // s2 = s2 + s3 + "\n";
                j++;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        // prodfieldsList.removeAll();
        // String s4 = replaceString(s2, s, s1);
        // for(StringTokenizer stringtokenizer = new StringTokenizer(s4, "\n"); stringtokenizer.hasMoreTokens(); prodfieldsList.addItem(stringtokenizer.nextToken()));
    }


    public void changeitem() {
        String s = tfieldFieldEdit.getText();
        try {
            modelDataList.set(oldInt, s);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    // !! dont know why i need all the garbage below in this function
// !! new function just changes the value (above)
    public void changeitemOLD() {
        String s = tfieldFieldEdit.getText();
        if (dataList.getSelectedValue() != null) {
            if (s.length() < 1) {
                modelDataList.set(oldInt, "isnull");

                labelFieldTitle.setText("Defaults to blank on Upload");
            }
            //            System.out.println(tfieldFieldEdit.getText());
            if (s.length() > 0 && (s.compareTo(oldString) > 0 || s.compareTo(oldString) < 0)) {
                modelDataList.set(oldInt, s);
            }
        }
        try {
            dataList.setSelectedIndex(-1);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void saveProdRecord() {
        changeitem();
        String s = "";
        int i = dataList.getModel().getSize();
        int j = i - 1;
        dataList.setSelectedIndex(j);
        try {
            for (int k = 0; k < i; k++)
                if (k == j)
                    s = s + modelDataList.getElementAt(k).toString();
                else
                    s = s + modelDataList.getElementAt(k).toString() + "\t";

        } catch (Exception exception) {
            System.out.println(exception);
        }
        dataList.setSelectedIndex(i);
        parent.procsRecord(currentDBPrnt, currentTblPrnt, s);
    }

    public void uploadSelectedRecord() {
        changeitem();
        String s = "";
        int i = dataList.getModel().getSize();
        int j = i - 1;
        dataList.setSelectedIndex(j);
        try {
            for (int k = 0; k < i; k++)
                if (k == j)
                    s = s + modelDataList.getElementAt(k).toString();
                else
                    s = s + modelDataList.getElementAt(k).toString() + "\t";

        } catch (Exception exception) {
            System.out.println(exception);
        }
        dataList.setSelectedIndex(i);


        String s1 = "REPLACE INTO " + currentTblPrnt + "  VALUES('";
        String s2 = adminApp.replaceString(s, "isnull", "");
        s2 = adminApp.replaceString(s2, "'", "\\'");
        s2 = adminApp.replaceString(s2, "\t", "','");
        String s3 = s1 + s2.trim() + "')";
        parent.startComtextQuery(s3);
    }


    public void delSerRecord() {

        if (dataList.getSelectedValue() != null) {
            int delseri = dataList.getSelectedIndex();
            String fcrit = dataList.getSelectedValue().toString();
            String hfld = headerfields[intprodfieldsList];
            fcrit = adminApp.replaceString(fcrit, "isnull", "");
            String serRecord = "delete from " + currentTblPrnt + " where " + hfld + " = '" + fcrit + "';";
            String aa = parent.aplangstrings.getProperty("text131");

            MsgBox message = new MsgBox(parent, aa, serRecord, true);
            requestFocus();
            if (message.id) {
                message.dispose();
                parent.writeFile(currentDBPrnt, serRecord);
            } else {
                message.dispose();
                System.out.println("Cancel pressed");
            }
        } else {
            parent.setQstatus(parent.aplangstrings.getProperty("text173") + parent.aplangstrings.getProperty("text174"), true);

        }
    }


    public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
        HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
        final URL url = hyperlinkEvent.getURL();
        // String urlString = url.toString();
        if (type == HyperlinkEvent.EventType.ENTERED) {


            // System.out.println("hyperlink event");
            // System.out.println("type: "+hyperlinkEvent.getEventType());
            // System.out.println("url: "+hyperlinkEvent.getURL());

        } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
            System.out.println("Activated: " + hyperlinkEvent.getURL());
            // parent.getUrlAction(url.toString(), hyperlinkEvent);
        }
    }


    public void setTareaFieldVal() {
        try {
            String stringTempListVal = "";
            if (dataList.getSelectedValue() != null) {
                stringTempListVal = modelDataList.getElementAt(dataList.getSelectedIndex()).toString();
            } else {
                dataList.setSelectedIndex(0);
                stringTempListVal = modelDataList.getElementAt(0).toString();
            }
            tfieldFieldEdit.setText("" + stringTempListVal + "");
            tareaFieldEdit.setText(stringTempListVal);
            getCardLaytPanel("desctAreaPan");
        } catch (Exception excep) {
            System.out.println("Error: SmartEditRecord [1113] : " + excep.toString());
        }

    }

    public void getprodlist() {

        //String ostring = replaceString(htmlPane.getText(), "\n", "<br>");
        //  String ostring = replaceString(tareaFieldEdit.getText(), "\n", "<br>");

        modelDataList.set(dataList.getSelectedIndex(), tareaFieldEdit.getText());
        tfieldFieldEdit.setText(tareaFieldEdit.getText());
        ((CardLayout) cardsSmall.getLayout()).show(cardsSmall, "mainPan");
    }


    public void serDisableButtons() {
        mnuSERrecord.setEnabled(false);
    }

    public void serEnableButtons() {
        mnuSERrecord.setEnabled(true);
    }

    String currentDBPrnt;
    String currentTblPrnt;
    String[] headerfields;
    String qitem;
    PopupMenu pm;
    MenuItem mi1;
    Component popComponent;
    java.awt.List prodfieldsList;
    Choice catChoice;
    JTextField searchinput;
    JTextField replaceinput;
    JTextField prodIDfield;
    protected boolean isbolder;
    Image img1;
    Image img2;
    Image img3;
    Image img5;
    Image img6;
    Image img7;
    Image img8;
    Image img9;
    Image img10;
    Image img12;
    ImageButton chandelete;
    ImageButton Butsearchprod;
    ImageButton Butshowprod;
    ImageButton ButImgUpload;
    ImageButton ButCatProds;
    ImageButton addchan;
    ImageButton bold;
    ImageButton UsellButton;
    ImageButton quicklinks;
    ImageButton searchbut;
    ImageButton bugReport;
    JLabel lblTipLabel;
    JLabel label1;
    JLabel searchinputLabel;
    JLabel replaceinputLabel;
    JLabel prodIDlabel;
    JLabel catIDlabel;
    JLabel prodTempteLabel;
    JLabel searchrepLabel;
    String inifile;
    Properties httpconfig;
    adminApp parent;
    JLabel l;
    static String s9 = "";
    JTextField tfieldFieldEdit;
    MediaTracker tracker;
    Hashtable popupTable;
    String oldString;
    int oldInt;
    int intprodfieldsList;
    int intListfieldvals;
    String ilinenum;
    JLabel imageuploadLabel;
    JLabel thumbimgUrlLabel;
    JLabel prodimgUrlLabel;
    JTextField thumbimgUrlTArea;
    JTextField prodimgUrlTArea;
    Object popobject;
    JTextArea tareaFieldEdit;
    JPanel cardsSmall;
    JButton btnApplyTfieldVal;
    JButton btnEditTfieldVal;

    JButton btnApplyTareaVal;
    JButton btnCancelTareaVal;


    JLabel labelFieldTitle;
    JLabel labelFieldName;
    JLabel topLabel;
    Choice UsellTypeChoice;
    JLabel UsellCloseLabel;
    JLabel UsellCloseLabelD;
    JLabel UsellCloseLabelH;
    JLabel UsellCloseLabelM;
    JTextField UsellCloseTfieldD;
    JTextField UsellCloseTfieldH;
    JTextField UsellCloseTfieldM;
    JLabel UsellPubPriceLabel;
    JLabel UsellWhsPriceLabel;
    JTextField UsellPubPriceTfield;
    JTextField UsellWhsPriceTfield;
    JButton btnCloseSer;
    JButton btnSERuloadRec;
    JButton btnSERaddToPending;


    JList fnList;
    JList dataList;
    DefaultListModel modelDataList;
    JEditorPane htmlPane;


    JMenuBar mbarSER;

    JMenu mnuSERrecord;
    JMenu mnuSERhelp;

    JMenuItem mnuiSERhelptopics;
    JMenuItem mnuiSERhelpwthis;
    JMenuItem mnuiSERuloadRec;
    JMenuItem mnuiSERdeleteRec;
    JMenuItem mnuiSEReditField;
    JMenuItem mnuiSERaddToPending;
    JMenuItem mnuiSERexit;


}
