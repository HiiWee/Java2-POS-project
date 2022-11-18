package com.project.controller;


import com.project.pages.MainPage;
import com.project.view.common.LaunchPage;
import com.project.view.manegement.ManegementMainPage;
import com.project.pages.sales.SalesMainPage;
import com.project.pages.sell.TablePanel;
import com.project.pages.sell.DetailTablePage;
import com.project.pages.sell.SellingPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PageController implements MouseListener {
    private MainPage mainPage = MainPage.getInstance();
    private final LaunchPage launchPage = LaunchPage.getInstance();
    private final SellingPage sellingPage = SellingPage.getInstance();
    private final DetailTablePage detailTablePage = DetailTablePage.getInstance();
    private final SalesMainPage salesMainPage=SalesMainPage.getInstance();
    private final ManegementMainPage manegementMainPage=ManegementMainPage.getinstance();

    public void startPos() {
//        launchPage.setVisible(true);
//        initSellingPage();
//        sellingPage.setVisible(false);
//        addActionStartButtonOnLaunchPage();
//        addActionCloseButtonOnSellingPage();
//        addActionEndButtonOnLaunchPage();
//        addActionBackButtonOnDetailPage();
//        addActionManegementButton();
//        addActionSalseButton();

        mainPage.setVisible(true);
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
    private void moveSellingPageToSalesMainPage(){
        sellingPage.setVisible(false);
        salesMainPage.setVisible(true);
    }
    private void moveSellingPageToManegementMainPage(){
        salesMainPage.setVisible(false);
        manegementMainPage.setVisible(true);
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
                .addActionListener(e -> moveLaunchToSellingPage());
    }

    private void addActionCloseButtonOnSellingPage() {
        sellingPage.getCloseButton()
                .addActionListener(e -> moveSellingToLaunchPage());
    }

//    private void addActionSalseButton() {
//        sellingPage.getSalesButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                moveSellingPageToSalesMainPage();
//            }
//        });
//    }
//    private void addActionManegementButton(){
//        sellingPage.getManagementButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                moveSellingPageToManegementMainPage();
//            }
//        });
//    }

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
