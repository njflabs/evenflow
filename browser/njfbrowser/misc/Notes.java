package njfbrowser.misc;

import java.awt.*;
import java.io.*;

public class Notes extends Frame {

    private void InitializeMenus(MenuBar menubar) {
        Menu menu = new Menu(mnuFile);
        menu.add(new MenuItem(mnuNew));
        menu.add(new MenuItem(mnuOpen));
        menu.add(new MenuItem(mnuSave));
        menu.add(new MenuItem(mnuSaveAs));
        menu.addSeparator();
        menu.add(new MenuItem(mnuQuit));
        menubar.add(menu);
        menu = new Menu(mnuHelp);
        menu.add(new CheckboxMenuItem(mnuTest));
        menu.addSeparator();
        menu.add(new MenuItem(mnuAbout));
        menubar.add(menu);
    }

    public Notes() {
        super("Java NotePad Editor");
        mnuFile = shortcut + "File";
        mnuNew = shortcut + "New";
        mnuOpen = shortcut + "Open";
        mnuSave = shortcut + "Save";
        mnuSaveAs = "Save " + shortcut + "As";
        mnuQuit = shortcut + "Quit";
        mnuHelp = shortcut + "Help";
        mnuTest = shortcut + "Test!";
        mnuAbout = "A" + shortcut + "bout";
        Font font = new Font("TimesRoman", 0, 12);
        setFont(font);
        setBackground(Color.white);
        filename = "";
        mbar = new MenuBar();
        InitializeMenus(mbar);
        setMenuBar(mbar);
        InitializeTextArea();
        openDialog = new FileDialog(this, "Open File...", 0);
        saveDialog = new FileDialog(this, "Save File...", 1);
        reshape(100, 100, 300, 200);
        pack();
        show();
    }

    public void read(String s)
            throws IOException {
        int i = 0;
        FileInputStream fileinputstream = null;
        DataInputStream datainputstream = null;
        try {
            fileinputstream = new FileInputStream(s);
            BufferedInputStream bufferedinputstream = new BufferedInputStream(fileinputstream);
            datainputstream = new DataInputStream(bufferedinputstream);
        } catch (IOException ioexception) {
            System.out.println("Error in opening file" + s);
            throw ioexception;
        }
        t.hide();
        try {
            String s1;
            while ((s1 = datainputstream.readLine()) != null) {
                t.insertText(s1 + "\n", i);
                i += s1.length() + 1;
            }
            fileinputstream.close();
        } catch (IOException ioexception1) {
            System.out.println("Error in reading file");
            throw ioexception1;
        }
        t.show();
    }

    static String queryMenuShortcut() {
        String s = System.getProperty("os.name", "?");
        if (s.startsWith("Windows 95"))
            return "&";
        if (s.startsWith("OS/2"))
            return "~";
        else
            return "";
    }

    public void write(String s)
            throws IOException {
        FileOutputStream fileoutputstream = null;
        DataOutputStream dataoutputstream = null;
        try {
            fileoutputstream = new FileOutputStream(s);
            BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(fileoutputstream);
            dataoutputstream = new DataOutputStream(bufferedoutputstream);
        } catch (IOException ioexception) {
            System.out.println("Error in opening file " + s);
            throw ioexception;
        }
        try {
            dataoutputstream.writeBytes(t.getText());
            dataoutputstream.flush();
            fileoutputstream.close();
            return;
        } catch (IOException ioexception1) {
            System.out.println("Error in writing to file");
            throw ioexception1;
        }
    }

    public static void main(String args[]) {
        new Notes();
    }

    public boolean action(Event event, Object obj) {
        String s = event.arg.toString();
        if (s.equals(mnuOpen)) {
            openDialog.show();
            try {
                filename = openDialog.getFile();
                if (filename != null)
                    read(filename);
            } catch (IOException _ex) {
                System.out.println("File not found");
            }
            return true;
        }
        if (s.equals(mnuNew)) {
            t.setText("");
        } else {
            if (s.equals(mnuSave) || s.equals(mnuSaveAs)) {
                try {
                    if (s.equals(mnuSaveAs) || filename == "") {
                        saveDialog.setFile("*.*");
                        saveDialog.show();
                        filename = saveDialog.getFile();
                    }
                    if (filename != null) {
                        if (filename.endsWith(".*.*"))
                            filename = filename.substring(0, filename.length() - 4);
                        write(filename);
                    }
                } catch (IOException _ex) {
                    System.out.println("File not found");
                }
                return true;
            }
            if (s.equals(mnuTest)) {
                if (((CheckboxMenuItem) event.target).getState()) {
                    resize(size());
                } else {
                    t.setBackground(Color.gray);
                    resize(size());
                }
            } else if (s.equals(mnuQuit))
                System.exit(0);
            else if (s.equals(mnuAbout)) {
                System.out.println("heheh");
//                AboutDialog aboutdialog = new AboutDialog(this);
                //             aboutdialog.show();
            }
        }
        return true;
    }

    private void InitializeTextArea() {
        t = new TextArea("", 12, 80);
        t.setEditable(true);
        add("Center", t);
    }

    public boolean handleEvent(Event event) {
        switch (event.id) {
            case 201: // Event.WINDOW_DESTROY
                if (event.target instanceof Frame)
                    System.exit(0);
                return true;
        }
        return super.handleEvent(event);
    }

    private TextArea t;
    private FileDialog openDialog;
    private FileDialog saveDialog;
    private String filename;
    private MenuBar mbar;
    private final String shortcut = queryMenuShortcut();
    private final String mnuFile;
    private final String mnuNew;
    private final String mnuOpen;
    private final String mnuSave;
    private final String mnuSaveAs;
    private final String mnuQuit;
    private final String mnuHelp;
    private final String mnuTest;
    private final String mnuAbout;
}
