package njfbrowser.spreadsheet;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class RecAlertDialog extends Dialog {

    RecAlertDialog(String s, Frame frame) {
        super(frame, "Alert:", false);
        setTitle("Alert:");
        aboutPanel = new Panel();
        lblCopyright = new Label("mySlic2.0", 1);
        lblWebSite = new Label(s, 1);
        lblEmail = new Label("", 1);
        okPanel = new Panel(new BorderLayout());
        btnOK = new Button("OK");
        pf = frame;
        setLayout(new BorderLayout());
        aboutPanel.add("Center", lblWebSite);
        btnOK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent) {
                dispose();
            }

        });
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent windowevent) {
                dispose();
            }

        });
        okPanel.add("South", btnOK);
        aboutPanel.add(okPanel);
        setResizable(false);
        okPanel.setBackground(new Color(250, 250, 150));
        add("Center", aboutPanel);
        pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);
        setVisible(true);
    }

    Frame pf;
    Panel aboutPanel;
    Label lblCopyright;
    Label lblWebSite;
    Label lblEmail;
    Panel okPanel;
    Button btnOK;
    RecEditPanel receditpanel;
}
