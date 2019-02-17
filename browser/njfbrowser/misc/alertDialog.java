package njfbrowser.misc;

import njfbrowser.main.adminApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class alertDialog extends JDialog
        implements ActionListener {
    public class LHMessdialogWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public LHMessdialogWinListener() {
        }
    }


    public alertDialog(adminApp admnaap, String s) {
        super(admnaap, " ", true);
        parent = admnaap;
        setTitle(parent.aplangstrings.getProperty("text501", "adminApp Alert"));
        confirmString = s;
        int i = confirmString.length() * 7;
        int j = i;
        getContentPane().setLayout(new BorderLayout());
        lhAlertTArea = new TextArea(confirmString, 6, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
        lhAlertLabel = new JLabel("  " + confirmString);
        okido = new JButton(parent.aplangstrings.getProperty("text507", "Close"));
        okido.setBackground(new Color(225, 180, 180));
        okido.setForeground(new Color(0, 0, 180));
        JPanel jpanel = new JPanel(new BorderLayout());
        if (confirmString.length() < 85) {
            // jpanel.add("Center", lhAlertLabel);
        } else {
            // jpanel.add("Center", lhAlertTArea);
        }
        jpanel.add("Center", lhAlertTArea);
        jpanel.add("South", okido);
        getContentPane().add(jpanel, "Center");
        addWindowListener(new LHMessdialogWinListener());
        okido.addActionListener(this);
        if (confirmString.length() < 85) {
            resize(j, 90);
        } else {
            resize(360, 180);
        }

        setResizable(false);
        setForeground(new Color(0, 0, 120));
        setLocation(110, 220);
        show();
        // setred();
    }

    public static void main(String args[]) {
    }

    public void setred() {
        okido.setBackground(new Color(255, 0, 0));
        for (int j = 0; j < 5000; j++) {
            System.out.println(String.valueOf(j));
        }
        okido.setBackground(new Color(225, 225, 225));
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == okido)
            dispose();
    }

    JLabel lhAlertLabel;
    JButton okido;
    adminApp parent;
    String confirmString;
    TextArea lhAlertTArea;
}
