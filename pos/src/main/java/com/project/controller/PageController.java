package com.project.controller;


import com.project.DB.JTable;
import com.project.view.MainFrame;
import com.project.view.billcheck.BillCheckPage;
import com.project.view.common.LaunchPage;
import com.project.view.management.ManagementEditPage;
import com.project.view.management.ManagementPage;
import com.project.view.management.ManagementSubPage;
import com.project.view.sell.DetailTableSubPage;
import com.project.view.sell.SellingPage;
import com.project.view.sell.TableSubPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;

public class PageController implements MouseListener {
    private final MainFrame mainPage = MainFrame.getInstance();
    private final LaunchPage launchPage = LaunchPage.getInstance();
    private final SellingPage sellingPage = SellingPage.getInstance();
    private final DetailTableSubPage detailTablePage = DetailTableSubPage.getInstance();
    private final BillCheckPage billCheckPage = BillCheckPage.getInstance();
    private final ManagementSubPage managementSubPage = ManagementSubPage.getInstance();
    private final ManagementEditPage managementEditPage = ManagementEditPage.getInstance();
    private final ManagementPage managementPage = ManagementPage.getInstance();
    private final JTable jTable = JTable.getInstance();

    public void startPos() {
        launchPage.setVisible(true);
        initSellingPage();
        addActionStartButtonOnLaunchPage();
        addActionCloseButtonOnSellingPage();
        addActionEndButtonOnLaunchPage();
        addActionBackButtonOnDetailPage();
        addActionBillButtonOnSellingPage();
        addActionBackButtonOnBillCheckPage();
        addActionRefreshButtonOnManagementPage();
        addActionDeleteButtonOnManagementPage();
        addActionAddButtonOnManagementEditPage();
        addActionCheckButtonOnManagementEditPage();
    }

    private void initSellingPage() {
        TableSubPanel[] tablePanels = sellingPage.getTablePanels();
        for (TableSubPanel tablePanel : tablePanels) {
            tablePanel.addMouseListener(this);
        }
    }

    private void end() {
        System.exit(0);
    }

    private void moveLaunchToMainPage() {
        launchPage.setVisible(false);
        mainPage.setVisible(true);
    }

    private void moveSellingToLaunchPage() {
        launchPage.setVisible(true);
        sellingPage.setVisible(false);
    }

    private void moveDetailToSellingPage() {
        detailTablePage.setVisible(false);
        sellingPage.setVisible(true);
    }

    private void moveSellingToBillCheckPage() {
        mainPage.setVisible(false);
        billCheckPage.setVisible(true);
    }

    private void moveBillCheckPageToMainPage() {
        billCheckPage.setVisible(false);
        mainPage.setVisible(true);
    }

    private void refreshJTable() {
        DefaultTableModel tableModel = (DefaultTableModel) managementPage.table.getModel();
        tableModel.setNumRows(0);
    }

    private void addActionBackButtonOnDetailPage() {
        detailTablePage.getBackButton()
                .addActionListener(e -> {
                    moveDetailToSellingPage();
                    detailTablePage.removeExistTablePanel();
                    detailTablePage.clearJTextAreaMenu();
                    detailTablePage.clearJTextFieldTotal();
                });
    }

    private void addActionEndButtonOnLaunchPage() {
        launchPage.getEndButton()
                .addActionListener(e -> end());
    }

    private void addActionStartButtonOnLaunchPage() {
        launchPage.getStartButton()
                .addActionListener(e -> moveLaunchToMainPage());
    }

    private void addActionCloseButtonOnSellingPage() {
        sellingPage.getCloseButton()
                .addActionListener(e -> moveSellingToLaunchPage());
    }

    private void addActionBillButtonOnSellingPage() {
        sellingPage.getBillButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSellingToBillCheckPage();
            }
        });
    }

    private void addActionBackButtonOnBillCheckPage() {
        billCheckPage.getBackButtonOnBillCheckPage().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBillCheckPageToMainPage();
            }
        });
    }

    private void addActionRefreshButtonOnManagementPage() {
        managementPage.getRefreshButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshJTable();
                jTable.select();
            }
        });
    }

    private void addActionDeleteButtonOnManagementPage() {
        managementPage.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.drop();
            }
        });
    }

    private void addActionAddButtonOnManagementEditPage() {
        managementEditPage.getAddButtom().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.insert();
                jTable.clearList();
            }
        });
    }
    private void addActionCheckButtonOnManagementEditPage(){
        managementEditPage.getcheckButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.saveList();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        TableSubPanel tablePanel = (TableSubPanel) e.getSource();
        detailTablePage.setTablePanel(tablePanel);
        sellingPage.setVisible(false);
        detailTablePage.setVisible(true);
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
