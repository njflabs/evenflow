package njfbrowser.tasks;

import njfbrowser.spreadsheet.EditDBdialog;

import javax.swing.*;

public class taskAAUpdateQcomms extends
        SwingWorker<Void, Void> {
    public taskAAUpdateQcomms(EditDBdialog edbDlg) {

        parent = edbDlg;
    }


    public Void doInBackground() {
        try {
            parent.doUpdateQcomms();
            System.out.println("taskAAUpdateQcomms doInBackground");
        } catch (Exception ex) {
            System.out.println("ERROR: taskAAUpdateQcomms [18]: " + ex.toString());
        }
        return null;
    }


    protected void done() {
        try {
            parent.stopUpdateQcomms();
            //  dispose();
        } catch (Exception ex) {
            System.out.println("ERROR: taskAAUpdateQcomms [23]: " + ex.toString());
        }
    }


    EditDBdialog edbDlg;
    EditDBdialog parent;

}