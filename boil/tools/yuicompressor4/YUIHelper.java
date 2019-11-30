import java.io.*;
import java.io.File;
import java.io.PrintStream;
import com.yahoo.platform.yui.compressor.*;
import java.lang.reflect.Method;

public class YUIHelper
{

    public YUIHelper()
    {
    }

    public static void main(String args[])
    {
        YUIHelper yuihelper = new YUIHelper();
        if(args.length > 0)
            yuihelper.recurseInDirFrom(args[0], args[1]);
        else
            System.out.println("Usage:  d:/irectory/of/js  thefileprefix- ");
    }









    public void recurseInDirFrom(String s, String s1)
    {
        File file = new File(s);
        if(file.isDirectory())
        {
            String as[] = file.list();
            for(int i = 0; i < as.length; i++) {
		String tmpFname =  as[i];
			String argoss[] = new String[5];
			 argoss[0] = "--nomunge";
			 argoss[1] = "--preserve-semi";
			 // argoss[2] = "--disable-optimizations";
			 argoss[2] = s + File.separatorChar + as[i];
			 argoss[3] = "-o";
			 argoss[4] = s + File.separatorChar + s1 + as[i];
        try {
        ClassLoader loader = new JarClassLoader();
        Thread.currentThread().setContextClassLoader(loader);
        Class c = loader.loadClass(YUICompressor.class.getName());
        Method main = c.getMethod("main", new Class[]{String[].class});
        main.invoke(null, new Object[]{argoss});
        recurseInDirFrom(s + File.separatorChar + as[i], s1);
	  } catch(Exception e) {
	System.out.println("Ex. file:" + as[i] +  ":: " + e);
      e.printStackTrace();
      recurseInDirFrom(s + File.separatorChar + as[i], s1);
	}	
 

		}
        }

 
    }
}
