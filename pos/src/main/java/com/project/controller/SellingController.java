package com.project.controller;


import com.project.view.common.LaunchPage;
import com.project.view.sell.SellingPage;

public class SellingController {
    private final LaunchPage launchPage = new LaunchPage();
    private final SellingPage sellingPage = new SellingPage();

    public void startPos() {
        sellingPage.setVisible(false);
        addActionStartButton();
        addActionEndButton();
    }

    private void end() {
        System.exit(0);
    }

    private void moveSellingPage() {
        launchPage.setVisible(false);
        sellingPage.setVisible(true);
        addActionCloseButton();
    }

    private void moveLaunchPage() {
        launchPage.setVisible(true);
        sellingPage.setVisible(false);
        addActionEndButton();
        addActionStartButton();
    }

    private void addActionEndButton() {
        launchPage.getEndButton()
                .addActionListener(e -> end());
    }

    private void addActionStartButton() {
        launchPage.getStartButton()
                .addActionListener(e -> moveSellingPage());
    }

    private void addActionCloseButton() {
        // TODO: 마감버튼으로
        sellingPage.getCloseButton()
                .addActionListener(e -> moveLaunchPage());
    }
}
