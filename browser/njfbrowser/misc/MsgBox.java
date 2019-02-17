package njfbrowser.misc;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MsgBox extends JDialog implements ActionListener {
    public boolean id = false;
    JButton ok, can;

    public MsgBox(Frame frame, String ttl, String msg, boolean okcan) {
        super(frame, ttl, true);
        getContentPane().setLayout(new BorderLayout());


        JPanel mainpan = new JPanel(new BorderLayout(3, 3));

        if (msg.length() < 85) {
            mainpan.add("Center", new JLabel("   " + msg + "   "));
        } else {
            mainpan.add("Center", new TextArea(msg, 6, 60, TextArea.SCROLLBARS_VERTICAL_ONLY));
        }


        JPanel btnpan = new JPanel(new FlowLayout());
        btnpan.add(ok = new JButton("OK"));
        ok.addActionListener(this);
        if (okcan == true) {
            btnpan.add(can = new JButton("Cancel"));
            can.addActionListener(this);
        }
        mainpan.add("South", btnpan);
        getContentPane().add(mainpan, "Center");
        Dimension d = getToolkit().getScreenSize();
        setLocation(d.width / 3, d.height / 3);
        pack();
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ok) {
            id = true;
            setVisible(false);
        } else if (ae.getSource() == can) {
            setVisible(false);
        }
    }
}
