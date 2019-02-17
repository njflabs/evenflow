package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.Messdialog1;

public class DBMiscActions {


    public DBMiscActions(adminApp admnapp, int i) {
        qchoiceI = i;
        parent = admnapp;
        procsQlistChoice();
    }


    public void procsQlistChoice() {
        int itdate = Integer.parseInt(adminApp.getTimeStamp());
        int ittdate = itdate - 86400;
        String tdate = Integer.toString(itdate);
        String ttdate = Integer.toString(ittdate);
        String miscs = "";
        if (qchoiceI == 0) {
            parent.QqueryTextArea.setText("select * from cart_owner where CartDate BETWEEN '" + ttdate + "' AND '" + tdate + "' ORDER BY CartDate;");
            // parent.runTextAreaQ();
        } else if (qchoiceI == 1) {
            miscs = parent.QqueryTextArea.getSelectedText();
            if (miscs.length() < 17) {
                new Messdialog1(parent, parent.aplangstrings.getProperty("text310"));
            } else {
                parent.gotoit(parent.hostfolder + "cart.php?donecart=" + miscs, 0);
            }
        } else if (qchoiceI == 2) {
            parent.QqueryTextArea.setText(adminApp.getTimeStamp());
        } else if (qchoiceI == 3) {
            parent.QqueryTextArea.setText("select * from product where UpsellType like 'npcI';");
            // parent.runTextAreaQ();
        } else {
            System.out.println("DBMiscActions! Not 0");
        }
    }

    adminApp parent;
    int qchoiceI;

}
