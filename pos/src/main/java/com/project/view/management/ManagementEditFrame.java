package com.project.view.management;

import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManagementEditFrame extends JFrame {
    private static final ManagementEditFrame instance = new ManagementEditFrame();
    private final JTextField jTextFieldNumber = new JTextField();
    private final JTextField jTextFieldStuffName = new JTextField();
    private final JTextField jTextFieldStuffPrice = new JTextField();
    private final JLabel jLabelNumber = new JLabel("고유번호");
    private final JLabel jLabelStuffName = new JLabel("이름");
    private final JLabel jLabelStuffPrice = new JLabel("가격");
    private final NormalButton checkButton = new NormalButton("확인");
    private final NormalButton cancelButton = new NormalButton("취소");

    public static ManagementEditFrame getInstance() {
        return instance;
    }

    public ManagementEditFrame() {
        initializePage();
        setVisible(false);
        labelSetting();
        add(jLabelNumber);
        add(jLabelStuffName);
        add(jLabelStuffPrice);
        add(jTextFieldNumber);
        add(jTextFieldStuffName);
        add(jTextFieldStuffPrice);
        add(checkButton);
        add(cancelButton);
        jTextFieldNumber.setEditable(false);
        jLabelNumber.setBounds(50, 20, 100, 100);
        jLabelStuffName.setBounds(75, 75, 100, 100);
        jLabelStuffPrice.setBounds(75, 125, 100, 100);
        jTextFieldNumber.setBounds(150, 55, 250, 30);
        jTextFieldStuffName.setBounds(150, 110, 250, 30);
        jTextFieldStuffPrice.setBounds(150, 160, 250, 30);
        checkButton.setBounds(150, 220, 100, 50);
        cancelButton.setBounds(300, 220, 100, 50);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.EDIT_WIDTH, InitializationGuiConstant.EDIT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Edit Page");
    }

    private void labelSetting() {
        jLabelNumber.setFont(jLabelNumber.getFont().deriveFont(15f));
        jLabelStuffName.setFont(jLabelStuffName.getFont().deriveFont(15f));
        jLabelStuffPrice.setFont(jLabelStuffPrice.getFont().deriveFont(15f));
    }
}
