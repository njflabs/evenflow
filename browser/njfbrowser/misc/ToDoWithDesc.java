package njfbrowser.misc;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

public class ToDoWithDesc extends Applet
        implements ActionListener, ItemListener {

    private Button getButton1() {
        if (ivjButton1 == null)
            try {
                ivjButton1 = new Button();
                ivjButton1.setName("Button1");
                ivjButton1.setBounds(329, 109, 56, 23);
                ivjButton1.setLabel("Add");
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjButton1;
    }

    private TextField getTextField1() {
        if (ivjTextField1 == null)
            try {
                ivjTextField1 = new TextField();
                ivjTextField1.setName("TextField1");
                ivjTextField1.setBounds(89, 50, 172, 23);
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjTextField1;
    }

    private Label getLabel3() {
        if (ivjLabel3 == null)
            try {
                ivjLabel3 = new Label();
                ivjLabel3.setName("Label3");
                ivjLabel3.setText("Description:");
                ivjLabel3.setBounds(9, 94, 73, 23);
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjLabel3;
    }

    public ToDoWithDesc() {
        ivjButton1 = null;
        ivjButton2 = null;
        ivjLabel1 = null;
        ivjLabel2 = null;
        ivjLabel3 = null;
        ivjList1 = null;
        ivjTextArea1 = null;
        ivjTextField1 = null;
        items = null;
    }

    public void itemStateChanged(ItemEvent itemevent) {
        if (itemevent.getSource() == getList1())
            connEtoM3(itemevent);
    }

    private TextArea getTextArea1() {
        if (ivjTextArea1 == null)
            try {
                ivjTextArea1 = new TextArea();
                ivjTextArea1.setName("TextArea1");
                ivjTextArea1.setBounds(89, 82, 174, 52);
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjTextArea1;
    }

    private void initConnections() {
        getButton1().addActionListener(this);
        getButton2().addActionListener(this);
        getList1().addItemListener(this);
    }

    private void connEtoM2(ActionEvent actionevent) {
        try {
            getList1().remove(getList1().getSelectedItem());
            return;
        } catch (Throwable throwable) {
            handleException(throwable);
        }
    }

    public void actionPerformed(ActionEvent actionevent) {
        if (actionevent.getSource() == getButton1())
            connEtoM1(actionevent);
        if (actionevent.getSource() == getButton2())
            connEtoM2(actionevent);
    }

    private void connEtoM1(ActionEvent actionevent) {
        try {
            System.out.println(getTextField1().getText());
            if (items.containsKey(getTextField1().getText())) {
                System.out.println(getTextField1().getText() + " is already in the list!!!!!!!!");
                return;
            } else {
                items.put(getTextField1().getText(), getTextArea1().getText());
                getList1().add(getTextField1().getText());
                return;
            }
        } catch (Throwable throwable) {
            handleException(throwable);
        }
    }

    private Label getLabel2() {
        if (ivjLabel2 == null)
            try {
                ivjLabel2 = new Label();
                ivjLabel2.setName("Label2");
                ivjLabel2.setText("Item:");
                ivjLabel2.setBounds(12, 50, 52, 23);
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjLabel2;
    }

    public String getAppletInfo() {
        return "Testing.ToDoWithDesc created using VisualAge for Java.";
    }

    private java.awt.List getList1() {
        if (ivjList1 == null)
            try {
                ivjList1 = new java.awt.List();
                ivjList1.setName("List1");
                ivjList1.setBounds(92, 152, 172, 129);
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjList1;
    }

    public void init() {
        super.init();
        try {
            setName("ToDoWithDesc");
            setLayout(null);
            setBackground(Color.pink);
            setSize(426, 499);
            add(getLabel1(), getLabel1().getName());
            add(getButton1(), getButton1().getName());
            add(getButton2(), getButton2().getName());
            add(getList1(), getList1().getName());
            add(getTextArea1(), getTextArea1().getName());
            add(getTextField1(), getTextField1().getName());
            add(getLabel2(), getLabel2().getName());
            add(getLabel3(), getLabel3().getName());
            initConnections();
            items = new Hashtable();
            return;
        } catch (Throwable throwable) {
            handleException(throwable);
        }
    }

    private Button getButton2() {
        if (ivjButton2 == null)
            try {
                ivjButton2 = new Button();
                ivjButton2.setName("Button2");
                ivjButton2.setBounds(329, 151, 56, 23);
                ivjButton2.setLabel("Remove");
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjButton2;
    }

    private Label getLabel1() {
        if (ivjLabel1 == null)
            try {
                ivjLabel1 = new Label();
                ivjLabel1.setName("Label1");
                ivjLabel1.setFont(new Font("dialog", 1, 18));
                ivjLabel1.setText("ToDoList With Description");
                ivjLabel1.setBounds(118, 13, 243, 23);
            } catch (Throwable throwable) {
                handleException(throwable);
            }
        return ivjLabel1;
    }

    private void connEtoM3(ItemEvent itemevent) {
        try {
            getTextField1().setText(getList1().getSelectedItem());
            String s = (String) items.get(getList1().getSelectedItem());
            getTextArea1().setText(s);
            return;
        } catch (Throwable throwable) {
            handleException(throwable);
        }
    }

    private void handleException(Throwable throwable) {
    }

    private Button ivjButton1;
    private Button ivjButton2;
    private Label ivjLabel1;
    private Label ivjLabel2;
    private Label ivjLabel3;
    private java.awt.List ivjList1;
    private TextArea ivjTextArea1;
    private TextField ivjTextField1;
    private Hashtable items;
}
