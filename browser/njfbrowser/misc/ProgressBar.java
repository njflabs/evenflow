package njfbrowser.misc;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ProgressBar extends Canvas {

    public ProgressBar() {
        maxValue = 0L;
        currentValue = 0L;
        addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent componentevent) {
                imgBuffer = null;
            }

        });
    }

    public int getPercentDone() {
        return (int) ((100D / (double) maxValue) * (double) currentValue);
    }

    public void addNotify() {
        super.addNotify();
    }

    public void paint(Graphics g) {
        Dimension dimension = getSize();
        if (imgBuffer == null)
            imgBuffer = createImage(dimension.width, dimension.height);
        Graphics g1 = imgBuffer.getGraphics();
        g1.setColor(new Color(225, 225, 225));
        g1.fillRect(0, 0, dimension.width, dimension.height);
        int i = getPercentDone();
        double d = (double) dimension.width / 100D;
        g1.setColor(Color.blue);
        g1.fillRect(0, 0, (int) ((double) i * d), dimension.height);
        g1.setColor(Color.black);
        g1.setFont(getFont());
        FontMetrics fontmetrics = g1.getFontMetrics(getFont());
        String s = i + "%";
        g1.drawString(s, dimension.width / 2 - fontmetrics.stringWidth(s) / 2, dimension.height / 2 + fontmetrics.getMaxAscent() / 2);
        g1.dispose();
        g.drawImage(imgBuffer, 0, 0, this);
    }

    public void setMaxValue(long l) {
        maxValue = l;
        repaint();
    }

    public void update(Graphics g) {
        paint(g);
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }

    public void setCurrentValue(long l) {
        currentValue = l;
        repaint();
    }

    public static void main(String args[]) {
    }

    long maxValue;
    long currentValue;
    Image imgBuffer;
}
