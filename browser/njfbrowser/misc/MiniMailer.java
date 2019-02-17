package njfbrowser.misc;

import njfbrowser.main.adminApp;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.StringTokenizer;

class MiniMailer extends JDialog
        implements Runnable, KeyListener, ActionListener, ItemListener, MouseListener {
    public class MiniMailerWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            if (mmThread != null)
                mmThread.stop();
            childMMShutdown();
        }

        public MiniMailerWindowListener() {
        }
    }


    public void childMMShutdown() {
        dispose();
    }

    public void readFile(String s) {
        Frame frame = new Frame();
        filedialog = new FileDialog(frame, "Open", 0);
        filedialog.setDirectory("bin" + fileSeparator + "mmailer" + fileSeparator + s);
        filedialog.show();
        String s1 = filedialog.getFile();
        String s2 = filedialog.getDirectory() + filedialog.getFile();
        openFile(s2, s);
    }

    public void openFile(String s, String s1) {
        String s2 = s;
        boolean flag = false;
        String s4 = "";
        try {
            FileInputStream fileinputstream = new FileInputStream(s2);
            BufferedInputStream bufferedinputstream = new BufferedInputStream(fileinputstream);
            DataInputStream datainputstream = new DataInputStream(bufferedinputstream);
            String s3;
            while ((s3 = datainputstream.readLine()) != null)
                s4 = s4 + s3 + "\n";
            fileinputstream.close();
            bufferedinputstream.close();
            datainputstream.close();
            if (s4.length() > 3)
                if (s1 == "emails")
                    usersTArea.setText(s4);
                else
                    htmlTArea.setText(s4);
        } catch (IOException ioexception) {
            System.out.println(ioexception.toString());
        }
    }

    public void saveFile(String s, String s1) {
        String s2 = s;
        Frame frame = new Frame();
        filedialog = new FileDialog(frame, "Save", 1);
        filedialog.setDirectory("bin" + fileSeparator + "mmailer" + fileSeparator + s1);
        filedialog.show();
        String s3 = filedialog.getFile();
        String s4 = filedialog.getDirectory() + filedialog.getFile();
        File file = new File(s4);
        writeFile(s4, s2);
    }

    public void writeFile(String s, String s1) {
        if (s.length() < 1)
            return;
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(fileoutputstream);
            DataOutputStream dataoutputstream = new DataOutputStream(bufferedoutputstream);
            dataoutputstream.writeBytes(s1);
            dataoutputstream.flush();
            dataoutputstream.close();
            fileoutputstream.close();
            bufferedoutputstream.close();
        } catch (IOException ioexception) {
            new MMMessdialog(this, "Error in saving file " + s + ".txt");
        }
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void saveInvalidEmails(String s) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("cbox/mmailer/logs/errors.txt", true);
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(s);
            printstream.close();
            fileoutputstream.close();
        } catch (Exception exception) {
            System.out.println(exception);
            new MMMessdialog(this, "Cant save email errors to file");
            return;
        }
    }

    public void windowIconified(WindowEvent windowevent) {
    }

    public void WindowSetResizable(boolean flag) {
    }

    public void windowDeiconified(WindowEvent windowevent) {
    }

    public void windowActivated(WindowEvent windowevent) {
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyPressed(KeyEvent keyevent) {
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void windowDeactivated(WindowEvent windowevent) {
    }

    public static void main(String args[]) {
    }

    public void append(String s) {
        statMMTArea.appendText(s + "\n");
    }

    public void getIP() {
        String s = "";
        try {
            URL url = new URL(ipScriptPref);
            URLConnection urlconnection = url.openConnection();
            urlconnection.setDoOutput(true);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            String s1;
            while ((s1 = bufferedreader.readLine()) != null)
                s = s + s1;
            bufferedreader.close();
            mmFromField.setText(mmFromField.getText() + s);
            return;
        } catch (UnknownHostException unknownhostexception) {
            new MMMessdialog(this, mmlangstrings.getProperty("text001"));
        } catch (IOException ioexception) {
            new MMMessdialog(this, mmlangstrings.getProperty("text001"));
        }
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

    public void showUserFile(String s) {
        try {
            FileInputStream fileinputstream = new FileInputStream(s);
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            while ((s = datainputstream.readLine()) != null)
                statMMTArea.appendText("\n" + s + "\n");
            datainputstream.close();
            fileinputstream.close();
            return;
        } catch (Exception exception) {
            statMMTArea.appendText("\n" + exception.toString());
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

    public void dalert(String s) {
        new MMMessdialog(this, s);
    }

    public void mmAlert() {
        AudioPlayer audioplayer = AudioPlayer.player;
        if (mmMaxed) {
            setLocation(20, 20);
            resize(400, 580);
            mmMaxed = false;
            try {
                AudioStream audiostream = new AudioStream(new FileInputStream("sound_yahoo.au"));
                audioplayer.start(audiostream);
                return;
            } catch (IOException ioexception) {
                statMMTArea.appendText("\naudio alert error");
            }
        }
    }

    public MiniMailer(adminApp adminapp) {
        super(adminapp, "MiniMailer");
        parent = adminapp;
        loadMMlangStrings();
        getContentPane().setLayout(new BorderLayout());
        fileSeparator = System.getProperty("file.separator");
        isMMpinging = false;
        pingMMOn = false;
        mmMailDelay = 5000;
        reginfo = "reg";
        mmMaxed = false;
        useproxyMMPref = "";
        proxyhostMMPref = "";
        proxyportMMPref = "";
        emailErrors = 0;
        ipScriptPref = "";
        smtpServPref = "";
        servPortPref = 25;
        esubjectPref = "";
        emailFromPref = "";
        batterUp = "";
        currentUsers = "";
        usersCount = 0;
        presentUser = 0;
        mmtracker = new MediaTracker(this);
        img1 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmsaveemails.gif");
        img2 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmsavehtml.gif");
        img3 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmlogin.gif");
        img4 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmlogoff.gif");
        img5 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmopenemails.gif");
        img6 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmopenhtml.gif");
        img7 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmminmax.gif");
        img8 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmgohome.gif");
        img9 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmicon.gif");
        img10 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmprefs.gif");
        img11 = Toolkit.getDefaultToolkit().getImage("cbox/images/mmhelp.gif");
        mmtracker.addImage(img1, 1);
        mmtracker.addImage(img2, 2);
        mmtracker.addImage(img3, 3);
        mmtracker.addImage(img4, 4);
        mmtracker.addImage(img5, 5);
        mmtracker.addImage(img6, 6);
        mmtracker.addImage(img7, 7);
        mmtracker.addImage(img8, 8);
        mmtracker.addImage(img9, 9);
        mmtracker.addImage(img10, 10);
        mmtracker.addImage(img11, 11);
        try {
            mmtracker.waitForAll();
        } catch (InterruptedException interruptedexception) {
        }
        btnMMSaveEmails = new MMImageButton("MMSaveemails", img1);
        btnMMSaveHtml = new MMImageButton("mmstop", img2);
        btnMMConnect = new MMImageButton("MMConect", img3);
        btnMMDisconct = new MMImageButton("MMDisconnect", img4);
        btnMMOpenEmails = new MMImageButton("mmfavs", img5);
        btnMMOpenHtml = new MMImageButton("mmbrow launch", img6);
        btnMMminmax = new MMImageButton("mmminmax", img7);
        btnMMGoHome = new MMImageButton("mmGo Home", img8);
        btnMMprefs = new MMImageButton("mmPrefs", img10);
        btnMMhelp = new MMImageButton("mmPrefs", img11);
        delayMMLabel = new JLabel(" Email Delay: ");
        delayMMChoice = new Choice();
        delayMMChoice.addItem("5");
        delayMMChoice.addItem("10");
        delayMMChoice.addItem("15");
        delayMMChoice.addItem("20");
        delayMMChoice.addItem("25");
        delayMMChoice.addItem("30");
        delayMMChoice.addItem("45");
        delayMMChoice.addItem("60");
        delayMMChoice.addItem("90");
        delayMMChoice.addItem("120");
        delayMMChoice.addItem("150");
        delayMMChoice.addItem("300");
        delayMMChoice.addItem("180");
        userTAreaLabel = new JLabel(" Mail List: ");
        htmlLabel = new JLabel(" HTML: ");
        mmstatusLabel = new JLabel(" Status: ");
        smtpServLabel = new JLabel(" smtpServer: ");
        smtpServField = new JTextField("");
        smtpPortLabel = new JLabel("   smtpPort: ");
        smtpPortField = new JTextField("25", 4);
        mmSubjectLabel = new JLabel(" Subject: ");
        mmSubjectField = new JTextField("       ");
        mmFromLabel = new JLabel(" From: ");
        mmFromField = new JTextField(" ");
        JPanel jpanel = new JPanel(new BorderLayout());
        jpanel.add("West", smtpServLabel);
        jpanel.add("Center", smtpServField);
        JPanel jpanel1 = new JPanel(new BorderLayout());
        jpanel1.add("West", smtpPortLabel);
        jpanel1.add("Center", smtpPortField);
        JPanel jpanel2 = new JPanel(new BorderLayout());
        jpanel2.add("West", mmSubjectLabel);
        jpanel2.add("Center", mmSubjectField);
        JPanel jpanel3 = new JPanel(new BorderLayout());
        jpanel3.add("West", delayMMLabel);
        jpanel3.add("Center", delayMMChoice);
        JPanel jpanel4 = new JPanel(new BorderLayout());
        jpanel4.add("West", mmFromLabel);
        jpanel4.add("Center", mmFromField);
        jpanel4.add("East", jpanel3);
        JPanel jpanel5 = new JPanel(new BorderLayout());
        jpanel5.add("East", jpanel1);
        jpanel5.add("Center", jpanel);
        JPanel jpanel6 = new JPanel(new GridLayout(3, 0, 3, 3));
        jpanel6.add(jpanel5);
        jpanel6.add(jpanel4);
        jpanel6.add(jpanel2);
        mmLabel = new JLabel(mmlangstrings.getProperty("text002") + "                   ", 2);
        mmtiplabel = new JLabel("Disconnected            ");
        mmtiplabel.setForeground(new Color(255, 0, 0));
        mmbtnPanel = new JPanel(new GridLayout(0, 5));
        mmbtnPanel.add(btnMMDisconct);
        mmbtnPanel.add(btnMMConnect);
        mmbtnPanel.add(btnMMGoHome);
        mmbtnPanel.add(btnMMhelp);
        mmbtnPanel.add(btnMMminmax);
        statMMTArea = new TextArea("", 2, 100, 1);
        statMMTArea.setBackground(new Color(0, 0, 0));
        statMMTArea.setForeground(new Color(255, 225, 10));
        usersTArea = new TextArea("", 10, 100, 1);
        htmlTArea = new TextArea("");
        JPanel jpanel7 = new JPanel(new BorderLayout());
        jpanel7.add("West", btnMMOpenEmails);
        jpanel7.add("Center", btnMMSaveEmails);
        JPanel jpanel8 = new JPanel(new BorderLayout());
        jpanel8.add("West", jpanel7);
        jpanel8.add("Center", userTAreaLabel);
        JPanel jpanel9 = new JPanel(new BorderLayout());
        jpanel9.add("North", jpanel8);
        jpanel9.add("Center", usersTArea);
        JPanel jpanel10 = new JPanel(new BorderLayout());
        jpanel10.add("West", btnMMOpenHtml);
        jpanel10.add("Center", btnMMSaveHtml);
        JPanel jpanel11 = new JPanel(new BorderLayout());
        jpanel11.add("West", jpanel10);
        jpanel11.add("Center", htmlLabel);
        JPanel jpanel12 = new JPanel(new BorderLayout());
        jpanel12.add("North", jpanel11);
        jpanel12.add("Center", htmlTArea);
        JPanel jpanel13 = new JPanel(new BorderLayout());
        jpanel13.add("North", mmstatusLabel);
        jpanel13.add("Center", statMMTArea);
        JPanel jpanel14 = new JPanel(new BorderLayout());
        jpanel14.add("North", jpanel9);
        jpanel14.add("Center", jpanel12);
        jpanel14.add("South", jpanel13);
        JPanel jpanel15 = new JPanel(new BorderLayout());
        jpanel15.add("North", jpanel6);
        JPanel jpanel16 = new JPanel(new BorderLayout());
        jpanel16.add("West", mmbtnPanel);
        jpanel16.add("Center", mmtiplabel);
        jpanel16.add("South", jpanel15);
        JPanel jpanel17 = new JPanel(new BorderLayout());
        jpanel17.add("North", jpanel16);
        jpanel17.add("Center", jpanel14);
        getContentPane().add(jpanel17, "Center");
        delayMMChoice.addItemListener(this);
        mmSubjectField.addActionListener(this);
        btnMMSaveEmails.addMouseListener(this);
        btnMMSaveEmails.addActionListener(this);
        btnMMSaveHtml.addMouseListener(this);
        btnMMSaveHtml.addActionListener(this);
        btnMMConnect.addMouseListener(this);
        btnMMConnect.addActionListener(this);
        btnMMDisconct.addMouseListener(this);
        btnMMDisconct.addActionListener(this);
        btnMMOpenEmails.addMouseListener(this);
        btnMMOpenEmails.addActionListener(this);
        btnMMOpenHtml.addMouseListener(this);
        btnMMOpenHtml.addActionListener(this);
        btnMMminmax.addMouseListener(this);
        btnMMminmax.addActionListener(this);
        btnMMGoHome.addMouseListener(this);
        btnMMGoHome.addActionListener(this);
        btnMMprefs.addMouseListener(this);
        btnMMprefs.addActionListener(this);
        btnMMhelp.addMouseListener(this);
        btnMMhelp.addActionListener(this);
        addWindowListener(new MiniMailerWindowListener());
        setLocation(5, 5);
        setBounds(10, 10, 350, 570);
        setVisible(true);
        loadMMprefs();
    }

    public void loadMMprefs() {
        try {
            ipScriptPref = parent.mmipScript;
            smtpServPref = parent.mmsmtpServer;
            String s = parent.mmserverPort;
            esubjectPref = parent.mmemailSubject;
            emailFromPref = parent.mmemailFrom;
            servPortPref = Integer.parseInt(s);
            statMMTArea.appendText("\nLoaded loadMMprefs");
            mmSubjectField.setText(esubjectPref);
            mmFromField.setText(emailFromPref);
            smtpPortField.setText(Integer.toString(servPortPref));
            smtpServField.setText(smtpServPref);
        } catch (Exception exception) {
            statMMTArea.setText("loadnewPref Ex" + exception.toString());
        }
    }

    public void loadMMlangStrings() {
        mmlangstrings = new Properties();
        try {
            FileInputStream fileinputstream = new FileInputStream("cbox/props/MMBundle.props");
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            mmlangstrings.load(fileinputstream);
            System.out.println("lang loaded");
            fileinputstream.close();
            datainputstream.close();
            return;
        } catch (Exception exception) {
            new MMMessdialog(this, "Error loading bin/props/MMBundle.props");
        }
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == mmSubjectField)
            statMMTArea.appendText("\nmmSubjectField actionPerformed");
    }

    public void mouseEntered(MouseEvent mouseevent) {
        java.awt.Component component = mouseevent.getComponent();
        if (component == btnMMConnect)
            if (!isMMpinging) {
                setCursor(Cursor.getPredefinedCursor(12));
                mmtiplabel.setText(mmlangstrings.getProperty("text003"));
                return;
            } else {
                setCursor(Cursor.getPredefinedCursor(12));
                mmtiplabel.setText(mmlangstrings.getProperty("text004"));
                return;
            }
        if (component == btnMMDisconct)
            if (!isMMpinging) {
                setCursor(Cursor.getPredefinedCursor(12));
                mmtiplabel.setText("Disconnected");
                return;
            } else {
                setCursor(Cursor.getPredefinedCursor(12));
                mmtiplabel.setText(mmlangstrings.getProperty("text005"));
                return;
            }
        if (component == btnMMSaveEmails) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText("Save Email List");
            return;
        }
        if (component == btnMMOpenEmails) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText("Open Email List");
            return;
        }
        if (component == btnMMSaveHtml) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText("Save Email Html");
            return;
        }
        if (component == btnMMOpenHtml) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText("Open Html Email");
            return;
        }
        if (component == btnMMprefs) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText(mmlangstrings.getProperty("text022"));
            return;
        }
        if (mouseevent.getSource() == btnMMhelp) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText(mmlangstrings.getProperty("text023"));
            return;
        }
        if (component == btnMMminmax)
            if (!mmMaxed) {
                setCursor(Cursor.getPredefinedCursor(12));
                mmtiplabel.setText(mmlangstrings.getProperty("text006"));
                return;
            } else {
                setCursor(Cursor.getPredefinedCursor(12));
                mmtiplabel.setText(mmlangstrings.getProperty("text007"));
                return;
            }
        if (component == btnMMGoHome) {
            setCursor(Cursor.getPredefinedCursor(12));
            mmtiplabel.setText("Get Your IP");
            return;
        } else {
            return;
        }
    }

    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == btnMMSaveHtml)
            if (htmlTArea.getText().length() < 5)
                new MMMessdialog(this, mmlangstrings.getProperty("text008"));
            else
                saveFile(htmlTArea.getText(), "mailings");
        if (mouseevent.getSource() == btnMMSaveEmails) {
            if (isMMpinging) {
                new MMMessdialog(this, mmlangstrings.getProperty("text009"));
                return;
            }
            if (usersTArea.getText().length() < 3)
                new MMMessdialog(this, mmlangstrings.getProperty("text010"));
            else
                saveFile(usersTArea.getText(), "emails");
        }
        if (mouseevent.getSource() == btnMMOpenHtml)
            readFile("mailings");
        if (mouseevent.getSource() == btnMMprefs)
            new AAPrefsdialog(parent, "noproxy");
        if (mouseevent.getSource() == btnMMhelp)
            BrowserControl.displayURL("http://www.profitcode.com");
        if (mouseevent.getSource() == btnMMOpenEmails)
            if (isMMpinging)
                new MMMessdialog(this, mmlangstrings.getProperty("text011"));
            else
                readFile("emails");
        if (mouseevent.getSource() == btnMMGoHome)
            getIP();
        if (mouseevent.getSource() == btnMMConnect)
            if (isMMpinging)
                new MMMessdialog(this, mmlangstrings.getProperty("text012"));
            else
                mmStart();
        if (mouseevent.getSource() == btnMMDisconct)
            if (isMMpinging)
                setMMPing(false);
            else
                new MMMessdialog(this, mmlangstrings.getProperty("text013"));
        if (mouseevent.getSource() == btnMMminmax)
            isLHminmax();
    }

    public void mmStop() {
    }

    public void mmStart() {
        smtpServPref = smtpServField.getText();
        servPortPref = Integer.parseInt(smtpPortField.getText());
        emailFromPref = mmFromField.getText();
        esubjectPref = mmSubjectField.getText();
        if (usersTArea.getText().length() < 2) {
            dalert("Copy some emails to the Mail List");
            return;
        }
        if (htmlTArea.getText().length() < 2) {
            dalert("Copy your HTML email to the HTML Area");
            return;
        } else {
            formatEmail();
            getUserCount();
            setMMPing(true);
            return;
        }
    }

    public void setMMPing(boolean flag) {
        isMMpinging = flag;
        if (flag) {
            setCursor(Cursor.getPredefinedCursor(3));
            mmThread = new Thread(this);
            mmThread.start();
            setCursor(Cursor.getPredefinedCursor(0));
            return;
        }
        if (mmThread != null)
            mmThread.stop();
        mmThread = null;
        isMMpinging = false;
        usersCount = 0;
    }

    public void run() {
        do
            try {
                Thread.sleep(mmMailDelay);
                checkMMPing();
            } catch (InterruptedException interruptedexception) {
                new MMMessdialog(this, interruptedexception.toString());
            }
        while (true);
    }

    public void checkMMPing() {
        if (pingMMOn) {
            new MMMessdialog(this, "Sending Mail.");
            return;
        } else {
            pingNextMM();
            return;
        }
    }

    public void pingNextMM() {
        if (presentUser >= usersCount) {
            presentUser = 0;
            dalert("All emails sent");
            mmAlert();
            setMMPing(false);
            return;
        } else {
            batterUp = usersArray[presentUser];
            sendMail(batterUp);
            return;
        }
    }

    public void getUserCount() {
        currentUsers = usersTArea.getText();
        if (currentUsers.length() < 1) {
            dalert("Copy some emails to the Mail List");
            return;
        }
        usersArray = readmessTokens(currentUsers, "\n");
        usersCount = usersArray.length;
        append("User Count: " + Integer.toString(usersCount));
        if (usersCount > 1000) {
            dalert("Max emails is 1000. Try again");
            usersTArea.setText("");
        }
    }

    public void itemStateChanged(ItemEvent itemevent) {
        Object obj = itemevent.getSource();
        if (obj == delayMMChoice)
            mmMailDelay = Integer.parseInt(delayMMChoice.getSelectedItem() + "000");
    }

    public void isLHminmax() {
        if (!mmMaxed) {
            setLocation(500, 3);
            resize(280, 51);
            mmMaxed = true;
            return;
        }
        if (mmMaxed) {
            setLocation(5, 5);
            resize(350, 570);
            mmMaxed = false;
            return;
        } else {
            return;
        }
    }

    public String getMMStatString() {
        String s1 = delayMMChoice.getSelectedItem();
        String s;
        if (isMMpinging)
            s = "Connected  Ping: " + s1;
        else
            s = "Disconnected ";
        return s;
    }

    public void mouseExited(MouseEvent mouseevent) {
        setCursor(Cursor.getPredefinedCursor(0));
        if (isMMpinging)
            mmtiplabel.setText("C: " + Integer.toString(presentUser) + "   T: " + Integer.toString(usersCount) + "   E: " + Integer.toString(emailErrors) + "   Png: " + delayMMChoice.getSelectedItem());
        else
            mmtiplabel.setText("Disconnected ");
    }

    public void sendMail(String s) {
        append("Sending to: " + s);
        String s1 = s + "\n";
        String s2 = "";
        int i = 0;
        try {
            String s3 = replaceString(cleanHTMLmail, "*#EMAIL#*", s);
            Socket socket = new Socket(smtpServPref, servPortPref);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "8859_1"));
            BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "8859_1"));
            String s4 = "DataSeparatorString";
            sendln(bufferedreader, bufferedwriter, "HELO world");
            sendln(bufferedreader, bufferedwriter, "MAIL FROM: <" + emailFromPref + ">");
            sendln(bufferedreader, bufferedwriter, "RCPT TO: <" + s + ">");
            sendln(bufferedreader, bufferedwriter, "DATA");
            for (; i < 1500; i++) ;
            i = 0;
            sendln(bufferedwriter, "MIME-Version: 1.0");
            sendln(bufferedwriter, "Subject: " + esubjectPref);
            sendln(bufferedwriter, "From: mailserv <" + emailFromPref + ">");
            sendln(bufferedwriter, "Content-Type: text/html; charset=\"iso-8859-1\"\r\n");
            sendln(bufferedwriter, s3);
            sendln(bufferedwriter, "\r\n");
            sendln(bufferedreader, bufferedwriter, "\r\n.\r\n");
            for (; i < 1500; i++) ;
            i = 0;
            sendln(bufferedreader, bufferedwriter, "QUIT");
            for (; i < 1500; i++) ;
            i = 0;
            bufferedreader.close();
            bufferedwriter.close();
            socket.close();
            String s5 = replaceString(usersTArea.getText(), s1, "");
            usersTArea.setText(s5);
            presentUser = presentUser + 1;
            mmtiplabel.setText("C: " + Integer.toString(presentUser) + "  T: " + Integer.toString(usersCount) + "  E: " + Integer.toString(emailErrors));
        } catch (Exception exception) {
            append(exception.toString());
        }
    }

    public void sendln(BufferedReader bufferedreader, BufferedWriter bufferedwriter, String s) {
        try {
            bufferedwriter.write(s + "\r\n");
            bufferedwriter.flush();
            s = bufferedreader.readLine();
            append(s);
            if (s.indexOf("550") != -1) {
                saveInvalidEmails(s);
                emailErrors = emailErrors + 1;
            }
        } catch (Exception exception) {
            append(exception.toString());
        }
    }

    public void sendln(BufferedWriter bufferedwriter, String s) {
        try {
            bufferedwriter.write(s + "\r\n");
            bufferedwriter.flush();
        } catch (Exception exception) {
            append(exception.toString());
        }
    }

    public void formatEmail() {
        htmlmail = htmlTArea.getText();
        cleanHTMLmail = replaceString(htmlmail, "\n", "<br>");
        cleanHTMLmail = replaceString(htmlmail, "\r", "<br>");
    }

    String htmlmail;
    String cleanHTMLmail;
    boolean mmMaxed;
    int mmMailDelay;
    boolean pingMMOn;
    Thread mmThread;
    boolean isMMpinging;
    TextField mmStatus;
    Image img1;
    Image img2;
    Image img3;
    Image img4;
    Image img5;
    Image img6;
    Image img7;
    Image img8;
    Image img9;
    Image img10;
    Image img11;
    MMImageButton btnMMSaveEmails;
    MMImageButton btnMMSaveHtml;
    MMImageButton btnMMConnect;
    MMImageButton btnMMDisconct;
    MMImageButton btnMMOpenHtml;
    MMImageButton btnMMminmax;
    MMImageButton btnMMOpenEmails;
    MMImageButton btnMMGoHome;
    MMImageButton btnMMprefs;
    MMImageButton btnMMhelp;
    MediaTracker mmtracker;
    JLabel mmLabel;
    Properties mmlangstrings;
    Properties mmconfig;
    Choice delayMMChoice;
    String reginfo;
    TextArea statMMTArea;
    TextArea usersTArea;
    TextArea htmlTArea;
    JLabel delayMMLabel;
    JLabel mmstatusLabel;
    JPanel mmbtnPanel;
    JLabel mmtiplabel;
    JLabel smtpServLabel;
    JTextField smtpServField;
    JLabel smtpPortLabel;
    JTextField smtpPortField;
    JLabel mmSubjectLabel;
    JTextField mmSubjectField;
    JLabel mmFromLabel;
    JTextField mmFromField;
    FileDialog filedialog;
    JLabel userTAreaLabel;
    JLabel htmlLabel;
    String ipScriptPref;
    String smtpServPref;
    String fileSeparator;
    int servPortPref;
    String esubjectPref;
    String emailFromPref;
    int emailErrors;
    String batterUp;
    String currentUsers;
    int usersCount;
    int presentUser;
    String usersArray[];
    String useproxyMMPref;
    String proxyhostMMPref;
    String proxyportMMPref;
    adminApp parent;
}
