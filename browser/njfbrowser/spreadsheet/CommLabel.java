package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.searchReplace;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


public class CommLabel extends Label {
    class Commclick extends MouseAdapter {

        public void mouseClicked(MouseEvent mouseevent) {
            parent.setWaitCursor();
            setForeground(_visitedURL);
            if (commtext == "reloadQs") {
                parent.QqueryTextArea.setText(parent.tempQResultsString);
            }
            if (commtext == "clearQtext") {
                parent.clearQqTextArea();
            }
            if (commtext == "close") {
                System.exit(0);
            }
            if (commtext == "hide") {
                parent.hideme();
            }
            if (commtext == "insertrecords") {
                parent.procsAllRecords();
            }
            if (commtext == "delcomquery") {
                parent.removecomQ();
            }
            if (commtext == "delallcomqueries") {
                parent.comtext.removeAll();
                parent.setQstatus(parent.aplangstrings.getProperty("text172"), false);
            }

            if (commtext == "searchrepQs") {
                new searchReplace(parent, parent.QqueryTextArea.getSelectedText());
            }
            if (commtext == "runcomquery") {
                try {
                    parent.postComtextQuery();
                } catch (Exception exception1) {
                    parent.setQstatus(exception1.toString(), true);
                }
            }
            if (commtext == "runallcomqueries") {
                try {
                    parent.postAllComtextQueries();
                } catch (Exception exception2) {
                    parent.setQstatus(exception2.toString(), true);
                }
            }
            parent.setDefaultCursor();
        }

        public void mousePressed(MouseEvent mouseevent) {
        }

        public void mouseEntered(MouseEvent mouseevent) {
            setForeground(_visitedURL);
            parent.setHandCursor();
            if (commtext == "reloadQs") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text506"));
            }
            if (commtext == "clearQtext") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text175"));
            }
            if (commtext == "close") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text507"));
            }
            if (commtext == "hide") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text508"));
            }
            if (commtext == "insertrecords") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text517"));
            }
            if (commtext == "delcomquery") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text044"));
            }
            if (commtext == "delallcomqueries") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text511"));
            }
            if (commtext == "searchrepQs") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text513"));
            }
            if (commtext == "runcomquery") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text515"));
            }
            if (commtext == "runallcomqueries") {
                parent.tiplabel.setText(parent.aplangstrings.getProperty("text516"));
            }
        }

        public void mouseExited(MouseEvent mouseevent) {
            setForeground(_unvisitedURL);
            parent.setDefaultCursor();
            parent.tiplabel.setText("");
        }

        Commclick() {
        }
    }


    public CommLabel(adminApp myslic, String s, String s1) {
        super(s);
        _target = "";
        _unvisitedURL = Color.blue;
        _visitedURL = Color.black;
        parent = myslic;
        commtext = s1;
        setForeground(_unvisitedURL);
        try {
            addMouseListener(new Commclick());
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

    public static void main(String args[]) {
    }


    private Applet _applet;
    private URL _url;
    private String _target;
    Color _unvisitedURL;
    Color _visitedURL;
    String commtext;
    adminApp parent;
}
