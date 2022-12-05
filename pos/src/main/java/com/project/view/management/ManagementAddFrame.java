package com.project.view.management;

import com.google.protobuf.Value;
import com.project.domain.Product;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManagementAddFrame extends JFrame {
    private static final ManagementAddFrame instance = new ManagementAddFrame();
    private final JTextField jTextFieldNumber = new JTextField();
    private final JTextField jTextFieldStuffName = new JTextField();
    private final JTextField jTextFieldStuffPrice = new JTextField();
    private final JLabel jLabelNumber = new JLabel("고유번호");
    private final JLabel jLabelStuffName = new JLabel("이름");
    private final JLabel jLabelStuffPrice = new JLabel("가격");
    private final NormalButton cancelButton = new NormalButton("취소");
    private final NormalButton addButton = new NormalButton("추가");

    public static ManagementAddFrame getInstance() {
        return instance;
    }

    private ManagementAddFrame() {
        initializePage();
        setVisible(true);
        labelSetting();
        add(jLabelNumber);
        add(jLabelStuffName);
        add(jLabelStuffPrice);
        add(jTextFieldNumber);
        add(jTextFieldStuffName);
        add(jTextFieldStuffPrice);
        add(cancelButton);
        add(addButton);
        jTextFieldNumber.setEditable(false);
        jLabelNumber.setBounds(50, 20, 100, 100);
        jLabelStuffName.setBounds(75, 75, 100, 100);
        jLabelStuffPrice.setBounds(75, 125, 100, 100);
        jTextFieldNumber.setBounds(150, 55, 250, 30);
        jTextFieldStuffName.setBounds(150, 110, 250, 30);
        jTextFieldStuffPrice.setBounds(150, 160, 250, 30);
        cancelButton.setBounds(300, 220, 100, 50);
        addButton.setBounds(150, 220, 100, 50);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.EDIT_WIDTH, InitializationGuiConstant.EDIT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Add Page");
    }

    private void labelSetting() {
        jLabelNumber.setFont(jLabelNumber.getFont().deriveFont(15f));
        jLabelStuffName.setFont(jLabelStuffName.getFont().deriveFont(15f));
        jLabelStuffPrice.setFont(jLabelStuffPrice.getFont().deriveFont(15f));
    }

    public NormalButton getCancelButton() {
        return cancelButton;
    }

    public NormalButton getAddButton() {
        return addButton;
    }

    public void clearTextField() {
        jTextFieldStuffName.setText("");
        jTextFieldStuffPrice.setText("");
    }
    public Product getAddedProduct() {
        String name=jTextFieldStuffName.getText();
        int price= Integer.parseInt(jTextFieldStuffPrice.getText());
        Product products = new Product(name, price);
        return products;
    }
}
