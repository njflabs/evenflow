package njfbrowser.tasks;

import njfbrowser.spreadsheet.DBQueryBox;

import javax.swing.*;

public class taskDBQBoxReloadDB extends
        SwingWorker<Void, Void> {
    public taskDBQBoxReloadDB(DBQueryBox dbQueryBox, String dname) {
        // super(dbQueryBox);
        parent = dbQueryBox;

        strngTaskDname = dname;

        //  parent.setDBQBoxQuery(tempQ, dname, tname);
        //initialize
    }


    public Void doInBackground() {
        try {
            parent.reloadDB(strngTaskDname);
            System.out.println("taskDBQBoxReloadDB doInBackground");
        } catch (Exception ex) {
            System.out.println("ERROR: taskDBQBoxReloadDB [18]: " + ex.toString());
        }
        return null;
    }


    protected void done() {
        try {
            parent.stopDBQBReloadDB();
            //  dispose();
        } catch (Exception ex) {
            System.out.println("ERROR: taskDBQBoxReloadDB [23]: " + ex.toString());
        }
    }


    String strngTaskDname;

    DBQueryBox dbQueryBox;
    DBQueryBox parent;

}