package com.project.view.management;

import com.project.domain.Product;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManagementAddFrame extends JFrame {
    private final Container ct = getContentPane();
    private static final ManagementAddFrame instance = new ManagementAddFrame();
    private final JTextField jTextFieldNumber = new JTextField();
    private final JTextField jTextFieldStuffName = new JTextField();
    private final JTextField jTextFieldStuffPrice = new JTextField();
    private final JLabel jLabelNumber = new JLabel(ButtonNameMessage.ITEM_NUMBER);
    private final JLabel jLabelStuffName = new JLabel(ButtonNameMessage.ITEM_NAME);
    private final JLabel jLabelStuffPrice = new JLabel(ButtonNameMessage.ITEM_PRICE);
    private final NormalButton cancelButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton addButton = new NormalButton(ButtonNameMessage.ITEM_ADD);

    public static ManagementAddFrame getInstance() {
        return instance;
    }

    private ManagementAddFrame() {
        initializePage();
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
        ct.setBackground(Color.WHITE);
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
        String name = jTextFieldStuffName.getText();
        int price = Integer.parseInt(jTextFieldStuffPrice.getText());
        Product products = new Product(name, price);
        return products;
    }
}
