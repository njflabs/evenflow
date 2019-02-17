package njfbrowser.misc;

import java.awt.*;
import java.io.*;

public final class JUtil {

    public static native long getFreeDiskSpace(String s);

    public static native void setWindowMaximized(int i);

    public static native void setWindowMaximizeEnabled(int i, boolean flag);

    private JUtil() {
    }

    public static native int getHwnd(String s);

    public static native void setWindowRestored(int i);

    public static native void setWindowRestoreEnabled(int i, boolean flag);

    public static native void setWindowAlwaysOnTop(int i, boolean flag);

    public static void setMenuBarDefaultFont(MenuBar menubar, Font font)
            throws IllegalArgumentException {
        int i = 0;
        if (menubar == null || font == null)
            throw new IllegalArgumentException("This method cannot accept null arguments!");
        i = menubar.getMenuCount();
        for (int j = 0; j < i; j++) {
            menubar.getMenu(j).setFont(font);
            int k = menubar.getMenu(j).getItemCount();
            for (int l = 0; l < k; l++)
                menubar.getMenu(j).getItem(l).setFont(font);

        }

    }

    public static native int getDriveType(String s);

    public static void setContainerDefaultFont(Container container, Font font)
            throws IllegalArgumentException {
        if (container == null || font == null)
            throw new IllegalArgumentException("This method cannot accept null arguments!");
        Component acomponent[] = container.getComponents();
        for (int i = 0; i < acomponent.length; i++)
            acomponent[i].setFont(font);

    }

    public static native void setWindowMoveEnabled(int i, boolean flag);

    public static native void setWindowSizeEnabled(int i, boolean flag);

    public static native String[] getLogicalDrives();

    public static native char getConsoleChar();

    public static native void setWindowMinimized(int i);

    public static native void setWindowMinimizeEnabled(int i, boolean flag);

    public static native String getCurrentDirectory();

    public static native String getVolumeLabel(String s);

    public static native boolean setVolumeLabel(String s, String s1);

    public static native boolean setCurrentDirectory(String s);

    public static void copyFile(String s, String s1)
            throws IllegalArgumentException, IOException {
        BufferedReader bufferedreader = null;
        BufferedWriter bufferedwriter = null;
        if (s == null || s.equals("") || s1 == null || s1.equals(""))
            throw new IllegalArgumentException("This method cannot accept null or empty (\"\") String arguments!");
        try {
            FileReader filereader = new FileReader(s);
            FileWriter filewriter = new FileWriter(s1);
            bufferedreader = new BufferedReader(filereader);
            bufferedwriter = new BufferedWriter(filewriter);
            File file = new File(s);
            int i = (int) file.length();
            for (char ac[] = new char[i]; bufferedreader.read(ac, 0, i) != -1; bufferedwriter.write(ac, 0, i)) ;
        } finally {
            try {
                if (bufferedreader != null)
                    bufferedreader.close();
                if (bufferedwriter != null)
                    bufferedwriter.close();
            } catch (IOException _ex) {
            }
        }
    }

    public static final int DRIVE_UNKNOWN = 0;
    public static final int DRIVE_NO_ROOT_DIR = 1;
    public static final int DRIVE_REMOVABLE = 2;
    public static final int DRIVE_FIXED = 3;
    public static final int DRIVE_REMOTE = 4;
    public static final int DRIVE_CDROM = 5;
    public static final int DRIVE_RAMDISK = 6;

    static {
        System.loadLibrary("JUtil");
    }
}
