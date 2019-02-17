package njfbrowser.misc;


import njfbrowser.main.adminApp;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;


public class getCatResPinger extends JDialog
        implements Runnable, ItemListener, ActionListener {


    private class ExitWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            setVisible(false);
            // WindowsTrayIcon.cleanUp();
            // System.exit(0);
        }

        ExitWindowListener() {
        }
    }


    public void aboutpop() {
    }

    public void setHstatus(String s, boolean flag) {
        // helpAlertDlog helpalertdlog = new helpAlertDlog(s, this);
        // helpalertdlog = null;
    }

    public static void centerDialog(Window window) {
        Dimension dimension = window.getSize();
        Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dimension1.width / 2 - dimension.width / 2, dimension1.height / 2 - dimension.height / 2);
    }

    public void doExit() {
        System.out.println("Exit selected.");

        System.exit(0);
    }

    public getCatResPinger(adminApp aebayapp)

    {

        super(aebayapp, "Task Pinger", false);
        parent = aebayapp;

        verinfo = "3.1.13";
        x = 0;
        oktorun = false;
        delay = 2000;
        oktorun = false;
        mplayerOn = false;
        pingHelpOn = false;
        rcount = 0;
        rpings = 4;
        pingFileUrl = "";
        setBackground(SystemColor.control);
        label = new JLabel("Ping Delay: ");
        new JLabel("Disconnected");

        setLayout(new BorderLayout(2, 2));


        delayChoice = new java.awt.Choice();
        delayChoice.addItem("5");
        delayChoice.addItem("1");
        delayChoice.addItem("2");
        delayChoice.addItem("3");
        delayChoice.addItem("4");
        delayChoice.addItem("6");
        delayChoice.addItem("7");
        delayChoice.addItem("8");
        delayChoice.addItem("9");
        delayChoice.addItem("10");
        delayChoice.addItem("11");
        delayChoice.addItem("12");
        delayChoice.addItem("13");
        delayChoice.addItem("14");
        delayChoice.addItem("15");
        delayChoice.addItem("20");
        delayChoice.addItem("25");
        delayChoice.addItem("30");
        delayChoice.addItem("45");
        delayChoice.addItem("60");
        delayChoice.addItem("90");
        delayChoice.addItem("120");
        delayChoice.addItem("150");
        delayChoice.addItem("300");
        delayChoice.addItem("180");

        taskTitleLabel = new JLabel(" Title: ");
        taskTitleChoice = new java.awt.Choice();
        delTaskTitle_bt = new JButton("Delete");
        JPanel taskTitlePan = new JPanel(new BorderLayout(2, 2));
        taskTitlePan.add(taskTitleLabel, BorderLayout.WEST);
        taskTitlePan.add(taskTitleChoice, BorderLayout.CENTER);
        taskTitlePan.add(delTaskTitle_bt, BorderLayout.EAST);


        taskLabel = new JLabel("Current Task Vars: ");
        taskChoice = new java.awt.Choice();
        delTaskChoice_bt = new JButton("Delete");
        JPanel taskChoicePan = new JPanel(new BorderLayout(2, 2));
        taskChoicePan.add(taskLabel, BorderLayout.WEST);
        taskChoicePan.add(taskChoice, BorderLayout.CENTER);
        taskChoicePan.add(delTaskChoice_bt, BorderLayout.EAST);


        addTaskVarsLabel = new JLabel("Add Task Vars:");
        addTaskVarsTField = new JTextField("?vars=these");
        addTaskVars_bt = new JButton("Add Vars");
        JPanel addTaskVarsPan = new JPanel(new BorderLayout(2, 2));
        addTaskVarsPan.add(addTaskVarsLabel, BorderLayout.WEST);
        addTaskVarsPan.add(addTaskVarsTField, BorderLayout.CENTER);
        addTaskVarsPan.add(addTaskVars_bt, BorderLayout.EAST);


        addTaskBoxLabel = new JLabel(" Add Task");

        addTaskTitleTFLabel = new JLabel(" Title: ");
        addTaskUrlTFLabel = new JLabel(" Url: ");

        addTaskUnameTFLabel = new JLabel(" Username: ");
        addTaskUpassTFLabel = new JLabel(" Password: ");

        addTaskTitleTField = new JTextField("Task Url Title");
        addTaskUrlTField = new JTextField("http://");
        addTaskUnameTField = new JTextField("taskUname ");
        addTaskUpassTField = new JTextField("taskPassword ");
        addTaskBox_bt = new JButton("Add Task Url");
        editTaskBox_bt = new JButton("Edit Task Url");
        hide_bt = new JButton("Hide");


        JPanel taskBoxTitlePan = new JPanel(new BorderLayout(2, 2));
        taskBoxTitlePan.add(addTaskTitleTFLabel, BorderLayout.WEST);
        taskBoxTitlePan.add(addTaskTitleTField, BorderLayout.CENTER);

        JPanel taskBoxUrlPan = new JPanel(new BorderLayout(2, 2));
        taskBoxUrlPan.add(addTaskUrlTFLabel, BorderLayout.WEST);
        taskBoxUrlPan.add(addTaskUrlTField, BorderLayout.CENTER);

        JPanel taskBoxUnamePan = new JPanel(new BorderLayout(2, 2));
        taskBoxUnamePan.add(addTaskUnameTFLabel, BorderLayout.WEST);
        taskBoxUnamePan.add(addTaskUnameTField, BorderLayout.CENTER);

        JPanel taskBoxPassPan = new JPanel(new BorderLayout(2, 2));
        taskBoxPassPan.add(addTaskUpassTFLabel, BorderLayout.WEST);
        taskBoxPassPan.add(addTaskUpassTField, BorderLayout.CENTER);

        JPanel taskBoxMainPan = new JPanel(new GridLayout(5, 1));
        taskBoxMainPan.add(addTaskBoxLabel);
        taskBoxMainPan.add(taskBoxTitlePan);
        taskBoxMainPan.add(taskBoxUrlPan);
        taskBoxMainPan.add(taskBoxUnamePan);
        taskBoxMainPan.add(taskBoxPassPan);

        JPanel taskBoxButtonPan = new JPanel(new GridLayout(1, 2));
        taskBoxButtonPan.add(addTaskBox_bt);
        taskBoxButtonPan.add(editTaskBox_bt); 
     
/*
        taskChoice.addItem("Select Task");
        taskChoice.addItem("getCatResults");
        taskChoice.addItem("getOpenCatTasks");
        taskChoice.addItem("setOpenTasks");
        taskChoice.addItem("getFullRefresh");
        taskChoice.addItem("getFolderRefresh");
        taskChoice.addItem("getPageRefresh");
        taskChoice.addItem("getIncPageRefresh");
        taskChoice.addItem("getCatIndexRefresh");
*/


        taskTextArea = new JTextArea("");
        taskTextArea.setEditable(true);
        taskTextArea.setLineWrap(true);
        taskTextArea.setWrapStyleWord(true);
        taskTextArea.setMargin(new Insets(5, 5, 5, 5));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));
        panel.add(login_bt = new JButton("Start"));
        panel.add(logout_bt = new JButton("Stop"));
        panel.add(pingHelp_bt = new JButton("Clear"));
        panel.add(hide_bt);


        JPanel taskTopPan = new JPanel(new GridLayout(3, 1, 3, 3));
        taskTopPan.add(taskTitlePan);
        taskTopPan.add(taskChoicePan);
        taskTopPan.add(addTaskVarsPan);


        JPanel taskPanel = new JPanel(new BorderLayout(2, 2));
        taskPanel.add(panel, BorderLayout.NORTH);

        taskPanel.add(taskTopPan, BorderLayout.CENTER);


        taskPanel.add(new JScrollPane(taskTextArea) {
                          private static final long serialVersionUID = 3978426918603075632L;

                          public Dimension getPreferredSize() {
                              Dimension size = getCatResPinger.this.getSize();
                              return new Dimension(size.width, size.height / 4);
                          }

                          public Dimension getMinimumSize() {
                              return new Dimension(100, 60);
                          }
                      },
                BorderLayout.SOUTH);


        // taskPanel.add(taskTextArea,);


        JPanel botbutpan = new JPanel(new BorderLayout(1, 4));
        botbutpan.add(taskBoxMainPan, BorderLayout.CENTER);
        botbutpan.add(taskBoxButtonPan, BorderLayout.SOUTH);


        logout_bt.setEnabled(false);

        JPanel panel1 = new JPanel(new BorderLayout(2, 2));
        JPanel panel2 = new JPanel(new BorderLayout(2, 2));
        panel2.add("Center", label);
        panel2.add("East", delayChoice);

        panel1.add("North", panel2);
        panel1.add("Center", taskPanel);
        panel1.add("South", botbutpan);


        hide_bt.addActionListener(this);
        login_bt.addActionListener(this);
        logout_bt.addActionListener(this);
        pingHelp_bt.addActionListener(this);
        delTaskTitle_bt.addActionListener(this);
        delTaskChoice_bt.addActionListener(this);
        addTaskVars_bt.addActionListener(this);
        addTaskBox_bt.addActionListener(this);
        editTaskBox_bt.addActionListener(this);

        delayChoice.addItemListener(this);
        taskTitleChoice.addItemListener(this);

        add(panel1);
        pack();
        validate();
        addWindowListener(new ExitWindowListener());

        setNewDelay();
        setResizable(true);
        resize(580, 400);
        setVisible(false);
        loadTaskTitles(true);
        // show();
    }

    public void itemStateChanged(ItemEvent itemevent) {
        if (itemevent.getSource() == taskChoice) {
            addTaskVarsTField.setText(taskChoice.getSelectedItem());
        }
        if (itemevent.getSource() == taskTitleChoice) {
            loadTaskProps(taskTitleChoice.getSelectedItem());
        }
        if (itemevent.getSource() == delayChoice) {
            setNewDelay();
        }
    }

    public void helpalert() {
        pingHelp_bt.setBackground(new Color(0, 205, 0));
        setVisible(true);
        requestFocus();
        AudioPlayer audioplayer = AudioPlayer.player;
        try {
            AudioStream audiostream = new AudioStream(new FileInputStream("bin/misc/sound_yahoo.au"));
            audioplayer.start(audiostream);
            return;
        } catch (IOException _ex) {
            setHstatus("NoAudio: " + _ex.toString(), false);
            System.out.println("no go");
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

    public void pingHelp() {
        Authenticator.setDefault(new MyTaskAuthenticator());
        String selectedTask = taskChoice.getSelectedItem();
        pingHelp_bt.setBackground(new Color(205, 205, 205));
        label.setBackground(new Color(0, 205, 0));
        pingHelpOn = true;
        String s = pingFileUrl + selectedTask;

        taskTextArea.append("Connecting to:\n" + s + "\n");
        String s1 = "";
        // System.out.println("Before the try"); 

        try {

            URL url = new URL(s);
            java.io.InputStream inputstream = url.openStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            String s2;
            while ((s2 = bufferedreader.readLine()) != null)
                s1 = s1 + s2;
        } catch (Exception exception) {
            System.out.println(exception);
            taskTextArea.append("\n\nFatal Error: " + exception.toString());
            pingHelpOn = false;
            dodm(false);
            label.setBackground(new Color(205, 205, 205));
            return;
        }
        label.setBackground(new Color(205, 205, 205));

        if (s1.length() > 3) {


            if (s1.indexOf("Fatal Error") != -1) {
                taskTextArea.append("\n" + s1);
                dodm(false);
            } else {

                taskTextArea.append("\n" + s1);
                pingHelpOn = false;
            }


        } else {
            taskTextArea.append("Fatal Error - No response from:\n" + s);
            pingHelpOn = false;
            dodm(false);
        }


        label.setBackground(new Color(205, 205, 205));
    }


    public static Image loadImage(String s) {
        return Toolkit.getDefaultToolkit().getImage("./bin/images" + File.separator + s);
    }


    public void setAllCsEnabled(String andenable) {
        if (andenable.equals("disable")) {
            login_bt.setEnabled(false);
            logout_bt.setEnabled(true);
            taskTitleChoice.disable();
            taskChoice.disable();
            delTaskTitle_bt.setEnabled(false);
            delTaskChoice_bt.setEnabled(false);
            addTaskVars_bt.setEnabled(false);
            addTaskBox_bt.setEnabled(false);
            editTaskBox_bt.setEnabled(false);
        } else {
            login_bt.setEnabled(true);
            logout_bt.setEnabled(false);
            taskTitleChoice.enable();
            taskChoice.enable();
            delTaskTitle_bt.setEnabled(true);
            delTaskChoice_bt.setEnabled(true);
            addTaskVars_bt.setEnabled(true);
            addTaskBox_bt.setEnabled(true);
            editTaskBox_bt.setEnabled(true);
        }
    }

    public void dodm(boolean flag) {

        oktorun = flag;
        if (flag) {
            System.out.println("Starting");
            setCursor(Cursor.getPredefinedCursor(3));
            setAllCsEnabled("disable");
            // login_bt.setEnabled(false);
            // logout_bt.setEnabled(true);
            dm = new Thread(this);
            dm.start();
            setCursor(Cursor.getPredefinedCursor(0));
        } else {
            setAllCsEnabled("enable");
            System.out.println("Stopping");
            // login_bt.setEnabled(true);
            // logout_bt.setEnabled(false);
            if (dm != null)
                dm.stop();
            dm = null;
        }
    }


    public void launchurl() {
//System.out.println("heheheh");
        String burlstring = helpbox + "helpbox.php?nick=" + shophelpnick + "&uname=" + taskUname + "&chat=LoggedIn";
        String jscripturlstring = "javascript:window.open('" + burlstring + "','newWin','scrollbars=no,status=no,width=350,height=480,left=230,top=35');";

// BrowserControl.displayURL(helpbox + "helpbox.php?nick=" + shophelpnick + "&uname=" + taskUname + "&chat=LoggedIn");
// BrowserControl.displayURL(burlstring);

    }


    public void run() {
        do {
            try {
                do {
                    Thread.sleep(delay);
                    checkPing();
                } while (true);
            } catch (InterruptedException interruptedexception) {
                setHstatus(interruptedexception.toString(), true);
            }
            login_bt.setEnabled(true);
        } while (true);
    }

    public void checkPing() {

        if (pingHelpOn) {
            setHstatus("Pinging help.", false);
            return;
        } else {

            pingHelp();
            return;
        }
    }

    public void doHide(boolean flag) {
        // icon.setVisible(true);
        setVisible(flag);
    }


    public void regurl() {
        pingHelpOn = false;
        // BrowserControl.displayURL("http://www.javaworld.com");

    }


    public void liveHelpLogin() {
        Authenticator.setDefault(new MyTaskAuthenticator());
        String selectedTask = taskChoice.getSelectedItem();
        if (selectedTask.equals("Select Task")) {
            parent.setQstatus("Select Task To Run", true);
            return;
        }
        taskTextArea.setText("");
        String loginUrlString = pingFileUrl + selectedTask;


        label.setBackground(new Color(0, 205, 0));

        pingHelpOn = true;
        String s = loginUrlString;
        System.out.println(s);
        String s1 = "";
        System.out.println("Before the try");
        try {
            URL url = new URL(s);
            java.io.InputStream inputstream = url.openStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            String s2;
            while ((s2 = bufferedreader.readLine()) != null)
                s1 = s1 + s2;
        } catch (Exception exception) {
            System.out.println(exception);
            taskTextArea.append("\nError: " + exception.toString());
            pingHelpOn = false;
            dodm(false);
            label.setBackground(new Color(205, 205, 205));
            return;
        }
        label.setBackground(new Color(205, 205, 205));
        if (s1.length() > 3) {
            if (s1.indexOf("Fatal Error") != -1) {
                taskTextArea.append("\n" + s1);
                dodm(false);
            } else {
                taskTextArea.append("\n" + s1);
                pingHelpOn = false;
                dodm(true);
            }
        } else {
            taskTextArea.append("Fatal Error - No response from:\n" + s);
            pingHelpOn = false;
            dodm(false);
        }
    }


    public void setNewDelay() {
        delay = Integer.parseInt(delayChoice.getSelectedItem()) * 1000;
        System.out.println(delay);
    }


    public void actionPerformed(ActionEvent actionevent) {

        Object obj = actionevent.getSource();
        if (obj == hide_bt) {
            doHide(false);
        }
        if (obj == login_bt) {
            liveHelpLogin();
        }
        if (obj == logout_bt) {
            dodm(false);
        }
        if (obj == pingHelp_bt) {
            taskTextArea.setText("");
        }


        if (obj == delTaskTitle_bt) {
            deleteTaskTitle();
        }
        if (obj == delTaskChoice_bt) {
            saveTaskTools("delete", taskID);
        }
        if (obj == addTaskVars_bt) {
            saveTaskTools("itsadd", taskID);
        }
        if (obj == addTaskBox_bt) {
            //taskTextArea.setText("addTaskBox_bt");
            String theTaskIDString = adminApp.getTimeStamp();
            validateTaskProps("addurl", theTaskIDString);
        }
        if (obj == editTaskBox_bt) {
            //taskTextArea.setText("addTaskBox_bt");

            validateTaskProps("editurl", taskID);
        }
    }


    public void deleteTaskTitle() {
        String tTitleString = taskTitleChoice.getSelectedItem();

        int iTcount = taskTitleChoice.getItemCount();
        if (iTcount == 1) {
            parent.setQstatus(parent.aplangstrings.getProperty("text081"), false);
            return;
        } else {
            taskTitleChoice.remove(taskTitleChoice.getSelectedItem());
        }

        try {
            File delTitleToolsfile = new File(parent.getUfile("cbox/taskprops/tasktools/" + taskID + ".dat"));
            boolean taskToolToDelBool = delTitleToolsfile.delete();


            File delTitlefile = new File(parent.getUfile("cbox/taskprops/taskprefs/" + tTitleString + ".dat"));
            boolean taskTitleToDelBool = delTitlefile.delete();


        } catch (Exception ex) {
            parent.setQstatus("Error 655A [getCatResPinger]: \n" + ex.toString(), true);
        }

        loadTaskTitles(true);

    }


    public void loadTaskTitles(boolean justtasks) {
        taskTitleChoice.removeAll();
        File file;
        String list[];

        try {
            file = new File(parent.getUfile("cbox/taskprops/taskprefs/"));
            if (file.isDirectory()) {
                list = file.list();
                for (int i = 0; i < list.length; i++) {
                    String taskTString = parent.getFileToTitle(list[i]);

                    taskTitleChoice.addItem(taskTString);
                }

            }


        } catch (Exception ex) {
            parent.setQstatus("Error 554A [getCatResPinger]: \n" + ex.toString(), false);
            // System.out.println(ex.toString());
            return;
        }
        if (justtasks) {
            System.out.println("just the tasks");
            loadTaskProps(taskTitleChoice.getSelectedItem());
        } else {
            loadTaskProps(taskTitleChoice.getSelectedItem());
        }
    }

    /*
        public void sortMyTaskList(String s1)
        {
            int j = 0;
            for(int k = taskChoice.countItems() - 1; j < k;)
            {
                int l = (j + k) / 2;
                if(s1.toLowerCase().compareTo(taskChoice.getItem(l).toLowerCase()) > 0)
                    j = l + 1;
                else
                    k = l;
            }

            taskChoice.addItem(s1, j);
        }

    */
    public void loadTaskTools(String taskTid) {
        taskChoice.removeAll();
        try {
            FileInputStream fileinputstream = new FileInputStream(parent.getUfile("cbox/taskprops/tasktools/" + taskTid + ".dat"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            String s;
            while ((s = datainputstream.readLine()) != null) {
                taskChoice.addItem(s);
            }
            datainputstream.close();
            fileinputstream.close();
            return;
        } catch (Exception _ex) {
            parent.setQstatus("Error 600A [getCatResPinger]: \n" + _ex.toString(), true);
        }
    }

    public void saveTaskTools(String svdTaskVars, String taskTid) {

        String theTaskVars = addTaskVarsTField.getText();
        try {

            FileOutputStream fileoutputstream = new FileOutputStream(parent.getUfile("cbox/taskprops/tasktools/" + taskTid + ".dat"));
            PrintStream printstream = new PrintStream(fileoutputstream);
            if (svdTaskVars.equals("noQvalue")) {
                taskChoice.removeAll();
                taskChoice.addItem("?example=this&exampleA=that");
            } else if (svdTaskVars.equals("delete")) {
                int iTcount = taskChoice.getItemCount();
                if (iTcount == 1) {
                    parent.setQstatus(parent.aplangstrings.getProperty("text081"), false);
                    return;
                } else {
                    taskChoice.remove(taskChoice.getSelectedItem());
                }
            } else {
                if (theTaskVars.length() < 2) {
                    parent.setQstatus("Please enter valid Url Variables", false);
                    return;
                } else {

                    for (int i = 0; i < taskChoice.getItemCount(); i++) {
                        if (taskChoice.getItem(i).equals(theTaskVars)) {
                            parent.setQstatus("These Variables are already Used, please use another", false);
                            return;
                        }
                    }
                    taskChoice.addItem(addTaskVarsTField.getText());
                }

            }
            for (int i = 0; i < taskChoice.getItemCount(); i++) {
                printstream.println(taskChoice.getItem(i));
            }

            parent.setQstatus(parent.aplangstrings.getProperty("text701"), false);
            printstream.close();
            fileoutputstream.close();
            loadTaskTools(taskTid);
        } catch (Exception exception) {
            parent.setQstatus("Error 692A [getCatResPinger]: \n" + exception.toString(), true);
            return;
        }
    }


    public void loadTaskProps(String s) {
        Properties newTaskProps = new Properties();
        String loadedTaskFname = s;
        try {
            FileInputStream fileinputstream = new FileInputStream(parent.getUfile("cbox/taskprops/taskprefs/" + loadedTaskFname + ".dat"));
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            newTaskProps.load(fileinputstream);
            pingFileUrl = newTaskProps.getProperty("taskUrl").trim();
            taskUname = newTaskProps.getProperty("taskUname").trim();
            taskPassword = newTaskProps.getProperty("taskPassword").trim();
            taskID = newTaskProps.getProperty("taskID").trim();
            fileinputstream.close();
            datainputstream.close();
            addTaskTitleTField.setText(loadedTaskFname);
            addTaskUrlTField.setText(pingFileUrl);
            addTaskUnameTField.setText(taskUname);
            addTaskUpassTField.setText(taskPassword);
            loadTaskTools(taskID);
        } catch (Exception exception) {
            System.out.println("loadnewPref Ex" + exception.toString());
            parent.setQstatus("Error 602A [getCatResPinger]: \n" + exception.toString(), true);
            return;
        }
    }


    public void validateTaskProps(String addOrEdit, String theTaskId) {

        String TaskTitleString = addTaskTitleTField.getText();
        String TaskUrlString = addTaskUrlTField.getText();
        String TaskUnameString = addTaskUnameTField.getText();
        String TaskUPassString = addTaskUpassTField.getText();

        if (addOrEdit.equals("addurl")) {

            if (TaskTitleString.length() < 2) {
                parent.setQstatus("Please enter a title for this Task Url", false);
                return;
            }

            for (int i = 0; i < taskTitleChoice.getItemCount(); i++) {
                if (taskTitleChoice.getItem(i).equals(parent.getTitleToFile(TaskTitleString))) {
                    parent.setQstatus("This Title is already Used, please select another", false);
                    return;
                }
            }

        } else { // editing

            if (taskTitleChoice.getSelectedItem().equals(parent.getTitleToFile(TaskTitleString))) {
                String dummystring = "yes";
            } else {
                addTaskTitleTField.setText(taskTitleChoice.getSelectedItem());
                parent.setQstatus("Title cannot be altered when Editing Task Url Properties.", false);
                return;
            }

        }

        if ((TaskUrlString.startsWith("http://")) && (TaskUrlString.length() > 10)) {
            System.out.println("started");
        } else {
            parent.setQstatus("Task Url must start with http://  And must be a valid URL", false);
            return;
        }

        if (TaskUnameString.length() < 2) {
            parent.setQstatus(parent.aplangstrings.getProperty("text106"), false);
            return;
        }

        if (TaskUPassString.length() < 2) {
            parent.setQstatus(parent.aplangstrings.getProperty("text107"), false);
            return;
        }

        saveTaskBox(TaskTitleString, TaskUrlString, TaskUnameString, TaskUPassString, theTaskId);


    }


    public void saveTaskBox(String a, String b, String c, String d, String e) {
        try {
            String taskFNameString = parent.getTitleToFile(a);
            FileOutputStream fileoutputstream = new FileOutputStream(parent.getUfile("cbox/taskprops/taskprefs/" + taskFNameString + ".dat"));
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println("taskUrl = " + b);
            printstream.println("taskUname = " + c);
            printstream.println("taskPassword = " + d);
            printstream.println("taskID = " + e);

            printstream.close();
            fileoutputstream.close();

            // copyDBscheme(dbIDString);

            parent.setQstatus(parent.aplangstrings.getProperty("text700"), false);
            saveTaskTools("noQvalue", e);
            loadTaskTitles(false);
            // loadTaskProps(taskFNameString);
        } catch (Exception exception) {
            parent.setQstatus("Error 692A [getCatResPinger]: \n" + exception.toString(), true);
            return;
        }
    }

    public class MyTaskAuthenticator extends Authenticator {
        // This method is called when a taskPassword-protected URL is accessed
        protected PasswordAuthentication getPasswordAuthentication() {
            // Get information about the request
            String promptString = getRequestingPrompt();
            String hostname = getRequestingHost();
            InetAddress ipaddr = getRequestingSite();
            int port = getRequestingPort();

            // Get the taskUname from the user...
            // String taskUname = "sample";

            // Get the taskPassword from the user...
            // String taskPassword = "samples";

            // Return the information
            return new PasswordAuthentication(taskUname, taskPassword.toCharArray());
        }
    }

    adminApp parent;
    Process process;
    String verinfo;
    String rstat;
    public static final int ENABLE_ITEM = 0;
    public static final int BOLD_ITEM = 1;
    public static final int CHECK_ITEM = 2;
    Thread dm;
    int x;
    int rcount;
    int rpings;

    private JButton exit_bt;
    private JButton hide_bt;
    private JButton login_bt;
    private JButton logout_bt;
    private JButton about_bt;
    private JButton pingHelp_bt;
    private JButton prefs_bt;
    boolean oktorun;
    int delay;
    FileInputStream fileinputstream;
    DataInputStream datainputstream;
    String helpfile;
    String helpbox;
    String taskUrl;
    String taskUname;
    String taskPassword;
    String taskID;

    String shophelpnick;
    String pingFileUrl;
    boolean mplayerOn;
    JLabel pingStatLabel;
    boolean pingHelpOn;
    JLabel statLabel;
    JLabel label;
    Choice delayChoice;
    Choice taskChoice;
    JLabel taskLabel;
    JTextArea taskTextArea;


    JLabel taskTitleLabel;
    java.awt.Choice taskTitleChoice;


    JButton delTaskTitle_bt;
    JButton delTaskChoice_bt;
    JButton addTaskVars_bt;
    JButton addTaskBox_bt;
    JButton editTaskBox_bt;

    JLabel addTaskBoxLabel;

    JLabel addTaskTitleTFLabel;
    JLabel addTaskUrlTFLabel;

    JLabel addTaskUnameTFLabel;
    JLabel addTaskUpassTFLabel;

    JLabel addTaskVarsLabel;
    JTextField addTaskVarsTField;


    JTextField addTaskTitleTField;
    JTextField addTaskUrlTField;
    JTextField addTaskUnameTField;
    JTextField addTaskUpassTField;


}
