package njfbrowser.spreadsheet;


import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class SharpPopmenuLabel extends Label {
    class SHRPLCommclick extends MouseAdapter {

        public void mouseClicked(MouseEvent mouseevent) {
            setForeground(thecolr);
            setBackground(new Color(195, 195, 195));


            if (sLcomtext == "copy") {
                parent.parntsetSharpTrick("copy");
            }
            if (sLcomtext == "paste") {
                parent.parntsetSharpTrick("paste");
            }
            if (sLcomtext == "browserpan") {
                parent.parntsetSharpTrick("browserpan");
            }
            if (sLcomtext == "uloadrecords") {
                parent.parntsetSharpTrick("uloadrecords");
            }
            if (sLcomtext == "addrow") {
                parent.parntsetSharpTrick("addrow");
            }
            if (sLcomtext == "dbqbox") {
                parent.parntsetSharpTrick("dbqbox");
            }
            if (sLcomtext == "closepop") {
                parent.hidefornow();
            }
            if (sLcomtext == "editrecord") {
                parent.parntsetSharpTrick("Teditrecord");
            }
            return;
        }

        public void mousePressed(MouseEvent mouseevent) {
        }

        public void mouseEntered(MouseEvent mouseevent) {
            setForeground(new Color(255, 255, 255));
            setBackground(new Color(0, 0, 160));
        }

        public void mouseExited(MouseEvent mouseevent) {
            setForeground(thecolr);
            setBackground(new Color(195, 195, 195));
        }

        SHRPLCommclick() {
        }
    }


    public SharpPopmenuLabel(SharpPopmenu sharpPopmenu, String s, String s1, int talign, Color clr) {
        super(s, talign);
        linkstring = s;
        _target = "";
        _unvisitedURL = Color.blue;
        _visitedURL = Color.black;
        parent = sharpPopmenu;
        sLcomtext = s1;
        thecolr = clr;
        setForeground(thecolr);
        // setFont(new Font("Arial", 1, 12));
        // setForeground(new Color(0, 0, 160));
        setBackground(new Color(195, 195, 195));
        try {
            addMouseListener(new SHRPLCommclick());
            return;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        // Rectangle r;
        super.paint(g);
        // r = g.getClipBounds();
        // g.drawLine(0, r.height - this.getFontMetrics(this.getFont()).getDescent(), this.getFontMetrics(this.getFont()).stringWidth(linkstring), r.height - this.getFontMetrics(this.getFont()).getDescent());

    }

    public void setUnvisitedURLColor(Color color) {
        _unvisitedURL = color;
    }

    public void setVisitedURLColor(Color color) {
        _visitedURL = color;
    }

    public static void main(String args[]) {
    }


    private Applet _applet;
    private URL _url;
    private String _target;
    Color _unvisitedURL;
    Color _visitedURL;
    Color thecolr;
    String sLcomtext;
    String linkstring;
    SharpPopmenu parent;
}
