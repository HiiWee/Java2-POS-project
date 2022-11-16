package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailTablePage extends JFrame {
    private static final DetailTablePage instance = new DetailTablePage();
    private final NormalButton detailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final JTextArea jTextAreamenu=new JTextArea();
   //private final JTextField jTextFieldTable_No = new JTextField(ButtonNameUtil.TABLE_NUMBER);
    private final JTextField jTextFieldTotal =new JTextField();
    private TablePanel tablePanel;
    private final Container container = getContentPane();

    public static DetailTablePage getInstance() {
        return instance;
    }

    private DetailTablePage() {
        initializePage();
        detailTableBackButton.setBounds(500, 430, 150, 100);
        container.add(detailTableBackButton);

    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        container.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
    }

    public void setTablePanel(TablePanel tablePanel) {
        this.tablePanel = new TablePanel(String.valueOf(tablePanel.getTableNumber()));
       // jTextFieldTable_No.setText("Table_NO_"+String.valueOf(tablePanel.getTableNumber()));
        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.add(jTextFieldTotal,BorderLayout.SOUTH);
        test.add(jTextAreamenu,BorderLayout.CENTER);
       //test.add(jTextFieldTable_No,BorderLayout.NORTH);
        this.tablePanel.add(test);
        jTextAreamenu.setColumns(34);
        jTextAreamenu.setRows(21);//jTextfieldTable 넣고 싶으면 20으로
        jTextAreamenu.setEditable(false);
        jTextFieldTotal.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextAreamenu);
        test.add(jScrollPane);
        container.add(this.tablePanel);
        this.tablePanel.setBounds(410, 0, 400, 400);
    }


    public JButton getBackButton() {
        return detailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }
    public void clearjTextFieldTotal(){
       jTextFieldTotal.setText(" ");
    }
    public void clearjTextAreamenu(){
        jTextAreamenu.setText(" ");
    }
}
