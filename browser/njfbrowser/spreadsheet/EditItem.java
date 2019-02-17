package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.BrowserControl;
import njfbrowser.misc.EDmnuCopy_ActionAdapter;
import njfbrowser.misc.ImageButton;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

public class EditItem extends Dialog
        implements ActionListener, KeyListener, ItemListener, MouseListener {
    public class EditItemWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            adminApp.qlinksframe1 = false;
            dispose();
        }

        public EditItemWindowListener() {
        }
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
        Object obj = itemevent.getSource();
        String s = prodFieldTarea.getText();
        if (prodfieldsList.getSelectedItem() != null) {
            if (s.length() < 1)
                prodfieldsList.replaceItem("noQvalue", oldInt);
            System.out.println(prodFieldTarea.getText());
            if (s.length() > 0 && (s.compareTo(oldString) > 0 || s.compareTo(oldString) < 0))
                prodfieldsList.replaceItem(s, oldInt);
            if (obj == prodfieldsList) {
                intprodfieldsList = prodfieldsList.getSelectedIndex();
                if (intprodfieldsList == 0)
                    label.setText("ProductID");
                if (intprodfieldsList == 1)
                    label.setText("ProductStock");
                if (intprodfieldsList == 2)
                    label.setText("ArtistID");
                if (intprodfieldsList == 3)
                    label.setText("CategoryID");
                if (intprodfieldsList == 4)
                    label.setText("ManufacturerID");
                if (intprodfieldsList == 5)
                    label.setText("ProductPrice");
                if (intprodfieldsList == 6)
                    label.setText("WholesalePrice");
                if (intprodfieldsList == 7)
                    label.setText("CostPrice");
                if (intprodfieldsList == 8)
                    label.setText("ProductImageURL");
                if (intprodfieldsList == 9)
                    label.setText("ProductImageThmbURL");
                if (intprodfieldsList == 10)
                    label.setText("ProductOptions");
                if (intprodfieldsList == 11)
                    label.setText("ProductWeight");
                if (intprodfieldsList == 12)
                    label.setText("ProductShortDescriptionENG");
                if (intprodfieldsList == 13)
                    label.setText("ProductLongDescriptionENG");
                if (intprodfieldsList == 14)
                    label.setText("ProductShortDescriptionPOR");
                if (intprodfieldsList == 15)
                    label.setText("ProductLongDescriptionPOR");
                if (intprodfieldsList == 16)
                    label.setText("ProductShortDescriptionSPA");
                if (intprodfieldsList == 17)
                    label.setText("ProductLongDescriptionSPA");
                if (intprodfieldsList == 18)
                    label.setText("ProductShortDescriptionITA");
                if (intprodfieldsList == 19)
                    label.setText("ProductLongDescriptionITA");
                if (intprodfieldsList == 20)
                    label.setText("ProductShortDescriptionFRE");
                if (intprodfieldsList == 21)
                    label.setText("ProductLongDescriptionFRE");
                if (intprodfieldsList == 22)
                    label.setText("ProductShortDescriptionGER");
                if (intprodfieldsList == 23)
                    label.setText("ProductLongDescriptionGER");
                if (intprodfieldsList == 24)
                    label.setText("DateCloses");
                if (intprodfieldsList == 25)
                    label.setText("UpsellType");
                if (intprodfieldsList == 26)
                    label.setText("PubPrice");
                if (intprodfieldsList == 27)
                    label.setText("WhsPrice");
                if (intprodfieldsList == 28)
                    label.setText("DateAdded");


                prodFieldTarea.setText(prodfieldsList.getItem(intprodfieldsList));
                searchinput.setText(prodFieldTarea.getText());
                oldString = prodfieldsList.getItem(intprodfieldsList);
                oldInt = intprodfieldsList;
                prodfieldsList.requestFocus();
            }
        }
    }

    public void mouseReleased(MouseEvent mouseevent) {
        Object obj = mouseevent.getSource();
        popobject = mouseevent.getSource();
        if (mouseevent.isPopupTrigger() && (obj == prodIDfield || obj == prodFieldTarea || obj == searchinput || obj == replaceinput || obj == prodimgUrlTArea || obj == thumbimgUrlTArea)) {
            popComponent = mouseevent.getComponent();
            PopupMenu popupmenu = getHash(popComponent);
            popupmenu.show(popComponent, popComponent.getSize().width / 2, popComponent.getSize().height / 2);
        }
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

    public void saveProdRecord() {
        changeitem();
        String s = "";
        int i = prodfieldsList.getItemCount();
        int j = i - 1;
        prodfieldsList.select(j);
        try {
            for (int k = 0; k < i; k++)
                if (k == j)
                    s = s + URLEncoder.encode(prodfieldsList.getItem(k));
                else
                    s = s + URLEncoder.encode(prodfieldsList.getItem(k)) + "\t";

        } catch (Exception exception) {
            System.out.println(exception);
        }
        prodfieldsList.select(i);
        parent.procsRecord(s, s, s);
        parent.setQstatus("Record Added to PendQ List.", true);
    }

    public void loadWatchlist() {
        prodfieldsList.removeAll();
        try {
            FileInputStream fileinputstream = new FileInputStream("cbox/iecanvaz/links.ini");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null)
                prodfieldsList.addItem(s);
            datainputstream.close();
            fileinputstream.close();
            return;
        } catch (Exception _ex) {
            parent.setQstatus(parent.aplangstrings.getProperty("text049"), false);
        }
    }

    public void savenewlist() {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/iecanvaz/links.ini");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int i = 0; i < prodfieldsList.getItemCount(); i++)
                printstream.println(prodfieldsList.getItem(i));

            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        parent.setQstatus(parent.aplangstrings.getProperty("text050"), false);
        loadWatchlist();
    }

    void addPopup(Component component, String s) {
        PopupMenu popupmenu = new PopupMenu();
        MenuItem menuitem = new MenuItem(s);
        popupmenu.add(menuitem);
        menuitem.addActionListener(new EDmnuCopy_ActionAdapter(this));
        setHash(component, popupmenu);
        component.add(popupmenu);
    }

    public static void main(String args[]) {
    }

    public void actionPerformed(ActionEvent actionevent) {
        System.out.println("actionPerformed, event=" + actionevent);
        System.out.println(" command=" + actionevent.getActionCommand());
        System.out.println(" param=" + actionevent.paramString());
        System.out.println(" source=" + actionevent.getSource());
        Object obj = actionevent.getSource();
        if (obj == searchinput)
            searchit();
        if (obj == descTaButton)
            getprodlist();

        if (obj == prodfieldsList) {
            String s = prodfieldsList.getSelectedItem();

            prodFieldTarea.setText("" + s + "");
            descTextArea.setText(s);
            getdesctarea();
            // parent.gotoit(prodFieldTarea.getText());
            return;
        } else {
            return;
        }
    }

    public void mouseEntered(MouseEvent mouseevent) {
        Component component = mouseevent.getComponent();
        setCursor(Cursor.getPredefinedCursor(12));
        if (component == UsellButton) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text542"));
            return;
        }
        if (component == Butshowprod) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text501"));
            return;
        }

        if (component == searchbut) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text047"));
            return;
        }
        if (component == chandelete) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text509"));
            return;
        }
        if (component == addchan) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text520"));
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
        setCursor(Cursor.getPredefinedCursor(0));
        tiplabel1.setText(parent.aplangstrings.getProperty("text521"));
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
        prodFieldTarea.setText(prodfieldsList.getSelectedItem());
        prodFieldTarea.requestFocus();
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent mouseevent) {
        setCursor(Cursor.getPredefinedCursor(3));
        if (mouseevent.getSource() == addchan)
            saveProdRecord();
        if (mouseevent.getSource() == searchbut)
            searchit();
        if (mouseevent.getSource() == Butshowprod)
            BrowserControl.displayURL(parent.hostfolder + "index.php?productID=" + prodfieldsList.getItem(0));
        if (mouseevent.getSource() == chandelete) {
            parent.setQLabels(parent.hostdbase, "product", "ProductID", prodfieldsList.getItem(0));
            parent.delRecords();
        }
        if (mouseevent.getSource() == UsellButton) {
            upSellItem();
        }
        if (mouseevent.getSource() == bold)
            isitBolder();
        if (mouseevent.getSource() == ButImgUpload)
            uploadImgs();
        if (mouseevent.getSource() == ButCatProds)
            parent.getCatProds(catChoice.getSelectedItem());
        setCursor(Cursor.getPredefinedCursor(0));
    }

    public void changeitem() {
        String s = prodFieldTarea.getText();
        if (prodfieldsList.getSelectedItem() != null) {
            if (s.length() < 1)
                prodfieldsList.replaceItem("noQvalue", oldInt);
            System.out.println(prodFieldTarea.getText());
            if (s.length() > 0 && (s.compareTo(oldString) > 0 || s.compareTo(oldString) < 0))
                prodfieldsList.replaceItem(s, oldInt);
        }
        prodfieldsList.select(-1);
    }

    public EditItem(adminApp myslic, String s) {
        super(myslic, "Product-Edit");
        popupTable = new Hashtable();
        parent = myslic;
        setBackground(new Color(225, 225, 225));
        setForeground(new Color(225, 225, 225));
        setLayout(new BorderLayout());
        qitem = "";
        tracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage("cbox/images/newmess.gif");
        img2 = Toolkit.getDefaultToolkit().getImage("cbox/images/searchprod.gif");
        img3 = Toolkit.getDefaultToolkit().getImage("cbox/images/showprod.gif");
        img5 = Toolkit.getDefaultToolkit().getImage("cbox/images/delchann.gif");
        img6 = Toolkit.getDefaultToolkit().getImage("cbox/images/addchann.gif");
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
        tiplabel1 = new Label(parent.aplangstrings.getProperty("text521"));
        tiplabel1.setForeground(new Color(200, 0, 0));
        label = new Label(parent.aplangstrings.getProperty("text522"));
        label.setForeground(new Color(0, 0, 240));
        label.setBackground(new Color(245, 245, 255));
        // Label UsellLabel = new Label(parent.aplangstrings.getProperty("text056"));


        UsellLabel = new Label("NicePrice", 2);
        UsellTypeLabel = new Label("Type:", 2);
        UsellTypeChoice = new Choice();
        UsellTypeChoice.addItem("npcI");
        UsellTypeChoice.addItem("newI");

        UsellCloseLabel = new Label("Closes:", 2);


        UsellCloseLabelD = new Label("Closes in D:", 2);
        UsellCloseLabelH = new Label("H:", 2);
        UsellCloseLabelM = new Label("M:", 2);

        UsellCloseTfieldD = new TextField("0");
        UsellCloseTfieldH = new TextField("0");
        UsellCloseTfieldM = new TextField("0");

        UsellPubPriceLabel = new Label("PubPrice:", 2);
        UsellWhsPriceLabel = new Label("WhsPrice:", 2);

        UsellPubPriceTfield = new TextField("0.00");
        UsellWhsPriceTfield = new TextField("0.00");


        Panel UsellTypePanel = new Panel(new BorderLayout());
        UsellTypePanel.add("West", UsellTypeLabel);
        UsellTypePanel.add("Center", UsellTypeChoice);


        Panel UsellClosesDPanel = new Panel(new BorderLayout());
        UsellClosesDPanel.add("West", UsellCloseLabelD);
        UsellClosesDPanel.add("Center", UsellCloseTfieldD);


        Panel UsellClosesHPanel = new Panel(new BorderLayout());
        UsellClosesHPanel.add("West", UsellCloseLabelH);
        UsellClosesHPanel.add("Center", UsellCloseTfieldH);

        Panel UsellClosesMPanel = new Panel(new BorderLayout());
        UsellClosesMPanel.add("West", UsellCloseLabelM);
        UsellClosesMPanel.add("Center", UsellCloseTfieldM);

        Panel UsellClosesGPanel = new Panel(new BorderLayout());
        UsellClosesGPanel.add("West", UsellClosesDPanel);
        UsellClosesGPanel.add("Center", UsellClosesHPanel);
        UsellClosesGPanel.add("East", UsellClosesMPanel);

        Panel UsellClosesPanel = new Panel(new BorderLayout());
        //UsellClosesPanel.add("West", UsellCloseLabel);
        UsellClosesPanel.add("Center", UsellClosesGPanel);

        Panel UsellPubPricePanel = new Panel(new BorderLayout());
        UsellPubPricePanel.add("West", UsellPubPriceLabel);
        UsellPubPricePanel.add("Center", UsellPubPriceTfield);

        Panel UsellWhsPricePanel = new Panel(new BorderLayout());
        UsellWhsPricePanel.add("West", UsellWhsPriceLabel);
        UsellWhsPricePanel.add("Center", UsellWhsPriceTfield);


        Panel UsellPricePanel = new Panel(new GridLayout(1, 2));
        UsellPricePanel.add(UsellPubPricePanel);
        UsellPricePanel.add(UsellWhsPricePanel);

        Panel UsellLeftPanel = new Panel(new BorderLayout());
        UsellLeftPanel.add("Center", UsellTypePanel);


        Panel UsellPanelTop = new Panel(new BorderLayout());
        UsellPanelTop.add("West", UsellLabel);
        UsellPanelTop.add("Center", UsellPricePanel);
        UsellPanelTop.add("East", UsellButton);

        Panel UsellPanelMid = new Panel(new BorderLayout());
        UsellPanelMid.add("West", UsellLeftPanel);
        UsellPanelMid.add("Center", UsellClosesPanel);


        Panel UsellPanel = new Panel(new BorderLayout());
        UsellPanel.add("North", UsellPanelTop);
        UsellPanel.add("Center", UsellPanelMid);
        UsellPanel.setForeground(new Color(0, 0, 80));
        UsellPanel.setBackground(new Color(255, 255, 250));

        Label label3 = new Label("Edit:");
        prodFieldTarea = new TextField("");
        prodFieldTarea.setForeground(new Color(0, 0, 80));
        addPopup(prodFieldTarea, "Paste");
        searchinput = new TextField("");
        addPopup(searchinput, "Paste");
        replaceinput = new TextField("");
        searchinputLabel = new Label(parent.aplangstrings.getProperty("text524"));
        searchinputLabel.setForeground(new Color(0, 0, 0));
        searchrepLabel = new Label(parent.aplangstrings.getProperty("text513"));
        searchrepLabel.setForeground(new Color(0, 0, 180));
        replaceinputLabel = new Label(parent.aplangstrings.getProperty("text525"));
        replaceinputLabel.setForeground(new Color(0, 0, 0));
        addPopup(replaceinput, "Paste");
        searchinput.setForeground(new Color(0, 0, 180));
        prodfieldsList = new java.awt.List(17);
        prodfieldsList.setBackground(new Color(252, 252, 255));
        prodfieldsList.setForeground(new Color(0, 0, 80));

        descTaButton = new Button("OK");
        descTextArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        descTextArea.setForeground(new Color(0, 0, 80));
        Panel descTpanel = new Panel(new BorderLayout());
        descTpanel.add("Center", descTextArea);
        descTpanel.add("South", descTaButton);
        cardsSmall = new Panel();
        cardsSmall.setLayout(new CardLayout(0, 0));


        // new Panel();
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(new Color(225, 225, 225));
        panel.setForeground(new Color(225, 225, 225));
        panel.add("West", addchan);
        panel.add("Center", chandelete);
        panel.add("East", Butshowprod);

        Panel panel1 = new Panel(new BorderLayout());
        panel1.setBackground(new Color(225, 225, 225));
        panel1.add("Center", UsellPanel);

        Panel panel2 = new Panel(new BorderLayout());
        panel2.add("West", label3);
        panel2.add("Center", prodFieldTarea);
        panel2.add("East", panel);
        Panel panel3 = new Panel(new BorderLayout());
        panel3.add("Center", label);
        Panel panel4 = new Panel(new BorderLayout());
        panel4.add("Center", panel2);
        panel4.add("North", panel3);
        Panel panel5 = new Panel(new BorderLayout());
        panel5.setBackground(new Color(225, 225, 225));
        panel5.setForeground(new Color(0, 0, 80));
        panel5.add("North", searchinput);
        panel5.add("Center", replaceinput);
        panel5.add("East", searchbut);
        Panel panel6 = new Panel(new BorderLayout());
        panel6.setBackground(new Color(225, 225, 225));
        panel6.setForeground(new Color(0, 0, 80));
        panel6.add("North", searchinputLabel);
        panel6.add("Center", replaceinputLabel);
        Panel panel7 = new Panel(new BorderLayout());
        panel7.add("East", bold);
        panel7.add("Center", tiplabel1);
        Panel panel8 = new Panel(new BorderLayout());
        panel8.setBackground(new Color(225, 225, 225));
        panel8.setForeground(new Color(225, 225, 225));
        panel8.add("North", searchrepLabel);
        // panel8.add("South", panel7);
        panel8.add("Center", panel5);
        panel8.add("West", panel6);
        Panel panel9 = new Panel(new BorderLayout());
        panel9.add("South", panel8);
        Panel panel10 = new Panel(new BorderLayout());
        label3.setForeground(new Color(0, 0, 80));
        new Panel(new BorderLayout());
        TextField textfield = new TextField("");
        textfield.setForeground(new Color(0, 0, 180));
        addPopup(textfield, "Paste");
        catChoice = new Choice();
        catChoice.setBackground(new Color(235, 235, 255));
        Panel panel11 = new Panel(new BorderLayout());
        panel11.add("Center", Butsearchprod);
        // panel11.add("East", Butshowprod);

        Panel panel12 = new Panel(new BorderLayout());
        panel12.add("East", ButCatProds);
        Panel panel13 = new Panel(new BorderLayout());
        Label label4 = new Label("ProductID:   ");
        label4.setForeground(new Color(0, 0, 80));
        Label label5 = new Label("CategoryID: ");
        label5.setForeground(new Color(0, 0, 80));
        panel13.add("West", label4);
        panel13.add("Center", textfield);
        panel13.add("East", panel11);
        Panel panel14 = new Panel(new BorderLayout());
        panel14.add("West", label5);
        panel14.add("Center", catChoice);
        panel14.add("East", panel12);
        panel10.add("Center", panel13);
        panel10.add("South", panel14);
        Panel panel15 = new Panel(new BorderLayout());
        panel15.add("North", panel1);
        // panel15.add("South", panel4);
        thumbimgUrlLabel = new Label("ImgThumbNail Url: ");
        thumbimgUrlLabel.setForeground(new Color(0, 0, 80));
        prodimgUrlLabel = new Label("ProductImage Url: ");
        prodimgUrlLabel.setForeground(new Color(0, 0, 80));
        thumbimgUrlTArea = new TextField("");
        thumbimgUrlTArea.setForeground(new Color(0, 0, 80));
        prodimgUrlTArea = new TextField("");
        prodimgUrlTArea.setForeground(new Color(0, 0, 80));
        Panel panel16 = new Panel(new BorderLayout());
        panel16.add("Center", prodimgUrlLabel);
        panel16.add("South", thumbimgUrlLabel);
        Panel panel17 = new Panel(new BorderLayout());
        panel17.add("North", prodimgUrlTArea);
        panel17.add("Center", thumbimgUrlTArea);
        panel17.add("East", ButImgUpload);
        Panel panel18 = new Panel(new BorderLayout());
        imageuploadLabel = new Label("Upload Images:");
        imageuploadLabel.setForeground(new Color(0, 0, 180));
        panel18.add("North", imageuploadLabel);
        panel18.add("Center", panel17);
        panel18.add("West", panel16);
        addPopup(thumbimgUrlTArea, "Paste");
        addPopup(prodimgUrlTArea, "Paste");
        Panel panel19 = new Panel(new BorderLayout());
        panel19.add("North", panel15);
        Panel panel19a = new Panel(new BorderLayout());
        panel19a.add("South", panel4);
        panel19a.add("Center", prodfieldsList);
        panel19.add("Center", panel19a);
        panel19.add("South", panel18);
        Panel panel20 = new Panel(new BorderLayout());

        cardsSmall.add("mainPan", panel19);
        cardsSmall.add("desctAreaPan", descTpanel);

        panel20.add("North", panel7);
        panel20.add("Center", cardsSmall);
        // panel20.add("Center", panel19);


        panel20.add("South", panel9);
        add("Center", panel20);


        bugReport.addActionListener(this);
        bugReport.addMouseListener(this);
        UsellButton.addActionListener(this);
        UsellButton.addMouseListener(this);
        addchan.addActionListener(this);
        addchan.addMouseListener(this);
        descTaButton.addActionListener(this);
        chandelete.addActionListener(this);
        chandelete.addMouseListener(this);
        bold.addActionListener(this);
        bold.addMouseListener(this);
        prodfieldsList.addItemListener(this);
        prodfieldsList.addActionListener(this);
        searchbut.addActionListener(this);
        searchbut.addMouseListener(this);
        searchinput.addMouseListener(this);
        replaceinput.addMouseListener(this);
        prodFieldTarea.addActionListener(this);
        prodFieldTarea.addMouseListener(this);
        textfield.addActionListener(this);
        textfield.addMouseListener(this);
        Butsearchprod.addActionListener(this);
        Butsearchprod.addMouseListener(this);
        Butshowprod.addActionListener(this);
        Butshowprod.addMouseListener(this);
        ButImgUpload.addActionListener(this);
        ButImgUpload.addMouseListener(this);
        ButCatProds.addActionListener(this);
        ButCatProds.addMouseListener(this);
        thumbimgUrlTArea.addMouseListener(this);
        prodimgUrlTArea.addMouseListener(this);
        addWindowListener(new EditItemWindowListener());
        qitem = s;
        qitem = replaceString(qitem, "\t\t", "\tnoQvalue\t");
        String as[] = readmessTokens(qitem, "\t");
        int i = 0;
        try {
            while (i < as.length) {
                prodfieldsList.addItem(as[i]);
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
            System.out.println(arrayindexoutofboundsexception + "\n" + ":\n" + as[i]);
        }
        prodfieldsList.select(0);
        prodfieldsList.select(1);
        prodfieldsList.addItemListener(this);
        prodfieldsList.addActionListener(this);
        prodFieldTarea.addActionListener(this);
        prodFieldTarea.setText(prodfieldsList.getItem(0));
        oldString = prodfieldsList.getItem(0);
        setLocation(20, 90);
        resize(320, 500);
        setVisible(true);
        isbolder = false;

        setResizable(false);
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

    public void saveWatchlist() {
        try {
            String s = prodFieldTarea.getText();
            prodfieldsList.addItem(s);
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/iecanvaz/links.ini");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int i = 0; i < prodfieldsList.getItemCount(); i++)
                printstream.println(prodfieldsList.getItem(i));

            printstream.close();
            fileoutputstream.close();
            parent.setQstatus(parent.aplangstrings.getProperty("text051"), false);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        loadWatchlist();
    }

    PopupMenu getHash(Component component) {
        return (PopupMenu) popupTable.get(component);
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void searchRecAndRep(String s, String s1) {
        String s2 = "";
        int i = prodfieldsList.getItemCount();
        int j = 0;
        try {
            while (j < i) {
                String s3 = prodfieldsList.getItem(j);

                String nstring = replaceString(s3, s, s1);
                prodfieldsList.replaceItem(nstring, j);
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

    public void getCatIDs() {
        catChoice.removeAll();
        try {
            FileInputStream fileinputstream = new FileInputStream("cbox/data/catids.txt");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null)
                catChoice.addItem(s);
            datainputstream.close();
            fileinputstream.close();
            return;
        } catch (Exception _ex) {
            parent.setQstatus(parent.aplangstrings.getProperty("text045"), false);
        }
    }

    public static String[] readmessTokens(String s, String s1) {
        String s2 = s;
        String s3 = s1;
        StringTokenizer stringtokenizer = new StringTokenizer(s2, s3);
        int i = stringtokenizer.countTokens();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
            as[j] = stringtokenizer.nextToken();

        return as;
    }

    public void saveCatIDs(String s) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/data/catids.txt");
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(s);
            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        parent.setQstatus(parent.aplangstrings.getProperty("text050"), false);
        getCatIDs();
    }

    public void isitBolder() {
        if (!isbolder) {
            resize(320, 48);
            setLocation(450, 10);
            isbolder = true;
            return;
        }
        if (isbolder) {
            resize(320, 500);
            setLocation(20, 90);
            isbolder = false;
            return;
        } else {
            return;
        }
    }

    public void uploadImgs() {
        String s = prodfieldsList.getItem(0);
        String s1 = prodimgUrlTArea.getText().toLowerCase();
        if (s1.endsWith(".gif"))
            s1 = s + ".gif";
        if (s1.endsWith(".jpg") || s1.endsWith(".jpeg"))
            s1 = s + ".jpg";
        if (s1.endsWith(".png"))
            s1 = s + ".png";
        prodfieldsList.replaceItem(s1, 8);
        String s2 = thumbimgUrlTArea.getText().toLowerCase();
        if (s2.endsWith(".gif"))
            s2 = s + "_small.gif";
        if (s2.endsWith(".jpg") || s2.endsWith(".jpeg"))
            s2 = s + "_small.jpg";
        if (s2.endsWith(".png"))
            s2 = s + "_small.png";
        prodfieldsList.replaceItem(s2, 9);
        String s3 = "theimgurl=" + prodimgUrlTArea.getText() + "&theimgname=" + s1 + "&thethmbimgurl=" + thumbimgUrlTArea.getText() + "&thethmbimgname=" + s2;
        String s4 = parent.hostfolder + "images/products/qimgupload.php?" + parent.userpassString + s3;
        System.out.println(s4);
        String s5 = "";
        try {
            URL url = new URL(s4);
            java.io.InputStream inputstream = url.openStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            String s6;
            while ((s6 = bufferedreader.readLine()) != null)
                s5 = s5 + s6;
        } catch (Exception exception) {
            System.out.println(exception);
            parent.setQstatus(exception.toString(), true);
        }
        parent.setQstatus(s5, false);
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
            if (popobject == prodFieldTarea)
                prodFieldTarea.setText(s);
            System.out.println(s);
            return;
        } catch (Throwable throwable) {
            System.err.println(throwable);
        }
    }


    public void getdesctarea() {
        ((CardLayout) cardsSmall.getLayout()).show(cardsSmall, "desctAreaPan");
    }


    public void getprodlist() {
        String ostring = replaceString(descTextArea.getText(), "\n", "<br>");
        String nstring = replaceString(ostring, "<ul>", "");
        String nstring1 = replaceString(nstring, "</ul>", "");
        String nstring2 = replaceString(nstring1, "<li>", "<br>* ");
        prodfieldsList.replaceItem(nstring2, prodfieldsList.getSelectedIndex());
        ((CardLayout) cardsSmall.getLayout()).show(cardsSmall, "mainPan");
    }


    public void upSellItem() {
        long nowLong = System.currentTimeMillis() / 1000;
        String datestring = Long.toString(nowLong);
        int myint = Integer.parseInt(datestring);
        System.out.println("Value is " + myint);

        int dtime = Integer.parseInt(UsellCloseTfieldD.getText()) * 86400;
        int htime = Integer.parseInt(UsellCloseTfieldH.getText()) * 3600;
        int mtime = Integer.parseInt(UsellCloseTfieldM.getText()) * 60;
        int ctime = dtime + htime + mtime;
        if (ctime <= 0) {
            parent.setQstatus(parent.aplangstrings.getProperty("text543"), false);
            return;
        }
        int ttime = myint + dtime + htime + mtime;
        String ttimestring = Integer.toString(ttime);
        prodfieldsList.replaceItem(ttimestring, 24);
        prodfieldsList.replaceItem(UsellTypeChoice.getSelectedItem(), 25);
        prodfieldsList.replaceItem(UsellPubPriceTfield.getText(), 26);
        prodfieldsList.replaceItem(UsellWhsPriceTfield.getText(), 27);
        saveProdRecord();

    }


    String qitem;
    PopupMenu pm;
    MenuItem mi1;
    Component popComponent;
    java.awt.List prodfieldsList;
    Choice catChoice;
    TextField searchinput;
    TextField replaceinput;
    TextField prodIDfield;
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
    Label tiplabel1;
    Label label1;
    Label searchinputLabel;
    Label replaceinputLabel;
    Label prodIDlabel;
    Label catIDlabel;
    Label prodTempteLabel;
    Label searchrepLabel;
    String inifile;
    Properties httpconfig;
    adminApp parent;
    Label l;
    static String s9 = "";
    TextField prodFieldTarea;
    MediaTracker tracker;
    Hashtable popupTable;
    String oldString;
    int oldInt;
    int intprodfieldsList;
    int intListfieldvals;
    String ilinenum;
    Label imageuploadLabel;
    Label thumbimgUrlLabel;
    Label prodimgUrlLabel;
    TextField thumbimgUrlTArea;
    TextField prodimgUrlTArea;
    Object popobject;
    TextArea descTextArea;
    Panel cardsSmall;
    Button descTaButton;
    Label label;
    Label UsellLabel;
    Label UsellTypeLabel;
    Choice UsellTypeChoice;
    Label UsellCloseLabel;
    Label UsellCloseLabelD;
    Label UsellCloseLabelH;
    Label UsellCloseLabelM;
    TextField UsellCloseTfieldD;
    TextField UsellCloseTfieldH;
    TextField UsellCloseTfieldM;
    Label UsellPubPriceLabel;
    Label UsellWhsPriceLabel;
    TextField UsellPubPriceTfield;
    TextField UsellWhsPriceTfield;
}
