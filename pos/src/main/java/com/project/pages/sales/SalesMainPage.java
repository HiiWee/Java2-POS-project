package com.project.pages.sales;

import com.project.utils.InitializationGuiUtil;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SalesMainPage extends JPanel {
   private static final SalesMainPage instance=new SalesMainPage();
    public static  SalesMainPage getInstance(){return instance;}
    public SalesMainPage(){
        setVisible(true);
    }

}
