package com.project.controller;

import com.project.service.ManagementService;
import com.project.view.management.ManagementAddFrame;
import com.project.view.management.ManagementEditFrame;
import com.project.view.management.ManagementFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManagementController extends MouseAdapter {
    private final ManagementService productService = new ManagementService();
    private final ManagementFrame managementFrame = ManagementFrame.getInstance();
    private final ManagementAddFrame managementAddFrame = ManagementAddFrame.getInstance();
    private final ManagementEditFrame managementEditFrame = ManagementEditFrame.getInstance();

    public void initManagementController(){
        addActionUpdate();
        addActionDrop();
        addActionSave();
        addMouesAction();
    }
    private void callTable() {
        managementFrame.addRowTable(productService.fineAllProducts());
    }

    private void dropTable() {
        productService.dropTable();
    }

    private void insert() {
        productService.insert(managementAddFrame.getAddedProduct());
    }

    private void update() {
        productService.update(managementEditFrame.getEditedProduct());
    }
    private void refreshTable(){
        initTable();
        callTable();
    }
    private void deleteStuff(){
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
                managementEditFrame.clearTextField();
                refreshTable();
            }
        });
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.table.getModel();
        tableModel.setNumRows(0);
    }
    private void deleteTableRow(){
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
        managementEditFrame.setVisible(true);
    }
}
