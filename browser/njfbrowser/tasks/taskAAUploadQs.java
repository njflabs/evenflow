package njfbrowser.tasks;

import njfbrowser.main.adminApp;

import javax.swing.*;

public class taskAAUploadQs extends
        SwingWorker<Void, Void> {
    public taskAAUploadQs(adminApp aApp) {
        // super(dbQueryBox);
        parent = aApp;
    }


    public Void doInBackground() {
        try {
            parent.procsAllRecords();
            System.out.println("taskAAUploadQs doInBackground");
        } catch (Exception ex) {
            System.out.println("ERROR: taskAAUploadQs [18]: " + ex.toString());
        }
        return null;
    }


    protected void done() {
        try {
            parent.stopUploadQs();
            //  dispose();
        } catch (Exception ex) {
            System.out.println("ERROR: taskAAUploadQs [23]: " + ex.toString());
        }
    }


    adminApp aApp;
    adminApp parent;

}