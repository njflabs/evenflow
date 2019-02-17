package njfbrowser.misc;


//////////////////////////////////////////////////////////////////
/**
 * This class creates a frame with a JEditorPane for loading HTML
 * help files
 */
//package goes here

import njfbrowser.utils.BasicUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

public class HelpWindow extends JFrame implements ActionListener {

//////////////////////////////////////////////////////////////////

    /**
     * HelpWindow constructor
     * @param String and URL
     */
    public HelpWindow(String title, String helpURL, String langUrl) {
        // super(title);
        super("adminApp Help");
        basicUtils = new BasicUtils();
        stringDocsDir = basicUtils.getUfile("docs/" + langUrl + "/");
        this.setEnabled(true);
        tracker = new MediaTracker(this);
        Image iconHelpImg = Toolkit.getDefaultToolkit().getImage(basicUtils.getUfile("cbox/images/iconHelp.gif"));
        tracker.addImage(iconHelpImg, 1);
        try {
            tracker.waitForAll();
        } catch (Exception interruptedexception) {
            System.out.println(interruptedexception.toString());

        }
        setIconImage(iconHelpImg);


        editorpane = new JEditorPane();
        scrollPaneEditor = new JScrollPane(editorpane);
        editorpane.setEditable(false);
        HTMLEditorKit kit = (HTMLEditorKit) editorpane.getEditorKitForContentType("text/html");
        editorpane.setEditorKit(kit);
        //anonymous inner listener
        editorpane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent ev) {
                try {

                    if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
// System.out.println("URL: " + ev.getURL());
                        System.out.println("URL getSourceElement: " + ev.getSourceElement());
                        System.out.println("URL: getDescription" + ev.getDescription());
                        // String uDirString = basicUtils.getUfile(ev.getURL().toString());
                        // String repTplateUri = "file:///" + uDirString;
                        //  editorpane.setPage(ev.getURL().toString());
                        // editorpane.setPage(repTplateUri);
                        // editorpane.setPage(uDirString);
                        doHEventUrlAction(ev.getDescription().toString());
                    }
                } catch (Exception ex) {
                    //put message in window
                    ex.printStackTrace();
                }
            }
        });


        menuPane = new JEditorPane();
        scrollPaneMenu = new JScrollPane(menuPane);

        menuPane.setEditable(false);
        HTMLEditorKit kitA = (HTMLEditorKit) menuPane.getEditorKitForContentType("text/html");
        menuPane.setEditorKit(kitA);
        //anonymous inner listener
        menuPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent ev) {
                try {

                    if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        System.out.println("URL ev.getEventType() " + ev.getEventType());
                        System.out.println("URL getSourceElement: " + ev.getSourceElement());
                        System.out.println("URL: getDescription" + ev.getDescription());
                        System.out.println("URL: getURL" + ev.getURL());
                        // editorpane.setText(getTemplate(basicUtils.getUfile("docs/" + ev.getDescription())));
                        // calculateLocation();
                        // getTemplate(ev.getDescription());
                        doHEventUrlAction(ev.getDescription().toString());
                    }
                } catch (Exception ex) {
                    //put message in window
                    ex.printStackTrace();
                }
            }
        });


        JSplitPane splitPaneHelp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneMenu, scrollPaneEditor);
        Border emptyBdr = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        splitPaneHelp.setOneTouchExpandable(true);
        splitPaneHelp.setContinuousLayout(false);
        splitPaneHelp.setBorder(emptyBdr);
        splitPaneHelp.setDividerLocation(215);


        JPanel panelHWmain = new JPanel(new BorderLayout());
        panelHWmain.add("Center", splitPaneHelp);
        getContentPane().add(panelHWmain, BorderLayout.CENTER);


        addButtons();
        // no need for listener just dispose
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // dynamically set location
        calculateLocation();
        setVisible(true);
        requestFocus();
        try {
            // editorpane.setText(getTemplate(helpURL));
            doHEventUrlAction(helpURL);
        } catch (Exception ex) {
            editorpane.setText(getTemplate(stringDocsDir + "index_body.html"));

        }
        try {

            menuPane.setText(getTemplate(stringDocsDir + "index_menu.html"));

        } catch (Exception ex) {
            System.out.println(ex.toString());

        }
        // end constructor
    }

    /**
     * An Actionlistener so must implement this method
     *
     */
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        URL tempURL;
        try {
            if (strAction == "Contents") {
                tempURL = editorpane.getPage();
                editorpane.setPage(helpURL);
            }
            if (strAction == "Close") {
                // more portable if delegated
                processWindowEvent(new WindowEvent(this,
                        WindowEvent.WINDOW_CLOSING));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * add buttons at the south
     */
    private void addButtons() {
        JButton btncontents = new JButton("Contents");
        btncontents.addActionListener(this);
        JButton btnclose = new JButton("Close");
        btnclose.addActionListener(this);
        //put into JPanel
        JPanel panebuttons = new JPanel();
        //panebuttons.add(btncontents);
        // panebuttons.add(btnclose);
        //add panel south
        getContentPane().add(panebuttons, BorderLayout.SOUTH);
    }

    /**
     * locate in middle of screen
     */
    private void calculateLocation() {
        Dimension screendim = Toolkit.getDefaultToolkit().getScreenSize();
        //  setSize(new Dimension(WIDTH, HEIGHT));
        setSize(new Dimension(screendim.width - 200, screendim.height - 200));
        int locationx = (screendim.width - (screendim.width - 200)) / 2;
        int locationy = (screendim.height - (screendim.height - 200)) / 2;
        setLocation(locationx, locationy);
    }


    public static void main(String[] args) {

        String strA;
        String strB;
        String strC;
        try {
            if (args[0] != null) {
                strA = args[0];
            } else {
                strA = "Help";
            }

            if (args[1] != null) {
                strB = args[1];
            } else {
                strB = "index_body.html";
            }

            if (args[2] != null) {
                strC = args[2];
            } else {
                strC = "en_US";
            }

            new HelpWindow(strA, strB, strC);

        } catch (Exception e) {
            new HelpWindow("help", "index_body.html", "en_US");
        }
    }

    public String getTemplate(String s) {
        String cleanTplateString = "noQvalue";
        try {
            String tplateString = basicUtils.readFileAsString(s);
            String repTplateUri = "src=\"file:///" + stringDocsDir;
            String cleanFileString = basicUtils.replaceString(repTplateUri, "\\", "/");
            cleanTplateString = basicUtils.replaceString(tplateString, "src=\"", cleanFileString);
            // System.out.println( "cleanTplateString: " + cleanTplateString);
            // editorpane.setText(cleanTplateString);


        } catch (Exception ex) {
            System.out.println("getTemplate: " + ex);
        }
        return cleanTplateString;
    }


    public void doHEventUrlAction(String urlString) {
        if (urlString.startsWith("http")) {
            try {
                BrowserControl.displayURL(urlString);
            } catch (Exception hexeception) {
                System.out.println("hexeception: " + hexeception.toString());
            }

        } else {
            editorpane.setText(getTemplate(stringDocsDir + urlString));
            editorpane.repaint();
            editorpane.setCaretPosition(0);
            // 	 hide();show();
        }

    }

    JScrollPane scrollPaneEditor;
    JScrollPane scrollPaneMenu;

    private final int WIDTH = 700;
    private final int HEIGHT = 560;
    private JEditorPane editorpane;
    private JEditorPane menuPane;
    private URL helpURL;
    BasicUtils basicUtils;
    MediaTracker tracker;
    String stringDocsDir;
}//end HelpWindow class
////////////////////////////////////////////////////////////////
