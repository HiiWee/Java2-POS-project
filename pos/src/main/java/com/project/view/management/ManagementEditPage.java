package com.project.view.management;

import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ManagementEditPage extends JFrame {
    private static final ManagementEditPage instance = new ManagementEditPage();

    public static ManagementEditPage getInstance() {
        return instance;
    }

    private final JTextField jTextFieldNumber = new JTextField();
    private final JTextField jTextFieldStuffName = new JTextField();
    private final JTextField jTextFieldStuffPrice = new JTextField();
    private final JLabel jLabelNumber = new JLabel("고유번호");
    private final JLabel jLabelStuffName = new JLabel("이름");
    private final JLabel jLabelStuffPrice = new JLabel("가격");
    private final NormalButton addButtom = new NormalButton("추가");
    private final NormalButton checkButton = new NormalButton("확인");
    private final NormalButton cancelButton = new NormalButton("취소");

    public ManagementEditPage() {
        initializePage();
        setVisible(true);
        labelSetting();
        add(jLabelNumber);
        add(jLabelStuffName);
        add(jLabelStuffPrice);
        add(jTextFieldNumber);
        add(jTextFieldStuffName);
        add(jTextFieldStuffPrice);
        add(checkButton);
        add(cancelButton);
        add(addButtom);
        //jTextFieldNumber.setEditable(false);
        jLabelNumber.setBounds(50, 20, 100, 100);
        jLabelStuffName.setBounds(75, 75, 100, 100);
        jLabelStuffPrice.setBounds(75, 125, 100, 100);
        jTextFieldNumber.setBounds(150, 55, 250, 30);
        jTextFieldStuffName.setBounds(150, 110, 250, 30);
        jTextFieldStuffPrice.setBounds(150, 160, 250, 30);
        checkButton.setBounds(150, 220, 100, 50);
        cancelButton.setBounds(300, 220, 100, 50);
        addButtom.setBounds(0,220,100,50);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiUtil.EDIT_WIDTH, InitializationGuiUtil.EDIT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Edit Page");
    }

    private void labelSetting() {
        jLabelNumber.setFont(jLabelNumber.getFont().deriveFont(15f));
        jLabelStuffName.setFont(jLabelStuffName.getFont().deriveFont(15f));
        jLabelStuffPrice.setFont(jLabelStuffPrice.getFont().deriveFont(15f));
    }
    public JTextField getjTextFieldStuffName(){
        return jTextFieldStuffName;
    }

    public JTextField getjTextFieldStuffPrice() {
        return jTextFieldStuffPrice;
    }

    public JTextField getjTextFieldNumber() {
        return jTextFieldNumber;
    }

    public JButton getAddButtom() {
        return addButtom;
    }
}
