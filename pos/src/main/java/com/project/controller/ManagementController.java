package com.project.controller;

import com.project.domain.Product;
import com.project.service.ManagementService;
import com.project.view.management.ManagementAddFrame;
import com.project.view.management.ManagementEditFrame;
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
    private final ManagementFrame managementFrame = ManagementFrame.getInstance();
    private final ManagementAddFrame managementAddFrame = ManagementAddFrame.getInstance();
    private final ManagementEditFrame managementEditFrame = ManagementEditFrame.getInstance();

    public void initManagementController() {
        addActionUpdate();
        addActionDrop();
        addActionSave();
        addMouesAction();
        refreshTable();
    }

    private void callTable() {
        managementFrame.addRowTable(productService.fineAllProducts());
    }

    private void dropTable() {
        productService.dropTable();
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
        try{
            productService.update(managementEditFrame.getEditedProduct());
        }catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null, "[ERROR] 상품정보를 올바르게 입력해주세요", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (IllegalArgumentException exception){
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
        managementFrame.table.addMouseListener(this);
    }

    private void addActionSave() {
        managementAddFrame.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert();
                managementAddFrame.clearTextField();
                refreshTable();
            }
        });
    }

    private void addActionDrop() {
        managementEditFrame.getDeleteStuffButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStuff();
                managementEditFrame.getDeleteStuffButton().setEnabled(false);
                managementEditFrame.getEditButton().setEnabled(false);
                managementEditFrame.clearTextField();
                refreshTable();
            }
        });
    }

    private void addActionUpdate() {
        managementEditFrame.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                refreshTable();
            }
        });
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.table.getModel();
        tableModel.setNumRows(0);
    }

    private void deleteTableRow() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.table.getModel();
        int row = managementFrame.table.getSelectedRow();
        if (row < 0) {
            return;
        }
        tableModel.removeRow(row);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = managementFrame.table.getSelectedRow();
        TableModel data = managementFrame.table.getModel();
        Long id = (Long) data.getValueAt(row, 0);
        String name = (String) data.getValueAt(row, 1);
        int price = (int) data.getValueAt(row, 2);
        managementEditFrame.getjTextFieldNumber().setText(String.valueOf(id));
        managementEditFrame.getjTextFieldStuffName().setText(name);
        managementEditFrame.getjTextFieldStuffPrice().setText(String.valueOf(price));
        managementEditFrame.getDeleteStuffButton().setEnabled(true);
        managementEditFrame.getEditButton().setEnabled(true);
        managementEditFrame.setVisible(true);
    }
}
