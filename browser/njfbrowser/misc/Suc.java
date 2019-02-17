package njfbrowser.misc;

import java.awt.*;

class Suc extends Frame {

    public Suc(String s) {
        super("Astro Solutions");
        pack();
        resize(200, 150);
        Label1 = new Label("Download erfolgreich! ");
        Label2 = new Label("Datei abgelegt unter: " + s);
        Button1 = new Button("Weiter");
        Panel panel = new Panel();
        panel.add(Label1);
        panel.add(Label2);
        panel.add(Button1);
        add("Center", panel);
        show();
    }

    public static void main(String args[]) {
        new Suc("");
    }

    public boolean handleEvent(Event event) {
        if (event.id == 1001 && event.arg == "Weiter")
            hide();
        if (event.id == 201)
            hide();
        return false;
    }

    Label Label1;
    Label Label2;
    Button Button1;
}
