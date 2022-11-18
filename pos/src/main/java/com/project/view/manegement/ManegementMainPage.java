package com.project.view.manegement;

import com.project.utils.InitializationGuiUtil;
import java.awt.Container;
import javax.swing.JFrame;

public class ManegementMainPage extends JFrame {
    private final Container container=new Container();
    private static final ManegementMainPage instance=new ManegementMainPage();
    public static ManegementMainPage getinstance(){return instance;}
    public ManegementMainPage(){
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
