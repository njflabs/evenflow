package njfbrowser.misc;

import njfbrowser.main.adminApp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

public class mCdon extends Dialog
        implements ActionListener, KeyListener, ItemListener, MouseListener {
    public class JBrowserOpenWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            adminApp.qlinksOn = false;
            dispose();
        }

        public JBrowserOpenWindowListener() {
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
            inifile = "cbox/prefs/01exe.ini";
            FileInputStream fileinputstream = new FileInputStream("cbox/prefs/01exe.ini");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            httpconfig.load(fileinputstream);
            String s = URLEncoder.encode(httpconfig.getProperty("search1"));
            String s1 = URLEncoder.encode(httpconfig.getProperty("search2"));
            String s2 = URLEncoder.encode(httpconfig.getProperty("search3"));
            String s3 = URLEncoder.encode(httpconfig.getProperty("search4"));
            String s4 = URLEncoder.encode(httpconfig.getProperty("search5"));
            String s5;
            while ((s5 = datainputstream.readLine()) != null)
                searchChoice.addItem(s);
            datainputstream.close();
            fileinputstream.close();
            searchChoice.addItem(s);
            searchChoice.addItem(s1);
            searchChoice.addItem(s2);
            searchChoice.addItem(s3);
            searchChoice.addItem(s4);
            return;
        } catch (Exception _ex) {
            System.out.println("Error loading searchlist");
        }
    }

    public void showdaquickie() {
        urlEdit.setText(quicklist.getSelectedItem());
        urlEdit.requestFocus();
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == addchan) {
            String s = urlEdit.getText();
            if (s.trim().length() < 1 || s == "quickLinks")
                new Messdialog1(parent, "Introduza o link que quer adicionar \340 lista quickLinks");
            else
                saveWatchlist();
        }
        if (mouseevent.getSource() == searchbut) {
            String s1 = searchChoice.getSelectedItem();
            String s2 = searchinput.getText();
            String s3 = s2.replace(' ', '+');
            if (s1.length() < 1 || s2.trim().length() < 1)
                new Messdialog1(parent, "Introduza a palavra ou frase que quer pesquisar");
            else
                favos("http://www.myslic.com/pushdex/" + s1 + ".shtml?" + s3);
        }
        if (mouseevent.getSource() == chandelete) {
            int i = quicklist.getSelectedIndex();
            if (i == 0) {
                new Messdialog1(parent, "Escolha da lista o link que quer eliminar");
            } else {
                int j = quicklist.getSelectedIndex();
                quicklist.remove(j);
                savenewlist();
            }
        }
        if (mouseevent.getSource() == bugReport)
            favos("http://www.myslic.com/buddyb/feedback.htm");
        if (mouseevent.getSource() == ircajuda)
            favos("http://www.myslic.com");
        if (mouseevent.getSource() == bold)
            isitBolder();
    }

    public mCdon(adminApp myslic) {
        super(myslic, "addOns");
        parent = myslic;
        setBackground(new Color(210, 200, 160));
        setForeground(new Color(210, 200, 160));
        tracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage("cbox/images/newmess.gif");
        img5 = Toolkit.getDefaultToolkit().getImage("cbox/images/delchann.gif");
        img6 = Toolkit.getDefaultToolkit().getImage("cbox/images/addchann.gif");
        img7 = Toolkit.getDefaultToolkit().getImage("cbox/images/minimiz.gif");
        img8 = Toolkit.getDefaultToolkit().getImage("cbox/images/help.gif");
        img11 = Toolkit.getDefaultToolkit().getImage("cbox/images/peslogo.gif");
        img12 = Toolkit.getDefaultToolkit().getImage("cbox/images/searchbut.gif");
        tracker.addImage(img1, 1);
        tracker.addImage(img5, 5);
        tracker.addImage(img6, 6);
        tracker.addImage(img7, 7);
        tracker.addImage(img8, 8);
        tracker.addImage(img11, 11);
        tracker.addImage(img12, 12);
        try {
            tracker.waitForID(1);
            tracker.waitForID(5);
            tracker.waitForID(6);
            tracker.waitForID(7);
            tracker.waitForID(8);
            tracker.waitForID(11);
            tracker.waitForID(12);
        } catch (InterruptedException _ex) {
        }
        bugReport = new ImageButton("Visite our Sponsors", img1);
        chandelete = new ImageButton("Delete Channel", img5);
        addchan = new ImageButton("Add Channel", img6);
        bold = new ImageButton("Minimize", img7);
        ircajuda = new ImageButton("Help", img8);
        quicklinks = new ImageButton("quicklinks", img11);
        searchbut = new ImageButton("searchbut", img12);
        tiplabel1 = new Label("quickLinks e pesQuisa");
        tiplabel1.setForeground(new Color(0, 0, 160));
        Label label = new Label("DuploClick no Link para abrir:");
        label.setForeground(new Color(0, 0, 0));
        urlEdit = new TextField();
        urlEdit.setBackground(new Color(240, 230, 210));
        urlEdit.setForeground(new Color(0, 0, 180));
        searchinput = new TextField();
        searchinput.setBackground(new Color(235, 235, 255));
        searchinput.setForeground(new Color(0, 0, 180));
        quicklist = new Choice();
        quicklist.setBackground(new Color(240, 230, 210));
        searchChoice = new Choice();
        searchChoice.setBackground(new Color(235, 235, 255));
        new Panel();
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(new Color(210, 200, 160));
        panel.setForeground(new Color(210, 200, 160));
        panel.add("West", addchan);
        panel.add("Center", chandelete);
        panel.add("East", ircajuda);
        Panel panel1 = new Panel(new BorderLayout());
        panel1.setBackground(new Color(210, 200, 160));
        panel1.setForeground(new Color(210, 200, 160));
        panel1.add("Center", quicklist);
        panel1.add("East", bold);
        Panel panel2 = new Panel(new BorderLayout());
        panel2.add("Center", urlEdit);
        panel2.add("East", panel);
        Panel panel3 = new Panel(new BorderLayout());
        panel3.add("Center", tiplabel1);
        Panel panel4 = new Panel(new BorderLayout());
        panel4.add("North", panel1);
        panel4.add("Center", panel2);
        panel4.add("South", panel3);
        Panel panel5 = new Panel(new BorderLayout());
        panel5.setBackground(new Color(210, 200, 160));
        panel5.setForeground(new Color(210, 200, 160));
        panel5.add("North", searchinput);
        panel5.add("Center", searchChoice);
        Panel panel6 = new Panel(new BorderLayout());
        panel6.setBackground(new Color(210, 200, 160));
        panel6.setForeground(new Color(210, 200, 160));
        panel6.add("Center", searchbut);
        Panel panel7 = new Panel(new BorderLayout());
        panel7.add("Center", panel5);
        panel7.add("West", panel6);
        Panel panel8 = new Panel(new BorderLayout());
        panel8.add("North", panel4);
        panel8.add("Center", panel7);
        add(panel8);
        bugReport.addActionListener(this);
        bugReport.addMouseListener(this);
        ircajuda.addActionListener(this);
        ircajuda.addMouseListener(this);
        addchan.addActionListener(this);
        addchan.addMouseListener(this);
        chandelete.addActionListener(this);
        chandelete.addMouseListener(this);
        bold.addActionListener(this);
        bold.addMouseListener(this);
        quicklist.addItemListener(this);
        quicklist.addMouseListener(this);
        quicklinks.addActionListener(this);
        quicklinks.addMouseListener(this);
        searchbut.addActionListener(this);
        searchbut.addMouseListener(this);
        urlEdit.addKeyListener(this);
        addWindowListener(new JBrowserOpenWindowListener());
        setLocation(300, 15);
        loadWatchlist();
        resize(280, 145);
        setVisible(true);
        isbolder = false;
        showlinkOns();
        setResizable(false);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyPressed(KeyEvent keyevent) {
        if (keyevent.getKeyCode() == 10)
            favos(urlEdit.getText());
    }

    public void itemStateChanged(ItemEvent itemevent) {
        showdaquickie();
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void favos(String s) {
        try {
            BrowserLauncher.openURL(s);
            keepitSmaller();
            return;
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        new Messdialog1(parent, "Est\341 Ligado?");
    }

    public void loadWatchlist() {
        quicklist.removeAll();
        try {
            FileInputStream fileinputstream = new FileInputStream("cbox/iecanvaz/links.ini");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null)
                quicklist.addItem(s);
            datainputstream.close();
            fileinputstream.close();
            return;
        } catch (Exception _ex) {
            new Messdialog1(parent, "quick links n\343o ligou");
        }
    }

    public void savenewlist() {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/iecanvaz/links.ini");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int i = 0; i < quicklist.getItemCount(); i++)
                printstream.println(quicklist.getItem(i));

            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        new Messdialog1(parent, "Link eliminado");
        loadWatchlist();
    }

    public void saveWatchlist() {
        try {
            String s = urlEdit.getText();
            quicklist.addItem(s);
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/iecanvaz/links.ini");
            PrintStream printstream = new PrintStream(fileoutputstream);
            for (int i = 0; i < quicklist.getItemCount(); i++)
                printstream.println(quicklist.getItem(i));

            printstream.close();
            fileoutputstream.close();
            new Messdialog1(parent, "Link adicionado.");
        } catch (Exception exception) {
            System.out.println(exception);
        }
        loadWatchlist();
    }

    public static void main(String args[]) {
    }

    public void actionPerformed(ActionEvent actionevent) {
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void mouseEntered(MouseEvent mouseevent) {
        Component component = mouseevent.getComponent();
        if (component == ircajuda) {
            tiplabel1.setText("Ajuda");
            return;
        }
        if (component == searchbut) {
            tiplabel1.setText("Pesquisa");
            return;
        }
        if (component == chandelete) {
            tiplabel1.setText("eliminar link");
            return;
        }
        if (component == addchan) {
            tiplabel1.setText("adicionar link");
            return;
        }
        if (component == bugReport) {
            tiplabel1.setText("Fale connosco");
            return;
        }
        if (component == bold) {
            if (!isbolder) {
                tiplabel1.setText("Minimizar");
                return;
            } else {
                tiplabel1.setText("Maximizar");
                return;
            }
        } else {
            return;
        }
    }

    public void mouseExited(MouseEvent mouseevent) {
        tiplabel1.setText("");
    }

    public void isitBolder() {
        if (!isbolder) {
            resize(280, 53);
            setLocation(300, 15);
            isbolder = true;
            return;
        }
        if (isbolder) {
            resize(280, 145);
            setLocation(300, 15);
            isbolder = false;
            return;
        } else {
            return;
        }
    }

    Choice quicklist;
    Choice searchChoice;
    TextField searchinput;
    protected boolean isbolder;
    Image img1;
    Image img5;
    Image img6;
    Image img7;
    Image img8;
    Image img11;
    Image img12;
    ImageButton chandelete;
    ImageButton addchan;
    ImageButton bold;
    ImageButton ircajuda;
    ImageButton quicklinks;
    ImageButton searchbut;
    ImageButton bugReport;
    Label tiplabel1;
    String inifile;
    Properties httpconfig;
    adminApp parent;
    Label l;
    static String s9 = "";
    TextField urlEdit;
    MediaTracker tracker;

}
