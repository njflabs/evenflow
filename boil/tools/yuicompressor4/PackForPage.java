import java.io.*;
import java.io.File;
import java.io.PrintStream;
import java.io.*;
import java.nio.charset.Charset;



public class PackForPage
{

 
    public PackForPage()
    {
 

    }

    public static void main(String args[])
    {
        Reader in = null;
        Writer out = null;
        PackForPage packForPage = new PackForPage();
        if(args.length > 0) {
            packForPage.doPacking(args[0], args[1]);
        } else {
            System.out.println("Usage:PackForPage  d:/irectory/of/js    d:/irectory/of/css");
	  }
    }
	
 

 




   



    public void doPacking(String fileDirString, String cssDirString) {
        try {
	saveATextString("\r\n // 01-js_spinner.js \r\n", fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-js_spinner.js"), fileDirString + File.separatorChar + "quickorder_min.js");

	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-lz-string.js"), fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString("\r\n // 01-x_all.js \r\n", fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-x_all.js"), fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString("\r\n // 01-x_booter.js \r\n", fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-x_booter.js"), fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString("\r\n // 01-x_admin.js \r\n", fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-x_admin.js"), fileDirString + File.separatorChar + "quickorder_min.js");



	saveATextString("\r\n // 01-x_menu.js \r\n", fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-x_menu.js"), fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString("\r\n // 01-js_actb.js \r\n", fileDirString + File.separatorChar + "quickorder_min.js");
	saveATextString(readAFileAsString(fileDirString + File.separatorChar + "01-js_actb.js"), fileDirString + File.separatorChar + "quickorder_min.js");




	saveATextString("\r\n /* 01-x_forms.css  */ \r\n", cssDirString + File.separatorChar + "quickorder_min.css");
	saveATextString(readAFileAsString(cssDirString + File.separatorChar + "01-x_forms.css"), cssDirString + File.separatorChar + "quickorder_min.css");
  

	saveATextString("\r\n /* 01-x_nav.css */ \r\n", cssDirString + File.separatorChar + "quickorder_min.css");
	saveATextString(readAFileAsString(cssDirString + File.separatorChar + "01-x_nav.css"), cssDirString + File.separatorChar + "quickorder_min.css");
 
	saveATextString("\r\n /* 01-x_dev.css */  \r\n", cssDirString + File.separatorChar + "quickorder_min.css");
	saveATextString(readAFileAsString(cssDirString + File.separatorChar + "01-x_dev.css"), cssDirString + File.separatorChar + "quickorder_min.css");
 

	saveATextString("\r\n /* 01-normalize.css  */ \r\n", cssDirString + File.separatorChar + "quickorder_min.css");
	saveATextString(readAFileAsString(cssDirString + File.separatorChar + "01-normalize.css"), cssDirString + File.separatorChar + "quickorder_min.css");
 


        } catch (Exception exception) {
            System.out.println(exception);
        }
    }





    public static void saveTextString(String theTextString, String fileNameString) {
        try {

            String uFileString = fileNameString;
            FileOutputStream tbeureekas_stream = new FileOutputStream(uFileString, true);
            PrintStream tbeureekas_pstream = new PrintStream(tbeureekas_stream);
            tbeureekas_pstream.println(theTextString);
            tbeureekas_pstream.close();
            tbeureekas_stream.close();

        } catch (Exception exception) {

            System.out.println(exception);

        }
    }

    public static void saveATextString(String theTextString, String fileNameString) {
        try {

            saveTextString(theTextString, fileNameString);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public static String readFileAsString(String filePath)
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

    public static String readAFileAsString(String filePath) {
        try {
        return readFileAsString(filePath);
        } catch (Exception exception) {
            System.out.println(exception);
	      return "nogo";
        }
    }


 
    }

