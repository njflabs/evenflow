package njfbrowser.misc;

import njfbrowser.main.adminApp;
import njfbrowser.spreadsheet.PopmenuLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Popmenu extends Window implements ActionListener {
    class PopmenuWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public PopmenuWindowListener() {
        }
    }


    public Popmenu(adminApp adminaap, String s, int popwidth, int popheight) {
        super(adminaap);
        parent = adminaap;
        imgName = s;
        int popWpoint = 0;
        int popHpoint = 0;
        sLtk = Toolkit.getDefaultToolkit();
        sLtktracker = new MediaTracker(this);
        setLayout(new BorderLayout(1, 1));
        imgSLdbpan = sLtk.getImage("cbox/images/imgbtnSLdbpan.gif");


        sLtktracker.addImage(imgSLdbpan, 1);
        try {
            sLtktracker.waitForAll();
        } catch (InterruptedException interruptedexception) {
            System.out.println(interruptedexception);
        }
        imgBtnSLdbpan = new ImageButton("DB Panel", imgSLdbpan);

        sLbrowsePpup = new PopmenuLabel(this, "Browser PopUp", "browserPpup", 1);
        sLshophome = new PopmenuLabel(this, "Shop Home", "shophome", 1);
        sLadminhome = new PopmenuLabel(this, "Admin Panel", "adminhome", 1);
        sLeditProd = new PopmenuLabel(this, "Edit Page Product", "editprod", 1);
        sLaddProd = new PopmenuLabel(this, "Add Product", "addprod", 1);
        sLeditcat = new PopmenuLabel(this, "Edit Page Category", "editcat", 1);
        sLaddcat = new PopmenuLabel(this, "Add Category", "addcat", 1);
        sLnicepriceDB = new PopmenuLabel(this, "DB Query", "nicepricedb", 1);
        sLnicepriceWEB = new PopmenuLabel(this, "AdminPanel", "nicepriceweb", 1);
        sLniceprice = new PopmenuLabel(this, "Nice Price:", "niceprice", 1);
        sLshowDBPan = new PopmenuLabel(this, "DB Panel", "showdbpan", 1);
        sLrefreshPg = new PopmenuLabel(this, "Refresh Page", "refreshpage", 1);
        sLclosePop = new PopmenuLabel(this, "Close", "closepop", 1);

        Panel npricePanel = new Panel(new GridLayout(1, 3));
        npricePanel.add(sLniceprice);
        npricePanel.add(sLnicepriceDB);
        npricePanel.add(sLnicepriceWEB);

        Panel linksPanel = new Panel(new GridLayout(1, 2));
        linksPanel.add(sLshophome);
        linksPanel.add(sLadminhome);

        Panel popCommsPanel = new Panel(new GridLayout(1, 2));
        popCommsPanel.add(sLclosePop);
        popCommsPanel.add(sLrefreshPg);

        // Panel showDBpanPanel = new Panel(new FlowLayout());
        // showDBpanPanel.add(imgBtnSLdbpan);
        //  showDBpanPanel.add(sLshowDBPan);

        poppanel = new Panel(new GridLayout(5, 1, 1, 1));
        // poppanel.add(sLbrowsePpup);
        poppanel.add(sLeditProd);
        poppanel.add(sLeditcat);
        // poppanel.add(sLaddcat);
        // poppanel.add(npricePanel);
        poppanel.add(sLshowDBPan);
        poppanel.add(linksPanel);
        poppanel.add(popCommsPanel);

        Panel popLeftPan = new Panel(new BorderLayout());
        popLeftPan.setBackground(new Color(0, 0, 0));
        Panel popTopPan = new Panel(new BorderLayout());
        popTopPan.setBackground(new Color(0, 0, 0));
        Panel popBotmPan = new Panel(new BorderLayout());
        popBotmPan.setBackground(new Color(0, 0, 0));
        Panel popRightPan = new Panel(new BorderLayout());
        popRightPan.setBackground(new Color(0, 0, 0));

        Panel popMpanel = new Panel(new BorderLayout());
        popMpanel.add("North", popTopPan);
        popMpanel.add("West", popLeftPan);
        popMpanel.add("Center", poppanel);
        popMpanel.add("East", popBotmPan);

        popMpanel.add(poppanel);

        add(popMpanel);
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
        setLocation(popwidth + 25, popheight + 25);
        // resize(150, 260);
        pack();
        setVisible(true);
        addWindowListener(new PopmenuWindowListener());
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
        if (obj == popbutton) {
            parent.launchEditItem();
            dispose();
        } else {
            return;
        }
    }

    public void hidefornow() {
        dispose();
    }


    public void goaway() {
        dispose();
    }

    public void parntSetBrwsrPopBool() {
        setVisible(false);
        parent.setAdminPopbool(false);
        dispose();
    }

    public void parntsetHmeCanvasUrl() {
        setVisible(false);
        parent.setCanvasUrl(parent.hostfolder);
        dispose();
    }

    public void parntsetAdmCanvasUrl() {
        setVisible(false);
        parent.setCanvasUrl(parent.hostfolder + "adminshop/index.php");
        dispose();
    }

    public void parntsetBrowTrick(String s) {
        setVisible(false);
        parent.browTricks(s);
    }

    public void parntReloadCanvas() {
        parent.reloadTheCanvas();
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
    Panel npricePanel;
    PopmenuLabel sLbrowsePpup;
    PopmenuLabel sLshophome;
    PopmenuLabel sLadminhome;
    PopmenuLabel sLeditProd;
    PopmenuLabel sLaddProd;
    PopmenuLabel sLeditcat;
    PopmenuLabel sLaddcat;
    PopmenuLabel sLniceprice;
    PopmenuLabel sLnicepriceDB;
    PopmenuLabel sLnicepriceWEB;
    PopmenuLabel sLshowDBPan;
    PopmenuLabel sLrefreshPg;
    PopmenuLabel sLclosePop;

    Image imgSLdbpan;
    ImageButton imgBtnSLdbpan;
    MediaTracker sLtktracker;
}
