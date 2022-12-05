package com.project;

import com.project.controller.PageController;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        System.out.println(new Date());
        PageController controller = new PageController();
        controller.startPos();
    }
}