package com.project.view.management;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class ManagementChangePasswordFrame extends JFrame {
    private static final ManagementChangePasswordFrame instance = new ManagementChangePasswordFrame();

    public static ManagementChangePasswordFrame getInstance() {
        return instance;
    }

    private final JPasswordField currentPasswordField = new JPasswordField();
    private final JPasswordField NewPasswordField = new JPasswordField();
    private final JPasswordField checkNewPasswordField = new JPasswordField();
    private final JLabel current = new JLabel("현재 비밀번호: ");
    private final JLabel anotherPassword = new JLabel("새로운 비밀번호: ");
    private final JLabel checkAnotherPassword = new JLabel("확인: ");
    private final NormalButton checkButton = new NormalButton(ButtonNameMessage.CHECK);
    private final NormalButton closeButton = new NormalButton(ButtonNameMessage.CLOSE);

    private ManagementChangePasswordFrame() {
        initializePage();
        setPasswordField();
        setButton();
        setLabel();
        setVisible(true);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.EDIT_WIDTH, InitializationGuiConstant.EDIT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management ChangePassword Page");
    }

    private void setPasswordField() {
        add(currentPasswordField);
        add(checkNewPasswordField);
        add(NewPasswordField);
        currentPasswordField.setBounds(170,30,200,30);
        NewPasswordField.setBounds(170,95,200,30);
        checkNewPasswordField.setBounds(170,150,200,30);
    }

    private void setButton() {
        add(checkButton);
        add(closeButton);
        checkButton.setBounds(100,200,100,75);
        closeButton.setBounds(275,200,100,75);
    }

    private void setLabel() {
        labelSetting();
        add(current);
        add(anotherPassword);
        add(checkAnotherPassword);
        current.setBounds(10,15,150,50);
        anotherPassword.setBounds(10,85,150,50);
        checkAnotherPassword.setBounds(60,135,100,50);
    }
    private void labelSetting() {
        current.setFont(current.getFont().deriveFont(15f));
        anotherPassword.setFont(anotherPassword.getFont().deriveFont(15f));
        checkAnotherPassword.setFont(checkAnotherPassword.getFont().deriveFont(15f));
    }

    public JPasswordField getCurrentPasswordField() {
        return currentPasswordField;
    }

    public JPasswordField getNewPasswordField() {
        return NewPasswordField;
    }

    public JPasswordField getCheckNewPasswordField() {
        return checkNewPasswordField;
    }

    public NormalButton getCheckButton() {
        return checkButton;
    }

    public NormalButton getCloseButton() {
        return closeButton;
    }
}