package njfbrowser.misc;

import njfbrowser.main.adminApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class AboutDialog extends JDialog
        implements ActionListener, MouseListener {
    public class AboutDialogWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public AboutDialogWinListener() {
        }
    }


    public AboutDialog(adminApp admnaap) {
        super(admnaap, " About adminApp", true);
        parent = admnaap;
        getContentPane().setLayout(new BorderLayout());

        ImageIcon imgdbqbox = new ImageIcon("cbox/images/browlaunch.gif");

        JLabel topLabel = new JLabel(parent.aplangstrings.getProperty("text000"), JLabel.CENTER);
        urlLabel = new JLabel("tunbleDryCode", imgdbqbox, JLabel.CENTER);
        urlLabel.setForeground(new Color(0, 0, 180));
        urlLabel.addMouseListener(this);


        JPanel inputpan = new JPanel(new BorderLayout());
        inputpan.add("North", topLabel);
        inputpan.add("Center", urlLabel);


        nogo = new JButton("Close");
        nogo.setBackground(new Color(225, 180, 180));
        nogo.setForeground(new Color(0, 0, 180));
        nogo.addActionListener(this);


        JPanel mainpanel = new JPanel(new BorderLayout());
        mainpanel.add("Center", inputpan);
        mainpanel.add("South", nogo);

        getContentPane().add(mainpanel, "Center");
        addWindowListener(new AboutDialogWinListener());
        resize(220, 100);
        setResizable(false);
        setLocation(200, 220);
        show();
        // setred();
    }


    public void actionPerformed(ActionEvent actionevent) {
        if (actionevent.getSource() == nogo) {
            dispose();
        }
    }


    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == urlLabel) {
            parent.favs("http://www.github.com/tunbleDryCode");
            dispose();
        }

    }


    public void mouseEntered(MouseEvent mouseevent) {
        if (mouseevent.getSource() == urlLabel) {
            setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        }
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
        if (mouseevent.getSource() == urlLabel) {
            setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }

    public static ImageIcon getImageIcon(String name) {

        URL url = ClassLoader.getSystemResource(name);
        if (url == null) {
            System.out.println("image " + name + " not found");
            return null;
        }

        return new ImageIcon(url);
    }


    JButton nogo;
    adminApp parent;
    JLabel urlLabel;
}
