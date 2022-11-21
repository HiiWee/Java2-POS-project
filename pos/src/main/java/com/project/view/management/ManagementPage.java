package com.project.view.management;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ManagementPage extends JFrame {
    /* private static final ManagementPage instance = new ManagementPage();

     public static ManagementPage getInstance() {
         return instance;
     }*/
    private final NormalButton backButtonOnManagementPage = new NormalButton(ButtonNameUtil.BACK);
    private final NormalButton addStuffButton = new NormalButton(ButtonNameUtil.ADD_STUFF);
    private final NormalButton deleteStuffButton = new NormalButton(ButtonNameUtil.DELETE_STUFF);
    //private final String header[] = {"고유번호", "이름", "가격"};
    //private final DefaultTableModel tableModel=new DefaultTableModel(header,0);
    //private final JTable table = new JTable(tableModel);
    private final JPanel jPanel = new JPanel();
    private final JTextArea jTextArea = new JTextArea(
            "JTable 이용해서 이부분 나타낼지 백엔드와 논의 필요함 https://reakwon.tistory.com/167 예시");

    public ManagementPage() {
        initializePage();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jTextArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        jPanel.add(scrollPane);
        jTextArea.setEditable(false);
        add(jPanel);
        add(backButtonOnManagementPage);
        add(addStuffButton);
        add(deleteStuffButton);
        backButtonOnManagementPage.setBounds(230, 480, 150, 100);
        addStuffButton.setBounds(600, 100, 150, 100);
        deleteStuffButton.setBounds(600, 300, 150, 100);
        jPanel.setBounds(50, 50, 500, 400);
        setVisible(true);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Page");
    }
}
