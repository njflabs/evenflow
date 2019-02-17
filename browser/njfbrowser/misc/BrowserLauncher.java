package njfbrowser.misc;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BrowserLauncher {

    private static boolean loadClasses() {
        switch (jvm) {
            default:
                break;

            case 0: // '\0'
                try {
                    Class class1 = Class.forName("com.apple.MacOS.AETarget");
                    Class macOSErrorClass = Class.forName("com.apple.MacOS.MacOSError");
                    Class class2 = Class.forName("com.apple.MacOS.OSUtils");
                    Class class3 = Class.forName("com.apple.MacOS.AppleEvent");
                    Class class4 = Class.forName("com.apple.MacOS.ae");
                    Class aeDescClass = Class.forName("com.apple.MacOS.AEDesc");
                    aeTargetConstructor = class1.getDeclaredConstructor(Integer.TYPE);
                    appleEventConstructor = class3.getDeclaredConstructor(Integer.TYPE, Integer.TYPE, class1, Integer.TYPE, Integer.TYPE);
                    aeDescConstructor = aeDescClass.getDeclaredConstructor(String.class);
                    makeOSType = class2.getDeclaredMethod("makeOSType", String.class);
                    putParameter = class3.getDeclaredMethod("putParameter", Integer.TYPE, aeDescClass);
                    sendNoReply = class3.getDeclaredMethod("sendNoReply");
                    Field field1 = class4.getDeclaredField("keyDirectObject");
                    keyDirectObject = (Integer) field1.get(null);
                    Field field2 = class3.getDeclaredField("kAutoGenerateReturnID");
                    kAutoGenerateReturnID = (Integer) field2.get(null);
                    Field field3 = class3.getDeclaredField("kAnyTransactionID");
                    kAnyTransactionID = (Integer) field3.get(null);
                    break;
                } catch (ClassNotFoundException classnotfoundexception) {
                    errorMessage = classnotfoundexception.getMessage();
                    return false;
                } catch (NoSuchMethodException nosuchmethodexception) {
                    errorMessage = nosuchmethodexception.getMessage();
                    return false;
                } catch (NoSuchFieldException nosuchfieldexception) {
                    errorMessage = nosuchfieldexception.getMessage();
                    return false;
                } catch (IllegalAccessException illegalaccessexception) {
                    errorMessage = illegalaccessexception.getMessage();
                }
                return false;

            case 1: // '\001'
                try {
                    Class mrjFileUtilsClass = Class.forName("com.apple.mrj.MRJFileUtils");
                    Class mrjOSTypeClass = Class.forName("com.apple.mrj.MRJOSType");
                    Field field = mrjFileUtilsClass.getDeclaredField("kSystemFolderType");
                    kSystemFolderType = field.get(null);
                    findFolder = mrjFileUtilsClass.getDeclaredMethod("findFolder", mrjOSTypeClass);
                    getFileType = mrjFileUtilsClass.getDeclaredMethod("getFileType", File.class);
                    break;
                } catch (ClassNotFoundException classnotfoundexception1) {
                    errorMessage = classnotfoundexception1.getMessage();
                    return false;
                } catch (NoSuchFieldException nosuchfieldexception1) {
                    errorMessage = nosuchfieldexception1.getMessage();
                    return false;
                } catch (NoSuchMethodException nosuchmethodexception1) {
                    errorMessage = nosuchmethodexception1.getMessage();
                    return false;
                } catch (SecurityException securityexception) {
                    errorMessage = securityexception.getMessage();
                    return false;
                } catch (IllegalAccessException illegalaccessexception1) {
                    errorMessage = illegalaccessexception1.getMessage();
                }
                return false;
        }
        return true;
    }

    private BrowserLauncher() {
    }

    public static void openURL(String s)
            throws IOException {
        if (!loadedWithoutErrors)
            throw new IOException("Exception in finding browser: " + errorMessage);
        Object obj = locateBrowser();
        if (obj == null)
            throw new IOException("Unable to locate browser: " + errorMessage);
        switch (jvm) {
            case 0: // '\0'
                try {
                    try {
                        Object obj1 = aeDescConstructor.newInstance(s);
                        putParameter.invoke(obj, keyDirectObject, obj1);
                        sendNoReply.invoke(obj);
                    } catch (InvocationTargetException invocationtargetexception) {
                        throw new IOException("InvocationTargetException while creating AEDesc: " + invocationtargetexception.getMessage());
                    } catch (IllegalAccessException illegalaccessexception) {
                        throw new IOException("IllegalAccessException while building AppleEvent: " + illegalaccessexception.getMessage());
                    } catch (InstantiationException instantiationexception) {
                        throw new IOException("InstantiationException while creating AEDesc: " + instantiationexception.getMessage());
                    }
                    return;
                } finally {
                    obj = null;
                }

            case 1: // '\001'
                Runtime.getRuntime().exec(new String[]{
                        (String) obj, s
                });
                return;

            case 2: // '\002'
            case 3: // '\003'
                Runtime.getRuntime().exec(new String[]{
                        (String) obj, "/c", "start", s
                });
                return;

            case -1:
                Process process = Runtime.getRuntime().exec(obj + " -remote 'openURL(" + s + ")'");
                try {
                    int i = process.waitFor();
                    if (i != 0) {
                        Runtime.getRuntime().exec(new String[]{
                                (String) obj, s
                        });
                        return;
                    }
                } catch (InterruptedException interruptedexception) {
                    throw new IOException("InterruptedException while launching browser: " + interruptedexception.getMessage());
                }
                break;

            default:
                Runtime.getRuntime().exec(new String[]{
                        (String) obj, s
                });
                return;
        }
    }

    private static Object locateBrowser() {
        if (browser != null)
            return browser;
        switch (jvm) {
            case 0: // '\0'
                try {
                    Integer integer = (Integer) makeOSType.invoke(null, "MACS");
                    Object obj = aeTargetConstructor.newInstance(integer);
                    Integer integer1 = (Integer) makeOSType.invoke(null, "GURL");
                    Object obj1 = appleEventConstructor.newInstance(integer1, integer1, obj, kAutoGenerateReturnID, kAnyTransactionID);
                    return obj1;
                } catch (IllegalAccessException illegalaccessexception) {
                    browser = null;
                    errorMessage = illegalaccessexception.getMessage();
                    return browser;
                } catch (InstantiationException instantiationexception) {
                    browser = null;
                    errorMessage = instantiationexception.getMessage();
                    return browser;
                } catch (InvocationTargetException invocationtargetexception) {
                    browser = null;
                    errorMessage = invocationtargetexception.getMessage();
                    return browser;
                }

            case 1: // '\001'
                File file;
                try {
                    file = (File) findFolder.invoke(null, kSystemFolderType);
                } catch (IllegalArgumentException illegalargumentexception) {
                    browser = null;
                    errorMessage = illegalargumentexception.getMessage();
                    return browser;
                } catch (IllegalAccessException illegalaccessexception1) {
                    browser = null;
                    errorMessage = illegalaccessexception1.getMessage();
                    return browser;
                } catch (InvocationTargetException invocationtargetexception1) {
                    browser = null;
                    errorMessage = invocationtargetexception1.getTargetException().getClass() + ": " + invocationtargetexception1.getTargetException().getMessage();
                    return browser;
                }
                String as[] = file.list();
                for (int i = 0; i < as.length; i++)
                    try {
                        File file1 = new File(file, as[i]);
                        if (file1.isFile()) {
                            Object obj2 = getFileType.invoke(null, file1);
                            if ("FNDR".equals(obj2.toString())) {
                                browser = file1.toString();
                                return browser;
                            }
                        }
                    } catch (IllegalArgumentException illegalargumentexception1) {
                        browser = browser;
                        errorMessage = illegalargumentexception1.getMessage();
                        return null;
                    } catch (IllegalAccessException illegalaccessexception2) {
                        browser = null;
                        errorMessage = illegalaccessexception2.getMessage();
                        return browser;
                    } catch (InvocationTargetException invocationtargetexception2) {
                        browser = null;
                        errorMessage = invocationtargetexception2.getTargetException().getClass() + ": " + invocationtargetexception2.getTargetException().getMessage();
                        return browser;
                    }

                browser = null;
                break;

            case 2: // '\002'
                browser = "cmd.exe";
                break;

            case 3: // '\003'
                browser = "command.com";
                break;

            case -1:
            default:
                browser = "netscape";
                break;
        }
        return browser;
    }

    private static int jvm;
    private static Object browser;
    private static boolean loadedWithoutErrors;
    private static Constructor aeTargetConstructor;
    private static Constructor appleEventConstructor;
    private static Constructor aeDescConstructor;
    private static Method findFolder;
    private static Method getFileType;
    private static Method makeOSType;
    private static Method putParameter;
    private static Method sendNoReply;
    private static Object kSystemFolderType;
    private static Integer keyDirectObject;
    private static Integer kAutoGenerateReturnID;
    private static Integer kAnyTransactionID;
    private static final int MRJ_2_0 = 0;
    private static final int MRJ_2_1 = 1;
    private static final int WINDOWS_NT = 2;
    private static final int WINDOWS_9x = 3;
    private static final int OTHER = -1;
    private static final String FINDER_TYPE = "FNDR";
    private static final String FINDER_CREATOR = "MACS";
    private static final String GURL_EVENT = "GURL";
    private static final String FIRST_WINDOWS_PARAMETER = "/c";
    private static final String SECOND_WINDOWS_PARAMETER = "start";
    private static final String NETSCAPE_OPEN_PARAMETER_START = " -remote 'openURL(";
    private static final String NETSCAPE_OPEN_PARAMETER_END = ")'";
    private static String errorMessage;

    static {
        loadedWithoutErrors = true;
        String s = System.getProperty("os.name");
        if ("Mac OS".equals(s)) {
            String s1 = System.getProperty("mrj.version");
            String s2 = s1.substring(0, 3);
            try {
                double d = Double.valueOf(s2).doubleValue();
                if (d == 2D)
                    jvm = 0;
                else if (d >= 2.1000000000000001D) {
                    jvm = 1;
                } else {
                    loadedWithoutErrors = false;
                    errorMessage = "Unsupported MRJ version: " + d;
                }
            } catch (NumberFormatException _ex) {
                loadedWithoutErrors = false;
                errorMessage = "Invalid MRJ version: " + s1;
            }
        } else if (s.startsWith("Windows")) {
            if (s.indexOf("9") != -1)
                jvm = 3;
            else
                jvm = 2;
        } else {
            jvm = -1;
        }
        if (loadedWithoutErrors)
            loadedWithoutErrors = loadClasses();
    }
}
