package njfbrowser.tasks;

import njfbrowser.spreadsheet.DBQueryBox;

import javax.swing.*;

public class taskDBQBoxQuery extends
        SwingWorker<Void, Void> {
    public taskDBQBoxQuery(DBQueryBox dbQueryBox, String tempQ, String dname, String tname) {
        // super(dbQueryBox);
        parent = dbQueryBox;
        strngTaskTempQ = tempQ;
        strngTaskDname = dname;
        strngTaskTname = tname;
        //  parent.setDBQBoxQuery(tempQ, dname, tname);
        //initialize
    }


    public Void doInBackground() {
        try {
            parent.setDBQBoxQuery(strngTaskTempQ, strngTaskDname, strngTaskTname);
            System.out.println("taskDBQBoxQuery doInBackground");
        } catch (Exception ex) {
            System.out.println("ERROR: taskDBQBoxQuery [18]: " + ex.toString());
        }
        return null;
    }


    protected void done() {
        try {
            parent.stopDBQBoxQuery();
            //  dispose();
        } catch (Exception ex) {
            System.out.println("ERROR: taskDBQBoxQuery [23]: " + ex.toString());
        }
    }


    String strngTaskTempQ;
    String strngTaskDname;
    String strngTaskTname;
    DBQueryBox dbQueryBox;
    DBQueryBox parent;

}