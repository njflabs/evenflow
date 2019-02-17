package njfbrowser.misc;

import java.io.IOException;

public class BrowserControl {

    public BrowserControl() {
    }

    public static boolean isWindowsPlatform() {
        String s = System.getProperty("os.name");
        return s != null && s.startsWith("Windows");
    }

    public static void displayURL(String s) {
        boolean flag = isWindowsPlatform();
        String s1 = null;
        try {
            if (flag) {
                s1 = "rundll32" + " " + "url.dll,FileProtocolHandler" + " " + s;
                Runtime.getRuntime().exec(s1);
                return;
            }
            s1 = "netscape" + " " + "-remote openURL" + "(" + s + ")";
            Process process = Runtime.getRuntime().exec(s1);
            try {
                int i = process.waitFor();
                if (i != 0) {
                    s1 = "netscape" + " " + s;
                    Process process1 = Runtime.getRuntime().exec(s1);
                    return;
                }
            } catch (InterruptedException interruptedexception) {
                System.err.println("Error bringing up browser, cmd='" + s1 + "'");
                System.err.println("Caught: " + interruptedexception);
                return;
            }
        } catch (IOException ioexception) {
            System.err.println("Could not invoke browser, command=" + s1);
            System.err.println("Caught: " + ioexception);
        }
    }

    public static void main(String args[]) {
        displayURL("http://www.javaworld.com");
    }

    private static final String WIN_ID = "Windows";
    private static final String WIN_PATH = "rundll32";
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    private static final String UNIX_PATH = "netscape";
    private static final String UNIX_FLAG = "-remote openURL";
}
