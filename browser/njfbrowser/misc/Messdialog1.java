package njfbrowser.misc;


import njfbrowser.main.adminApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Messdialog1 extends Dialog
        implements ActionListener {
    public class Messdialog1WinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public Messdialog1WinListener() {
        }
    }


    public Messdialog1(adminApp admapp, String s) {

        super(admapp, "Alert");
        parent = admapp;

        confirmString = "  " + s;
        int slength = confirmString.length() * 7;
        int mslength = confirmString.length() * 7;

        setLayout(new BorderLayout());
        lhAlertLabel = new Label(confirmString);
        okido = new Button("close");
        okido.setBackground(new Color(225, 225, 225));
        okido.setForeground(new Color(0, 0, 120));
        Panel panel = new Panel(new BorderLayout());
        panel.add("Center", lhAlertLabel);
        panel.add("South", okido);
        add(panel, BorderLayout.CENTER);
        addWindowListener(new Messdialog1WinListener());
        okido.addActionListener(this);
        resize(mslength, 90);
        setResizable(false);
        setBackground(new Color(225, 225, 225));
        setForeground(new Color(0, 0, 120));
        setLocation(110, 220);
        show();
    }

    public static void main(String args[]) {
    }

    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == okido)
            dispose();
    }

    Label lhAlertLabel;
    Button okido;
    adminApp parent;
    String confirmString;
}
