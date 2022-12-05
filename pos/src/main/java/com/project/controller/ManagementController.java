package com.project.controller;

import com.project.service.ManagementService;
import com.project.view.management.ManagementAddFrame;
import com.project.view.management.ManagementEditFrame;
import com.project.view.management.ManagementFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManagementController implements MouseListener {
    private final ManagementService productService = new ManagementService();
    private final ManagementFrame managementFrame = ManagementFrame.getInstance();
    private final ManagementAddFrame managementAddFrame = ManagementAddFrame.getInstance();
    private final ManagementEditFrame managementEditFrame = ManagementEditFrame.getInstance();

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

    public void addMouesAction() {
        managementFrame.table.addMouseListener(this);
    }

    public void addActionSave() {
        managementAddFrame.getAddButtom().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert();
            }
        });
    }


    public void addActionRefresh() {
        managementFrame.getRefreshButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initTable();
                callTable();
            }
        });
    }

    public void addActionDrop() {
        managementFrame.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropTable();
            }
        });
    }

    public void addActionUpdate() {
        managementEditFrame.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.table.getModel();
        tableModel.setNumRows(0);
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


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
