package njfbrowser.spreadsheet;

import njfbrowser.main.adminApp;
import njfbrowser.misc.ImageButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SharpPopmenu extends JWindow implements ActionListener, MouseListener

{


    class SharpPopmenuWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public SharpPopmenuWindowListener() {
        }
    }


    public SharpPopmenu(adminApp adminaaAp, String s, int popwidth, int popheight) {
        super(adminaaAp);
        parent = adminaaAp;
        imgName = s;


        int popWpoint = 0;
        int popHpoint = 0;
        sLtk = Toolkit.getDefaultToolkit();
        sLtktracker = new MediaTracker(this);
        getContentPane().setLayout(new BorderLayout(1, 1));
        setBackground(new Color(0, 0, 0));
        imgSLdbpan = sLtk.getImage("cbox/images/imgbtnSLdbpan.gif");


        sLtktracker.addImage(imgSLdbpan, 1);
        try {
            sLtktracker.waitForAll();
        } catch (InterruptedException interruptedexception) {
            System.out.println(interruptedexception);
        }
        imgBtnSLdbpan = new ImageButton("DB Panel", imgSLdbpan);

        sLcopy = new SharpPopmenuLabel(this, "Copy", "copy", 1, new Color(0, 0, 160));
        sLpaste = new SharpPopmenuLabel(this, "Paste", "paste", 1, new Color(0, 0, 160));
        sLuploadrecords = new SharpPopmenuLabel(this, "Upload Records", "uloadrecords", 1, new Color(180, 0, 0));
        sLaddRow = new SharpPopmenuLabel(this, "Add Row", "addrow", 1, new Color(180, 18, 180));
        sLEditRecord = new SharpPopmenuLabel(this, "Edit Record", "editrecord", 1, new Color(180, 18, 180));
        sLdbqBox = new SharpPopmenuLabel(this, "DB Query Box", "dbqbox", 1, new Color(0, 0, 0));
        sLbrowserpan = new SharpPopmenuLabel(this, "Browser Panel", "browserpan", 1, new Color(0, 0, 250));
        sLclosePop = new SharpPopmenuLabel(this, "Close", "closepop", 1, new Color(0, 0, 40));


        jlblTest = new JLabel("hehe hahah");
        jlblTest.setOpaque(true);
        jlblTest.addMouseListener(this);


        poppanel = new Panel(new GridLayout(9, 1, 1, 1));
        poppanel.setBackground(new Color(0, 0, 0));
        poppanel.add(sLcopy);
        poppanel.add(sLpaste);
        poppanel.add(sLuploadrecords);
        poppanel.add(sLaddRow);
        poppanel.add(sLEditRecord);
        poppanel.add(sLdbqBox);
        poppanel.add(sLbrowserpan);
        poppanel.add(sLclosePop);
        poppanel.add(jlblTest);

        popMpanel = new Panel(new GridLayout(1, 1, 1, 1));
        poppanel.setBackground(new Color(0, 0, 0));
        popMpanel.add("Center", poppanel);


        Panel popLeftPan = new Panel(new BorderLayout(1, 1));
        popLeftPan.setBackground(new Color(0, 0, 0));
        Panel popTopPan = new Panel(new BorderLayout(1, 1));
        popTopPan.setBackground(new Color(0, 0, 0));
        Panel popBotmPan = new Panel(new BorderLayout(1, 1));
        popBotmPan.setBackground(new Color(0, 0, 0));
        Panel popRightPan = new Panel(new BorderLayout(1, 1));
        popRightPan.setBackground(new Color(0, 0, 0));

        Panel popTTpanel = new Panel(new BorderLayout(1, 1));
        popTTpanel.setBackground(new Color(0, 0, 0));
        popTTpanel.add("North", popTopPan);
        popTTpanel.add("West", popLeftPan);
        popTTpanel.add("Center", popMpanel);
        popTTpanel.add("East", popRightPan);
        // popTTpanel.add("South", popBotmPan);


        getContentPane().add(popTTpanel);
        int scrnsquareW = sLtk.getScreenSize().width / 2;
        int scrnsquareH = sLtk.getScreenSize().height / 2;
        if (popwidth > scrnsquareW) {
            popWpoint = popwidth - 180;
        } else {
            popWpoint = popwidth;
        }
        if (popheight > scrnsquareH) {
            popHpoint = popheight - 160;
        } else {
            popHpoint = popheight;
        }
        setLocation(popWpoint, popHpoint);
        // resize(150, 260);
        pack();
        setVisible(true);
        //   addWindowListener(new SharpPWindowListener());
        addWindowListener(new SharpPopmenuWindowListener());


    }

    public void paint(Graphics g) {
        g.drawImage(splashImage, 2, 2, imgWidth, imgHeight, this);
    }


    public Image loadSplashImage() {
        MediaTracker mediatracker = new MediaTracker(this);
        Image image = sLtk.getImage(imgName);
        mediatracker.addImage(image, 0);
        try {
            mediatracker.waitForAll();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        imgWidth = image.getWidth(this);
        imgHeight = image.getHeight(this);
        return image;
    }


    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
    }

    public void hidefornow() {
        parent.boolSharpopOpen = false;
        dispose();
    }


    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == jlblTest) {
            parent.setQstatus("heheh", true);
        }

    }


    public void mouseEntered(MouseEvent mouseevent) {
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
    }

    public void parntsetSharpTrick(String s) {
        setVisible(false);
        if (s == "closepop") {
            parent.boolSharpopOpen = false;
            dispose();
        } else {
            parent.sharpTricks(s);
            System.out.println("sharpTricks: " + s);
            dispose();
        }
    }


    private Image splashImage;
    private int imgWidth;
    private int imgHeight;
    private String imgName;
    private static final int BORDERSIZE = 2;
    Toolkit sLtk;
    Button popbutton;
    adminApp parent;

    Panel poppanel;
    Panel popMpanel;

    SharpPopmenuLabel sLcopy;
    SharpPopmenuLabel sLpaste;
    SharpPopmenuLabel sLbrowserpan;
    SharpPopmenuLabel sLuploadrecords;
    SharpPopmenuLabel sLaddRow;
    SharpPopmenuLabel sLEditRecord;
    SharpPopmenuLabel sLdbqBox;
    SharpPopmenuLabel sLclosePop;
    JLabel jlblTest;
    Image imgSLdbpan;
    ImageButton imgBtnSLdbpan;
    MediaTracker sLtktracker;

}
