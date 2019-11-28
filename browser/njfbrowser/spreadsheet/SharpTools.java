package njfbrowser.spreadsheet;


import njfbrowser.main.adminApp;
import njfbrowser.misc.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;

public class SharpTools extends JPanel implements MouseListener, KeyListener, ActionListener, ListSelectionListener {


    public SharpTools(adminApp adminapp) {

        parent = adminapp;


        // read configuration file
        config = new Config("cbox/prefs/adminapp.ini");

        // set default value
        config.setInt("ROWS", 20);
        config.setInt("COLUMNS", 10);
        //	config.set("AUTORESIZE", "TRUE");
        config.setInt("HISTOGRAMWIDTH", 600);
        config.setInt("HISTOGRAMHEIGHT", 400);

        // read file
        config.load();


        // only change it when DEBUG is uncommented in the config file
        if (config.get("DEBUG") != null)
            Debug.setDebug(config.getBoolean("DEBUG"));

        // initialize the function handler table object
        Formula.registerFunctions();


        setLayout(new BorderLayout());
        JPanel mainpan = new JPanel(new BorderLayout());


        table = new JTable();

        jppupmenu = new JPopupMenu();

        sLcopy = new JMenuItem(parent.aplangstrings.getProperty("text160", "Copy"));
        sLpaste = new JMenuItem(parent.aplangstrings.getProperty("text161", "Paste"));
        sLfill = new JMenuItem(parent.aplangstrings.getProperty("text162", "Fill Column"));
        sLuploadrecords = new JMenuItem(parent.aplangstrings.getProperty("text182", "Upload All Records to Database"));
        sLuploadSelRec = new JMenuItem(parent.aplangstrings.getProperty("text181", "Upload Selected Record to Database"));
        sLaddRow = new JMenuItem(parent.aplangstrings.getProperty("text191", "Add Row"));
        sLaddMRows = new JMenuItem(parent.aplangstrings.getProperty("text192", "Add Rows"));
        sLaddToPendQ = new JMenuItem(parent.aplangstrings.getProperty("text520", "Add to Pending Query List"));
        sLdeleteRow = new JMenuItem(parent.aplangstrings.getProperty("text193", "Delete Row(s)"));
        sLEditRecord = new JMenuItem(parent.aplangstrings.getProperty("text163", "Edit Record"));
        sLdbqBox = new JMenuItem(parent.aplangstrings.getProperty("text214", "Search Database"));
        sLbrowserpan = new JMenuItem(parent.aplangstrings.getProperty("text185", "Web Browser Panel"));


        jppupmenu.add(sLEditRecord);
        jppupmenu.addSeparator();
        jppupmenu.add(sLuploadSelRec);
        jppupmenu.add(sLuploadrecords);
        jppupmenu.addSeparator();
        jppupmenu.add(sLaddToPendQ);
        jppupmenu.addSeparator();
        jppupmenu.add(sLcopy);
        jppupmenu.add(sLpaste);
        jppupmenu.add(sLfill);
        jppupmenu.addSeparator();
        jppupmenu.add(sLaddRow);
        jppupmenu.add(sLaddMRows);
        jppupmenu.addSeparator();
        jppupmenu.add(sLdeleteRow);
        jppupmenu.addSeparator();
        jppupmenu.add(sLdbqBox);


        // jppupmenu.addSeparator();       
        // jppupmenu.add(sLbrowserpan);


        sLcopy.addActionListener(this);
        sLpaste.addActionListener(this);
        sLfill.addActionListener(this);
        sLuploadrecords.addActionListener(this);
        sLuploadSelRec.addActionListener(this);
        sLaddToPendQ.addActionListener(this);
        sLaddRow.addActionListener(this);
        sLaddMRows.addActionListener(this);
        sLdeleteRow.addActionListener(this);
        sLEditRecord.addActionListener(this);
        sLdbqBox.addActionListener(this);
        sLbrowserpan.addActionListener(this);

        jppupmenu.setLightWeightPopupEnabled(false);

        table.add(jppupmenu);


        tracker = new MediaTracker(this);

        newIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/new.gif");
        openIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/open.gif");
        saveIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/save.gif");
        unlockedIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/unlocked.gif");
        //   lockedIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/locked.gif");
        printIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/print.gif");
        undoIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/undo.gif");
        redoIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/redo.gif");
        cutIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/cut.gif");
        copyIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/copy.gif");
        pasteIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/paste.gif");
        findIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/find.gif");
        insertRowIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/insertrow.gif");
        insertColumnIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/insertcolumn.gif");
        deleteRowIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/deleterow.gif");
        deleteColumnIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/deletecolumn.gif");
        sortIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/sort.gif");
        chartIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/chart.gif");
        helpIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/help.gif");
        dbuiIcon = Toolkit.getDefaultToolkit().getImage("cbox/images/adminsheet/dbui.gif");

        tracker.addImage(newIcon, 1);
        tracker.addImage(openIcon, 2);
        tracker.addImage(saveIcon, 3);
        tracker.addImage(unlockedIcon, 4);
        tracker.addImage(printIcon, 5);
        tracker.addImage(undoIcon, 6);
        tracker.addImage(redoIcon, 7);

        tracker.addImage(cutIcon, 8);
        tracker.addImage(copyIcon, 9);
        tracker.addImage(pasteIcon, 10);
        tracker.addImage(findIcon, 11);

        tracker.addImage(insertRowIcon, 12);

        tracker.addImage(insertColumnIcon, 13);
        tracker.addImage(deleteRowIcon, 14);
        tracker.addImage(deleteColumnIcon, 15);
        tracker.addImage(sortIcon, 16);
        tracker.addImage(chartIcon, 17);
        tracker.addImage(helpIcon, 18);
        tracker.addImage(dbuiIcon, 19);

        try {

            tracker.waitForAll();
        } catch (InterruptedException _ex) {
            System.out.println(_ex);
        }


        newButton = new ImageButton("test", newIcon);
        openButton = new ImageButton("test", openIcon);
        saveButton = new ImageButton("test", saveIcon);
        passwordButton = new ImageButton("test", unlockedIcon);
        printButton = new ImageButton("test", printIcon);
        undoButton = new ImageButton("test", undoIcon);
        redoButton = new ImageButton("test", redoIcon);
        cutButton = new ImageButton("test", cutIcon);
        copyButton = new ImageButton("test", copyIcon);
        pasteButton = new ImageButton("test", pasteIcon);
        findButton = new ImageButton("test", findIcon);
        sortButton = new ImageButton("test", sortIcon);
        insertRowButton = new ImageButton("test", insertRowIcon);
        insertColumnButton = new ImageButton("test", insertColumnIcon);
        deleteRowButton = new ImageButton("test", deleteRowIcon);
        deleteColumnButton = new ImageButton("test", deleteColumnIcon);
        dbuiButton = new ImageButton("test", dbuiIcon);
	 
        JPanel toolBarPan = new JPanel(new FlowLayout());

        toolBarPan.add(undoButton);
        toolBarPan.add(redoButton);

        toolBarPan.add(copyButton);
        toolBarPan.add(pasteButton);
        toolBarPan.add(insertRowButton);
        toolBarPan.add(sortButton);
        toolBarPan.add(deleteRowButton);
/*
    toolBarPan.add(dbuiButton);
	toolBarPan.add(printButton);
      toolBarPan.add(cutButton);

	toolBarPan.add(newButton);
	toolBarPan.add(openButton);
	toolBarPan.add(saveButton);
      toolBarPan.add(passwordButton);
      toolBarPan.add(findButton);

      toolBarPan.add(insertColumnButton);
      toolBarPan.add(deleteRowButton);
      toolBarPan.add(deleteColumnButton);

*/


        ImageIcon cup = new ImageIcon("cbox/images/adddata.gif");
        ImageIcon imgdbqbox = new ImageIcon("cbox/images/dbqbox.gif");
        ImageIcon imgtools = new ImageIcon("cbox/images/tools.gif");

        uploadqs = new JButton(parent.aplangstrings.getProperty("text547", "Upload Record(s)"), cup);
        uploadqs.setToolTipText(parent.aplangstrings.getProperty("text517", "Upload all Records from SpreadSheet"));

        uploadqs.setBackground(new Color(225, 180, 180));
        uploadqs.setForeground(new Color(0, 0, 180));
        uploadqs.addActionListener(this);

        jbtnDBQBox = new JButton(parent.aplangstrings.getProperty("text539", "Search Database"), imgdbqbox);
        jbtnDBQBox.setToolTipText(parent.aplangstrings.getProperty("text323", "Open") + " " + parent.aplangstrings.getProperty("text322", "Database Query Box"));
        jbtnDBQBox.setBackground(new Color(225, 180, 180));
        jbtnDBQBox.setForeground(new Color(0, 0, 180));
        jbtnDBQBox.addActionListener(this);

        jbtnTools = new JButton(parent.aplangstrings.getProperty("text324", "Tools"), imgtools);
        jbtnTools.setToolTipText(parent.aplangstrings.getProperty("text324", "Tools") + "  -  " + parent.aplangstrings.getProperty("text325", "Right Clicking over SpreadSheet will also show this menu"));
        jbtnTools.setBackground(new Color(225, 180, 180));
        jbtnTools.setForeground(new Color(0, 0, 180));
        // jbtnTools.addActionListener(this);
        jbtnTools.addMouseListener(this);


        ImageIcon imgIbrowser = new ImageIcon("cbox/images/browlaunch.gif");
        jbtBrowLaunch = new JButton(parent.aplangstrings.getProperty("text177", "Wen Browser"), imgIbrowser);
        jbtBrowLaunch.addMouseListener(this);


        currtDBMnlabel = new JLabel("  " + parent.aplangstrings.getProperty("text079") + ":  ");
        currentDBlabel = new JLabel(parent.aplangstrings.getProperty("text137"));
        currentDBlabel.setForeground(Color.blue);
        currtTblMnlabel = new JLabel(parent.aplangstrings.getProperty("text032") + ":  ");
        currentTbllabel = new JLabel(parent.aplangstrings.getProperty("text137"));
        currentTbllabel.setForeground(Color.blue);



        JPanel sharpcurDBPan = new JPanel(new BorderLayout());
        sharpcurDBPan.add("West", currtDBMnlabel);
        sharpcurDBPan.add("Center", currentDBlabel);

        JPanel sharpcurTblPan = new JPanel(new BorderLayout());
        sharpcurTblPan.add("West", currtTblMnlabel);
        sharpcurTblPan.add("Center", currentTbllabel);

        JPanel sharpTopCommlblPan = new JPanel(new GridLayout(0, 3, 3, 3));
        sharpTopCommlblPan.add(jbtBrowLaunch);
        sharpTopCommlblPan.add(sharpcurDBPan);
        sharpTopCommlblPan.add(sharpcurTblPan);


        JPanel sharpInsertQPan = new JPanel(new BorderLayout());
        // sharpInsertQPan.add("West", jbtnTools);
        sharpInsertQPan.add("Center", jbtnDBQBox);
        sharpInsertQPan.add("East", uploadqs);

// !!!!!!!!!!!!!! Start of the progressBarAA

        progressBarAA = new JProgressBar(10, 100);
        Border pbBorder = progressBarAA.getBorder();
        Border spaceBelow = BorderFactory.createEmptyBorder(0, 0, 5, 0);
        progressBarAA.setBorder(BorderFactory.createCompoundBorder(spaceBelow, pbBorder));
        progressBarAA.setPreferredSize(new Dimension(100, 15));
        progressBarAA.setOpaque(true);
        progressBarAA.setForeground(getForeground());

// !!!!!!!!!!!!!! End of the progressBarAA


        JPanel panelBtnsProgBar = new JPanel(new BorderLayout());
        panelBtnsProgBar.add("Center", sharpInsertQPan);
        panelBtnsProgBar.add("East", progressBarAA);

        JPanel sharpTopLblPan = new JPanel(new BorderLayout());
        sharpTopLblPan.add("East", panelBtnsProgBar);
        // sharpTopLblPan.add("West", toolBarPan);
        sharpTopLblPan.add("Center", sharpTopCommlblPan);


        JPanel sharpTopPan = new JPanel(new BorderLayout());
        sharpTopPan.add("Center", sharpTopLblPan);
        // sharpTopPan.add("East", sharpInsertQPan);

        // newButton.addActionListener(this);
        newButton.addMouseListener(this);
        // openButton.addActionListener(this);
        openButton.addMouseListener(this);
        saveButton.addMouseListener(this);
        passwordButton.addMouseListener(this);
        printButton.addMouseListener(this);
        undoButton.addMouseListener(this);
        redoButton.addMouseListener(this);
        cutButton.addMouseListener(this);
        copyButton.addMouseListener(this);
        pasteButton.addMouseListener(this);
        findButton.addMouseListener(this);
        sortButton.addMouseListener(this);
        insertRowButton.addMouseListener(this);
        insertColumnButton.addMouseListener(this);
        deleteRowButton.addMouseListener(this);
        deleteColumnButton.addMouseListener(this);


        newTableModel(config.getInt("ROWS"), config.getInt("COLUMNS"));

        // set window pos and size
        int w = config.getInt("WIDTH");
        int h = config.getInt("HEIGHT");


        // init fileOp objects
        fileOp = new FileOp(this);

        // clobber resizing of all columns
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // set table editor and renderer to custom ones
        table.setDefaultRenderer(Cell.class, new SharpCellRenderer());
        table.setDefaultEditor(Cell.class, new SharpCellEditor(new JTextField()));

        // set selection mode for contiguous  intervals
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setCellSelectionEnabled(true);


        // we don't allow reordering
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().addMouseListener(new HeaderMouseAdapter());

        table.addMouseListener(new MainTableAdapter());

        // create selection models
        ListSelectionModel rowSelectionModel = table.getSelectionModel();
        ListSelectionModel columnSelectionModel = table.getColumnModel().getSelectionModel();

        // add selection listeners to the selection models
        rowSelectionModel.addListSelectionListener(this);
        columnSelectionModel.addListSelectionListener(this);

        mainpan.add(sharpTopPan, BorderLayout.NORTH);
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainpan.add(scrollPane, BorderLayout.CENTER);


        // initial selection
        resetSelection();

        table.setRequestFocusEnabled(true);
        table.requestFocus();
        add(mainpan);
        // add(popup);
        // table.add(popup);
        // pack();
        show();
    }

    public void setColumnWidth(int colWidth) {

        if (colWidth > 0)
            for (int i = baseCol; i < tableModel.getColumnCount(); i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setMinWidth(colWidth);
                column.setPreferredWidth(colWidth);
            }
    }

    /**
     * Creates new blank SharpTableModel object with specified number of
     * rows and columns.  table is set to this table model to update screen.
     *
     * @param rows number of rows in new table model
     * @param cols number of columns in new table model
     */
    public void newTableModel(int rows, int cols) {

        tableModel = new SharpTableModel(this, rows, cols);
        table.setModel(tableModel);

        setBaseColumnWidth();

        setColumnWidth(config.getInt("COLUMNWIDTH"));

        // update history with new one
        history = new History(this);
        tableModel.setHistory(history);

        // inform tableModel that it's unmodified now
        tableModel.setPasswordModified(false);
        tableModel.setModified(false);

        // init op objects
        // we shouldn't init fileOp!

        if (editOp == null) {
            editOp = new EditOp(this);
        } else {
	    /* if we already have an object, we don't construct
	       a new EditOp to keep the clipboard and findValue
	       still valid.  This makes us be able to exchange
	       data across files */
            editOp.init(this);
        }

        tableOp = new TableOp(this);

        // NOTES!   -   Maybee dont need it for now???
        // histogram = new Histogram(this, "Histograms", config.getInt("HISTOGRAMWIDTH"), config.getInt("HISTOGRAMHEIGHT"));

        tableModel.setModified(false);

        resetSelection();

        // menubar/toolbar initial status
        checkUndoRedoState();

        table.requestFocus();
    }

    /**
     * a function to display warning messages
     *
     * @param s the operation that caused this error
     */
    public void noCellsSelected(String s) {
        SharpOptionPane.showMessageDialog(this, s + ": No cells selected", "Error", JOptionPane.ERROR_MESSAGE, null);
    }

    /**
     * The error message for clicks on functions we haven't done yet.
     */
    private void notYetImplemented() {
        SharpOptionPane.showMessageDialog(this,
                "Sorry, this function \n" +
                        "is not yet implemented!\n" +
                        "sharp@cs.columbia.edu",
                "Sharp Tools Spreadsheet",
                JOptionPane.WARNING_MESSAGE,
                null);
    }

    /** Exit the Application 
     public void exit() {

     }
     */
    /**
     * Directly open a file with specified name - used only in main
     *
     * @param filename the String of filename
     */
    private void openInitFile(String filename) {
        fileOp.openTableModel(new File(filename));
    }


    /**
     * Check menu items and toolbar buttons
     * Set to appropriate status (disable/enable)         
     */

    /**
     * Check the save menu/button state
     * Enable only when the file has been modified
     */
    public void checkSaveState() {
        boolean modified = tableModel.isModified();
//	saveMenuItem.setEnabled(modified);
//	saveButton.setEnabled(modified);
    }

    /**
     * Check the set password toolbar button icon
     * Change the icon based on whether password is set
     */
    public void checkPasswordState() {
        // if (fileOp.hasPassword())
        //    passwordButton.setIcon(lockedIcon);
        // else
        //    passwordButton.setIcon(unlockedIcon);
    }

    /**
     * Check the undo/redo menu/button state
     * Enable only when it's undoable/redoable
     */
    public void checkUndoRedoState() {
/*
	boolean enable = history.isUndoable();
	undoMenuItem.setEnabled(enable);
	undoButton.setEnabled(enable);
	enable = history.isRedoable();
	redoMenuItem.setEnabled(enable);
	redoButton.setEnabled(enable);
*/
    }

    /**
     * Check the find next menu/button state
     * Enable only when the user has searched once
     */
    public void checkFindNextState() {
        // findNextMenuItem.setEnabled(editOp.hasFindValue());
    }

    /**
     * Check the show histogram menu
     * Enable only when there is at least one histogram defined
     */
    public void checkShowHistogramState() {
        // showHistogramMenuItem.setState(histogram.isVisible());
        // showHistogramMenuItem.setEnabled(histogram.hasChart());
    }

    public void setBaseColumnWidth() {
        // resize first column
        if (baseCol > 0) {
            TableColumn firstColumn = table.getColumnModel().getColumn(baseCol - 1);
            int firstColWidth = config.getInt("FIRSTCOLUMNWIDTH");
            if (firstColWidth > 0) {
                firstColumn.setMinWidth(firstColWidth);
                firstColumn.setPreferredWidth(firstColWidth);
            }
        }
    }


    public static ImageIcon getImageIcon(String name) {
        File ifile = new File(name);
        // URL url = ClassLoader.getSystemResource(name);

        if (ifile == null) {
            System.out.println("image " + name + " not found");
            return null;
        }

        return new ImageIcon(name);
    }


    class HeaderMouseAdapter extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            //  System.out.println("HeaderMouseAdapter!!!!!!!!");
            TableColumnModel colModel = table.getColumnModel();
            int col = colModel.getColumn(colModel.getColumnIndexAtX(e.getX())).getModelIndex();

            int rowCount = table.getRowCount();
            table.setRowSelectionInterval(baseRow, rowCount - 1);

            if (col < baseCol) {
                table.setColumnSelectionInterval(baseCol, table.getColumnCount() - 1);
                //  System.out.println("col < baseCol");
            } else {
                table.setColumnSelectionInterval(col, col);
                //   System.out.println("col < baseCol else");
            }
        }

    }


    // the ListSelectionListener interface
    public void valueChanged(ListSelectionEvent e) {
        table.requestFocus();
        // Ignore extra messages
        if (e.getValueIsAdjusting()) return;

        // Get event source
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        if (lsm.isSelectionEmpty() && table.getColumnCount() > baseCol &&
                table.getRowCount() > baseRow) {
            // always set selection
            table.setRowSelectionInterval(baseRow, baseRow);
            table.setColumnSelectionInterval(baseCol, baseCol);
        }


          
/* else {
	    if (table.getSelectedColumn() < baseCol) {
		int columnCount = table.getColumnCount();
                if (columnCount > baseCol) {
                    Debug.println("setColumnSelectionInterval "
                                  + String.valueOf(baseCol)
                                  + " " + String.valueOf(columnCount - 1));
                              
                    table.setColumnSelectionInterval(baseCol, columnCount - 1);
                    table.removeColumnSelectionInterval(baseRow,baseRow);
                }
                }
		}*/
    }


    public void resetSelection() {
        table.setRowSelectionInterval(baseRow, baseRow);
        table.setColumnSelectionInterval(baseCol, baseCol);
    }


    public void actionPerformed(ActionEvent actionevent) {
        Object obj = actionevent.getSource();
        if (obj == uploadqs) {
            MsgBox sharpMsgBox = new MsgBox(parent, parent.aplangstrings.getProperty("text547"), parent.aplangstrings.getProperty("text517") + "?!", true);
            requestFocus();
            if (sharpMsgBox.id) {
                sharpMsgBox.dispose();
                parent.startUploadQs();
                // parent.procsAllRecords();
//parent.startDBQBoxQuery(rQstring, dbsDBQChoice.getSelectedItem(), dbTablesAchoice.getSelectedItem());
            } else {
                sharpMsgBox.dispose();
            }


        }
        if (obj == jbtnDBQBox) {
            parent.getDBQBox();
        }
        if (obj == sLcopy) {
            //  parent.getAppStatus();
            editOp.copy();
            table.requestFocus();
        }
        if (obj == sLpaste) {
            editOp.paste();
            table.requestFocus();
        }
        if (obj == sLfill) {
            editOp.fill();
        }
        if (obj == sLbrowserpan) {
            parent.gettheBrowser();
        }
        if (obj == sLuploadrecords) {
            // parent.procsAllRecords();
            parent.startUploadQs();
        }
        if (obj == sLuploadSelRec) {
            parent.uploadSlctdRowRec();
        }
        if (obj == sLaddToPendQ) {
            addRowToPendingQs();
        }

        if (obj == sLaddRow) {
            tableOp.insert(true);
            table.requestFocus();
        }
        if (obj == sLaddMRows) {
            new SmartAddRows(parent);
            //   tableOp.insert(true);
            //   table.requestFocus();
        }
        if (obj == sLdeleteRow) {
            tableOp.remove(true);
            table.requestFocus();
        }
        if (obj == sLdbqBox) {
            parent.getDBQBox();
        }
        if (obj == sLEditRecord) {
            editRowRecord(true);
        }


    }


    public void keyPressed(KeyEvent keyevent) {
        Object objk = keyevent.getSource();
    }


    public void keyTyped(KeyEvent keyevent) {
    }


    public void keyReleased(KeyEvent keyevent) {
    }

    public void mouseClicked(MouseEvent mouseevent) {

    }

    public void mousePressed(MouseEvent mouseevent) {

        if (mouseevent.getSource() == newButton) {
            fileOp.newFile();
            table.requestFocus();
        }
        if (mouseevent.getSource() == openButton) {
            fileOp.openFile();
            table.requestFocus();
        }
        if (mouseevent.getSource() == saveButton) {
            fileOp.saveFile();
            table.requestFocus();
        }
        if (mouseevent.getSource() == passwordButton) {
            // to delete  for now, use it in future shop integration
            // new CartToolsLeft(parent, "\t\t          ");
            // fileOp.setPassword();
            // table.requestFocus();
        }
        if (mouseevent.getSource() == printButton) {
            Thread runner = new Thread() {
                public void run() {
                    fileOp.printData();
                }
            };
            runner.start();
            table.requestFocus();
        }
        if (mouseevent.getSource() == undoButton) {
            history.undo(tableModel);
            table.requestFocus();
        }
        if (mouseevent.getSource() == redoButton) {
            // new SharpToolsDBUI(this, "hehe	haha	hoho");
            new Messdialog1(parent, parent.aplangstrings.getProperty("text045"));
            history.redo(tableModel);
            // table.requestFocus();
        }
        if (mouseevent.getSource() == cutButton) {
            editOp.cut();
            table.requestFocus();
        }
        if (mouseevent.getSource() == copyButton) {
            editOp.copy();
            table.requestFocus();
        }
        if (mouseevent.getSource() == pasteButton) {
            editOp.paste();
            table.requestFocus();
        }
        if (mouseevent.getSource() == findButton) {
            editOp.find(true);
            table.requestFocus();
        }
        if (mouseevent.getSource() == sortButton) {
            tableOp.sort(false);
            table.requestFocus();
        }
        if (mouseevent.getSource() == insertRowButton) {
            tableOp.insert(true);
            table.requestFocus();
        }
        if (mouseevent.getSource() == insertColumnButton) {
            tableOp.insert(false);
            table.requestFocus();
        }

        if (mouseevent.getSource() == deleteRowButton) {
            tableOp.remove(true);
            table.requestFocus();
        }
        if (mouseevent.getSource() == deleteColumnButton) {
            tableOp.remove(false);
            table.requestFocus();
        }
        if (mouseevent.getSource() == jbtBrowLaunch) {
            parent.gettheBrowser();
        }
        if (mouseevent.getSource() == jbtnTools) {

            Point relative = mouseevent.getPoint();
            Component comp = jbtnTools;

            Point location = comp.getLocationOnScreen();
            // 	Point absolute = new Point(location.x, location.y);
            //     System.out.println("pointssssss:  " + absolute);

            jppupmenu.show(jbtnTools, relative.x, relative.y);
        }
    }


    public void mouseEntered(MouseEvent mouseevent) {
        Component component = mouseevent.getComponent();

        if (component == newButton) {
            setCursor(Cursor.getPredefinedCursor(12));
            // tiplabel.setText(aplangstrings.getProperty("text012"));
            return;
        }
        if (component == openButton) {
            setCursor(Cursor.getPredefinedCursor(12));
            // tiplabel.setText(aplangstrings.getProperty("text012"));
            return;
        }
        if (component == saveButton) {
            setCursor(Cursor.getPredefinedCursor(12));
            // tiplabel.setText(aplangstrings.getProperty("text012"));
            return;
        }
    }


    public void mouseReleased(MouseEvent mouseevent) {

    }


    public void mouseExited(MouseEvent mouseevent) {
        setCursor(Cursor.getPredefinedCursor(0));
    }


    class MainTableAdapter extends MouseAdapter {

        int irow = table.getSelectedRow();
        int icolumn = table.getSelectedColumn();

        public void mousePressed(MouseEvent evt) {
            //  System.out.println("MainTableAdapter " + String.valueOf(irow) + ":" + String.valueOf(icolumn));
            // parent.killSharpPopUp();
            if ((table.getSelectedRow() >= 0) && (table.getSelectedColumn() < 1)) {
                if (evt.getClickCount() == 2) {
                    editRowRecord(true);
                } else {
                    editRowRecord(false);
                }
            }

        }

        public void mouseReleased(MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                Point relative = evt.getPoint();
                // Point relative = evt.getLocationOnScreen();
                Component comp = evt.getComponent();
                Point location = comp.getLocationOnScreen();
                location.translate(evt.getX(), evt.getY());
                // Point absolute = new Point(location.x, location.y);

                //      System.out.println("isPopupTrigger  X: " + relative.x + "  Y: " + relative.y);
                // new JBrowserOpen(parent, "heheheh", 1);
                // popup.show(SharpTools.this, evt.getX(), evt.getY());
                // new SharpPopUp(parent, "heheheh", evt.getX(), evt.getY());
                // parent.getPopUp(relative.x, relative.y);
                //   getsharpPopup(location.x, location.y);
                // jppupmenu.show(table, table.getSize().width / 2, table.getSize().height / 2);
                // jppupmenu.show(table, location.x, location.y);
                jppupmenu.show(table, evt.getX(), evt.getY());
            }
        }


    }


    public void dummyfunction(String s) {

        try {
            hasFieldHeaders = true;
            String firstline = s.substring(0, s.indexOf("\n"));
            //  String firstline = s.substring(0, s.indexOf("<nr>"));
            fieldNames = adminApp.readmessTokens(firstline, "\t");
            fileOp.newQryResultsTbl(s.substring(s.indexOf("\n") + 1, s.length()));
            //  s = parent.replaceString(s, "\n\r", "<nl>");
            //  s = parent.replaceString(s, "\r", "<nl>");
            //  s = parent.replaceString(s, "\n", "<nl>");
            //  parent.setQstatus(s, false);
            //  fileOp.newQryResultsTbl(s.substring(s.indexOf("<nr>") + 1, s.length()));
            history = new History(this);
            tableModel.setHistory(history);


            if (editOp == null) {
                editOp = new EditOp(this);
            } else {
	    /* if we already have an object, we don't construct
	       a new EditOp to keep the clipboard and findValue
	       still valid.  This makes us be able to exchange
	       data across files */
                editOp.init(this);
            }

        } catch (Exception ioExc) {
            parent.setQstatus("Error 874A [ST]: \n" + ioExc.toString() + "\nString:\n" + s, false);

        }

        //   parent.setQstatus("hehehe", true);
        //  fileOp.setPassword();
        // table.requestFocus();

    }


    public void editRowRecord(boolean andedit) {

        TableColumnModel colModel = table.getColumnModel();
        int col = table.getSelectedColumn();
        int row = table.getSelectedRow();
        //    System.out.println(String.valueOf(col) + "           "   + String.valueOf(row));
        boolean toggle = false;
        boolean extend = true;
        int dbuiColumn = table.getColumnCount();
        int dbuiRow = table.getSelectedRow();

        CellRange range = new CellRange(dbuiRow, dbuiRow, 1, table.getColumnCount());
        CellPoint starter = new CellPoint(SharpTools.baseRow + dbuiRow, SharpTools.baseCol + 1);
        table.setRowSelectionInterval(dbuiRow, dbuiRow);
        table.setColumnSelectionInterval(1, dbuiColumn - 1);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(dbuiRow, 0, true)));
        String str = tableModel.toString(range, false);
        String fullTstring = adminApp.replaceString(str, "\t", "");
        System.out.println("The STR: " + str);
        System.out.println("The STR length: " + String.valueOf(fullTstring.trim().length()));

        table.requestFocus();


        if (andedit) {
            if ((fullTstring.length() <= 1) || (parent.currentDBID.equals("555"))) {
                parent.setQstatus(parent.aplangstrings.getProperty("text092"), false);
                System.out.println("NOGO STR: " + str);
            } else {
                new SmartEditRecord(parent, str, fieldNames);
            }

        }


        System.out.println("STR: " + str);
        return;
    }


    public String getRowToString() {
        String str = "noQvalue";

        TableColumnModel colModel = table.getColumnModel();
        int col = table.getSelectedColumn();
        int row = table.getSelectedRow();
        System.out.println(String.valueOf(col) + "           " + String.valueOf(row));
        boolean toggle = false;
        boolean extend = true;
        int dbuiColumn = table.getColumnCount();
        int dbuiRow = table.getSelectedRow();

        CellRange range = new CellRange(dbuiRow, dbuiRow, 1, table.getColumnCount());
        CellPoint starter = new CellPoint(SharpTools.baseRow + dbuiRow, SharpTools.baseCol + 1);
        table.setRowSelectionInterval(dbuiRow, dbuiRow);
        table.setColumnSelectionInterval(1, dbuiColumn - 1);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(dbuiRow, 0, true)));
        str = tableModel.toString(range, false);
        table.requestFocus();

        System.out.println("SharpTools getRowToString [988] str: ");
        return str;
    }


    public String getTableToGo() {
        return tableModel.toString();
    }


    public void setDBString(String dbstring) {
        currentDBlabel.setText(dbstring);
    }

    public void setTblString(String tblstring) {
        currentTbllabel.setText(tblstring);
    }


    public void doTrick(String s) {
        String ststring = s;
    }

    public void getsharpPopup(int x, int y) {
        parent.getSharpPopUp(x, y);
    }

    public void addSmartRow() {
        tableOp.insert(true);
        table.requestFocus();
    }

    public void deleteSmartRow() {
        tableOp.remove(true);
        table.requestFocus();
    }

    public void fillSmartColumn() {
        editOp.fill();
    }

    public void copySmartEdit() {
        editOp.copy();
        table.requestFocus();
    }

    public void pasteSmartEdit() {
        editOp.paste();
        table.requestFocus();
    }


    public void addRowToPendingQs() {
        try {
            String fullPqstring = getRowToString();
            parent.procsRecord(parent.currentDB, parent.tablename, fullPqstring);
        } catch (Exception excepAddPQ) {
            parent.setQstatus(excepAddPQ.toString(), false);
        }
    }


    public void setParentQstatus(String tqStatString) {
        parent.setQstatus(tqStatString, false);
    }




/*
* file functions called from adminApp
*/

    public void doFileNew() {
        fileOp.newFile();
        table.requestFocus();
    }

    public void doFileOpen() {
        fileOp.openFile();
        table.requestFocus();
    }

    public void doFileSave() {
        fileOp.saveFile();
        table.requestFocus();
    }

    public void doFileSaveAs() {
        fileOp.saveAsFile();
        table.requestFocus();
    }

    public void sharpEnableButtons() {
        uploadqs.setEnabled(true);
        jbtnDBQBox.setEnabled(true);
        jbtnTools.setEnabled(true);
    }

    public void sharpDisableButtons() {
        uploadqs.setEnabled(false);
        jbtnDBQBox.setEnabled(false);
        jbtnTools.setEnabled(false);
    }

    boolean hasFieldHeaders = false;
    String fieldNames[];
    ImageButton newButton;
    ImageButton openButton;
    ImageButton saveButton;
    ImageButton passwordButton;
    ImageButton printButton;
    ImageButton undoButton;
    ImageButton redoButton;
    ImageButton cutButton;
    ImageButton copyButton;
    ImageButton pasteButton;
    ImageButton findButton;
    ImageButton sortButton;
    ImageButton insertRowButton;
    ImageButton insertColumnButton;
    ImageButton deleteRowButton;
    ImageButton deleteColumnButton;
    ImageButton chartButton;
    ImageButton helpButton;
    ImageButton dbuiButton;


    Image newIcon;
    Image openIcon;
    Image saveIcon;
    Image unlockedIcon;
    // Image	lockedIcon;
    Image printIcon;
    Image undoIcon;
    Image redoIcon;
    Image cutIcon;
    Image copyIcon;
    Image pasteIcon;
    Image findIcon;
    Image insertRowIcon;
    Image insertColumnIcon;
    Image deleteRowIcon;
    Image deleteColumnIcon;
    Image sortIcon;
    Image chartIcon;
    Image helpIcon;
    Image dbuiIcon;


    JLabel currtDBMnlabel;
    JLabel currentDBlabel;
    JLabel currtTblMnlabel;
    JLabel currentTbllabel;

    JButton uploadqs;
    JButton jbtnDBQBox;
    JButton jbtnTools;
    JButton jbtBrowLaunch;

    protected int maxNumPage = 1;

    // the rest of the components
    Container container;
    JTable table;
    JScrollPane scrollPane;
    // JToolBar toolBar;
    // private JMenuBar menuBar;
    private JPanel barPanel;
    History history;
    Histogram histogram;

    static private Config config;

    private SharpTableModel tableModel;

    private URL url;

    // these are used to access our global objects
    static public Config getConfig() {
        return config;
    }

    public History getHistory() {
        return history;
    }

    public JTable getTable() {
        return table;
    }

    public SharpTableModel getTableModel() {
        return tableModel;
    }

    private FileOp fileOp;
    private EditOp editOp;
    private TableOp tableOp;
    //    private HelpOp helpOp;
    public static int baseRow = 0;
    public static int baseCol = 1;
    adminApp adminapp;
    adminApp parent;
    MediaTracker tracker;

    Component popComponent;
    CommLabel insertQs;

    JPopupMenu jppupmenu;
    JMenuItem sLcopy;
    JMenuItem sLpaste;
    JMenuItem sLfill;
    JMenuItem sLuploadrecords;
    JMenuItem sLuploadSelRec;
    JMenuItem sLaddToPendQ;
    JMenuItem sLaddRow;
    JMenuItem sLaddMRows;
    JMenuItem sLdeleteRow;
    JMenuItem sLEditRecord;
    JMenuItem sLdbqBox;
    JMenuItem sLbrowserpan;

    public JProgressBar progressBarAA;

}

