package com.project.controller;


import com.project.view.common.LaunchPage;
import com.project.view.sell.TablePanel;
import com.project.view.sell.DetailTablePage;
import com.project.view.sell.SellingPage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PageController implements MouseListener {
    private final LaunchPage launchPage = LaunchPage.getInstance();
    private final SellingPage sellingPage = SellingPage.getInstance();
    private final DetailTablePage detailTablePage = DetailTablePage.getInstance();

    public void startPos() {
        launchPage.setVisible(true);
        initSellingPage();
        sellingPage.setVisible(false);
        addActionStartButtonOnLaunchPage();
        addActionCloseButtonOnSellingPage();
        addActionEndButtonOnLaunchPage();
        addActionBackButtonOnDetailPage();
    }

    private void initSellingPage() {
        TablePanel[] tablePanels = sellingPage.getTablePanels();
        for (TablePanel tablePanel : tablePanels) {
            tablePanel.addMouseListener(this);
        }
    }

    private void end() {
        System.exit(0);
    }

    private void moveLaunchToSellingPage() {
        launchPage.setVisible(false);
        sellingPage.setVisible(true);
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
                    detailTablePage.clearjTextAreamenu();
                    detailTablePage.clearjTextFieldTotal();
                });
    }

    private void addActionEndButtonOnLaunchPage() {
        launchPage.getEndButton()
                .addActionListener(e -> end());
    }

    private void addActionStartButtonOnLaunchPage() {
        launchPage.getStartButton()
                .addActionListener(e -> moveLaunchToSellingPage());
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
        TablePanel tablePanel = (TablePanel) e.getSource();
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
