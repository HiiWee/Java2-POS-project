package com.project;

import com.project.controller.PageController;
import com.project.utils.DButil;
import com.project.view.management.ManagementEditPage;
import com.project.view.management.ManagementPage;

public class Application {
    public static void main(String[] args) {
        PageController controller = new PageController();
        controller.startPos();
    }
}