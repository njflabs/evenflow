package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class RecEditLabel extends Label {
    class Recclick extends MouseAdapter {

        public void mouseClicked(MouseEvent mouseevent) {
            parent.setWaitCursor();
            setForeground(_visitedURL);
            if (rectext == "saverecpan")
                parent.saveRecord();
            if (rectext == "searchreplace")
                parent.searchAndRep();
            if (rectext == "cancel")
                parent.dispose();
            if (rectext == "saverectofile")
                parent.saveRecordToFile();
        }

        public void mousePressed(MouseEvent mouseevent) {
        }

        public void mouseEntered(MouseEvent mouseevent) {
            setForeground(_visitedURL);
            parent.setHandCursor();
        }

        public void mouseExited(MouseEvent mouseevent) {
            setForeground(_unvisitedURL);
            parent.setDefaultCursor();
        }

        Recclick() {
        }
    }


    public RecEditLabel(RecEditPanel receditpanel, String s, String s1) {
        super(s);
        _target = "";
        _unvisitedURL = Color.blue;
        _visitedURL = Color.black;
        parent = receditpanel;
        rectext = s1;
        setForeground(_unvisitedURL);
        try {
            addMouseListener(new Recclick());
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
    String rectext;
    RecEditPanel parent;
    adminApp admiNapp;
}
