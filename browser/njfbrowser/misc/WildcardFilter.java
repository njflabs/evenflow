package njfbrowser.misc;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

class WildcardFilter
        implements FilenameFilter {

    protected boolean matchChar(char c, char c1) {
        return c == '?' || c == c1;
    }

    public WildcardFilter(String s, boolean flag) {
        mustBeFile = flag;
        pattern = s;
        char ac[] = s.toCharArray();
        int i = ac.length;
        startsWithStar = ac[0] == '*';
        endsWithStar = ac[i - 1] == '*';
        int j = 0;
        boolean flag1 = false;
        Vector vector = new Vector(5);
        int k;
        for (; !flag1; flag1 = k >= i) {
            while (j < i && ac[j] == '*')
                j++;
            for (k = j + 1; k < i && ac[k] != '*'; k++) ;
            if (j < i) {
                String s1 = pattern.substring(j, k);
                vector.addElement(s1);
            }
            j = k + 1;
        }

        subpatterns = new String[vector.size()];
        vector.copyInto(subpatterns);
    }

    public WildcardFilter(String s) {
        this(s, false);
    }

    public boolean accept(File file, String s) {
        int i = 0;
        int j = subpatterns.length;
        if (mustBeFile && !file.isFile())
            return false;
        for (int k = 0; k < j; k++) {
            int l = getMatchPosition(s, subpatterns[k], i);
            if (l == -1)
                return false;
            if (!startsWithStar && k == 0 && l != 0)
                return false;
            if (!endsWithStar && k == j - 1 && l != s.length() - subpatterns[k].length() && getMatchPosition(s, subpatterns[k], s.length() - subpatterns[k].length()) == -1)
                return false;
            i = l + subpatterns[k].length();
        }

        return true;
    }

    protected int getMatchPosition(String s, String s1, int i) {
        int j = s.length() - s1.length();
        if (j < i)
            return -1;
        int k = s1.length();
        for (int l = i; l <= j; l++) {
            boolean flag = true;
            for (int i1 = 0; i1 < k && flag; i1++)
                flag = matchChar(s1.charAt(i1), s.charAt(l + i1));

            if (flag)
                return l;
        }

        return -1;
    }

    String pattern;
    String subpatterns[];
    boolean startsWithStar;
    boolean endsWithStar;
    boolean mustBeFile;
}
