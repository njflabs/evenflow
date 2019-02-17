package njfbrowser.misc;

import njfbrowser.main.adminApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class StatusBox extends JDialog
        implements ActionListener, MouseListener {
    public class StatusBoxWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            parent.boolstatusBoxOpen = false;
            hide();
        }

        public StatusBoxWinListener() {
        }
    }


    public StatusBox(adminApp admnaap) {
        super(admnaap, "Status Output");
        parent = admnaap;
        getContentPane().setLayout(new BorderLayout());

        ImageIcon imgdbqbox = new ImageIcon("cbox/images/browlaunch.gif");

        JLabel topLabel = new JLabel(parent.aplangstrings.getProperty("text000"), JLabel.CENTER);
        QstatusTextArea = new JTextArea("", 8, 50);

        JScrollPane qscrollPane = new JScrollPane(QstatusTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        urlLabel = new JLabel("visit: profitCode Software", imgdbqbox, JLabel.CENTER);
        urlLabel.setForeground(new Color(0, 0, 180));
        urlLabel.addMouseListener(this);


        JPanel inputpan = new JPanel(new BorderLayout());
        inputpan.add("Center", qscrollPane);
        // inputpan.add("Center", urlLabel);


        nogo = new JButton("Close");
        nogo.setBackground(new Color(225, 180, 180));
        nogo.setForeground(new Color(0, 0, 180));
        nogo.addActionListener(this);


        JPanel mainpanel = new JPanel(new BorderLayout());
        mainpanel.add("Center", inputpan);
        mainpanel.add("South", nogo);

        getContentPane().add(mainpanel, "Center");
        addWindowListener(new StatusBoxWinListener());
        pack();
        //        resize(500, 200);
        // setResizable(false);
        setLocation(50, 100);
        setVisible(false);
        // setred();
    }


    public void actionPerformed(ActionEvent actionevent) {
        if (actionevent.getSource() == nogo) {
            parent.boolstatusBoxOpen = false;
            hide();
        }
    }


    public void mousePressed(MouseEvent mouseevent) {
        if (mouseevent.getSource() == urlLabel) {
            parent.setCanvasUrl("http://www.profitcode.net");
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

    public void setCurrStatus(String name) {
        QstatusTextArea.append(name);
    }

    JTextArea QstatusTextArea;
    JButton nogo;
    adminApp parent;
    JLabel urlLabel;
}
