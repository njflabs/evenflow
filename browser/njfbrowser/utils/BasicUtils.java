// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SmartAddRows.java

package njfbrowser.utils;


import java.io.*;
import java.util.Properties;

public class BasicUtils {


    public BasicUtils() {
        udString = System.getProperty("user.dir");
        fsString = File.separator;
        System.out.println("udString: " + udString);
        System.out.println("fsString: " + fsString);
    }


    public static void main(String argv[]) {
        new BasicUtils();
    }

    public String getWorkingDir() {

        String workDirString = System.getProperty("user.dir");
        return workDirString;
    }

    public String getUfile(String s) {

        String uFileString = "";
        String cleanFileString = replaceString(s, "/", fsString);
        cleanFileString = replaceString(cleanFileString, "\\", fsString);
        uFileString = udString + fsString + cleanFileString;
        return uFileString;
    }


    public String replaceString(String s, String s1, String s2) {
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


    public void saveTextString(String theTextString, String fileNameString) {
        try {

            String uFileString = fileNameString;
            FileOutputStream tbeureekas_stream = new FileOutputStream(uFileString, false);
            PrintStream tbeureekas_pstream = new PrintStream(tbeureekas_stream);
            tbeureekas_pstream.println(theTextString);
            tbeureekas_pstream.close();
            tbeureekas_stream.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public String readFileAsString(String filePath)
            throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }


    public String getCachedFile(String filePath, long timeFrame)
            throws java.io.IOException {
        String fileString = "noQvalue";
        File file = new File(filePath);
        long lastModified = file.lastModified() / 1000;
        long nowLong = System.currentTimeMillis() / 1000;
        long timeLimit = nowLong - timeFrame;
        if (lastModified < timeLimit) {
            return "noQvalue";
        } else {
            StringBuffer fileData = new StringBuffer(1000);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
            fileString = fileData.toString();
        }


        return fileString;
    }


    public Properties loadTheProps(String thePropFile) {
        try {
            Properties theTempProp = new Properties();
            FileInputStream fileinputstream = new FileInputStream(thePropFile);
            DataInputStream datainputstream = new DataInputStream(fileinputstream);
            theTempProp.load(fileinputstream);
            fileinputstream.close();
            datainputstream.close();

            return theTempProp;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }


    public void storeTheProps(Properties theProps, String thePropFile) {
        try {

            FileOutputStream fileoutputstream = new FileOutputStream(thePropFile);
            theProps.store(fileoutputstream, thePropFile);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    String udString;
    String fsString;

}