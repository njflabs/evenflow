package njfbrowser.misc;

import njfbrowser.spreadsheet.EditItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EDmnuCopy_ActionAdapter
        implements ActionListener {

    public EDmnuCopy_ActionAdapter(EditItem edititem) {
        adaptee = edititem;
    }

    public void actionPerformed(ActionEvent actionevent) {
        adaptee.EDmnuCopy_actionPerformed(actionevent);
    }

    EditItem adaptee;
}
