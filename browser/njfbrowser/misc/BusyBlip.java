package njfbrowser.misc;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusyBlip extends JComponent
        implements ActionListener {

    public BusyBlip() {
        count = 0;
        c = null;
        pos1 = 0;
        pos2 = 0;
        LtoR = true;
        indicator = false;
    }

    public void start() {
        synchronized (this) {
            timer.start();
        }
    }

    public void stop() {
        synchronized (this) {
            timer.stop();
            pos1 = pos2 = 0;
            count = 0;
        }
        repaint();
    }

    public void setProgress(int i, int j) {
        if (j == 0) {
            indicator = false;
            pos1 = pos2 = 0;
            repaint();
        } else {
            indicator = true;
            int k = getWidth();
            Insets insets = getInsets();
            k -= insets.left + insets.right;
            pos2 = insets.left;
            pos1 = (k * i) / j;
            repaint();
        }
    }

    public void actionPerformed(ActionEvent actionevent) {
        if (indicator)
            return;
        int i = getWidth();
        Insets insets = getInsets();
        i -= insets.left + insets.right;
        if (LtoR) {
            if (pos1 < i) {
                pos1++;
                if (pos1 > i / 3)
                    pos2++;
            } else {
                pos1 = pos2 = i;
                LtoR = false;
            }
        } else if (pos1 > 0) {
            pos1--;
            if (pos1 < i - i / 3)
                pos2--;
        } else {
            pos1 = pos2 = 0;
            LtoR = true;
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        int i = getWidth();
        int j = getHeight();
        Color color = getBackground();
        Color color1 = getForeground();
        if (c == null) {
            c = new Color[15];
            for (int k = 0; k < 15; k++) {
                int l = (color1.getRed() - (color.getRed() * k) / 15) + color.getRed();
                int j1 = (color1.getGreen() - (color.getGreen() * k) / 15) + color.getGreen();
                int l1 = (color1.getBlue() - (color.getBlue() * k) / 15) + color.getBlue();
                c[k] = new Color(l, j1, l1);
            }

        }
        if (isOpaque()) {
            g.setColor(color);
            g.fillRect(0, 0, i, j);
            g.setColor(color1);
        }
        Insets insets = getInsets();
        i -= insets.left + insets.right;
        j -= insets.top + insets.bottom;
        int i1 = pos1 <= pos2 ? pos2 - pos1 : pos1 - pos2;
        if (LtoR) {
            int k1 = (insets.left + pos1) - i1;
            k1 = k1 <= 0 ? 0 : k1;
            g.fillRect(k1, insets.top, i1, j);
        } else {
            g.fillRect(insets.left + pos1, insets.top, i1, j);
        }
    }

    protected static final int DELAY = 18;
    protected static final int COLOURS = 15;
    protected final Timer timer = new Timer(18, this);
    protected int count;
    protected Color c[];
    protected int pos1;
    protected int pos2;
    protected boolean LtoR;
    protected boolean indicator;
}
