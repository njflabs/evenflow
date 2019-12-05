import java.io.*;
import java.io.File;
import java.io.PrintStream;
import com.yahoo.platform.yui.compressor.*;
import java.lang.reflect.Method;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;
import java.nio.charset.Charset;



public class YUIHelper
{
                            String charset;
                            boolean nomunge;
                            boolean verbose;
                            boolean preserveAllSemiColons;
                            boolean disableOptimizations;
				    String type;
					int linebreakpos;
        Reader in;
        Writer out;
    public YUIHelper()
    {

                             nomunge = true;
                             preserveAllSemiColons = true;
                             verbose = false;
                             disableOptimizations = true;
					charset =  "UTF-8";
                              type = "js";
					linebreakpos = -1;
					in = null;
					out = null;
    }

    public static void main(String args[])
    {
        Reader in = null;
        Writer out = null;
        YUIHelper yuihelper = new YUIHelper();
        if(args.length > 0) {
            yuihelper.recurseInDirFrom(args[0], args[1]);
        } else {
            System.out.println("Usage:  d:/irectory/of/js  thefileprefix- ");
	  }
    }
	
 







    public void recurseInDirFrom(String s, String s1)
    {




        File file = new File(s);
        if(file.isDirectory())
        {
            String as[] = file.list();

          String[] fileArgs = file.list();
            java.util.List files = java.util.Arrays.asList(fileArgs);
            if (files.isEmpty()) {
  
                files = new java.util.ArrayList();
                files.add("-"); // read from stdin
            }
            int i = 0;
            java.util.Iterator filenames = files.iterator();
            while(filenames.hasNext()) {
		String tmpFname =  (String)filenames.next();

         if(tmpFname.contains(".css")) {
		 type = "css";
		System.out.println("!!!!! current type is css");
	  }




		String inputFilename = s + File.separatorChar + tmpFname;
		String outputFilename = s + File.separatorChar + s1 + tmpFname;
			String argoss[] = new String[5];
			 argoss[0] = "--nomunge";
			 argoss[1] = "--preserve-semi";
			 // argoss[2] = "--disable-optimizations";
			 argoss[2] = s + File.separatorChar + tmpFname;
			 argoss[3] = "-o";
			 argoss[4] = s + File.separatorChar + s1 + tmpFname;

                try {
                in = new InputStreamReader(new FileInputStream(inputFilename), charset);


                 if (type.equalsIgnoreCase("js")) {

                         try {
                            JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {

                                public void warning(String message, String sourceName,
                                        int line, String lineSource, int lineOffset) {
                                    if (line < 0) {
                                        System.err.println("\n[WARNING] " + message);
                                    } else {
                                        System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
                                    }
                                }

                                public void error(String message, String sourceName,
                                        int line, String lineSource, int lineOffset) {
                                    if (line < 0) {
                                        System.err.println("\n[ERROR] " + message);
                                    } else {
                                        System.err.println("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
                                    }
                                }

                                public EvaluatorException runtimeError(String message, String sourceName,
                                        int line, String lineSource, int lineOffset) {
                                    error(message, sourceName, line, lineSource, lineOffset);
                                    return new EvaluatorException(message);
                                }
                            });

                            // Close the input stream first, and then open the output stream,
                            // in case the output file should override the input file.
                            in.close(); in = null;

                            if (outputFilename == null) {
                                out = new OutputStreamWriter(System.out, charset);
                            } else {
                                out = new OutputStreamWriter(new FileOutputStream(outputFilename), charset);
                            }


                            compressor.compress(out, linebreakpos, nomunge, verbose, preserveAllSemiColons, disableOptimizations);

                        } catch (EvaluatorException e) {
                           System.err.println("\n[ERROR] compressing file: " + inputFilename);
				 saveATextString(readAFileAsString(inputFilename), outputFilename);
                            e.printStackTrace();
                          // recurseInDirFrom(s + File.separatorChar + as[i], s1);

                        }




                     }







			  if (type.equalsIgnoreCase("css")) {
                      try {
                        CssCompressor compressor = new CssCompressor(in);

                        // Close the input stream first, and then open the output stream,
                        // in case the output file should override the input file.
                        in.close(); in = null;

                        if (outputFilename == null) {
                            out = new OutputStreamWriter(System.out, charset);
                        } else {
                            out = new OutputStreamWriter(new FileOutputStream(outputFilename), charset);
                        }

                        compressor.compress(out, linebreakpos);


		    	  } catch(EvaluatorException e) {
		    	  saveATextString(readAFileAsString(inputFilename), outputFilename);
          		// recurseInDirFrom(s + File.separatorChar + as[i], s1);
		    	}	




                    }



                         } catch (Exception e) {
                           System.err.println("\n[ERROR] compressing file: " + inputFilename);
				 saveATextString(readAFileAsString(inputFilename), outputFilename);
                            e.printStackTrace();
                          recurseInDirFrom(s + File.separatorChar + as[i], s1);

                        } finally {

                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }




		}
        }
      }













    public static void saveTextString(String theTextString, String fileNameString) {
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

