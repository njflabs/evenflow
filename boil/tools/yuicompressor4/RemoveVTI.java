// takes the file folder and string to search in file/folder names
// will remove them. use with caution.


import java.io.*;


public class RemoveVTI
{
    String tstrCSSext = "";
    String tstrLastDir = "";
    public RemoveVTI()
    {
    }

    public static void main(String args[])
    {
        RemoveVTI dirs = new RemoveVTI();
        if(args.length > 0)
            dirs.recurseInDirFrom(args[0], args[1]);
        else
            System.out.println("Usage:  jview . filorfoldertodelete");
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

    public void checkForBom(String s) {
        String cleanTplateString;
        try {
            File file = new File(s);
            FileInputStream fis = new FileInputStream(file);
            long size = file.length();
            byte[] b = new byte[(int) size];
            int bytesRead = fis.read(b, 0, (int) size);
            if (bytesRead != size) {
                throw new IOException("cannot read file");
            } else {
                fis.close();
            }
            int b0 = b[0] & 0xff;
            int b1 = b[1] & 0xff;
            int b2 = b[2] & 0xff;
            if (b0 == 0xef && b1 == 0xbb && b2 == 0xbf) {
                cleanTplateString = new String(b, 3, b.length - 3, "UTF8");
                System.out.println("BOM found in: " + s);
                 saveTextString(cleanTplateString, s);
            } else {
                System.out.println("no BOM found in: " + s);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
 
    }


    public void recurseInDirFrom(String s, String s1)
    {

	  
        File file = new File(s);
        if(file.isDirectory())
        {
		tstrLastDir = s;
            String as[] = file.list();
            for(int i = 0; i < as.length; i++)
                recurseInDirFrom(s + File.separatorChar + as[i], s1);

        } else {
        if(s.indexOf(s1) != -1) {
            file.delete();
	  } else {
	   if(tstrLastDir.contains(File.separatorChar + "misc") || tstrLastDir.contains(File.separatorChar + "docs") || tstrLastDir.contains(File.separatorChar + "quickorder")) {
	  System.out.println("checkForBom: " + s);
	   checkForBom(s);
	   } else {
	  System.out.println("tstrLastDir not used: " + tstrLastDir);
	   }
	   }
	   }
	  System.out.println(s);
    }
}
