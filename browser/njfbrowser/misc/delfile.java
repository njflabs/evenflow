package njfbrowser.misc;

import java.io.File;
import java.io.FilenameFilter;

public class delfile {
    class ExtensionFilter
            implements FilenameFilter {

        public boolean accept(File file, String s) {
            return s.endsWith(extension);
        }

        private String extension;

        public ExtensionFilter(String s) {
            extension = s;
        }
    }


    public delfile() {
    }

    public void deleteFiles(String s, String s1) {
        ExtensionFilter extensionfilter = new ExtensionFilter(s1);
        File file = new File(s);
        String as[] = file.list(extensionfilter);
        if (as.length == 0)
            return;
        for (int i = 0; i < as.length; i++) {
            File file1 = new File(s + as[i]);
            boolean flag = file1.delete();
            System.out.print(file1);
            System.out.println("  deleted " + flag);
        }

    }

    public static void main(String args[]) {
        System.out.println("hello");
        delfile delfile1 = new delfile();
        delfile1.deleteFiles("./", ".java");
        delfile1.deleteFiles("./shdocvw/", ".java");
        delfile1.deleteFiles("./com/jeans/trayicon/", ".java");
    }
}
