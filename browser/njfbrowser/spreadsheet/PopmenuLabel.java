package njfbrowser.spreadsheet;


import njfbrowser.misc.Popmenu;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class PopmenuLabel extends Label {
    class SLCommclick extends MouseAdapter {

        public void mouseClicked(MouseEvent mouseevent) {
            setForeground(new Color(0, 0, 160));
            setBackground(new Color(195, 195, 195));


/*
        sLbrowsePpup = new PopmenuLabel(this, "Browser PopUp", "browserPpup");
        sLshophome = new PopmenuLabel(this, "Shop Home", "shophome");
        sLadminhome = new PopmenuLabel(this, "Admin Home", "adminhome");
        sLeditProd = new PopmenuLabel(this, "Edit Page Product", "editprod");     
        sLaddProd = new PopmenuLabel(this, "Add Product", "addprod"); 
        sLeditcat = new PopmenuLabel(this, "Edit Page Category", "editcat");     
        sLaddcat = new PopmenuLabel(this, "Add Category", "addcat"); 
        sLniceprice = new PopmenuLabel(this, "Nice Price", "niceprice");     
        sLclosePop = new PopmenuLabel(this, "Close", "closepop"); 

            if(commtext == "browserPpup") {
            parent.parntSetBrwsrPopBool();
            }
*/

            if (commtext == "shophome") {
                parent.parntsetHmeCanvasUrl();
            }
            if (commtext == "adminhome") {
                parent.parntsetAdmCanvasUrl();
            }
            if (commtext == "editprod") {
                parent.parntsetBrowTrick("editprod");
            }
            if (commtext == "editcat") {
                parent.parntsetBrowTrick("editcat");
            }
            if (commtext == "addcat") {
                parent.parntsetBrowTrick("addcat");
            }
            if (commtext == "niceprice") {
                parent.parntsetBrowTrick("niceprice");
            }
            if (commtext == "showdbpan") {
                parent.parntsetBrowTrick("showdbpan");
            }
            if (commtext == "refreshpage") {
                parent.parntReloadCanvas();
            }
            if (commtext == "closepop") {
                parent.goaway();
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
            setForeground(new Color(0, 0, 160));
            setBackground(new Color(195, 195, 195));
        }

        SLCommclick() {
        }
    }


    public PopmenuLabel(Popmenu popmenu, String s, String s1, int talign) {
        super(s, talign);
        linkstring = s;
        _target = "";
        _unvisitedURL = Color.blue;
        _visitedURL = Color.black;
        parent = popmenu;
        commtext = s1;
        setForeground(new Color(0, 0, 160));
        setBackground(new Color(195, 195, 195));
        try {
            addMouseListener(new SLCommclick());
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
    String commtext;
    String linkstring;
    Popmenu parent;
}
