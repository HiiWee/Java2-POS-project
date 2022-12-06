package com.project.view.management;

import com.project.domain.Product;
import com.project.utils.ButtonNameMessage;
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
    private final JLabel jLabelNumber = new JLabel(ButtonNameMessage.ITEM_NUMBER);
    private final JLabel jLabelStuffName = new JLabel(ButtonNameMessage.ITEM_NAME);
    private final JLabel jLabelStuffPrice = new JLabel(ButtonNameMessage.ITEM_PRICE);

    private final NormalButton deleteStuffButton = new NormalButton(ButtonNameMessage.DELETE_STUFF);
    private final NormalButton cancelButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton editButton = new NormalButton(ButtonNameMessage.ITEM_EDIT);

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
        add(cancelButton);
        add(editButton);
        add(deleteStuffButton);
        jTextFieldNumber.setEditable(false);
        jLabelNumber.setBounds(50, 20, 100, 100);
        jLabelStuffName.setBounds(75, 75, 100, 100);
        jLabelStuffPrice.setBounds(75, 125, 100, 100);
        jTextFieldNumber.setBounds(150, 55, 250, 30);
        jTextFieldStuffName.setBounds(150, 110, 250, 30);
        jTextFieldStuffPrice.setBounds(150, 160, 250, 30);
        cancelButton.setBounds(300, 220, 100, 50);
        editButton.setBounds(50, 220, 100, 50);
        deleteStuffButton.setBounds(175, 220, 100, 50);
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

    public JTextField getjTextFieldNumber() {
        return jTextFieldNumber;
    }

    public JTextField getjTextFieldStuffName() {
        return jTextFieldStuffName;
    }

    public JTextField getjTextFieldStuffPrice() {
        return jTextFieldStuffPrice;
    }

    public NormalButton getCancelButton() {
        return cancelButton;
    }

    public NormalButton getEditButton() {
        return editButton;
    }

    public NormalButton getDeleteStuffButton() {
        return deleteStuffButton;
    }

    public void clearTextField() {
        jTextFieldNumber.setText("");
        jTextFieldStuffName.setText("");
        jTextFieldStuffPrice.setText("");
    }

    public Product getEditedProduct() {
        Long id = Long.valueOf(jTextFieldNumber.getText());
        String name = jTextFieldStuffName.getText();
        int price = Integer.parseInt(jTextFieldStuffPrice.getText());
        Product product = new Product(id, name, price);
        return product;
    }
}
