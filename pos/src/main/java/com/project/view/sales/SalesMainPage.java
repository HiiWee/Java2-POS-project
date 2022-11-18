package com.project.view.sales;

import com.project.utils.InitializationGuiUtil;
import java.awt.Container;
import javax.swing.JFrame;

public class SalesMainPage extends JFrame {
    private final Container container=new Container();
   private static final SalesMainPage instance=new SalesMainPage();
    public static  SalesMainPage getInstance(){return instance;}
    public SalesMainPage(){
        initializePage();
        setVisible(true);
    }
    private void initializePage() {
        setTitle("POS SYSTEM");
        container.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
    }

}
