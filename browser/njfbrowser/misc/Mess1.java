package njfbrowser.misc;

import java.awt.*;

class Mess1 extends Frame {

    public Mess1(String s) {
        super("Info");
        tracker = new MediaTracker(this);
        pack();
        resize(240, 90);
        l = new Label(s);
        okido = new Button("close");
        okido.setBackground(new Color(217, 209, 175));
        okido.setForeground(new Color(0, 0, 120));
        Panel panel = new Panel(new BorderLayout());
        panel.add("North", l);
        panel.add("South", okido);
        add("Center", panel);
        setResizable(false);
        setBackground(new Color(217, 209, 175));
        setForeground(new Color(0, 0, 120));
        setLocation(220, 220);
        show();
    }

    public static void main(String args[]) {
    }

    public boolean handleEvent(Event event) {
        if (event.arg == "close") {
            hide();
            dispose();
        }
        if (event.id == 201)
            hide();
        return false;
    }

    Label l;
    Thread t;
    Button okido;
    Image img8;
    MediaTracker tracker;
}
