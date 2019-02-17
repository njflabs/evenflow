package njfbrowser.misc;

import njfbrowser.main.adminApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class TimeStampBox extends JDialog
        implements ActionListener, KeyListener, ItemListener, MouseListener {
    public class TimeStampBoxWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public TimeStampBoxWindowListener() {
        }
    }


    public void ItemListener(ItemEvent itemevent) {
    }

    public void keepitSmaller() {
        resize(280, 53);
        setLocation(300, 15);
        isbolder = true;
    }


    public void keyPressed(KeyEvent keyevent) {
    }

    public void itemStateChanged(ItemEvent itemevent) {

    }

    public void mouseReleased(MouseEvent mouseevent) {

    }


    public void actionPerformed(ActionEvent actionevent) {

    }

    public void mouseEntered(MouseEvent mouseevent) {
        Component component = mouseevent.getComponent();
        setCursor(Cursor.getPredefinedCursor(12));
        if (component == addchan) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text902"));
            return;
        }
        if (component == chandelete) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text903"));
            return;
        }

        if (component == btnTstamp) {
            tiplabel1.setText(parent.aplangstrings.getProperty("text904"));
            return;
        }
    }

    public void mouseExited(MouseEvent mouseevent) {
        setCursor(Cursor.getPredefinedCursor(0));
        tiplabel1.setText(parent.aplangstrings.getProperty("text538"));
    }

    private Frame getParentFrame() {
        Container container;
        for (container = getParent(); !(container instanceof Frame); container = container.getParent()) ;
        return (Frame) container;
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent mouseevent) {
        setCursor(Cursor.getPredefinedCursor(3));
        if (mouseevent.getSource() == addchan) {
            setCurrTime(true);
        }
        if (mouseevent.getSource() == chandelete) {
            setCurrTime(false);
        }
        if (mouseevent.getSource() == btnTstamp) {
            UsellcurrTfield.setText(adminApp.getTimeStamp());
            UsellcurrTfield.requestFocus();
        }
        setCursor(Cursor.getPredefinedCursor(0));
    }

    public TimeStampBox(adminApp myslic, String s) {
        super(myslic, "TimeStamp Box");

        parent = myslic;
        getContentPane().setLayout(new BorderLayout());
        qitem = "";
        tracker = new MediaTracker(this);
        img5 = Toolkit.getDefaultToolkit().getImage("cbox/images/tstampdel.gif");
        img6 = Toolkit.getDefaultToolkit().getImage("cbox/images/tstampadd.gif");
        img7 = Toolkit.getDefaultToolkit().getImage("cbox/images/tstamp.gif");
        tracker.addImage(img5, 5);
        tracker.addImage(img6, 6);
        tracker.addImage(img7, 7);
        try {
            tracker.waitForAll();
        } catch (InterruptedException _ex) {
        }
        chandelete = new ImageButton("Delete Channel", img5);
        addchan = new ImageButton("Add Channel", img6);
        btnTstamp = new ImageButton("Current Time", img7);
        tiplabel1 = new JLabel(parent.aplangstrings.getProperty("text538"));
        tiplabel1.setForeground(new Color(200, 0, 0));
        label = new JLabel(parent.aplangstrings.getProperty("text522"));

        UsellLabel = new JLabel("NicePrice", 2);


        UsellCloseLabelD = new JLabel("Days: ", 2);
        UsellCloseLabelH = new JLabel("Hours: ", 2);
        UsellCloseLabelM = new JLabel("Minutes: ", 2);

        UsellCloseTfieldD = new JTextField("0", 3);
        UsellCloseTfieldH = new JTextField("0", 3);
        UsellCloseTfieldM = new JTextField("0", 3);

        UsellcurrTLabel = new JLabel("TimeStamp: ");
        UsellcurrTfield = new JTextField(adminApp.getTimeStamp());

        JPanel UsellcurrTPanel = new JPanel(new BorderLayout());
        UsellcurrTPanel.add("West", UsellcurrTLabel);
        UsellcurrTPanel.add("Center", UsellcurrTfield);


        JPanel UsellClosesDPanel = new JPanel(new BorderLayout());
        UsellClosesDPanel.add("West", UsellCloseLabelD);
        UsellClosesDPanel.add("Center", UsellCloseTfieldD);


        JPanel UsellClosesHPanel = new JPanel(new BorderLayout());
        UsellClosesHPanel.add("West", UsellCloseLabelH);
        UsellClosesHPanel.add("Center", UsellCloseTfieldH);

        JPanel UsellClosesMPanel = new JPanel(new BorderLayout());
        UsellClosesMPanel.add("West", UsellCloseLabelM);
        UsellClosesMPanel.add("Center", UsellCloseTfieldM);

        JPanel UsellClosesGPanel = new JPanel(new BorderLayout(2, 2));
        UsellClosesGPanel.add("West", UsellClosesDPanel);
        UsellClosesGPanel.add("Center", UsellClosesHPanel);
        UsellClosesGPanel.add("East", UsellClosesMPanel);

        JPanel UsellBtnPanel = new JPanel(new GridLayout(0, 3, 3, 3));
        UsellBtnPanel.add(addchan);
        UsellBtnPanel.add(chandelete);
        UsellBtnPanel.add(btnTstamp);



/*
        UsellClosesGPanel.add("West", UsellClosesDPanel);
        UsellClosesGPanel.add("Center", UsellClosesHPanel);
        UsellClosesGPanel.add("East", UsellClosesMPanel);
*/


        JPanel UsellClosesPanel = new JPanel(new BorderLayout());
        UsellClosesPanel.add("Center", UsellClosesGPanel);
        UsellClosesPanel.add("East", UsellBtnPanel);

        JPanel UsellPanelMid = new JPanel(new BorderLayout());
        // UsellPanelMid.add("North", UsellLabel);
        UsellPanelMid.add("Center", UsellClosesPanel);


        JPanel UsellPanel = new JPanel(new BorderLayout());
        UsellPanel.add("North", tiplabel1);
        UsellPanel.add("Center", UsellPanelMid);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(new Color(225, 225, 225));
        panel1.add("North", UsellPanel);
        panel1.add("South", UsellcurrTPanel);

        getContentPane().add("Center", panel1);


        addchan.addActionListener(this);
        addchan.addMouseListener(this);

        chandelete.addActionListener(this);
        chandelete.addMouseListener(this);

        btnTstamp.addActionListener(this);
        btnTstamp.addMouseListener(this);

        addWindowListener(new TimeStampBoxWindowListener());

        setLocation(160, 160);
        pack();
        // resize(320, 100);
        setVisible(true);
        isbolder = false;

        // setResizable(false);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void EDmnuCopy_actionPerformed(ActionEvent actionevent) {

    }

    public void keyReleased(KeyEvent keyevent) {
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

    public void setCurrTime(boolean toadd) {
        long nowLong = System.currentTimeMillis() / 1000;
        String datestring = Long.toString(nowLong);
        int myint = Integer.parseInt(datestring);
        int ttime = 0;
        System.out.println("Value is " + myint);

        int dtime = Integer.parseInt(UsellCloseTfieldD.getText()) * 86400;
        int htime = Integer.parseInt(UsellCloseTfieldH.getText()) * 3600;
        int mtime = Integer.parseInt(UsellCloseTfieldM.getText()) * 60;
        int ctime = dtime + htime + mtime;
        if (ctime <= 0) {
            UsellCloseTfieldD.requestFocus();
            parent.setQstatus(parent.aplangstrings.getProperty("text905"), false);
            return;
        }

        if (toadd) {
            ttime = myint + ctime;
        } else {
            ttime = myint - ctime;
        }
        UsellcurrTfield.setText(String.valueOf(ttime));
        UsellcurrTfield.requestFocus();
    }


    String qitem;
    protected boolean isbolder;
    Image img5;
    Image img6;
    Image img7;
    ImageButton chandelete;
    ImageButton addchan;
    ImageButton btnTstamp;
    JLabel tiplabel1;
    JLabel label1;

    String inifile;
    Properties httpconfig;
    adminApp parent;
    JLabel l;
    static String s9 = "";

    MediaTracker tracker;

    String ilinenum;


    JLabel label;
    JLabel UsellLabel;
    JLabel UsellcurrTLabel;

    JLabel UsellCloseLabelD;
    JLabel UsellCloseLabelH;
    JLabel UsellCloseLabelM;

    JTextField UsellCloseTfieldD;
    JTextField UsellCloseTfieldH;
    JTextField UsellCloseTfieldM;
    JTextField UsellcurrTfield;

}
