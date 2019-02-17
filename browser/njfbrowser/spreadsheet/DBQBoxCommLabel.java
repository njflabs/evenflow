package njfbrowser.spreadsheet;


import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class DBQBoxCommLabel extends Label {
    class DBQCommclick extends MouseAdapter {

        public void mouseClicked(MouseEvent mouseevent) {
            // parent.progressBar.setIndeterminate(true);
            // setForeground(_visitedURL);
            if (dbqcommtext == "reloadDB") {
                parent.startDBQBReloadDB(parent.dbsDBQChoice.getSelectedItem());
            }
            if (dbqcommtext == "deleterecords") {
                parent.giveThequery(false, true);
            }
            if (dbqcommtext == "runquery") {
                parent.giveThequery(true, true);
            }
            if (dbqcommtext == "runtextquery") {
                parent.runTextQ();
            }
/*
            if(dbqcommtext == "runTAquery") {
                parent.runTAreaQ();
            }
*/
            if (dbqcommtext == "minmax") {
                parent.isitDBQBolder();
            }
            if (dbqcommtext == "brwDBaction") {
                parent.getBrowDBA();
            }
            if (dbqcommtext == "savethequery") {
                try {
                    System.out.println(" parent.addQuery() - from commlabel");

                } catch (Exception exception) {
                    parent.setTheQstatus(exception.toString());
                }
            }


        }

        public void mousePressed(MouseEvent mouseevent) {
        }

        public void mouseEntered(MouseEvent mouseevent) {
            parent.setTheHandCursor();
            setForeground(_visitedURL);
            if (dbqcommtext == "deleterecords") {
                parent.getLangString("text132");
            }
            if (dbqcommtext == "brwDBaction") {
                parent.getLangString("text086");
            }
            if (dbqcommtext == "runquery") {
                parent.getLangString("text510");
            }
            if (dbqcommtext == "runtextquery") {
                parent.getLangString("text545");
            }
            if (dbqcommtext == "minmax") {

                if (!parent.boolDbQBold) {
                    parent.getLangString("text020");
                    return;
                } else {
                    parent.getLangString("text021");
                    return;
                }


            }
            if (dbqcommtext == "savethequery") {
                parent.getLangString("text514");
            }

        }

        public void mouseExited(MouseEvent mouseevent) {
            parent.setTheDefaultCursor();
            setForeground(_unvisitedURL);

            parent.getLangString("text006");
        }

        DBQCommclick() {
        }
    }


    public DBQBoxCommLabel(DBQueryBox dbqbox, String s, String s1) {
        super(s, 1);
        _target = "";
        _unvisitedURL = Color.blue;
        _visitedURL = Color.black;
        parent = dbqbox;
        dbqcommtext = s1;
        setForeground(_unvisitedURL);
        try {
            addMouseListener(new DBQCommclick());
            return;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public void setUnvisitedURLColor(Color color) {
        _unvisitedURL = color;
    }

    public void setVisitedURLColor(Color color) {
        _visitedURL = color;
    }

    private Applet _applet;
    private URL _url;
    private String _target;
    Color _unvisitedURL;
    Color _visitedURL;
    String dbqcommtext;
    DBQueryBox parent;
}
