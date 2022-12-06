package com.project.controller;

import com.project.service.ManagementService;
import com.project.service.MemberService;
import com.project.service.SellService;
import com.project.view.management.ChangePasswordFrame;
import com.project.view.management.ManagementAddFrame;
import com.project.view.management.ManagementEditFrame;
import com.project.view.management.ManagementEnterPanel;
import com.project.view.management.ManagementFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManagementController extends MouseAdapter {
    private final ManagementService productService = new ManagementService();
    private final MemberService memberService = new MemberService();
    private final ManagementFrame managementFrame = ManagementFrame.getInstance();
    private final ManagementAddFrame managementAddFrame = ManagementAddFrame.getInstance();
    private final ManagementEditFrame managementEditFrame = ManagementEditFrame.getInstance();
    private final ManagementEnterPanel managementEnterPanel = ManagementEnterPanel.getInstance();
    private final ChangePasswordFrame changePasswordFrame = ChangePasswordFrame.getInstance();

    public void initManagementController() {
        refreshTable();
        addActionUpdate();
        addActionDrop();
        addActionSave();
        addMouesAction();
        addActionOnChangePassword();
    }

    private void callTable() {
        managementFrame.addRowTable(productService.fineAllProducts());
    }

    private void dropTable() {
        productService.dropProductById(managementEditFrame.getEditedProduct().getId());
    }

    private void insert() {
        try {
            productService.insert(managementAddFrame.getAddedProduct());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "[ERROR] 상품정보를 올바르게 입력해주세요", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void update() {
        try {
            productService.update(managementEditFrame.getEditedProduct());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "[ERROR] 상품정보를 올바르게 입력해주세요", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        initTable();
        callTable();
    }

    private void deleteStuff() {
        deleteTableRow();
        dropTable();
    }

    private void addMouesAction() {
        managementFrame.getTable().addMouseListener(this);
    }

    public boolean checkPassword() {
        return String.valueOf(managementEnterPanel.getPasswordField().getPassword())
                .equals(memberService.getPassword());
    }

    private String changePassword() {
        if (checkCurrentPassword() && checkNewPasswords())
            ;
        return String.valueOf(changePasswordFrame.getNewPasswordField().getPassword());
    }

    private boolean checkCurrentPassword() {
        try {
            validatePassword();
            String.valueOf(changePasswordFrame.getCurrentPasswordField().getPassword())
                    .equals(String.valueOf(memberService.getPassword()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }


    private boolean checkNewPasswords() {
        validatePassword();
        try {
            String.valueOf(changePasswordFrame.getNewPasswordField().getPassword())
                    .equals(String.valueOf(changePasswordFrame.getCheckNewPasswordField().getPassword()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

    private void validatePassword() {
        if (String.valueOf(changePasswordFrame.getNewPasswordField().getPassword()).equals("")
                || String.valueOf(changePasswordFrame.getNewPasswordField().getPassword()) == null || String.valueOf(
                changePasswordFrame.getCurrentPasswordField().getPassword()).equals("")
                || String.valueOf(changePasswordFrame.getCurrentPasswordField().getPassword()) == null
                || String.valueOf(changePasswordFrame.getCheckNewPasswordField().getPassword()).equals("")
                || String.valueOf(changePasswordFrame.getNewPasswordField().getPassword()) == null) {
            throw new IllegalArgumentException("[ERROR]빈칸을 모두 채워주세요");
        }
        if (!String.valueOf(changePasswordFrame.getNewPasswordField().getPassword())
                .equals(String.valueOf(changePasswordFrame.getCheckNewPasswordField().getPassword()))) {
            throw new IllegalArgumentException("[ERROR]새로 바뀐 비밀번호가 매칭되지 않습니다");
        }
        if (!String.valueOf(changePasswordFrame.getCurrentPasswordField().getPassword())
                .equals(String.valueOf(memberService.getPassword()))) {
            throw new IllegalArgumentException("[ERROR]현재 비밀 번호가 다릅니다");
        }
    }

    private void addActionSave() {
        managementAddFrame.getAddButton().addActionListener(e -> {
            insert();
            managementAddFrame.clearTextField();
            refreshTable();
        });
    }

    private void addActionDrop() {
        managementEditFrame.getDeleteStuffButton().addActionListener(e -> {
            deleteStuff();
            managementEditFrame.clearTextField();
            refreshTable();
        });
    }

    private void addActionUpdate() {
        managementEditFrame.getEditButton().addActionListener(e -> {
            update();
            managementEditFrame.resetTextField(managementFrame.getProduct(managementEditFrame.getEditedProduct()));
            refreshTable();
        });
    }

    private void addActionOnChangePassword() {
        changePasswordFrame.getCheckButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memberService.changePassword(changePassword());
                changePasswordFrame.clearJPasswordField();
                changePasswordFrame.setVisible(false);
                managementFrame.setVisible(true);
            }
        });
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.getTable().getModel();
        tableModel.setNumRows(0);
    }

    private void deleteTableRow() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.getTable().getModel();
        int row = managementFrame.getTable().getSelectedRow();
        if (row < 0) {
            return;
        }
        tableModel.removeRow(row);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = managementFrame.getTable().getSelectedRow();
        TableModel data = managementFrame.getTable().getModel();
        Long id = (Long) data.getValueAt(row, 0);
        String name = (String) data.getValueAt(row, 1);
        int price = (int) data.getValueAt(row, 2);
        managementEditFrame.getProductId().setText(String.valueOf(id));
        managementEditFrame.getProductName().setText(name);
        managementEditFrame.getProductPrice().setText(String.valueOf(price));
        managementEditFrame.setVisible(true);
    }
}
