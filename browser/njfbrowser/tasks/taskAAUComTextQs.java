package njfbrowser.tasks;

import njfbrowser.main.adminApp;

import javax.swing.*;

public class taskAAUComTextQs extends
        SwingWorker<Void, Void> {
    public taskAAUComTextQs(adminApp aApp, String wqs) {
        // super(dbQueryBox);
        parent = aApp;
        whichQs = wqs;


        // progressBarAA.setIndeterminate(true);

    }


    public Void doInBackground() {
        try {
            if (whichQs.equals("all")) {
                parent.postAllComtextQueries();
            } else if (whichQs.equals("selected")) {
                parent.postComtextQuery();
            } else {
                parent.writeFile(parent.currentDB, whichQs);
            }
            System.out.println("taskAAUComTextQs doInBackground");
        } catch (Exception ex) {
            System.out.println("ERROR: taskAAUComTextQs [18]: " + ex.toString());
        }
        return null;
    }


    protected void done() {
        try {
            parent.stopUploadQs();
            //  dispose();
        } catch (Exception ex) {
            System.out.println("ERROR: taskAAUComTextQs [23]: " + ex.toString());
        }
    }


    String whichQs;
    adminApp aApp;
    adminApp parent;

}