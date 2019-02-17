package njfbrowser.misc;

import njfbrowser.main.adminApp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class UserPane extends Panel
        implements KeyListener, ActionListener, ItemListener, MouseListener {

    public void WindowSetTop(int i) {
    }

    public void snavigates(String s) {
        BrowserControl.displayURL(s);
    }

    public void open() {
        System.out.println("Ok");
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == btnGoHome)
            snavigates(parent.hostfolder);
        if (mouseevent.getSource() == btnStop)
            System.out.println("home");
        if (mouseevent.getSource() == btnRefresh)
            System.out.println("home");
        if (mouseevent.getSource() == btnNext)
            System.out.println("home");
        if (mouseevent.getSource() == btnPrevious)
            System.out.println("home");
        if (mouseevent.getSource() == btnGTrans)
            snavigates(parent.hostfolder + "admin/index.php");
        if (mouseevent.getSource() == btnBlaunch)
            if (maddress.getText().startsWith("http") || maddress.getText().startsWith("www") || maddress.getText().startsWith("ftp"))
                snavigates(maddress.getText());
            else
                new Messdialog1(parent, parent.aplangstrings.getProperty("text198"));
        if (mouseevent.getSource() == btnFavs) {
            if (!adminApp.qlinksframe1) {
                String s = maddress.getText();
                new JBrowserOpen(parent, s, 99);
                adminApp.qlinksframe1 = true;
                return;
            }
            new Messdialog1(parent, parent.aplangstrings.getProperty("text014"));
        }
    }

    public void favs(String s) {
        try {
            BrowserLauncher.openURL(s);
            return;
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public UserPane(adminApp myslic) {
        parent = myslic;
        setLayout(new BorderLayout());
        tracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage("cbox/images/refresh.gif");
        img2 = Toolkit.getDefaultToolkit().getImage("cbox/images/stop.gif");
        img3 = Toolkit.getDefaultToolkit().getImage("cbox/images/next.gif");
        img4 = Toolkit.getDefaultToolkit().getImage("cbox/images/previous.gif");
        img5 = Toolkit.getDefaultToolkit().getImage("cbox/images/favsbutton.gif");
        img6 = Toolkit.getDefaultToolkit().getImage("cbox/images/browlaunch.gif");
        img7 = Toolkit.getDefaultToolkit().getImage("cbox/images/btnGTrans.gif");
        img8 = Toolkit.getDefaultToolkit().getImage("cbox/images/gohome.gif");
        tracker.addImage(img1, 1);
        tracker.addImage(img2, 2);
        tracker.addImage(img3, 3);
        tracker.addImage(img4, 4);
        tracker.addImage(img5, 5);
        tracker.addImage(img6, 6);
        tracker.addImage(img7, 7);
        tracker.addImage(img8, 8);
        try {
            tracker.waitForAll();
        } catch (InterruptedException _ex) {
        }
        btnRefresh = new ImageButton("refresh", img1);
        btnStop = new ImageButton("stop", img2);
        btnNext = new ImageButton("next", img3);
        btnPrevious = new ImageButton("previous", img4);
        btnFavs = new ImageButton("favs", img5);
        btnBlaunch = new ImageButton("brow launch", img6);
        btnGTrans = new ImageButton("trans launch", img7);
        btnGoHome = new ImageButton("Go Home", img8);
        addressLabel = new Label(parent.aplangstrings.getProperty("text195") + " ", 2);
        Panel panel = new Panel(new GridLayout(0, 8));
        panel.add(btnPrevious);
        panel.add(btnNext);
        panel.add(btnStop);
        panel.add(btnRefresh);
        panel.add(btnBlaunch);
        panel.add(btnFavs);
        panel.add(btnGTrans);
        panel.add(btnGoHome);
        btnRefresh.addMouseListener(this);
        btnRefresh.addActionListener(this);
        btnStop.addMouseListener(this);
        btnStop.addActionListener(this);
        btnNext.addMouseListener(this);
        btnNext.addActionListener(this);
        btnPrevious.addMouseListener(this);
        btnPrevious.addActionListener(this);
        btnFavs.addMouseListener(this);
        btnFavs.addActionListener(this);
        btnBlaunch.addMouseListener(this);
        btnBlaunch.addActionListener(this);
        btnGTrans.addMouseListener(this);
        btnGTrans.addActionListener(this);
        btnGoHome.addMouseListener(this);
        btnGoHome.addActionListener(this);
        TextField textfield = new TextField("Status");
        Panel panel1 = new Panel(new BorderLayout());
        panel1.add("Center", textfield);
        maddress = new TextField("");
        Panel panel2 = new Panel(new BorderLayout());
        panel2.add("Center", maddress);
        panel2.add("West", addressLabel);
        Panel panel3 = new Panel(new BorderLayout());
        panel3.add("West", panel);
        panel3.add("Center", panel2);
        TextArea textarea = new TextArea();
        Panel panel4 = new Panel(new BorderLayout());
        panel4.add("North", panel3);
        panel4.add("Center", textarea);
        panel4.add("South", panel1);
        add("Center", panel4);
        maddress.addActionListener(this);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyPressed(KeyEvent keyevent) {
    }

    public void itemStateChanged(ItemEvent itemevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void windowDeactivated(WindowEvent windowevent) {
    }

    public void getMainMenu(String s) {
        BrowserControl.displayURL(s);
        maddress.setText(s);
    }

    public void logoff() {
    }

    public static void main(String args[]) {
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void actionPerformed(ActionEvent actionevent) {
    }

    public void mouseEntered(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
        parent.tiplabel.setText("");
    }

    public void windowDeiconified(WindowEvent windowevent) {
    }

    public void windowActivated(WindowEvent windowevent) {
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void snavigate() {
        BrowserControl.displayURL(maddress.getText());
    }

    public void append(String s) {
        System.out.println(s);
    }

    public void windowIconified(WindowEvent windowevent) {
    }

    public void newby() {
    }

    public void WindowSetResizable(boolean flag) {
    }

    public void WindowSetLeft(int i) {
    }

    TextField status;
    TextField maddress;
    TextArea browser;
    adminApp parent;
    Image img1;
    Image img2;
    Image img3;
    Image img4;
    Image img5;
    Image img6;
    Image img7;
    Image img8;
    ImageButton btnRefresh;
    ImageButton btnStop;
    ImageButton btnNext;
    ImageButton btnPrevious;
    ImageButton btnBlaunch;
    ImageButton btnGTrans;
    ImageButton btnFavs;
    ImageButton btnGoHome;
    MediaTracker tracker;
    Label addressLabel;
}
