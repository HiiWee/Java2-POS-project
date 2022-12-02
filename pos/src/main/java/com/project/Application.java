package com.project;

import com.project.controller.PageController;
import com.project.repository.ProductRepository;

public class Application {
    public static void main(String[] args) {
        PageController controller = new PageController();
        controller.startPos();
    }
}