package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailTablePage extends JFrame {
    private static final DetailTablePage instance = new DetailTablePage();
    private final NormalButton DetailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final JScrollBar jScrollBar=new JScrollBar(1,0,0,0,0);
    private final JTextArea jTextArea=new JTextArea("test");
    private final JTextField jTextField=new JTextField(ButtonNameUtil.TABLE_NUMBER);
    private final JTextField jTextField1=new JTextField("합계");
    private JPanel detailTablePanel;
    private final JPanel testPanel=new JPanel();
    private final Container container = getContentPane();

    public static DetailTablePage getInstance() {
        return instance;
    }

    private DetailTablePage() {
        initializePage();
        DetailTableBackButton.setBounds(500, 430, 150, 100);
        testPanel.setLayout(null);
        add(testPanel);
        container.add(DetailTableBackButton);

    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        container.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
    }

    public void setTablePanel(ConstructorTablePanel constructorTablePanel) {
        detailTablePanel = new ConstructorTablePanel(String.valueOf(constructorTablePanel.getTableNumber()));
        testPanel.add(detailTablePanel);
        //detailTablePanel.add(jTextField1);
        //detailTablePanel.add(jScrollBar);
        //detailTablePanel.add(jTextField);
        //detailTablePanel.add(jTextArea);
        //jTextField.setBounds(600,0,50,50);
        //jTextArea.setBounds(500,100,200,200);
        //container.add(detailTablePanel);
        //detailTablePanel.setBounds(410, 0, 400, 400);
    }


    public JButton getBackButton() {
        return DetailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(detailTablePanel);
    }
}
