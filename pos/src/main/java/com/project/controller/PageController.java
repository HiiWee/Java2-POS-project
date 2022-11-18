package com.project.controller;


import com.project.view.MainFrame;
import com.project.view.common.LaunchPage;
import com.project.view.management.ManagementPage;
import com.project.view.sales.SalesPage;
import com.project.view.sell.TableSubPanel;
import com.project.view.sell.DetailTableSubPage;
import com.project.view.sell.SellingPage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PageController implements MouseListener {
    private MainFrame mainPage = MainFrame.getInstance();
    private final LaunchPage launchPage = LaunchPage.getInstance();
    private final SellingPage sellingPage = SellingPage.getInstance();
    private final DetailTableSubPage detailTablePage = DetailTableSubPage.getInstance();

    public void startPos() {
        launchPage.setVisible(true);
        initSellingPage();
        addActionStartButtonOnLaunchPage();
        addActionCloseButtonOnSellingPage();
        addActionEndButtonOnLaunchPage();
        addActionBackButtonOnDetailPage();
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

    private void addActionBackButtonOnDetailPage() {
        detailTablePage.getBackButton()
                .addActionListener(e -> {
                    moveDetailToSellingPage();
                    detailTablePage.removeExistTablePanel();
                    detailTablePage.clearjTextAreaMenu();
                    detailTablePage.clearjTextFieldTotal();
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