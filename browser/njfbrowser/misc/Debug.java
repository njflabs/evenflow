package njfbrowser.misc;

/*
 * This is a class used for print out debug information and can be
 * easily turned on/off.
 */

public class Debug {

    public static boolean debug = false;

    public static void setDebug(boolean flag) {
        debug = flag;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void println(Object s) {
        if (debug)
            System.out.println(s.toString());
    }
}
