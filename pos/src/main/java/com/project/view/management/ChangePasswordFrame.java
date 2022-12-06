package com.project.view.management;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;


public class ChangePasswordFrame extends JFrame {
    private static final ChangePasswordFrame instance = new ChangePasswordFrame();

    public static ChangePasswordFrame getInstance() {
        return instance;
    }

    private final JPasswordField currentPasswordField = new JPasswordField();
    private final JPasswordField newPasswordField = new JPasswordField();
    private final JPasswordField checkNewPasswordField = new JPasswordField();
    private final JLabel current = new JLabel("현재 비밀번호: ");
    private final JLabel anotherPassword = new JLabel("새로운 비밀번호: ");
    private final JLabel checkAnotherPassword = new JLabel("확인: ");
    private final NormalButton checkButton = new NormalButton(ButtonNameMessage.CHECK);
    private final NormalButton closeButton = new NormalButton(ButtonNameMessage.CLOSE);

    private ChangePasswordFrame() {
        initializePage();
        setLocationRelativeTo(null);
        setPasswordField();
        setButton();
        setLabel();
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
        add(newPasswordField);
        currentPasswordField.setBounds(150, 55, 250, 30);
        newPasswordField.setBounds(150, 110, 250, 30);
        checkNewPasswordField.setBounds(150, 160, 250, 30);
    }

    private void setButton() {
        add(checkButton);
        add(closeButton);
        checkButton.setBounds(150, 220, 100, 50);
        closeButton.setBounds(300, 220, 100, 50);
    }

    private void setLabel() {
        labelSetting();
        add(current);
        add(anotherPassword);
        add(checkAnotherPassword);
        current.setBounds(25, 20, 150, 100);
        anotherPassword.setBounds(10, 75, 150, 100);
        checkAnotherPassword.setBounds(90, 125, 100, 100);
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
        return newPasswordField;
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

    public void clearJPasswordField() {
        currentPasswordField.setText("");
        newPasswordField.setText("");
        checkNewPasswordField.setText("");
    }
}
