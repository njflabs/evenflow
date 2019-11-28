
// todo:
// remove qqwurytextarea stuff

package njfbrowser.main;


import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import njfbrowser.misc.*;
import njfbrowser.spreadsheet.*;
import njfbrowser.tasks.taskAAUComTextQs;
import njfbrowser.tasks.taskAAUploadQs;
import njfbrowser.js_interfaces.JSI_adminApp;
import njfbrowser.utils.BasicUtils;
import njfbrowser.utils.Bundle;
import njfbrowser.utils.UtilSQLAdapter;
import org.jibble.simplewebserver.SimpleWebServer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.CookiePolicy;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class adminApp extends JFrame
        implements ActionListener, KeyListener, ItemListener, MouseListener {
    static final String MAINPANEL = "sharpToolsCard";
    public static boolean qlinksOn = false;


    // stuff to delete
// --------------------------
// --------------------------
    public static boolean qlinksframe1 = false;


    // -------------------------
// end of stuff to delete
// -------------------------
    static Frame frame;
    public String tempRecEdit;
    public String currentDB;
    public String currentDBID;
    public String currentDBHost;
    public String tablename;
    public String fieldname;

    /// --------------------------------------
/// --------------------------------------
/// Database Scheme saving and getting
/// this saveAAPrefs was deprecated 
/// Use for something else
    public String criterianame;
    public String tempQResultsString;
    public String nowQ;
    public String prefsUserLang;
    public String prefsNumResults;
    public String prefsDefTableName;
    public boolean isIEversion;
    public boolean useAdminPop;
    public boolean boolDBQBoxOpen;
    public boolean boolAAPrefsdlgOpen;
    public boolean boolAddDBDlgOpen;
    public boolean boolLHelpOpen;
    public boolean boolSharpopOpen;
    public boolean boolBrowDBAOpen;
    public boolean boolstatusBoxOpen;
    public String inifile;
    public Properties httpconfig;
    public Properties aplangstrings;
    public Properties aamainprefs;
    public Properties aanewdbprefs;
    public JLabel tiplabel;
    public String server;
    public JTextArea QstatusTextArea;
    public JLabel QqueryTextlabel;
    public java.awt.List comtext;
    public String hostdbase;
 
    public JTextField maddress;
    public String aapassword;
    public String hostfolder;
    public String encryptedUname;
    public String encryptedPass;
    public String autosaveqs;
    public String aausername;
    public String userpassString;
    public String aahelpfolder;
    public String aahelpfile;
    public String aahelpusername;
    public String aahelppassword;
    public String aahelpdbname;

 
    public String aahelpnick;
    // to delete ?
    public String mmipScript;
    //-----------------------------------------------------------
//--------------------     Query stuff
    public String mmsmtpServer;
    public String mmserverPort;
    public String mmemailSubject;
    public String mmemailFrom;
    public String mnmstring;
    public String oldmnmstring;
    public String udString;
    public String fsString;
    public String currentDBTitle;
    public SharpTools dbfpanel;
    public JTextArea QqueryTextArea;
    protected boolean isbold;
    protected boolean browircStat;
    protected boolean winOntop;
    protected Applet parentApplet;
    protected Frame parentFrame;
    protected String ninfo;
    protected String boldstr;
    protected int ii;
    protected int total;
    protected JLabel statusLabel;
    protected BusyBlip blip;
    JSplitPane sharpSpltMainPanel;
    MediaTracker tracker;
    Image img1;
    Image img4;
    Image img7;
    Image img8;
    Image img9;

 
    Image img22;
    Image img23;
    Image img26;
    Image img27;
    Image img28;
    Image img29;
    Image img30;
    Image img31;
    ImageButton bold;
    ImageButton btnAAprefs;
    ImageButton irclogo;
    ImageButton btnadminPop;
    ImageButton btnmainbrowpan;


 
    ImageButton btnLiveHelp;
    ImageButton btnSnapShot;
    ImageButton bannerbut1;

 
    ImageButton winOntopImg;
    ImageIcon btnDBPan;
    JButton jbtnDBPan;
    ImageButton btnchckserv;
    ImageButton btnAAhelp;
    Image offscrimg;
    JPanel bannerimg;
    String spush;
    String t[];
    String o;
    String ous;
 
    int ihi;
    String url1[];
    int clbanner;
    String myipnumber;
    String args[];
    JLabel spacelabel;
    JPanel cards;
    Splash mySplash;
    String ircServerstring;
    JLabel Qcomtextlabel;
    CommLabel closebox;
    CommLabel clearbox;
    CommLabel insertQs;
    CommLabel searchrepQs;
    CommLabel runCom;
    CommLabel runAllComs;
    CommLabel deleteCom;
    CommLabel deleteAllComs;
 
    CommLabel getDstamp;
    CommLabel reloadQs;
    Button QdeleteQ;
    Button QdeleteallQs;
    String Qdbasestring;
    String Qtablestring;
    String Qcolumnstring;
    String Qcriteriastring;
    String Qfieldnames;
    String Qfilestring;
    String Qcomtextstring;
    String responseUrl;
    String dbRecord;
    FileOutputStream fos1;
    PrintWriter out1;
    JLabel clientLabel;
    CommLabel hidebox;
    SimpleDateFormat formatter;
    String dateText;
    JMenuBar mb;
    JMenu mFile;
    JMenu mView;
    JMenu mTools;
    JMenu mWidgets;
 
    JMenu mEdit;
    JMenu mData;
    JMenu mHelp;
    JMenu miLangMenu;
    JMenuItem miEditCopy;
    JMenuItem miEditPaste;
    JMenuItem miEditFillColumn;
    JMenuItem miDataEditRcrd;
    JMenuItem miDataUpldRcrd;
    JMenuItem miDataUpldAllRcrds;
    JMenuItem miDataAddRow;
    JMenuItem miDataAddNRows;
    JMenuItem miDataDelNRows;
    JMenuItem miOpen;
    JMenuItem miSave;
    JMenuItem miSaveAs;
    JMenuItem miQuit;
    JMenuItem miBrowPan;
    JMenuItem miDBPanel;
    JMenuItem miStatusBox;
    JMenuItem miMinMax;
    JMenuItem miOnTop;
    JMenuItem miDBQBox;
    JMenuItem miLHApp;
    JMenuItem miWebSttngs;
    JMenuItem miAddDB;
    JMenuItem miLangptPT;
    JMenuItem miLangenUS;
    JMenuItem miTimeStamp;
    JMenuItem miOLHelp;
    JMenuItem miOLHelpWthis;
    JMenuItem miAbout;
    JPopupMenu adminppmenu;
    JMenuItem brtodbPPmItem;
    JMenuItem closePPmItem;
    String localdbnameProp;
    Calendar calendr;
    UserPane ivjNMEETUI1;
    Image broWimg1;
    Image broWimg2;
    Image broWimg3;
    Image broWimg4;
    Image broWimg5;
    Image broWimg6;
    Image broWimg7;
    Image broWimg8;
    ImageButton broWbtnRefresh;
    ImageButton broWbtnStop;
    ImageButton broWbtnNext;
    ImageButton broWbtnPrevious;
    ImageButton broWbtnBlaunch;
    ImageButton broWbtnGTrans;
    ImageButton broWbtnFavs;
    ImageButton broWbtnGoHome;
    JButton btnDelPendQuery;
    JButton btnDelAllPendQuery;
    JButton btnRunPendQuery;
    JButton btnRunAllPendQuery;
    JLabel addressLabel;
    JPanel broWbtnPanel;
    int useproxyAAInt;
    String useproxyAAPref;
    String proxyhostAAPref;
    String proxyportAAPref;
    String answerstring;
    String localwebfolder;
    Popmenu adminpop;
    JPanel pnlBrowserCage;
    ClientHttpRequest chttpreq;
    DBQueryBox dbqueryBox;
    AAPrefsdialog aaPrefsDlg;
 
    EditDBdialog editDBDlg;
    JBrowserOpen jbrowserOpen;
    SharpPopmenu shrppopmenu;
 
    StatusBox statusBox;
    BrowDBAction dbactionBox;
 
    taskAAUploadQs taskAAUQs;
    taskAAUComTextQs taskAAUCTQs;
    String strngTempDB;
    String strngTempTable;
    String strngTempQStrng;
    Thread dm;
 
    getCatResPinger getCATRP;
    JFileChooser fileChAdmnApp;
    DefaultListModel tempModelDataList;
    private PrintWriter fileOut;
    private Stage stage;
    private WebView browser;
    private JFXPanel jfxPanel;
    private JButton swingButton;
    private WebEngine webEngine;

	JSI_adminApp jsiAdminApp;
	SimpleWebServer simpleWebServer;
	BasicUtils basicUtils;
	public Bundle currConfBundle;



    public  String currPageVars;

    public UtilSQLAdapter dbMSQLA;







































    public adminApp() {
        setTitle("adminApp");
        isIEversion = true;

        try {
            // Set cross-platform Java L&F (also called "Metal")
            //  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        udString = System.getProperty("user.dir");
        fsString = File.separator;
        mySplash = new Splash(this, getUfile("cbox/images/splash.gif"));
 
        loadAAprefs();
        loadAPlangStrings();
        getContentPane().setLayout(new BorderLayout());
        setFont(new java.awt.Font("Arial", 0, 14));
        setBackground(new java.awt.Color(225, 225, 225));
        statusBox = new StatusBox(this);
	  currPageVars = "noQvalue";
 
 
	  /*
      * JFileChooser Code
	  */
        fileChAdmnApp = new JFileChooser();
	  dbMSQLA = new UtilSQLAdapter();

        System.out.println("ud: " + udString + "   fs: " + fsString);

 
        QqueryTextlabel = new JLabel("Query Results:");
        QqueryTextArea = new JTextArea("", 50, 300);
        QqueryTextArea.addMouseListener(this);

 


 


        myipnumber = "";
        ircServerstring = "";
        int i = 10;
        clbanner = (int) (Math.random() * (double) i) + 1;
        String s = "cbox/images/slicbnr" + clbanner + ".gif";
        server = "http://localhost";
        qlinksframe1 = false;
        qlinksOn = false;
        winOntop = false;

        boolDBQBoxOpen = false;
        boolAAPrefsdlgOpen = false;
        boolAddDBDlgOpen = false;
        boolLHelpOpen = false;
        boolSharpopOpen = false;
        boolBrowDBAOpen = false;
        boolstatusBoxOpen = false;
        browircStat = true;
        useproxyAAInt = 0;


        currentDB = "Demo-Database";
        currentDBHost = "localhost";
        currentDBID = "555";
        currentDBTitle = "";

        tempRecEdit = "";
        tablename = "";
        fieldname = "";
        criterianame = "";
        tempQResultsString = "";
        nowQ = "";

        prefsUserLang = aamainprefs.getProperty("userLang");
        prefsNumResults = aamainprefs.getProperty("prefsNumResults");
        prefsDefTableName = aamainprefs.getProperty("prefsDefTableName");
	  jsiAdminApp = new JSI_adminApp(this);


	/*  simplewebserver is not used here
	 *  maybe future releases for sqlite compatibility
	 *  but that goees against using LAMP stack
	 *  will just keep it here
            try {
	  simpleWebServer = new SimpleWebServer(new File("html"), 80);
            }
            catch (Exception e) {
                System.out.println(e);
            }

	 */



        
        tempModelDataList = new DefaultListModel();
        strngTempDB = "";
        strngTempTable = "";
        strngTempQStrng = "";
        String s1 = "yyyy-MM-dd";
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s1);
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, -1);


        useAdminPop = true;
        String absname = System.getProperty("java.class.path");
        // System.out.println("the path: " + absname);


        mb = new JMenuBar();
        // mb.setBorderPainted(true);
        mFile = new JMenu(aplangstrings.getProperty("text139", "File"));

        mEdit = new JMenu(aplangstrings.getProperty("text010", "Edit"));
        mData = new JMenu(aplangstrings.getProperty("text143", "Data"));
        mTools = new JMenu(aplangstrings.getProperty("text324", "Tools"));

        mView = new JMenu(aplangstrings.getProperty("text144", "View"));

        mWidgets = new JMenu(aplangstrings.getProperty("text145", "Widgets"));
        mHelp = new JMenu(aplangstrings.getProperty("text012", "Help"));
        mHelp.setForeground(new java.awt.Color(0, 0, 255)); //XXX windows lnf?


        mFile.getPopupMenu().setLightWeightPopupEnabled(false);
        mEdit.getPopupMenu().setLightWeightPopupEnabled(false);
        mView.getPopupMenu().setLightWeightPopupEnabled(false);
        mTools.getPopupMenu().setLightWeightPopupEnabled(false);
        mHelp.getPopupMenu().setLightWeightPopupEnabled(false);
        mWidgets.getPopupMenu().setLightWeightPopupEnabled(false);

        //         mEdit
        miEditCopy = new JMenuItem(aplangstrings.getProperty("text160", "Copy"));
        miEditPaste = new JMenuItem(aplangstrings.getProperty("text161", "Paste"));
        miEditFillColumn = new JMenuItem(aplangstrings.getProperty("text162", "Fill Column"));

        //         mData
        miDataEditRcrd = new JMenuItem(aplangstrings.getProperty("text163", "Edit Record"));
        miDataUpldRcrd = new JMenuItem(aplangstrings.getProperty("text181", "Upload Selected Record to Database"));
        miDataUpldAllRcrds = new JMenuItem(aplangstrings.getProperty("text182", "Upload All Records to Database"));

        miDataAddRow = new JMenuItem(aplangstrings.getProperty("text191", "Add Row"));
        miDataAddNRows = new JMenuItem(aplangstrings.getProperty("text192", "Add Rows"));
        miDataDelNRows = new JMenuItem(aplangstrings.getProperty("text193", "Delete Row(s)"));


        miQuit = new JMenuItem(aplangstrings.getProperty("text149", "Quit"));
        miOpen = new JMenuItem(aplangstrings.getProperty("text323", "Open"));
        miSave = new JMenuItem(aplangstrings.getProperty("text306", "Save"));
        miSaveAs = new JMenuItem(aplangstrings.getProperty("text537", "Save As"));

        miDBQBox = new JMenuItem(aplangstrings.getProperty("text214", "Search Database"));
        miLHApp = new JMenuItem(aplangstrings.getProperty("text174", "LiveHelp App"));
        miWebSttngs = new JMenuItem(aplangstrings.getProperty("text177", "WebSite Settings"));
        miAddDB = new JMenuItem(aplangstrings.getProperty("text130", "Add DataBase"));

	  /*
      * The button group
	  * and menu items for the Language Settings
	  */
        ButtonGroup btnGroupLang = new ButtonGroup();
        miLangMenu = new JMenu(aplangstrings.getProperty("text417", "Language"));
        miLangenUS = new JRadioButtonMenuItem(aplangstrings.getProperty("text418", "English"));
        miLangptPT = new JRadioButtonMenuItem(aplangstrings.getProperty("text419", "Portuguese"));


        btnGroupLang.add(miLangenUS);
        btnGroupLang.add(miLangptPT);
        miLangMenu.add(miLangenUS);
        miLangMenu.add(miLangptPT);
        if (aamainprefs.getProperty("userLang").equals("en_US")) {
            miLangenUS.setSelected(true);
        } else {
            miLangptPT.setSelected(true);
        }
        miBrowPan = new JMenuItem(aplangstrings.getProperty("text177", "Admin Browser"));
        miDBPanel = new JMenuItem(aplangstrings.getProperty("text534", "DataBase Panel"));
        miStatusBox = new JMenuItem(aplangstrings.getProperty("text194", "Output"));
        miMinMax = new JMenuItem(aplangstrings.getProperty("text200", "Min-Max(imized)"));
        miOnTop = new JMenuItem(aplangstrings.getProperty("text202", "Window On Top"));

        miTimeStamp = new JMenuItem(aplangstrings.getProperty("text538", "TimeStamp Box"));

        miOLHelp = new JMenuItem(aplangstrings.getProperty("text414", "Help Topics"));
        miOLHelpWthis = new JMenuItem(aplangstrings.getProperty("text415", "Whats This?"), new ImageIcon(getUfile("cbox/images/iconHelp.gif")));

        miAbout = new JMenuItem(aplangstrings.getProperty("text416", "About"));


        mb.add(mFile);
        mFile.add(miOpen);
        mFile.add(miSave);
        mFile.add(miSaveAs);
        mFile.addSeparator();
        mFile.add(miQuit);

        mb.add(mEdit);
        mEdit.add(miEditCopy);
        mEdit.add(miEditPaste);
        mEdit.addSeparator();
        mEdit.add(miEditFillColumn);


        if (isIEversion) {
            mb.add(mView);
            mView.add(miBrowPan);
            mView.add(miDBPanel);
            mView.addSeparator();
            mView.add(miStatusBox);
            mView.addSeparator();
            mView.add(miMinMax);
            mView.add(miOnTop);
        }


        mb.add(mTools);
        mTools.add(miDBQBox);
        mTools.addSeparator();
        mTools.add(miAddDB);
        mTools.addSeparator();
        mTools.add(miLangMenu);
        // mTools.add(miLHApp);
        // mTools.add(miWebSttngs);


        mb.add(mData);
        mData.add(miDataEditRcrd);
        mData.addSeparator();
        mData.add(miDataUpldRcrd);
        mData.add(miDataUpldAllRcrds);
        mData.addSeparator();
        mData.add(miDataAddRow);
        mData.add(miDataAddNRows);
        mData.addSeparator();
        mData.add(miDataDelNRows);


        // mb.add(mWidgets);
        mWidgets.add(miTimeStamp);

        mb.add(mHelp);
        mHelp.add(miOLHelp);
        mHelp.add(miOLHelpWthis);
        mHelp.addSeparator();
        mHelp.add(miAbout);


        miOpen.addActionListener(this);
        miSave.addActionListener(this);
        miSaveAs.addActionListener(this);
        miQuit.addActionListener(this);

        miBrowPan.addActionListener(this);
        miDBPanel.addActionListener(this);
        miStatusBox.addActionListener(this);
        miMinMax.addActionListener(this);
        miOnTop.addActionListener(this);

        miDBQBox.addActionListener(this);
        miLHApp.addActionListener(this);
        miWebSttngs.addActionListener(this);
        miAddDB.addActionListener(this);
        miLangenUS.addActionListener(this);
        miLangptPT.addActionListener(this);

        miDataEditRcrd.addActionListener(this);
        miDataUpldRcrd.addActionListener(this);
        miDataUpldAllRcrds.addActionListener(this);
        miDataAddRow.addActionListener(this);
        miDataAddNRows.addActionListener(this);
        miDataDelNRows.addActionListener(this);
        miEditCopy.addActionListener(this);
        miEditPaste.addActionListener(this);
        miEditFillColumn.addActionListener(this);


        miTimeStamp.addActionListener(this);

        miOLHelp.addActionListener(this);
        miOLHelpWthis.addActionListener(this);
        miAbout.addActionListener(this);


// !!!!!!!!!!!!! End of the JMenu


        tracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage(s);
        img4 = Toolkit.getDefaultToolkit().getImage("cbox/images/buddyb.gif");
        img7 = Toolkit.getDefaultToolkit().getImage("cbox/images/minimiz.gif");
        img8 = Toolkit.getDefaultToolkit().getImage("cbox/images/aa-help.gif");
        img9 = Toolkit.getDefaultToolkit().getImage("cbox/images/irclogo.gif");
        img22 = Toolkit.getDefaultToolkit().getImage("cbox/images/winOntop.gif");
        img23 = Toolkit.getDefaultToolkit().getImage("cbox/images/dbpan.gif");
        img26 = Toolkit.getDefaultToolkit().getImage("cbox/images/btnadminPop.gif");
        img27 = Toolkit.getDefaultToolkit().getImage("cbox/images/mainbrowpan.gif");
        img28 = Toolkit.getDefaultToolkit().getImage("cbox/images/aa-livehelp.gif");
        img29 = Toolkit.getDefaultToolkit().getImage("cbox/images/snapshot.gif");
        img31 = Toolkit.getDefaultToolkit().getImage("cbox/images/aa-prefs.gif");
        broWimg1 = Toolkit.getDefaultToolkit().getImage("cbox/images/refresh.gif");
        broWimg2 = Toolkit.getDefaultToolkit().getImage("cbox/images/stop.gif");
        broWimg3 = Toolkit.getDefaultToolkit().getImage("cbox/images/next.gif");
        broWimg4 = Toolkit.getDefaultToolkit().getImage("cbox/images/previous.gif");
        broWimg5 = Toolkit.getDefaultToolkit().getImage("cbox/images/favsbutton.gif");
        broWimg6 = Toolkit.getDefaultToolkit().getImage("cbox/images/browlaunch.gif");
        broWimg7 = Toolkit.getDefaultToolkit().getImage("cbox/images/btnGTrans.gif");
        broWimg8 = Toolkit.getDefaultToolkit().getImage("cbox/images/gohome.gif");
        tracker.addImage(img1, 1);
        tracker.addImage(img4, 4);
        tracker.addImage(img7, 7);
        tracker.addImage(img8, 8);
        tracker.addImage(img9, 9);
        tracker.addImage(img22, 22);
        tracker.addImage(img23, 23);
        tracker.addImage(img26, 26);
        tracker.addImage(img27, 27);
        tracker.addImage(img28, 28);
        tracker.addImage(img29, 29);
        tracker.addImage(img31, 31);
        tracker.addImage(broWimg1, 1);
        tracker.addImage(broWimg2, 2);
        tracker.addImage(broWimg3, 3);
        tracker.addImage(broWimg4, 4);
        tracker.addImage(broWimg5, 5);
        tracker.addImage(broWimg6, 6);
        tracker.addImage(broWimg7, 7);
        tracker.addImage(broWimg8, 8);
        try {
            tracker.waitForAll();
        } catch (InterruptedException interruptedexception) {
            setStatusText(interruptedexception.toString());

        }
        setIconImage(img4);
        bannerbut1 = new ImageButton("Visite our Sponsors", img1);
        bold = new ImageButton("Minimize", img7);
        btnAAhelp = new ImageButton("Helper", img8);
        irclogo = new ImageButton("BuddyB Irc", img9);
        winOntopImg = new ImageButton("winOntop", img22);
        btnDBPan = new ImageIcon("cbox/images/dbpan.gif");
        btnadminPop = new ImageButton("TriBrowser", img26);
        btnmainbrowpan = new ImageButton("Main Browser JPanel", img27);
        btnLiveHelp = new ImageButton("Live Help", img28);
        btnSnapShot = new ImageButton("Screen Shot", img29);
        btnAAprefs = new ImageButton("adminApp help", img31);
        broWbtnRefresh = new ImageButton("refresh", broWimg1);
        broWbtnStop = new ImageButton("stop", broWimg2);
        broWbtnNext = new ImageButton("next", broWimg3);
        broWbtnPrevious = new ImageButton("previous", broWimg4);
        broWbtnFavs = new ImageButton("favs", broWimg5);
        broWbtnBlaunch = new ImageButton("brow launch", broWimg6);
        broWbtnGTrans = new ImageButton("trans launch", broWimg7);
        broWbtnGoHome = new ImageButton("Go Home", broWimg8);
        addressLabel = new JLabel(aplangstrings.getProperty("text195") + " ", 2);
        maddress = new JTextField(64);
	  maddress.setFont(new java.awt.Font("Arial", 0, 14));


        jbtnDBPan = new JButton(aplangstrings.getProperty("  Databse/Spread-Sheet ", "  Databse/Spread-Sheet "), btnDBPan);
 


        closebox = new CommLabel(this, "Close", "close");
        hidebox = new CommLabel(this, "Hide", "hide");
        clearbox = new CommLabel(this, "Clear Results", "clearQtext");
 
        searchrepQs = new CommLabel(this, "Search&Replace", "searchrepQs");
        reloadQs = new CommLabel(this, "Reload", "reloadQs");

// !! delete this stuff, its been replaced with buttons

        runCom = new CommLabel(this, "Run Selected Query", "runcomquery");
        runAllComs = new CommLabel(this, "Run All", "runallcomqueries");
        deleteCom = new CommLabel(this, "Remove Query", "delcomquery");
        deleteAllComs = new CommLabel(this, "Remove All", "delallcomqueries");

// !! end of   delete this stuff, its been replaced with buttons


        getDstamp = new CommLabel(this, "DStamp", "getDatestamp");

        tiplabel = new JLabel(aplangstrings.getProperty("text112") + "                       ");
        tiplabel.setForeground(new java.awt.Color(0, 0, 165));
        tiplabel.setOpaque(true);


        adminppmenu = new JPopupMenu();
        brtodbPPmItem = new JMenuItem("BrowserToDB Action");
        closePPmItem = new JMenuItem(aplangstrings.getProperty("text507"));
        adminppmenu.add(brtodbPPmItem);
        adminppmenu.addSeparator();
        adminppmenu.add(closePPmItem);
        brtodbPPmItem.addActionListener(this);
        closePPmItem.addActionListener(this);
        adminppmenu.setLightWeightPopupEnabled(false);
        getContentPane().add(adminppmenu);


//|||| the top tiplabel and adminApp main ImgButtons

        JPanel TopTipBtnPanel = new JPanel(new BorderLayout());
        JPanel TopBtnPanel = new JPanel(new GridLayout(0, 5));
        // TopBtnPanel.add(btnmainbrowpan);
       // TopBtnPanel.add(btnDBPan);
        TopBtnPanel.add(btnLiveHelp);
        // TopBtnPanel.add(btnAAprefs);
        // TopBtnPanel.add(btnadminPop);
        // TopBtnPanel.add(winOntopImg);
        TopBtnPanel.add(btnSnapShot);


        // we will use the btnAAhelp as the Task Pop UP
        TopBtnPanel.add(btnAAhelp);
        // TopBtnPanel.add(bold);

       //  TopTipBtnPanel.add("Center", tiplabel);
        // TopTipBtnPanel.add("West", TopBtnPanel);

// end of the top tiplabel and adminApp main ImgButtons


//|||| the browser main panel

        JPanel browBtnPan = new JPanel(new GridLayout(0, 9));
        browBtnPan.add(broWbtnPrevious);
        browBtnPan.add(broWbtnNext);
        browBtnPan.add(broWbtnStop);
        browBtnPan.add(broWbtnRefresh);
        // browBtnPan.add(broWbtnBlaunch);
        // browBtnPan.add(btnadminPop);
        browBtnPan.add(broWbtnFavs);
        // browBtnPan.add(broWbtnGTrans);
        browBtnPan.add(broWbtnGoHome);
        // browBtnPan.add(btnDBPan);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(browBtnPan);
        panel2.add(addressLabel);
        panel2.add(maddress);


        JPanel panel33a = new JPanel(new BorderLayout());
 
        panel33a.add("Center", panel2);

        JLabel tJlbl = new JLabel("      ");
        JPanel panel3east = new JPanel(new BorderLayout());
        panel3east.add("Center", tJlbl);

	   
        JPanel panel3 = new JPanel(new BorderLayout());
 
        panel3.add("Center", panel33a);
        panel3.add("West", jbtnDBPan);
        panel3.add("East", panel3east);        
 


        JPanel blipPanel = new JPanel();
        blipPanel.setLayout(new FlowLayout(0));
        blip = new BusyBlip();
        blip.setPreferredSize(new Dimension(100, 20));
        blip.setOpaque(true);
        // !!xbug blip.setBorder(new CompoundBorder(new CompoundBorder(new EmptyBorder(new Insets(1, 1, 1, 1)), new LineBorder(getForeground())), new EmptyBorder(new Insets(1, 1, 1, 1))));
        blip.setForeground(getForeground());
        statusLabel = new JLabel("ready...");
        blipPanel.add(blip);
        blipPanel.add(statusLabel);

        javax.swing.border.Border emptyBdr = javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0);
        javax.swing.border.Border raisedbvl = javax.swing.BorderFactory.createLineBorder(java.awt.Color.black, 1);


        JPanel mainBrowserPanel = new JPanel(new BorderLayout());
        setStatusText("Loading the Browser Panel");
        pnlBrowserCage = new JPanel(new BorderLayout());

        jfxPanel = new JFXPanel();
        pnlBrowserCage.add("Center", jfxPanel);
        jfxPanel.setBackground(new java.awt.Color(10, 222, 222));

        //  JFXPanel browserPan = new JFXPanel();
        //   browserPan.add("Center", browser);
        // !!xbug  browserPan.setBorder(raisedbvl);


        mainBrowserPanel.add("North", panel3);
        mainBrowserPanel.add("Center", pnlBrowserCage);
        mainBrowserPanel.add("South", blipPanel);


        // mainBrowserPanel.setBorder(raisedbvl);
        setStatusText("Loaded Browser Panel");

// endo of the browser main panel


        dbfpanel = new SharpTools(this);

        JPanel panel8 = new JPanel(new BorderLayout());
        JPanel panel9 = new JPanel(new BorderLayout());

        comtext = new java.awt.List(3, false);
        comtext.addActionListener(this);
        Qcomtextlabel = new JLabel("   " + aplangstrings.getProperty("text528", "Pending Queries") + ":");

        QstatusTextArea = new JTextArea("", 2, 300);
        QstatusTextArea.setBackground(new java.awt.Color(0, 0, 0));
        QstatusTextArea.setForeground(new java.awt.Color(255, 225, 10));

        btnDelPendQuery = new JButton(aplangstrings.getProperty("text044"));
        btnDelAllPendQuery = new JButton(aplangstrings.getProperty("text217"));
        btnRunPendQuery = new JButton(aplangstrings.getProperty("text515"));
        btnRunAllPendQuery = new JButton(aplangstrings.getProperty("text218"));
        btnDelPendQuery.addActionListener(this);
        btnDelPendQuery.addMouseListener(this);
        btnDelAllPendQuery.addActionListener(this);
        btnDelAllPendQuery.addMouseListener(this);
        btnRunPendQuery.addActionListener(this);
        btnRunPendQuery.addMouseListener(this);
        btnRunAllPendQuery.addActionListener(this);
        btnRunAllPendQuery.addMouseListener(this);

        JPanel pendQCommLblPan = new JPanel(new GridLayout(0, 4));
 

        JPanel pendQCommTopPan = new JPanel(new BorderLayout());


        // pendQCommLblPan.add(Qcomtextlabel);
        pendQCommLblPan.add(btnDelPendQuery);
        pendQCommLblPan.add(btnDelAllPendQuery);
        pendQCommLblPan.add(btnRunPendQuery);
        pendQCommLblPan.add(btnRunAllPendQuery);

        pendQCommTopPan.add(Qcomtextlabel, "Center");
        pendQCommTopPan.add(pendQCommLblPan, "East");

        // to delete !! sharptools -  pendQCommLblPan.add(progressBarAA);

        JPanel panel21 = new JPanel(new BorderLayout());
        panel21.add("East", closebox);

        JPanel pendQcommMainPan = new JPanel(new BorderLayout());
        pendQcommMainPan.add("North", pendQCommTopPan);
        pendQcommMainPan.add("Center", comtext);
        // pendQcommMainPan.add("South", panel21);


        JPanel sharpMainPanel = new JPanel(new BorderLayout());
        sharpMainPanel.add("Center", dbfpanel);


        JScrollPane scrollPane = new JScrollPane(QstatusTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        sharpSpltMainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sharpMainPanel, pendQcommMainPan);

        sharpSpltMainPanel.setOneTouchExpandable(true);
        sharpSpltMainPanel.setContinuousLayout(false);
        sharpSpltMainPanel.setBorder(emptyBdr);
        JPanel sharpToolsPanel = new JPanel(new BorderLayout());
        sharpToolsPanel.add("Center", sharpSpltMainPanel);
        // sharpToolsPanel.setBorder(emptyBdr);


        broWbtnRefresh.addMouseListener(this);
        broWbtnRefresh.addActionListener(this);
        broWbtnStop.addMouseListener(this);
        broWbtnStop.addActionListener(this);
        broWbtnNext.addMouseListener(this);
        broWbtnNext.addActionListener(this);
        broWbtnPrevious.addMouseListener(this);
        broWbtnPrevious.addActionListener(this);
        broWbtnFavs.addMouseListener(this);
        broWbtnFavs.addActionListener(this);
        broWbtnBlaunch.addMouseListener(this);
        broWbtnBlaunch.addActionListener(this);
        broWbtnGTrans.addMouseListener(this);
        broWbtnGTrans.addActionListener(this);
        broWbtnGoHome.addMouseListener(this);
        broWbtnGoHome.addActionListener(this);
        maddress.addActionListener(this);
        bannerbut1.addActionListener(this);
        bannerbut1.addMouseListener(this);
        btnAAhelp.addActionListener(this);
        btnAAhelp.addMouseListener(this);
        btnAAprefs.addActionListener(this);
        btnAAprefs.addMouseListener(this);
        irclogo.addActionListener(this);
        irclogo.addMouseListener(this);
        bold.addActionListener(this);
        bold.addMouseListener(this);
        btnadminPop.addActionListener(this);
        btnadminPop.addMouseListener(this);
        btnmainbrowpan.addActionListener(this);
        btnmainbrowpan.addMouseListener(this);
        btnLiveHelp.addActionListener(this);
        btnLiveHelp.addMouseListener(this);
        btnSnapShot.addActionListener(this);
        btnSnapShot.addMouseListener(this);
        winOntopImg.addActionListener(this);
        winOntopImg.addMouseListener(this);
 
	  jbtnDBPan.addActionListener(this);
	  jbtnDBPan.addMouseListener(this);
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        cards.add("mainBrowserCard", mainBrowserPanel);
        cards.add("sharpToolsCard", sharpToolsPanel);

 

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add("Center", cards);


        getContentPane().add(mainPanel, "Center");

        setJMenuBar(mb);

 


        maddress.setText("about:blank");
        addWindowListener(new mySlicWindowListener());
        setVisible(true);
        setLocation(0, 0);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
 
        createScene();
 


        String stringSplitLocation = aamainprefs.getProperty("splitLocation");
        sharpSpltMainPanel.setDividerLocation(Integer.parseInt(stringSplitLocation));






        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
			try {
 
		    // need this here. notifies web css of resize in case im seeing layout in mobile dimensions
		    
                browser.setPrefSize(pnlBrowserCage.getWidth() - 1, pnlBrowserCage.getHeight() - 1);
                // System.out.println("[3049] browser resize: " + pnlBrowserCage.getWidth() + " :: " + pnlBrowserCage.getHeight());
                } catch (Exception cpe) {
                    System.out.println(cpe.toString());
                }
            }
        });

	 currConfBundle = getConfBundle();
	basicUtils = new BasicUtils();



/*
* this is a JavaFX dilema with Cookie Storage.
* Just saving them to the aamainprefs and calling them to set cookies on start-up
* namely quid (userID), prfsSHOPuser (user prefs string), which are actually the html cookies used
*/

CookieManager cookie_manager = new CookieManager();
CookieStore Cookie_Store = new MyCookieStore(cookie_manager.getCookieStore());
CookieManager acookie_manager = new CookieManager(Cookie_Store, CookiePolicy.ACCEPT_ALL);
CookieHandler.setDefault(acookie_manager);
URI ccuri = URI.create("https://localhost/evenflow/webdroid/assets/quickorder");
Map<String, java.util.List<String>> ccheaders = new LinkedHashMap<String, java.util.List<String>>();
Map<String, java.util.List<String>> ccaheaders = new LinkedHashMap<String, java.util.List<String>>();
Map<String, java.util.List<String>> ccbheaders = new LinkedHashMap<String, java.util.List<String>>();
ccheaders.put("Set-Cookie", Arrays.asList("quid=" + aamainprefs.getProperty("quid", "1")));
ccaheaders.put("Set-Cookie", Arrays.asList("prfsSHOPuser=" + aamainprefs.getProperty("prfsSHOPuser", "x1prfDspLmenux4falsex5scvx2gx6")));
ccbheaders.put("Set-Cookie", Arrays.asList("cartID=" + aamainprefs.getProperty("cartID", "noQvalue")));

try {
java.net.CookieHandler.getDefault().put(ccuri, ccheaders);
java.net.CookieHandler.getDefault().put(ccuri, ccaheaders);
java.net.CookieHandler.getDefault().put(ccuri, ccbheaders);
} catch(Exception e) {
e.printStackTrace();
}

}











    public static void main(String args1[]) {

        new adminApp();

    }

    public static String getTimeStamp() {
        long l = System.currentTimeMillis() / 1000L;
        String s = Long.toString(l);
        int i = Integer.parseInt(s);
        String s1 = Integer.toString(i);
        return s1;
    }

    public static String[] readmessTokens(String s, String s1) {
        StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
        int i = stringtokenizer.countTokens();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
            as[j] = stringtokenizer.nextToken();
        return as;
    }

    public static String[] readmessyTokens(String s, String s1) {
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

    public static String replaceString(String target, String from, String to) {
        // target is the original string
        // from   is the string to be replaced
        // to     is the string which will used to replace
        int start = target.indexOf(from);
        if (start == -1) return target;
        int lf = from.length();
        char[] targetChars = target.toCharArray();
        StringBuilder buffer = new StringBuilder();
        int copyFrom = 0;
        while (start != -1) {
            buffer.append(targetChars, copyFrom, start - copyFrom);
            buffer.append(to);
            copyFrom = start + lf;
            start = target.indexOf(from, copyFrom);
        }
        buffer.append(targetChars, copyFrom, targetChars.length - copyFrom);
        return buffer.toString();
    }

    public void shutdown() {

        try {

            dbqueryBox.saveQMinMaxProp();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        saveAllPrefs();
        dispose();
        System.exit(0);
    }

    public void to_delete_loadnewprefs(String s) {
        aanewdbprefs = new Properties();
        try {
            FileInputStream fileinputstream = new FileInputStream(udString + "/" + "cbox/data/dbs/" + s + "---.dat");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            aanewdbprefs.load(fileinputstream);
            hostfolder = aanewdbprefs.getProperty("hostfolder");
            // currentDB = aanewdbprefs.getProperty("webdbasename");
            aausername = aanewdbprefs.getProperty("adminusername");
            aapassword = aanewdbprefs.getProperty("adminpassword");
            userpassString = "auser=" + aausername + "&apass=" + aapassword + "&";
            hostfolder = hostfolder;
            // hostdbase = currentDB;

//            Qdbasefield.setText(hostdbase);
            // System.out.println(hostfolder);
            QstatusTextArea.setText("HostFolder: " + hostfolder);


            fileinputstream.close();
            datainputstream.close();
/*
        if(currentDB == aplangstrings.getProperty("text137")) {
        System.out.println("Default DB");
        } else {

        dbfpanel.dummyfunction(dmystring);
        }
*/
            return;
        } catch (Exception exception) {
            System.out.println("loadnewPref Ex" + exception.toString());
            setQstatus("Error 177A [adminApp]: \n" + exception.toString(), true);
            return;
        }
    }

    public void setCanvasUrl(String s) {
        ((CardLayout) cards.getLayout()).show(cards, "mainBrowserCard");
        navigate(s);
    }

    public void showBrowserPan(String s, int i) {


        ((CardLayout) cards.getLayout()).show(cards, "mainBrowserCard");
        navigate(s);
        browircStat = true;
    }

    public void favs(String s) {
        navigate(s);
    }

    public void gotoit(String s, int i) {
        if (s.trim().length() < 1) {
            new Messdialog1(this, aplangstrings.getProperty("text111"));
            return;
        } else {
            showBrowserPan(s, i);
            return;
        }
    }

    public void showmeHelp(String helpTitle, String helpBodyFile) {
        File hfCheckFile = new File(getUfile("docs/" + prefsUserLang + "/" + helpBodyFile));
        if (hfCheckFile.exists()) {
            new HelpWindow(helpTitle, helpBodyFile, prefsUserLang);
        } else {
            new HelpWindow("adminApp Help", "index_body.html", "en_US");
        }
    }

    public void loadAPlangStrings() {
        aplangstrings = new Properties();
        try {
            String strULangLocale = aamainprefs.getProperty("userLang", "en_US");
            if (strULangLocale.equals("noQvalue")) {
                Locale locale = Locale.getDefault();
                String lang = System.getProperty("user.language");
                String country = System.getProperty("user.country");

                // String lang = locale.getDisplayLanguage();
                // String country = locale.getDisplayCountry();
                System.out.println("loadAPlangStrings:: lang: " + lang + " :: country: " + country);

                if (lang.toLowerCase().indexOf("pt") != -1) {
                    strULangLocale = "pt_PT";
                } else {
                    strULangLocale = "en_US";
                }
                aamainprefs.setProperty("userLang", strULangLocale);
            }
            FileInputStream fileinputstream = new FileInputStream(getUfile("cbox/props/APBundle_" + strULangLocale + ".props"));

            // FileInputStream fileinputstream = new FileInputStream(udString + "/cbox/props/APBundle.props");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            aplangstrings.load(fileinputstream);
            fileinputstream.close();
            datainputstream.close();
            setStatusText("Language File loaded");
        } catch (Throwable exception) {
            System.out.println(exception.toString());
            // setQstatus("loadAPLang Ex" + exception.toString(), false);
        }
    }

    public void saveAAPrefs(String strAA, String strBB, String strCC, String strDD) {
        try {




            FileOutputStream fileoutputstream = new FileOutputStream(getUfile("cbox/prefs/AAPrefs.prfs"));
            PrintStream printstream = new PrintStream(fileoutputstream);

            aamainprefs.store(fileoutputstream, "---No Comment---");


            printstream.close();
            fileoutputstream.close();
            System.out.println(aplangstrings.getProperty("text205"));
        } catch (Exception exception) {
            setQstatus("Error 1602A [adminApp]: \n" + exception.toString(), true);
        }
    }


    public void saveAllPrefs() {
        try {
		 aamainprefs.setProperty("lastWebPage", maddress.getText());

            int splitLocation = sharpSpltMainPanel.getDividerLocation();
            aamainprefs.setProperty("splitLocation", Integer.toString(splitLocation));


              FileOutputStream fileoutputstream = new FileOutputStream(getUfile("cbox/prefs/AAPrefs.prfs"));
            PrintStream printstream = new PrintStream(fileoutputstream);

            aamainprefs.store(fileoutputstream, "---No Comment---");


            printstream.close();
            fileoutputstream.close();
            System.out.println(aplangstrings.getProperty("text205"));
        } catch (Exception exception) {
            setQstatus("Error 1602A [adminApp]: \n" + exception.toString(), true);
        }
    }

    public void loadAAprefs() {
        try {
            aamainprefs = new Properties();
            FileInputStream fileinputstream = new FileInputStream(getUfile("cbox/prefs/AAPrefs.prfs"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            aamainprefs.load(fileinputstream);

            fileinputstream.close();
            datainputstream.close();
            setStatusText("Loaded Main Preferences");
		 
        } catch (Exception exception) {
            setQstatus("Error 808A [adminApp]: \n" + exception.toString(), false);
        }
    }

    public void adminStatusTextChange(String s) {
        statusLabel.setText(s);
    }

    public void adminTitleChange(String s) {
        setTitle(s + " - adminApp");
    }

    public void adminDocumentComplete(String s) {
        statusLabel.setText(s);
    }

    public boolean showContextMenu() {

        System.out.println("Context Called");
        return false;
    }

    public void getPopUp(int x, int y) {

        if (adminpop != null) {
            adminpop.goaway();
        }

        adminpop = new Popmenu(this, "heeeeeeeeeeeheeeeeeeeeeeee", x, y);

        // new Messdialog1(this, "heeeeeeeeehjeeeeeee");
    }

    public void fixWords(String s) {
        String s1 = s;
        int i = 0;
        String as[] = readmessTokens(s1, "<ttok>");
        try {
            while (i < as.length)
                i++;
        } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
            setQstatus("Error 887A [adminApp]: \n" + arrayindexoutofboundsexception + "\n" + ":\n" + as[i], false);
        }
    }

    private String removeBSpaces(String s) {
        String s1 = "";
        char c = '?';
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            if (c1 != ' ' || c != 32) {
                s1 = s1 + c1;
                c = c1;
            }
        }

        return s1;
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == maddress) {
            String madtext = maddress.getText().trim();
            if (madtext.startsWith("props")) {
                loadAPlangStrings();
                setQstatus("Prop Strings reloaded", false);
            } else {
                showBrowserPan(maddress.getText(), 0);
            }
        }
        if (obj == miDBQBox) {
            getDBQBox();
        }
        if (obj == miBrowPan) {
            gettheBrowser();
        }
        if (obj == miDBPanel) {
            getDBapp();
        }
        if (obj == miStatusBox) {
            getStatusBox();
        }
        if (obj == miQuit) {
            shutdown();
        }
        if (obj == miMinMax) {
            isitBold();
        }
        if (obj == miOnTop) {
            isitOntop();
        }
        if (obj == miLHApp) {
            getLiveHelp();
        }
        if (obj == miOLHelp) {
            showmeHelp("adminApp Help", "index_body.html");
        }
        if (obj == miOLHelpWthis) {
            showmeHelp("adminApp - Spread-Sheet Help", "help-window_spread_sheet.html");
        }
        if (obj == miAbout) {
            new AboutDialog(this);
        }
        if (obj == miWebSttngs) {
            getWebPrefs();
        }
        // old!!! delete!!  if(obj == miAddDB) { getAddDBPrefs(); }


        if (obj == miEditCopy) {
            dbfpanel.copySmartEdit();

        }
        if (obj == miOpen) {
            dbfpanel.doFileOpen();
 

        }

        if (obj == miSave) {
            dbfpanel.doFileSave();


        }
        if (obj == miSaveAs) {
            dbfpanel.doFileSaveAs();


        }
        if (obj == miEditPaste) {
            dbfpanel.pasteSmartEdit();
        }
        if (obj == miEditFillColumn) {
            dbfpanel.fillSmartColumn();
        }
        if (obj == miDataUpldRcrd) {
            uploadSlctdRowRec();
        }
        if (obj == miDataUpldAllRcrds) {
            MsgBox uploadQsMsgBox = new MsgBox(this, aplangstrings.getProperty("text547"), aplangstrings.getProperty("text517") + "?!", true);
            requestFocus();
            if (uploadQsMsgBox.id) {
                uploadQsMsgBox.dispose();
                startUploadQs();
            } else {
                uploadQsMsgBox.dispose();
            }
        }
        if (obj == miDataAddRow) {
            dbfpanel.addSmartRow();
        }
        if (obj == miDataAddNRows) {
            new SmartAddRows(this);

        }
        if (obj == miDataDelNRows) {
            dbfpanel.deleteSmartRow();
        }

        if (obj == miDataEditRcrd) {
            dbfpanel.editRowRecord(true);
        }


        if (obj == miAddDB) {
            getEditDBPrefs("noQvalue");
        }
        if (obj == miTimeStamp) {
            new TimeStampBox(this, "heheh");
        }


        if (obj == btnDelPendQuery) {
            removecomQ();
        }
        if (obj == btnDelAllPendQuery) {
            comtext.removeAll();
            setQstatus(aplangstrings.getProperty("text172"), false);
        }
        if (obj == btnRunPendQuery) {
            try {
                startComtextQuery("selected");
                // postComtextQuery();
            } catch (Exception exception1) {
                setQstatus(exception1.toString(), true);
            }
        }
        if (obj == btnRunAllPendQuery) {
            try {
                startComtextQuery("all");
                // postAllComtextQueries();
            } catch (Exception exception2) {
                setQstatus(exception2.toString(), true);
            }
        }
        if (obj == brtodbPPmItem) {
            getDBActionBox(currentDB, tablename);
        }

        if (obj == miLangenUS) {
            aamainprefs.setProperty("userLang", "en_US");
            setQstatus(aplangstrings.getProperty("text500", "Changes will take effect next time you open adminApp"), false);
        }
        if (obj == miLangptPT) {
            aamainprefs.setProperty("userLang", "pt_PT");
            setQstatus(aplangstrings.getProperty("text500", "Changes will take effect next time you open adminApp"), false);
        }

    }

    public void mousePressed(MouseEvent mouseevent) {
        // mouseevent
        if (mouseevent.getSource() == bannerbut1) {
            favs(server + "pushdex/pt/slicbnr" + clbanner + ".shtml");
        }
        if (mouseevent.getSource() == irclogo) {
            favs(server);
        }
        if (mouseevent.getSource() == btnAAhelp) {
            getCATRP.doHide(true);
        }
        // if(mouseevent.getSource() == btnAAhelp) { showmeHelp(); }
        if (mouseevent.getSource() == btnAAprefs) {
            getWebPrefs();
        }
        if (mouseevent.getSource() == btnLiveHelp) {
            getLiveHelp();
        }
        if (mouseevent.getSource() == btnmainbrowpan) {
            gettheBrowser();
        }
        if (mouseevent.getSource() == bold) {
            isitBold();
        }
        if (mouseevent.getSource() == winOntopImg) {
            isitOntop();
        }

 
        if (mouseevent.getSource() == jbtnDBPan) {
            getDBapp();
        }
        if (mouseevent.getSource() == broWbtnGoHome) {


        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                webEngine.loadContent(basicUtils.readFileAsString(basicUtils.getUfile("cbox/misc/intro.html")));

                } catch (Throwable e) {
                    System.out.println("[1555] loadContent.intro: " + e);

                }


            }
        });
 
        }
        if (mouseevent.getSource() == broWbtnRefresh) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
            webEngine.reload();
                } catch (Throwable e) {
                    System.out.println("[1555] broWbtnRefresh: " + e);

                }


            }
        });



        }
        if (mouseevent.getSource() == broWbtnNext) {
            navigate(goForward());
        }
        if (mouseevent.getSource() == broWbtnPrevious) {
            navigate(goBack());
        }
        if (mouseevent.getSource() == broWbtnGTrans) {
            navigate(hostfolder + "adminshop/index.html");
        }
        if (mouseevent.getSource() == broWbtnBlaunch) {
            navigate(maddress.getText());
        }
        if (mouseevent.getSource() == broWbtnFavs) {
            getQuickLinks();
        }


        if (mouseevent.getSource() == btnSnapShot) {
            //  showSlicPan();
            screenShot();
        }


        if (mouseevent.getSource() == btnadminPop) {

            Point relative = mouseevent.getPoint();

            adminppmenu.show(btnadminPop, relative.x, relative.y);

        }

    }

    public void keyPressed(KeyEvent keyevent) {
        Object obj = keyevent.getSource();
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mouseEntered(MouseEvent mouseevent) {
        java.awt.Component component = mouseevent.getComponent();
        if (component == btnAAhelp) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text012"));
            return;
        }
        if (component == btnAAprefs) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text113"));
            return;
        }
        if (component == bannerbut1) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text068"));
            return;
        }
        if (component == irclogo) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text069"));
            return;
        }
        if (component == bold)
            if (!isbold) {
                setCursor(Cursor.getPredefinedCursor(12));
                tiplabel.setText(aplangstrings.getProperty("text020"));
                return;
            } else {
                setCursor(Cursor.getPredefinedCursor(12));
                tiplabel.setText(aplangstrings.getProperty("text021"));
                return;
            }
        if (component == winOntopImg)
            if (!winOntop) {
                setCursor(Cursor.getPredefinedCursor(12));
                tiplabel.setText(aplangstrings.getProperty("text153"));
                return;
            } else {
                setCursor(Cursor.getPredefinedCursor(12));
                tiplabel.setText(aplangstrings.getProperty("text154"));
                return;
            }
 
        if (component == jbtnDBPan) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text534"));
            return;
        }
        if (component == btnLiveHelp) {
            setCursor(Cursor.getPredefinedCursor(12));
            if (!boolLHelpOpen) {
                tiplabel.setText(aplangstrings.getProperty("text300"));
                return;
            } else {
                tiplabel.setText(aplangstrings.getProperty("text014"));
                return;
            }
        }
        if (component == btnSnapShot) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text536"));
        }
        if (component == btnadminPop) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text086"));
        }
        if (component == btnmainbrowpan) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text185"));
        }
        if (component == broWbtnRefresh) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text190"));
            return;
        }
        if (component == broWbtnStop) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text189"));
            return;
        }
        if (component == broWbtnBlaunch) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text196"));
            return;
        }
        if (component == broWbtnGTrans) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text197"));
            return;
        }
        if (component == broWbtnGoHome) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText("Home");
            return;
        }
        if (component == broWbtnNext) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text188"));
            return;
        }
        if (component == broWbtnPrevious) {
            setCursor(Cursor.getPredefinedCursor(12));
            tiplabel.setText(aplangstrings.getProperty("text187"));
            return;
        }
        if (component == broWbtnFavs) {
            setCursor(Cursor.getPredefinedCursor(12));
            if (!qlinksframe1) {
                tiplabel.setText(aplangstrings.getProperty("text015"));
                return;
            } else {
                tiplabel.setText(aplangstrings.getProperty("text014"));
                return;
            }
        } else {
            return;
        }
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
        // setDefaultCursor();
        // tiplabel.setText("");
    }

    public void setHandCursor() {
        setCursor(12);
    }

    public void setDefaultCursor() {
        // setCursor(0);
        setCursor(Cursor.getPredefinedCursor(0));
    }
// to delete     AffPanelLeft affpanelleft;

    public void hideme() {
        setVisible(false);
    }

 

    public void showTriPan() {
        ((CardLayout) cards.getLayout()).show(cards, "triUserPanes");
    }

    public void showSlicPan() {
        ((CardLayout) cards.getLayout()).show(cards, "slicpan");
    }

    public void showXLpan() {
        ((CardLayout) cards.getLayout()).show(cards, "xlPan");
    }

    public void showLastPanel() {
        ((CardLayout) cards.getLayout()).previous(this);
    }

    public void getDBapp() {
        ((CardLayout) cards.getLayout()).show(cards, "sharpToolsCard");
        browircStat = false;
    }

    public void gettheBrowser() {
 
            ((CardLayout) cards.getLayout()).show(cards, "mainBrowserCard");
 
    }

    public void setStatusText(String s) {
        //  statusBox.setCurrStatus(s + "\n");
        System.out.println(s);
    }

    private UserPane getNMEETUI1() {
        try {
            ivjNMEETUI1 = new UserPane(this);
        } catch (Throwable throwable) {
            System.out.println("cant get the nmeet pan");
        }
        return ivjNMEETUI1;
    }

    public void setWaitCursor() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //    setCursor(3);
    }

    public void setAdminPopbool(boolean bool) {
        useAdminPop = bool;
    }

    public void screenShot() {
        String outFileName = "adminstuff/" + getTimeStamp() + ".png";
        try {

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            ImageIO.write(image, "png", new File(outFileName));
            setStatusText("Saved screen shot (" + image.getWidth() + " x " + image.getHeight() + " pixels) to file \"" + outFileName + "\".");
        } catch (Exception exception) {
            setQstatus("Screen Shot error: " + exception.toString(), false);
            return;
        }
 
    }

    public void reloadTheCanvas() {
        webEngine.reload();
    }

    public void setQstatus(String s, boolean flag) {
        new alertDialog(this, s);

    }

    public void itemStateChanged(ItemEvent itemevent) {

    }

    public void removecomQ() {
        if (comtext.getSelectedItem() != null) {
            int i = comtext.getSelectedIndex();
            comtext.remove(i);
            setQstatus(aplangstrings.getProperty("text172"), false);
            return;
        } else {
            setQstatus(aplangstrings.getProperty("text035"), false);
        }
    }

    public void delRecords() {

        if (currentDB.length() < 1) {
            setQstatus("Enter a Database!", true);
        }
        if (tablename.length() < 1) {
            setQstatus("Enter a Table!", true);
        }
        if (fieldname.length() < 1) {
            setQstatus("Enter a Field!", true);
        }
        if (criterianame.length() < 1) {
            setQstatus("Enter a Criteria!", true);
        } else {
            String s = "Delete from " + tablename + "  where " + fieldname + " like " + "'" + criterianame + "';|#|";
            // comtext.addItem(s);
            comtext.add(s);
            setQstatus(aplangstrings.getProperty("text523"), true);
        }
    }

    public void getCatProds(String s) {
        QqueryTextArea.setText("");
 
    }

    public void searchAndRep(String s, String s1) {
        String s2 = replaceString(QqueryTextArea.getText(), s, s1);
        QqueryTextArea.setText(s2);
    }

    public void setQfields(String s, String s1, String s2, String s3) {
        currentDB = s;
        tablename = s1;
        fieldname = s2;
        criterianame = s3;
    }

    public void runQcommAction(String s, String s1) {
        String s2 = hostfolder + "qcomms/" + "qcomms.php?" + userpassString + "&action=" + s + "&qstring=" + s1;
        String s3 = "";
        try {
            URL url = new URL(s2);
            InputStream inputstream = url.openStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            String s4;
            while ((s4 = bufferedreader.readLine()) != null)
                s3 = s3 + s4;
            inputstream.close();
            bufferedreader.close();
        } catch (Exception exception) {
            System.out.println(exception);
            setQstatus("Error 1460A [adminApp]: \n" + exception.toString(), true);
            return;
        }
        if (s3.startsWith("Error: ")) {
            setQstatus(s3, false);
            return;
        } else {
            s3 = replaceString(s3, "<nr>", "\n");
            criterianame = s3;
            return;
        }
    }

    public void clearQqTextArea() {
        QqueryTextlabel.setForeground(new java.awt.Color(0, 0, 0));
        QqueryTextlabel.setText("Query Results: ");
        QqueryTextArea.setText("");
    }

    public void startUploadQs() {

        if (currentDBID.equals("555")) {
            setQstatus(aplangstrings.getProperty("text092"), false);
            return;
        }


        aaDisableButtons();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dbfpanel.progressBarAA.setIndeterminate(true);

        try {
            taskAAUQs = new taskAAUploadQs(this);
            taskAAUQs.execute();
        } catch (Exception exception) {
            setQstatus("ERROR: adminApp [1586]: " + exception.toString(), true);
            return;
        }

    }

    public void stopUploadQs() {
        aaEnableButtons();
        setDefaultCursor();


        dbfpanel.progressBarAA.setIndeterminate(false);

        try {
            System.out.println("stopUploadQs");


        } catch (Exception exception) {
            setQstatus("ERROR: adminApp [1605]: " + exception.toString(), true);
        }


    }

    public void procsAllRecords() {

        if (currentDBID.equals("555")) {
            setQstatus(aplangstrings.getProperty("text092"), false);
            return;
        }
 

        nowQ = "";
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s5 = "";
        nowQ = dbfpanel.getTableToGo();
        // nowQ = dbfpanel.getTableToGo();
        nowQ = replaceString(nowQ, "'", "\\'");
        // setQstatus(s5, false);
        String s4 = "REPLACE INTO " + tablename + " VALUES('";
        String as[] = readmessTokens(nowQ, "\n");
        int j = as.length;
        String nQline;
        setStatusText("starting string replace");
        s5 = replaceString(nowQ, "\n", "')\n" + s4);
        s5 = s4 + s5 + "')";
        s5 = replaceString(s5, "\t", "','");
        s5 = replaceString(s5, "\nREPLACE INTO " + tablename + " VALUES('')", "");
        setStatusText("Ending string replace");
        nowQ = s5;
        try {
            postQuery();
            return;
        } catch (Exception exception) {

            System.out.println(exception);
            setQstatus("Error 1528A [adminApp]: \n" + exception.toString(), true);
            return;
        }


    }

    public void launchEditor() {
        String cleantab = replaceString(tempRecEdit, "\t\t", "\tnoQvalue\t");
        tempRecEdit = cleantab;

        if (tablename.startsWith("product") && !currentDB.startsWith("profit")) {
            new EditItem(this, tempRecEdit);
        } else {
            new RecEditPanel(this, tempRecEdit);
            System.out.println(tablename);
        }
    }

    public void setQLabels(String s, String s1, String s2, String s3) {
        currentDB = s;
        tablename = s1;
        fieldname = s2;
        criterianame = s3;
    }

    public void launchEditItem() {
        new EditItem(this, "New Product\t");
    }

    public void postQuery() {
        //   str = replaceString(str, "'", "");
        writeFile(currentDB, nowQ);

    }

    public void setCurrentDB(String dbstring) {
        dbfpanel.setDBString(dbstring);
        currentDB = dbstring;
//        currentDBlabel.setText(dbstring);
    }

    public void setCurrentTbl(String tblstring) {
        tablename = tblstring;
        dbfpanel.setTblString(tblstring);
        //       currentTbllabel.setText(tblstring);
    }

    public void setCellQuery(String s) {
        try {

            dbfpanel.dummyfunction(s);

        } catch (Exception ioExc) {
            setQstatus("Error 1902A [adminApp]: \n" + ioExc.toString(), false);

        }


    }

    public void browTricks(String s) {
        setWaitCursor();
        String theTrick = s;
        String browAddress = maddress.getText();

        if (theTrick == "editprod") {
            if (browAddress.indexOf(hostfolder + "index.html?productID=") != -1) {
                int j = browAddress.indexOf("productID=", 0);
                String s3 = browAddress.substring(j + 10, browAddress.length());
                System.out.println("Ok, got the editprod Trick: " + s3);
//         setQuery(hostdbase, "product", "ProductID", s3);
            } else {
                setQstatus("You must be on a product page.\nEx:  index.html?productID=myProd01", true);
            }
        }
        if (theTrick == "editcat") {
            if (browAddress.indexOf(hostfolder + "index.html?categoryID=") != -1) {
                getDBapp();
                int j = browAddress.indexOf("categoryID=", 0);
                String s3 = browAddress.substring(j + 11, browAddress.length());
                s3 = URLDecoder.decode(s3);
                // setQuery(hostdbase, "product", "CategoryID", s3);
                System.out.println("Ok, got the editprod Trick: " + s3);

            } else {
                setQstatus("You must be on a category page.\nEx:  index.html?categoryID=My ProductsCategory", true);
            }
        }
        if (theTrick == "showdbpan") {
            getDBapp();
        }

        if (adminpop != null) {
            adminpop.goaway();
        }
        setDefaultCursor();
    }


    // these should not be made public

    public void sharpTricks(String s) {
        dbfpanel.doTrick(s);
    }

    public void procsRecord(String db, String tbl, String s) {
        String s1 = "REPLACE INTO " + tbl + "  VALUES('";
        String s2 = replaceString(s, "isnull", "");
        s2 = replaceString(s2, "'", "\\'");
        s2 = replaceString(s2, "\t", "','");
        String s3 = db + "[|]" + s1 + s2.trim() + "')";
        comtext.addItem(s3);
        int i = comtext.getItemCount();
        comtext.select(0);
        getDBapp();
        setQstatus(aplangstrings.getProperty("text210"), false);
    }

    public void startComtextQuery(String whichComtext) {

        String tempMsgBoxTitle = aplangstrings.getProperty("text547");
        String tempMsgBoxBody = aplangstrings.getProperty("text548");

        System.out.println("startComtextQuery: " + whichComtext);

        if (currentDBID.equals("555")) {
            setQstatus(aplangstrings.getProperty("text092"), false);
            return;
        }


        if (whichComtext.equals("selected")) {
            tempMsgBoxTitle = aplangstrings.getProperty("text547");
            tempMsgBoxBody = aplangstrings.getProperty("text219");

            if (comtext.getSelectedItem() == null) {
                setQstatus(aplangstrings.getProperty("text035"), true);

                return;
            }
        }
        if (whichComtext.equals("all")) {
            tempMsgBoxTitle = aplangstrings.getProperty("text547");
            tempMsgBoxBody = aplangstrings.getProperty("text516");
            if (comtext.getItemCount() < 0) {
                setQstatus(aplangstrings.getProperty("text183"), true);
                return;
            }
        }

        MsgBox aaUPQMsgBox = new MsgBox(this, tempMsgBoxTitle, tempMsgBoxBody + "?", true);
        requestFocus();
        if (aaUPQMsgBox.id) {
            setWaitCursor();
            aaDisableButtons();
            dbfpanel.progressBarAA.setIndeterminate(true);
            aaUPQMsgBox.dispose();

            try {


                taskAAUCTQs = new taskAAUComTextQs(this, whichComtext);
                taskAAUCTQs.execute();
            } catch (Exception exception) {
                setQstatus("ERROR: adminApp [1865c]: " + exception.toString(), true);
                aaUPQMsgBox.dispose();
                return;
            }

        } else {
            aaUPQMsgBox.dispose();
        }


    }

    public void stopComtextQuery() {
        aaEnableButtons();
        setDefaultCursor();
        dbfpanel.progressBarAA.setIndeterminate(false);
        try {
            System.out.println("stopUploadQs");
        } catch (Exception exception) {
            setQstatus("ERROR: adminApp [1880c]: " + exception.toString(), true);
        }
    }

    public void postComtextQuery() {

        if (comtext.getSelectedItem() != null) {

            String s = comtext.getSelectedItem();
            int i = comtext.getSelectedIndex();

            if (s.indexOf("[|]") != -1) {
                String psBbstring = s.substring(0, s.indexOf("[|]"));
                String psQystring = s.substring(s.indexOf("[|]") + 3, s.length());
                System.out.println(psBbstring);
                System.out.println(psQystring);
                boolean ctispumped = pumpComtextQ(psBbstring, psQystring);
                if (ctispumped) {
                    comtext.remove(i);
                    setQstatus(aplangstrings.getProperty("text068") + ": 1", false);
                    // delete this function writeFile ?  writeFile(psBbstring, psQystring);
                } else {
                    // do nothing, pumpComtextQ,  handles it
                }
            } else {
                setQstatus(aplangstrings.getProperty("text184") + ":\n" + s, true);
            }

        } else {
            setQstatus(aplangstrings.getProperty("text035"), true);
            return;
        }
    }

    public void postAllComtextQueries() {

        String s = "";
        String totalstrng = "";
        int outcount = 0;
        int i = comtext.getItemCount();
        int j = i - 1;
        for (int k = 0; k < i; k++) {
            s = comtext.getItem(k);
            totalstrng = totalstrng + s;
            if (s.indexOf("[|]") != -1) {
                String psBbstring = s.substring(0, s.indexOf("[|]"));
                String psQystring = s.substring(s.indexOf("[|]") + 3, s.length());
                System.out.println(psBbstring);
                System.out.println(psQystring);
                boolean ctispumped = pumpComtextQ(psBbstring, psQystring);
                if (ctispumped) {
                    outcount = outcount + 1;
                }
            } else {
                setQstatus(aplangstrings.getProperty("text184") + ":\n" + s, true);
                return;
            }
        }
        if (totalstrng.length() < 1) {
            setQstatus("No Pending Queries to Run...", true);
            return;
        }
        comtext.removeAll();
        setQstatus(aplangstrings.getProperty("text068") + ": " + outcount, false);
    }

    public boolean pumpComtextQ(String wbase, String str) {
        boolean gotctpumped = false;

        try {
            long nowLong = System.currentTimeMillis() / 1000;
            String datestring = Long.toString(nowLong);
            int myint = Integer.parseInt(datestring);
            String dbqi = Integer.toString(myint);
            dbqi = dbqi.substring(dbqi.length() - 2, dbqi.length());
            setStatusText("Compressed File: " + dbqi);

            FileOutputStream fos = new FileOutputStream(udString + "/" + "cbox/dbqs/" + dbqi + ".pcdbq");
            BufferedOutputStream buffer = new BufferedOutputStream(fos);
            GZIPOutputStream gzip = new GZIPOutputStream(buffer);

            gzip.write(str.getBytes());

            gzip.close();
            buffer.close();
            fos.close();

            File file = new File(getUfile("cbox/dbqs/" + dbqi + ".pcdbq"));
            // URL url = new URL(hostfolder + "qcomms/qcomms.php?" + userpassString + "&dbqi=" + dbqi + "&action=uploadFileQuery");
            URL url = new URL(hostfolder + "qcomms/qcomms.php?" + userpassString + "&action=uploadFileQuery&ttime=" + datestring + "&dbqi=" + dbqi);
            setStatusText("Insert Records:\n" + url);
            // URLConnection urlconnection = url.openConnection();
            chttpreq = new ClientHttpRequest(url);
            if (chttpreq != null) {
                chttpreq.setParameter("filename", file);
                // chttpreq.setParameter("dbqi", dbqi);
                setStatusText("loading file to server");
                InputStream instream = ClientHttpRequest.post(url, new Object[]{"filename", file});

                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(instream));
                String s1;
                String s2 = "";
                while ((s1 = bufferedreader.readLine()) != null) {
                    s2 = s2 + s1;
                }
                setStatusText("pumpComtextQ output:: " + s2);
                gotctpumped = true;
            }

        } catch (Exception e) {
            System.out.println(e);
            gotctpumped = false;
            setQstatus("Error 1979A [adminApp]: \n" + e.toString(), false);
        }
        return gotctpumped;
    }

    public void writeFile(String wbase, String str) {
        System.out.println("writeFile function: 1737");
        try {
	  if(hostfolder.startsWith("jdbc")) {
        setQstatus("its sqllight", true);
	  int its =  0;
        String as[] = readmessyTokens(str, "\n");
        int i = as.length;
        int j = i - 1;
        for (int iSA = 0; iSA < as.length; iSA++) {
 	   dbMSQLA.setTabsQ(as[iSA]);
		its++;
        }

                setQstatus("Ttl: " + its, false);
		} else {

	 
            // str = replaceString(str, "'", "");
            long nowLong = System.currentTimeMillis() / 1000;
            String datestring = Long.toString(nowLong);
            int myint = Integer.parseInt(datestring);
            String dbqi = Integer.toString(myint);
            dbqi = dbqi.substring(dbqi.length() - 2, dbqi.length());
            setStatusText("Compressed File: " + dbqi);

            FileOutputStream fos = new FileOutputStream(getUfile("cbox/dbqs/" + dbqi + ".pcdbq"));
            BufferedOutputStream buffer = new BufferedOutputStream(fos);
            GZIPOutputStream gzip = new GZIPOutputStream(buffer);

            gzip.write(str.getBytes());

            gzip.close();
            buffer.close();
            fos.close();

            File afile = new File(getUfile("cbox/dbqs/" + dbqi + ".pcdbq"));
            Authenticator.setDefault(new MyAuthenticator());
            String uWFString = hostfolder + "qcomms/qcomms.php?" + userpassString + "&action=uploadFileQuery&ttime=" + datestring;
            URL url = new URL(uWFString);

            // URLConnection urlconnection = url.openConnection();
            chttpreq = new ClientHttpRequest(url);
            if (chttpreq != null) {

                chttpreq.setParameter("filename", afile);
                chttpreq.setParameter("dbqi", dbqi);
                // InputStream instream = chttpreq.post(url, new Object[] { "filename", afile });
                InputStream instream = chttpreq.post();

                String s1;
                String s2 = getGzipAsString(instream);
 



                QstatusTextArea.setText("Insert Records:\n" + url);
                 setStatusText("loading file to server: writefile\n" + uWFString);
                setQstatus(s2, false);
            }



	  } // end of else not jdbc
        } catch (Exception e) {
            System.out.println(e);
            String eString = e.toString();
            if ((eString.indexOf("redir") != -1) && (encryptedUname.length() > 2)) {
                eString += "It seems your password protected folders username/password are incorrect.\nOr your .htaccess file may not be configured properly";
            }
            setQstatus("Error 1773A [adminApp]: \n" + eString, false);
        }

    }

    public void getQuickLinks() {
        String s = maddress.getText();
        if (!qlinksframe1) {
            jbrowserOpen = new JBrowserOpen(this, s, 1);
            qlinksframe1 = true;
            return;
        } else {
            jbrowserOpen.jbodummy();
            return;
        }
    }

    public void getLiveHelp() {
        String s = maddress.getText();

    }

    public void getWebPrefs() {
        if (boolAAPrefsdlgOpen) {
            // aaPrefsDlg.aaprfsdummy();
            aaPrefsDlg.requestFocus();
        } else {
            aaPrefsDlg = new AAPrefsdialog(this, "useproxy");
            boolAAPrefsdlgOpen = true;
        }
    }

    // these should not be made public

    public void getEditDBPrefs(String theEfname) {
        if ((currentDBID.equals("555")) && (!theEfname.equals("noQvalue"))) {
            setQstatus(aplangstrings.getProperty("text326"), false);
        } else {
            editDBDlg = new EditDBdialog(this, theEfname);
            boolAddDBDlgOpen = true;
        }
    }

    /*
    * this is used when didnt wnat the EditDBdialog modal
    * now making it modal
    * using simpler function above
    */
    public void getEditDBPrefsOLD(String theEfname) {

        if ((currentDBID.equals("555")) && (!theEfname.equals("noQvalue"))) {
            setQstatus(aplangstrings.getProperty("text326"), false);
        } else {


            if (boolAddDBDlgOpen) {
                // editDBDlg.aaprfsdummy();
                if (theEfname == editDBDlg.pboolstring) {
                    editDBDlg.requestFocus();
                } else {
                    editDBDlg.dispose();
                    boolAddDBDlgOpen = false;
                    editDBDlg = new EditDBdialog(this, theEfname);
                    boolAddDBDlgOpen = true;
                }
            } else {
                editDBDlg = new EditDBdialog(this, theEfname);
                boolAddDBDlgOpen = true;
            }

        }
    }

    public void getDBQBox() {
        if (boolDBQBoxOpen) {
            dbqueryBox.dbqdummy();
        } else {
            dbqueryBox = new DBQueryBox(this, "hehe");
            boolDBQBoxOpen = true;
        }
    }

    public void getDBActionBox(String db, String qstring) {
        if (boolBrowDBAOpen) {
            dbactionBox.dbqdummy();
        } else {
            dbactionBox = new BrowDBAction(this, maddress.getText(), db, tablename, qstring, 0);
            boolBrowDBAOpen = true;
        }
    }

    public void getSharpPopUp(int x, int y) {
        System.out.println("getSharpPopUp boolSharpopOpen2");
        new SharpPopmenu(this, "heheheh", x, y);
        boolSharpopOpen = true;
    }
// end to delete

    public void isitBold() {
        if (!isbold) {
            setLocation(5, 5);
            resize(280, 51);
            isbold = true;
            return;
        }
        if (isbold) {
            setLocation(0, 0);
            resize(Toolkit.getDefaultToolkit().getScreenSize());
            isbold = false;
            return;
        } else {
            return;
        }
    }

    public void isitOntop() {
        if (!winOntop) {
            winOntop = true;
            ontop();
            setQstatus(aplangstrings.getProperty("text038"), false);
        } else {
            winOntop = false;
            onbottom();
            setQstatus(aplangstrings.getProperty("text039"), false);
        }
    }

    public void onbottom() {
        // JUtil.setWindowAlwaysOnTop(JUtil.getHwnd(getTitle()), false);
        winOntop = false;
    }

    public void ontop() {
        // JUtil.setWindowAlwaysOnTop(JUtil.getHwnd(getTitle()), true);
        winOntop = true;
    }

    public void getAppStatus() {
        setQstatus(QqueryTextArea.getText(), false);
    }

    public void getStatusBox() {
        if (boolstatusBoxOpen) {
            statusBox.requestFocus();
        } else {
            statusBox.setVisible(true);
            boolstatusBoxOpen = true;
        }
    }

    public void getBrowToDBAction(String ucString) {
        maddress.setText(ucString);
        String compUrl = maddress.getText().trim();

        if (compUrl.lastIndexOf("aaAction=") != -1) {

            String partUrl = compUrl;
            String urlToken = "";
            String finalDBstring = currentDB;
            String finalTblstring = "";
            String finalQstring = "";
            int hasUrl = 0;


            System.out.println("has action");
            urlToken = URLDecoder.decode(compUrl.substring(compUrl.lastIndexOf("aaAction=") + 9, compUrl.length()));

            // if(compUrl.lastIndexOf("=") != -1) {
            // partUrl = compUrl.substring(0, compUrl.lastIndexOf("=") + 1);
            // }

            if (urlToken.lastIndexOf("::") != -1) {
                finalTblstring = urlToken.substring(0, urlToken.lastIndexOf("::"));
                finalQstring = urlToken.substring(urlToken.lastIndexOf("::") + 2, urlToken.length());
            }

//      setDBQQuery(finalQstring, finalDBstring, finalTblstring);
            setStatusText(finalQstring + " : " + finalDBstring + " : " + finalTblstring);

        }

    }

    public void addSmartRows(int nr) {
        setStatusText("adding row: " + nr);
        for (int i = 0; i < nr; i++) {
            setStatusText("adding row: " + nr);
            dbfpanel.addSmartRow();
        }
    }

    private void setBasicAuthentication(String name, String password, URLConnection connection) {
        assert name != null && password != null : "Need name and password for this method";
        String userPassword = name + ":" + password;
        String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encoding);
    }

    public String getUfile(String s) {
        String uFileString = "";
        String cleanFileString = replaceString(s, "/", fsString);
        cleanFileString = replaceString(s, "\\", fsString);
        uFileString = udString + fsString + cleanFileString;
        return uFileString;
    }

    public String getTitleToFile(String stfs) {
        String TitleToFileString = stfs;
        TitleToFileString = replaceString(TitleToFileString, "/", "-");
        TitleToFileString = replaceString(TitleToFileString, "\\", "-");
        TitleToFileString = replaceString(TitleToFileString, " ", "-");
        TitleToFileString = replaceString(TitleToFileString, ".", "-");
        return TitleToFileString;

    }

    public String getFileToTitle(String sfts) {
        String FileToTitle = sfts;
        FileToTitle = replaceString(FileToTitle, " ", "-");
        FileToTitle = replaceString(FileToTitle, ".dat", "");
        return FileToTitle;
    }

    public String getDBID(String fileTitle) {
        String theDBID = "noQvalue";
        try {
            String theFtitle = getTitleToFile(fileTitle);
            Properties getDBProps = new Properties();
            FileInputStream fileinputstream = new FileInputStream(getUfile("cbox/data/dbs/" + theFtitle + ".dat"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            getDBProps.load(fileinputstream);
            theDBID = getDBProps.getProperty("dbID");
        } catch (Exception exception) {
            // dbqueryBox.
            System.out.println("Error 2274AA [adminApp] fileTitle: \n" + fileTitle + " :: " + exception.toString());
            // setQstatus("Error 2274AA [adminApp]: \n" + fileTitle + " :: " + exception.toString(), false);
            theDBID = "555";
        }
        return theDBID.trim();
    }

    public void loadnewDBprefs(String s) {
        Properties newDBProps = new Properties();
        currentDBTitle = getTitleToFile(s);
        try {
            FileInputStream fileinputstream = new FileInputStream(getUfile("cbox/data/dbs/" + currentDBTitle + ".dat"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            newDBProps.load(fileinputstream);
            hostfolder = newDBProps.getProperty("hostfolder").trim();
            aausername = newDBProps.getProperty("adminusername").trim();
            aapassword = newDBProps.getProperty("adminpassword").trim();
            encryptedUname = newDBProps.getProperty("encryptedUname").trim();
            encryptedPass = newDBProps.getProperty("encryptedPass").trim();
            currentDB = newDBProps.getProperty("webdbasename").trim();
            currentDBHost = newDBProps.getProperty("dbHost", "localhost").trim();
            hostdbase = currentDB;
            currentDBID = newDBProps.getProperty("dbID", "555").trim();
            userpassString = "auser=" + aaEncryptUrl(aausername) + "&apass=" + aaEncryptUrl(aapassword) + "&" + "dyndbase=" + hostdbase + "&host=" + currentDBHost;
            System.out.println("loadnewDBprefs: " + hostfolder + "&" + currentDB + "&" + currentDBID);
            QstatusTextArea.setText("HostFolder: " + hostfolder);
            fileinputstream.close();
            datainputstream.close();
            return;
        } catch (Exception exception) {
            System.out.println("loadnewPref Ex" + exception.toString());
            setQstatus("Error 2256A [adminApp]: \n" + exception.toString(), true);
            // return;
        }
    }

    public void loadNewDB(String s) {
        currentDBTitle = getTitleToFile(s);
        try {
            dbqueryBox.startDBQBReloadDB(currentDBTitle);
        } catch (Exception exc) {
            setQstatus("Error 2392B [adminApp]: \n" + exc.toString(), false);
        }
/* delete this, the function reloadDB already pops up a comfirm box
    MsgBox message = new MsgBox(this, aplangstrings.getProperty("text319"), aplangstrings.getProperty("text320"), true);
    requestFocus();
    if (message.id) {
       message.dispose();


       } else { // not message.id
        message.dispose();
       System.out.println("Cancel pressed");
      }

*/


    }

    public void aaDisableButtons() {
        dbfpanel.sharpDisableButtons();
        btnDelPendQuery.setEnabled(false);
        btnDelAllPendQuery.setEnabled(false);
        btnRunPendQuery.setEnabled(false);
        btnRunAllPendQuery.setEnabled(false);
    }

    public void aaEnableButtons() {
        dbfpanel.sharpEnableButtons();
        btnDelPendQuery.setEnabled(true);
        btnDelAllPendQuery.setEnabled(true);
        btnRunPendQuery.setEnabled(true);
        btnRunAllPendQuery.setEnabled(true);
    }

    public String getGzipAsString(InputStream instream) {
        StringBuffer gzipStrngBuffer = null;
        String fullString = null;
        GZIPInputStream gzipIStream = null;
        BufferedReader gzipBReader = null;
        int len = 0;
        int ttlen = 0;
        char chars[] = new char[1024];
        try {

            gzipIStream = new GZIPInputStream(instream);
            gzipBReader = new BufferedReader(new InputStreamReader(gzipIStream));


            gzipStrngBuffer = new StringBuffer();
            //Write chunks of characters to the StringBuffer
            while ((len = gzipBReader.read(chars, 0, chars.length)) >= 0) {
                ttlen++;
                // System.out.println("chars.length: " + String.valueOf(chars.length) +  "chars: " + chars + "len: " + String.valueOf(len));
                gzipStrngBuffer.append(chars, 0, len);
            }
            chars = null;

            gzipIStream.close();
            gzipBReader.close();

            instream.close();

            fullString = gzipStrngBuffer.toString();
        } catch (Exception gzipException) {
            len = 0;
            ttlen = 0;
            BufferedReader normBR = new BufferedReader(new InputStreamReader(instream));
            StringBuffer normSTRB = new StringBuffer();
            try {
                while ((len = normBR.read(chars, 0, chars.length)) >= 0) {
                    ttlen++;
                    // System.out.println("chars.length: " + String.valueOf(chars.length) +  "chars: " + chars + "len: " + String.valueOf(len));
                    normSTRB.append(chars, 0, len);
                }
                fullString = "ERROR; " + gzipException.toString() + " :: " + normSTRB.toString();

            } catch (Exception except) {
                fullString = "ERROR; " + except.toString() + " :: " + instream.toString();
            }

        }
        return fullString;
    }

    public void uploadSlctdRowRec() {
        String tempSavedS = "";
        String stringUploadRcrd = dbfpanel.getRowToString();
        String s = "";
        String stringURcrdA = replaceString(stringUploadRcrd, "\n", "");

        String as[] = readmessyTokens(stringURcrdA, "\t");
        int i = as.length;
        int j = i - 1;
        for (int iSA = 0; iSA < as.length; iSA++) {
            if (iSA == j)
                s = s + as[iSA];
            else
                s = s + as[iSA] + "\t";

        }


        System.out.println("stringUploadRcrd: " + stringUploadRcrd + "count: " + String.valueOf(as.length));


        String s1 = "REPLACE INTO " + tablename + "  VALUES('";
        String s2 = replaceString(s, "isnull", "");
        s2 = replaceString(s2, "'", "\\'");
        s2 = replaceString(s2, "\t", "','");
        String s3 = s1 + s2.trim() + "')";
        startComtextQuery(s3);
        // procsAllRecords();
    }

    public String aaEncryptUrl(String theString) {

        String sReverse = new StringBuilder(theString).reverse().toString();
        String uPassStringEncd = new sun.misc.BASE64Encoder().encode(sReverse.getBytes());
        return uPassStringEncd;

    }

    public void updateQcomms() {


        System.out.println("updateQcomm function: 2891");
        String verstring = "1.1";
        long nowLong = System.currentTimeMillis() / 1000;
        String datestring = Long.toString(nowLong);
        try {

            File afile = new File(getUfile("website_uploads/qcomms/qcomms.php"));


            FileInputStream fileinputstream = new FileInputStream(afile);
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null) {
                if (s.startsWith("$qcomms_version")) {
                    String fver = replaceString(s, "$qcomms_version = ", "");
                    verstring = replaceString(fver, "\"", "");
                    verstring = replaceString(verstring, ";", "");
                    System.out.println("[2905] updateQcomm verstring: " + verstring);
                }
            }
            datainputstream.close();
            fileinputstream.close();


            Authenticator.setDefault(new MyAuthenticator());
            String uWFString = hostfolder + "qcomms/qcomms.php?" + userpassString + "&action=updateQcomms&qversion=" + verstring + "&ttime=" + datestring;
            URL url = new URL(uWFString);
            System.out.println("updateQcomm URL: 2918: " + uWFString);
            // URLConnection urlconnection = url.openConnection();
            chttpreq = new ClientHttpRequest(url);
            if (chttpreq != null) {

                chttpreq.setParameter("filename", afile);
                InputStream instream = chttpreq.post();
                String s2 = getGzipAsString(instream);
                setQstatus(s2, false);
            }

        } catch (Exception e) {
            System.out.println(e);
            String eString = e.toString();
            setQstatus("Error 2932B [adminApp]: \n" + eString, false);
        }


    }

    private void createScene() {
        PlatformImpl.startup(new Runnable() {
            @Override
            public void run() {

                stage = new Stage();

                //  stage.setTitle("Hello Java FX");
                stage.setResizable(true);

                Group root = new Group();
                Scene scene = new Scene(root, pnlBrowserCage.getWidth(), pnlBrowserCage.getHeight());
 
                stage.setScene(scene);
                // Set up the embedded browser:
                browser = new WebView();
                browser.setPrefSize(pnlBrowserCage.getWidth() - 1, pnlBrowserCage.getHeight() - 1);
                System.out.println("[3049] browser s: " + browser.getWidth() + " :: " + browser.getHeight());

                System.out.println("[3049] browser resize: " + pnlBrowserCage.getWidth() + " :: " + pnlBrowserCage.getHeight());
                webEngine = browser.getEngine();


                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State state) {
                        // System.out.println("prog loaded: " + state + " :: " + webEngine.getLoadWorker().getProgress() + " :: " + webEngine.getLoadWorker().getTotalWork() + " :: " + webEngine.getLoadWorker().getWorkDone() + " :: " + webEngine.getLoadWorker().getMessage() + " :: " + webEngine.getLoadWorker().getTitle());
                        String strdates = "loading...";
                        int iDS = 0;
                        switch (state) {
                            case READY:
                                System.out.println("Location ready  + " + webEngine.getLocation());
                                // startTime.set(System.nanoTime());




                                break;
                            case SCHEDULED:
                                System.out.println("Location SCHEDULED + " + webEngine.getLocation());
                                // startTime.set(System.nanoTime());

                                break;
                            case RUNNING:
                                iDS = 2;
                                 System.out.println("Location SCHEDULED + " + webEngine.getLocation());
                                // startTime.set(System.nanoTime());
                                break;

                            case SUCCEEDED:
                                System.out.println("Location SUCCEEDED + " + webEngine.getLocation());
                                // endTime.set(System.nanoTime());
                                iDS = 3;
 					  String url = webEngine.getLocation();
            if(url.contains("/localhost") && url.contains("?")) {
                System.out.print("onPageStarted:oride " + url);
                String[] temp;
                temp = url.split(Pattern.quote("?"));
		    // showDaToast("is one: " + temp[1]);
 		    // epMainHbook.setCurrPageVars(temp[1]);
		    currPageVars = temp[1];
			//  webEngine.load(temp[0]);
            }

 

                                strdates = "ready...";
                                maddress.setText(webEngine.getLocation());
                                JSObject win = (JSObject) webEngine.executeScript("window");
                                win.setMember("app", jsiAdminApp);
                                break;
			



                            case FAILED:
                                iDS = 4;
                                strdates = "Location failed: " + webEngine.getLoadWorker().getException().toString();
                                System.out.println("Location failed + " + webEngine.getLoadWorker().getException().toString());
                                // endTime.set(System.nanoTime());
                                System.out.println("Location FAILED + " + webEngine.getLocation());
                                break;
                        }

                        browserProgressChange(iDS, strdates);
                    }

                });
                try {
		    String tstrLV = currConfBundle.getString("lastWebPage");
		    if((tstrLV.equals("about:blank") || tstrLV.length() < 3)) {
                webEngine.loadContent(basicUtils.readFileAsString(basicUtils.getUfile("cbox/misc/intro.html")));
		    } else {
                webEngine.load(currConfBundle.getString("lastWebPage"));
		    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(WebEvent<String> event) {

                        // dcontent.getChildren().addAll(new Label(event.getData()));
                        doMsg(event.getData());

                    }
                });

                ObservableList<Node> children = root.getChildren();
                children.setAll(browser);
 


                System.out.println("[3049] browser s: " + scene.getWidth() + " :: " + scene.getHeight());

                System.out.println("[3049] browser resize: " + pnlBrowserCage.getWidth() + " :: " + pnlBrowserCage.getHeight());

 


                jfxPanel.setScene(scene);





            }
        });




        /* TODO: fix this getDBQBox(). not thread safe to get DBQuery Box untill adminapp is fully loaded
         * will just wait now till its actually called from the user clicking SearchDB button
	  */
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    getDBQBox();
			  
                } catch (Exception e) {
                    System.out.println("[1120] getDBQbox: " + e);

                }
            }
        });


    }

    public void getDynDBapp(String theString) {
        System.out.println("getDynDBapp theString: " + theString);
        try {
 
            String dStr = theString;
            System.out.println("getDynDBapp dStr: " + dStr);

            String as[] = readmessTokens(dStr, ":|:");
            currentDB = as[0];
            tablename = as[1];
            String strTmpQ = as[2];
            System.out.println("getDynDBapp strTmpQ: " + currentDB + " :: " + tablename + " :: " + strTmpQ);

            if (boolDBQBoxOpen) {
                dbqueryBox.doQ(strTmpQ);
            } else {
                dbqueryBox = new DBQueryBox(this, "hehe");
                boolDBQBoxOpen = true;
                dbqueryBox.doQ(strTmpQ);
            }
        } catch (Exception excep) {
            System.out.println("getDynDBapp function: 3189: " + excep);
        }
    }

    public void browserProgressChange(int daState, String s) {
        switch (daState) {
            case 0:
                blip.start();
                blip.setProgress(10, 100);
                statusLabel.setText(s);
                break;
            case 1:
                blip.setProgress(30, 100);
                statusLabel.setText(s);
                break;
            case 2:
                blip.setProgress(65, 100);
                statusLabel.setText(s);
                break;
            case 3:
                blip.stop();
                statusLabel.setText(s);
                break;
            case 4:
                blip.stop();
                statusLabel.setText(s);
                break;
        }

    }

    public void navigate(final String strNav) {


        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    if (strNav.indexOf("(") != -1) {
                        webEngine.executeScript(strNav);
                    } else {
                        webEngine.load(strNav);
                    }
                } catch (Throwable e) {
                    System.out.println("[3210] navigate: " + e);

                }


            }
        });
    }

    public void doMsg(String strMsg) {
        MsgBox aaUPQMsgBox = new MsgBox(this, "JS ALert", strMsg, true);

    }

    public String goBack() {
        final WebHistory history = webEngine.getHistory();
        ObservableList<WebHistory.Entry> entryList = history.getEntries();
        int currentIndex = history.getCurrentIndex();
//    Out("currentIndex = "+currentIndex);
//    Out(entryList.toString().replace("],","]\n"));

        Platform.runLater(new Runnable() {
            public void run() {
                history.go(-1);
            }
        });
        return entryList.get(currentIndex > 0 ? currentIndex - 1 : currentIndex).getUrl();
    }

    public String goForward() {
        final WebHistory history = webEngine.getHistory();
        ObservableList<WebHistory.Entry> entryList = history.getEntries();
        int currentIndex = history.getCurrentIndex();
//    Out("currentIndex = "+currentIndex);
//    Out(entryList.toString().replace("],","]\n"));

        Platform.runLater(new Runnable() {
            public void run() {
                history.go(1);
            }
        });
        return entryList.get(currentIndex < entryList.size() - 1 ? currentIndex + 1 : currentIndex).getUrl();
    }

    public class mySlicWindowListener extends WindowAdapter {

        public mySlicWindowListener() {
        }

        public void windowActivated(WindowEvent windowevent) {
        System.out.println("windowActivated" + windowevent.getSource().toString());
        }
        public void windowClosing(WindowEvent windowevent) {
            shutdown();
        }
    }

    public class MyAuthenticator extends Authenticator {
        // This method is called when a password-protected URL is accessed
        protected PasswordAuthentication getPasswordAuthentication() {
            // Get information about the request
            String promptString = getRequestingPrompt();
            String hostname = getRequestingHost();
            InetAddress ipaddr = getRequestingSite();
            int port = getRequestingPort();

            // Get the username from the user...
            String username = encryptedUname;

            // Get the password from the user...
            String password = encryptedPass;

            // Return the information
            return new PasswordAuthentication(username, password.toCharArray());
        }
    }

    public class JavaApp {

        public void doDB(String dbString) {
 
            getDynDBapp(dbString);
        }
 

    }


    public Bundle getConfBundle() {
        Bundle theConfBundle = new Bundle();
        theConfBundle.putInteger("quid", Integer.parseInt(aamainprefs.getProperty("quid", "1")));
        theConfBundle.putString("cartID", aamainprefs.getProperty("cartID"));
        theConfBundle.putString("usrlang", aamainprefs.getProperty("usrlang"));
        theConfBundle.putString("prfsSHOPuser", aamainprefs.getProperty("prfsSHOPuser"));
        theConfBundle.putString("lastWebPage", aamainprefs.getProperty("lastWebPage", "about:blank"));
        return theConfBundle;
    }


    public String getConfValString(String theKey) {
        String strTheKey = "noQvalue";
        try {
            strTheKey = currConfBundle.getString(theKey);
            System.out.println("getConValString: " + strTheKey);
        } catch (Exception err) {
            System.out.println("Error.getConValString: " + err);
        }
        return strTheKey;
    }

    public Integer getConfValInt(String theKey) {

        int strTheKey = 1234;
        try {
            strTheKey = currConfBundle.getInteger(theKey);
            System.out.println("getConfValInt: " + strTheKey);
        } catch (Exception err) {
            System.out.println("Error.getConValInt: " + err);
        }
        return strTheKey;
    }

 

    public void putConfValString(String theKey, String theVal) {


        aamainprefs.setProperty(theKey, theVal);
        currConfBundle = getConfBundle();
             System.out.println("putConfValInt: " + theKey);

 

    }

    public void putConfValInt(String theKey, Integer theVal) {
       try {
        aamainprefs.setProperty(theKey, String.valueOf(theVal));
        currConfBundle = getConfBundle();
            System.out.println("putConfValInt: " + theKey);
 

	} catch(Exception e) {
            System.out.println("Error.putConfValInt: " + e);	
	}

    }
    public String getCurrPageVars(String tmpStrQstr) {
 
        return currPageVars;
    }
    public String getStrDBTitles() {
        return dbqueryBox.giveStrDBTitles();
    }

    public void getNewDBdialog(String tstrHfolder) {
        hostfolder = tstrHfolder;
	  getEditDBPrefs("noQvalue");
    }
    public void setWebDBTitle(String tstrDBt) {
	  try {
        navigate("setNewDBTitle('" + tstrDBt + "');");
	} catch(Exception e) {
            System.out.println("Error.setWebDBTitle: " + e);	
	}
    }

}