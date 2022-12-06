package com.project.controller;


import com.project.view.MainFrame;
import com.project.view.billcheck.BillCheckFrame;
import com.project.view.common.LaunchPage;
import com.project.view.management.ManagementAddFrame;
import com.project.view.management.ManagementEditFrame;
import com.project.view.management.ManagementFrame;
import com.project.view.sell.DetailTableFrame;
import com.project.view.sell.SellingPanelTab;
import com.project.view.sell.TableSubPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PageController {
    private final MainFrame mainFrame = MainFrame.getInstance();
    private final LaunchPage launchPage = LaunchPage.getInstance();
    private final SellingPanelTab sellingPanel = SellingPanelTab.getInstance();
    private final DetailTableFrame detailTableFrame = DetailTableFrame.getInstance();
    private final BillCheckFrame billCheckFrame = BillCheckFrame.getInstance();
    private final ManagementFrame managementFrame = ManagementFrame.getInstance();
    private final ManagementAddFrame managementAddFrame = ManagementAddFrame.getInstance();
    private final ManagementEditFrame managementEditFrame = ManagementEditFrame.getInstance();

    private final BillCheckController billCheckController = new BillCheckController();
    private final SellController sellController = new SellController();
    private final ManagementController managementController = new ManagementController();

    public void startPos() {
        launchPage.setVisible(true);
        initSellingPage();
        // 영업개시
        addActionStartButtonOnLaunchPage();
        // 마감버튼
        addActionCloseButtonOnSellingPage();
        // 종료버튼
        addActionEndButtonOnLaunchPage();
        // 상세 테이블 뒤로 버튼
        addActionBackButtonOnDetailPage();
        // 영수증 조회 버튼
        addActionBillButtonOnSellingPage();
        // 영수증 조회에서 뒤로 버튼
        addActionBackButtonOnBillCheckPage();
        //managementFrame->addFrame
        addActionAddButtonOnManagementFrame();
        //add->managementFrame
        addActionBackButtonOnAdd();
        //edit->managementFrame
        addActionBackButtonOnEdit();
        // 각 페이지 초기 작업 초기화
        sellController.initSellPage();
        managementController.initManagementController();
    }

    private void initSellingPage() {
        TableSubPanel[] tablePanels = sellingPanel.getTablePanels();
        for (TableSubPanel tablePanel : tablePanels) {
            tablePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent e) {
                    sellController.refreshProducts();
                    TableSubPanel tablePanel = (TableSubPanel) e.getSource();
                    detailTableFrame.setTablePanel(tablePanel);
                    mainFrame.setVisible(false);
                    detailTableFrame.setVisible(true);
                }
            });
        }
    }

    private void end() {
        System.exit(0);
    }

    private void moveLaunchToMainPage() {
        launchPage.setVisible(false);
        mainFrame.setVisible(true);
    }

    private void moveSellingToLaunchPage() {
        launchPage.setVisible(true);
        mainFrame.setVisible(false);
    }

    private void moveDetailToSellingPage() {
        detailTableFrame.setVisible(false);
        mainFrame.setVisible(true);
    }

    private void moveSellingToBillCheckPage() {
        mainFrame.setVisible(false);
        billCheckFrame.setVisible(true);
    }

    private void moveBillCheckPageToMainPage() {
        billCheckFrame.setVisible(false);
        mainFrame.setVisible(true);
    }

    private void openAddFrame() {
        managementAddFrame.setVisible(true);
    }

    private void moveAddToManagementFrame() {
        managementAddFrame.setVisible(false);
        managementFrame.setVisible(true);
    }

    private void moveEditToManagementFrane() {
        managementEditFrame.setVisible(false);
        managementFrame.setVisible(true);
    }


    private void addActionBackButtonOnDetailPage() {
        detailTableFrame.getBackButton()
                .addActionListener(e -> {
                    moveDetailToSellingPage();
                    detailTableFrame.removeExistTablePanel();
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
        sellingPanel.getCloseButton()
                .addActionListener(e -> moveSellingToLaunchPage());
    }

    private void addActionBillButtonOnSellingPage() {
        sellingPanel.getBillButton().addActionListener(e -> {
            billCheckController.initBillCheckFrame();
            moveSellingToBillCheckPage();
        });
    }

    private void addActionBackButtonOnBillCheckPage() {
        billCheckFrame.getBackButtonOnBillCheckPage().addActionListener(e -> moveBillCheckPageToMainPage());
    }

    private void addActionBackButtonOnAdd() {
        managementAddFrame.getCancelButton().addActionListener(e -> moveAddToManagementFrame());
    }

    private void addActionAddButtonOnManagementFrame() {
        managementFrame.getAddStuffButton().addActionListener(e -> openAddFrame());
    }

    private void addActionBackButtonOnEdit() {
        managementEditFrame.getCancelButton().addActionListener(e -> moveEditToManagementFrane());
    }

}
