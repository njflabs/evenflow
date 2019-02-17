package njfbrowser.misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MMMessdialog extends JDialog
        implements ActionListener {
    public class MMMessdialogWinListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public MMMessdialogWinListener() {
        }
    }


    public MMMessdialog(MiniMailer minimailer, String s) {
        super(minimailer, "MiniMailer Alert");
        mmconfirmString = s;
        int i = mmconfirmString.length() * 7;
        parent = minimailer;
        getContentPane().setLayout(new BorderLayout());
        l = new JLabel(s);
        okido = new JButton("close");
        okido.setBackground(new Color(225, 225, 225));
        okido.setForeground(new Color(0, 0, 120));
        JPanel jpanel = new JPanel(new BorderLayout());
        jpanel.add("North", l);
        jpanel.add("South", okido);
        getContentPane().add(jpanel, "Center");
        addWindowListener(new MMMessdialogWinListener());
        okido.addActionListener(this);
        resize(i, 90);
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

    JLabel l;
    JButton okido;
    MiniMailer parent;
    String mmconfirmString;
}
