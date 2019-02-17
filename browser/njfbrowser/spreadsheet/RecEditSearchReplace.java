package njfbrowser.spreadsheet;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class RecEditSearchReplace extends Frame
        implements ActionListener {
    public class searchnreplaceWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            dispose();
        }

        public searchnreplaceWindowListener() {
        }
    }


    public RecEditSearchReplace(RecEditPanel receditpanel, String s) {
        setTitle("Find and Replace");
        parent = receditpanel;
        adbudlabel = new Label("Find What:");
        adbudlabel.setFont(new Font("ARIAL", 0, 10));
        adbudlabel.setForeground(new Color(95, 70, 47));
        b = new TextField(s);
        ok = new Button("Ok");
        ok.addActionListener(this);
        cannit = new Button("Close");
        cannit.addActionListener(this);
        Panel panel = new Panel(new FlowLayout());
        panel.add(ok);
        panel.add(cannit);
        newText = new TextField(s);
        Panel panel1 = new Panel(new BorderLayout());
        panel1.add("North", new Label("Replace with:"));
        panel1.add("Center", newText);
        Panel panel2 = new Panel(new BorderLayout());
        panel2.add("North", adbudlabel);
        panel2.add("Center", b);
        Panel panel3 = new Panel(new BorderLayout());
        panel3.add("North", panel2);
        panel3.add("Center", panel1);
        panel3.add("South", panel);
        add(panel3);
        setBackground(new Color(225, 225, 225));
        addWindowListener(new searchnreplaceWindowListener());
        resize(340, 150);
        setLocation(200, 150);
        show();
    }

    public void actionPerformed(ActionEvent actionevent) {
        b.getText();
        Object obj = actionevent.getSource();
        if (obj == cannit)
            dispose();
        if (obj == ok) {
            parent.searchRecAndRep(b.getText(), newText.getText());
            dispose();
        }
    }

    TextField newText;
    Button ok;
    Button cannit;
    TextField b;
    java.awt.List c;
    Label adbudlabel;
    RecEditPanel parent;
}
