package com.project.view.management;

import com.project.utils.InitializationGuiUtil;
import javax.swing.JPanel;

public class ManagementPage extends JPanel {

    private static final ManagementPage instance = new ManagementPage();

    public static ManagementPage getInstance() {
        return instance;
    }

    public ManagementPage() {
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setVisible(true);
    }
}
