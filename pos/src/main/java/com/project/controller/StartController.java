package com.project.controller;


import com.project.view.common.LaunchPage;
import com.project.view.sales.SellingPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController {
    private LaunchPage launchPage;
    private SellingPage sellingPage;

    public void startSystem() {
        launchPage = new LaunchPage();
        addActionStartButton();
        addActionEndButton();
    }

    private void end() {
        System.exit(0);
    }

    private void moveSellingPage() {
        launchPage.setVisible(false);
        sellingPage = new SellingPage();
        addActionBackButton();
    }

    private void moveLaunchPage() {
        sellingPage.setVisible(false);
        launchPage = new LaunchPage();
        addActionEndButton();
        addActionStartButton();
    }

    private void addActionEndButton() {
        launchPage.getEndButton().addActionListener(e -> end());
    }

    private void addActionStartButton() {
        launchPage.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSellingPage();
            }
        });
    }

    private void addActionBackButton() {
        sellingPage.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLaunchPage();
            }
        });
    }
}
