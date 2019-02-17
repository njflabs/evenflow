package njfbrowser.misc;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;

public class ImageButton extends Component {

    public ImageButton(String s, Image image1) {
        colorfilter = new DarkenFilter();
        clicked = false;
        entered = false;
        enabled = true;
        label = s;
        image = image1;
        name = "ImageButton" + nameCounter++;
        super.setName(name);
        width = image.getWidth(this);
        height = image.getHeight(this);
        grayimage = createImage(new FilteredImageSource(image.getSource(), colorfilter));
    }

    public void paint(Graphics g) {
        g.getColor();
        if (enabled) {
            super.paint(g);
            g.drawImage(image, 2, 2, this);
            g.drawRect(1, 1, width, height);
            g.setColor(new Color(215, 215, 215));
            g.drawLine(1, 1, width + 1, 1);
            g.drawLine(1, 1, 1, height + 1);
        }
    }

    public void processMouseEvent(MouseEvent mouseevent) {
        if (!enabled) {
            super.processMouseEvent(mouseevent);
            return;
        }
        switch (mouseevent.getID()) {
            case 501:
                clicked = true;
                repaint();
                break;

            case 504:
                entered = true;
                break;

            case 505:
                entered = false;
                break;
        }
        super.processMouseEvent(mouseevent);
    }

    public void removeActionListener(ActionListener actionlistener) {
        actionListener = AWTEventMulticaster.remove(actionListener, actionlistener);
    }

    public boolean contains(int i, int j) {
        int k = getSize().width;
        int l = getSize().height;
        return i >= 0 && k > i && j >= 0 && l > j;
    }

    public void update(Graphics g) {
        super.paint(g);
    }

    public void addActionListener(ActionListener actionlistener) {
        actionListener = AWTEventMulticaster.add(actionListener, actionlistener);
        enableEvents(16L);
    }

    public Dimension getPreferredSize() {
        return new Dimension(width + 4, height + 4);
    }

    public Dimension getMinimumSize() {
        return new Dimension(width + 4, height + 4);
    }

    public void setEnabled(boolean flag) {
        enabled = flag;
    }

    protected String paramString() {
        return super.paramString() + ",label=" + label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String s) {
        label = s;
        invalidate();
    }

    Image image;
    Image grayimage;
    ImageFilter colorfilter;
    int width;
    int height;
    String label;
    String name;
    private boolean clicked;
    private boolean entered;
    ActionListener actionListener;
    private static final String base = "ImageButton";
    private static int nameCounter;
    private boolean enabled;
}
