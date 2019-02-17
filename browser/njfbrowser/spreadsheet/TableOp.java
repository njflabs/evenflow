package njfbrowser.spreadsheet;

 /*
  * @(#)TableOp.java
  *
  * $Id: TableOp.java,v 1.40 2002/08/08 05:15:09 huaz Exp $
  *
  * Created on November 5, 2000, 11:21 PM
  */

import njfbrowser.main.adminApp;
import njfbrowser.misc.Debug;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * This contain certain higher level operations on the spreadsheet table
 * such as sorting.
 *
 * @author Ricky Chin
 * @version $Revision: 1.40 $
 */
public class TableOp implements ActionListener {

    // these variables correspond to the variables in SharpTools
    private SharpTools sharp;
    private JTable table;
    private SharpTableModel tableModel;
    private History history;
    private JToolBar toolBar;
    final static private ImageIcon colwidthIcon = SharpTools.getImageIcon("colwidth.gif");
    //    private Histogram histo;


    adminApp adminapp;

    // constructor
    TableOp(SharpTools sharp) {
        init(sharp);
        sharp.checkFindNextState();

        // Identifying the Insert/Delete KeyStroke user can modify this
        // to some other Key combination.

        KeyStroke insertrow = KeyStroke.getKeyStroke
                (KeyEvent.VK_INSERT,
                        ActionEvent.CTRL_MASK,
                        false);

        KeyStroke insertcolumn = KeyStroke.getKeyStroke
                (KeyEvent.VK_INSERT,
                        ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK,
                        false);

        KeyStroke deleterow = KeyStroke.getKeyStroke
                (KeyEvent.VK_DELETE,
                        ActionEvent.CTRL_MASK,
                        false);

        KeyStroke deletecolumn = KeyStroke.getKeyStroke
                (KeyEvent.VK_DELETE,
                        ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK,
                        false);

        table.registerKeyboardAction(this, "Insert Row", insertrow,
                JComponent.WHEN_FOCUSED);

        table.registerKeyboardAction(this, "Insert Column", insertcolumn,
                JComponent.WHEN_FOCUSED);

        table.registerKeyboardAction(this, "Delete Row", deleterow,
                JComponent.WHEN_FOCUSED);

        table.registerKeyboardAction(this, "Delete Column", deletecolumn,
                JComponent.WHEN_FOCUSED);

    }


    /**
     * init method.
     *
     * @param sharp the GUI object
     */
    private void init(SharpTools sharp) {
        this.sharp = sharp;
        table = sharp.getTable();
        tableModel = sharp.getTableModel();
        history = sharp.getHistory();
        // toolBar = sharp.getToolBar();
    }


    /* insert a row or column */
    public void insert(boolean byRow) {
        //checks if anything is selected
        int insertNum = table.getSelectedRowCount();
        if (insertNum != 0) {

            if (byRow) {
/*  I put this in for the if by row.
        int row = table.getSelectedRow();
		CellRange range = new CellRange(new CellPoint(row, SharpTools.baseCol), new CellPoint(row+insertNum-1, tableModel.getColumnCount()-1));
	 	history.add(tableModel, range, History.INSERTROW);
	       tableModel.insertRow(range);
*/
                tableModel.addRow();
            } else {
                insertNum = table.getSelectedColumnCount();
                int column = table.getSelectedColumn();
                CellRange range = new CellRange(new CellPoint(SharpTools.baseRow, column), new CellPoint(tableModel.getRowCount() - 1, column + insertNum - 1));

                history.add(tableModel, range, History.INSERTCOLUMN);
                tableModel.insertColumn(range);

            }
        } else {
            sharp.noCellsSelected("Insert");
        }
    }

    public void remove(boolean byRow) {
        int removeNum = table.getSelectedRowCount();

        //checks if anything is selected

        if (removeNum != 0) {

            if (byRow) {

                if (removeNum == table.getRowCount() - 1) {
                    tooMuchDeletion();
                    return;
                }

                int row = table.getSelectedRow();
                CellRange range = new CellRange(new CellPoint(row, SharpTools.baseCol), new CellPoint(row + removeNum - 1, tableModel.getColumnCount() - 1));

                if (tableModel.isDeletionSafe(range, true) || unsafeDeletion()) {
                    history.add(tableModel, range, History.REMOVEROW);
                    tableModel.removeRow(range);
                    Debug.println(range);

                    Debug.println("Delete row range " + range);
                }
            } else {
                int column = table.getSelectedColumn();

                removeNum = table.getSelectedColumnCount();

                if (removeNum == table.getColumnCount() - 1) {
                    tooMuchDeletion();
                    return;
                }

                CellRange range =
                        new CellRange(new CellPoint(SharpTools.baseRow, column),
                                new CellPoint(tableModel.getRowCount() - 1,
                                        column + removeNum - 1));
                Debug.println("Delete column range " + range);
                if (tableModel.isDeletionSafe(range, false) || unsafeDeletion()) {
                    history.add(tableModel, range, History.REMOVECOLUMN);
                    Debug.println(range);
                    tableModel.removeColumn(range);
                }
            }

        } else {
            sharp.noCellsSelected("Remove");
        }
    }

    public void sort(boolean byRow) {
        //checks if anything is selected
        if (table.getSelectedRowCount() != 0) {
            CellRange range = new CellRange
                    (table.getSelectedRows(), table.getSelectedColumns());


            //gets parameters for combo box in dialog
            Vector primary = new Vector();
            Vector secondary = new Vector();
            secondary.add("None");
            if (byRow) {
                for (int i = range.getStartRow(); i <= range.getEndRow(); i++) {
                    primary.add("Row " + Node.translateRow(i));
                    secondary.add("Row " + Node.translateRow(i));
                }
            } else {
                for (int i = range.getStartCol(); i <= range.getEndCol(); i++) {
                    primary.add("Column " + Node.translateColumn(i));
                    secondary.add("Column " + Node.translateColumn(i));
                }
            }

            //create and show the sort dialog
            SortDialog sortDialog = new SortDialog(adminapp, primary, secondary);
            sortDialog.pack();
            sortDialog.setLocationRelativeTo(sharp);
            sortDialog.setVisible(true);


            Debug.println("Here we go " + sortDialog.getCriteriaA() + ": And : " + sortDialog.getCriteriaB());
            Debug.println(sortDialog.firstAscending() + " : " + sortDialog.secondAscending());
            int first = sortDialog.getCriteriaA();
            //check to see if there is sorting criteria
            if (first >= 0) {
                history.add(tableModel, range);
                //translate first to col/row number
                if (byRow) {
                    first += range.getStartRow();
                } else {
                    first += range.getStartCol();
                }

                int second = sortDialog.getCriteriaB();
                //check to see if there is second sorting criteria
                if (second > 0) {
                    //translate second to col/row number
                    if (byRow) {
                        //takes into account "none" choice
                        second += range.getStartRow() - 1;
                    } else {
                        second += range.getStartCol() - 1;
                    }

                    tableModel.sort(range, first, second, byRow,
                            sortDialog.firstAscending(),
                            sortDialog.secondAscending());
                } else {
                    //sort according to first only
                    tableModel.sort(range, first, first, byRow,
                            sortDialog.firstAscending(),
                            sortDialog.secondAscending());
                }
            }
        } else {
            sharp.noCellsSelected("Sort");
        }
    }

    // set the column width
    public void setColumnWidth() {
        int width = SharpTools.getConfig().getInt("COLUMNWIDTH");
        Integer input = SharpOptionPane.showIntegerInputDialog
                (sharp,
                        "Please input the column width in pixels: ",
                        "Column Width", JOptionPane.QUESTION_MESSAGE, colwidthIcon,
                        new Integer(width));
        if (input != null) {
            try {
                int newwidth = input.intValue();
                if (newwidth != width && newwidth >= 0) {
                    SharpTools.getConfig().setInt("COLUMNWIDTH", newwidth);
                    sharp.setColumnWidth(newwidth);
                }
            } catch (Exception e) {
            }
        }
    }

    private boolean unsafeDeletion() {

        int choice =
                SharpOptionPane.showOptionDialog
                        (sharp,
                                "The deletion may cause irriversible data loss in other cells.\n\n" +
                                        "Do you really want to proceed?\n\n",
                                "Delete",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null, 1);

        return choice == JOptionPane.YES_OPTION;
    }

    private void tooMuchDeletion() {

// if (removeNum == table.getRowCount()-1) {
// if (removeNum == table.getColumnCount()-1) {

        String fsTMD = "getSelectedRowCount : " + String.valueOf(table.getSelectedRowCount());
        fsTMD += "getColumnCount : " + String.valueOf(table.getColumnCount());
        fsTMD += "getRowCount : " + String.valueOf(table.getRowCount());
        fsTMD += "getSelectedRow : " + String.valueOf(table.getSelectedRow());
        sharp.setParentQstatus("You can not delete all the rows or columns");
/*
        SharpOptionPane.showMessageDialog
	   (sharp, "You can not delete all the rows or columns!", "Delete",
	     JOptionPane.ERROR_MESSAGE);
*/
        System.out.println("You can not delete all the rows or columns - getSelectedRowCount is less than getRowCount or getColumnCount: " + fsTMD);
    }

    /**
     * This method is activated on the Keystrokes we are listening to
     * in this implementation. Here it listens for keystroke
     * ActionCommands.
     * <p>
     * Without this listener, when we press certain keys the individual
     * cell will be activated into editing mode in addition to the
     * effect of the key accelerators we defined with menu items.
     * With this key listener, we avoid this side effect.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().compareTo("Insert Row") == 0) {
            insert(true);
        } else if (e.getActionCommand().compareTo("Insert Column") == 0) {
            insert(false);
        } else if (e.getActionCommand().compareTo("Delete Row") == 0) {
            remove(true);
        } else if (e.getActionCommand().compareTo("Delete Column") == 0) {
            remove(false);
        }
    }

}
