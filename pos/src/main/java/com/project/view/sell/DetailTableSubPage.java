package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class DetailTableSubPage extends JFrame {
    private static final DetailTableSubPage instance = new DetailTableSubPage();
    private final NormalButton detailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final NormalButton detailTableDiscountButton = new NormalButton(ButtonNameUtil.DISCOUNT);
    private final NormalButton detailTablePayButton = new NormalButton(ButtonNameUtil.PAYMENT);
    private final NormalButton detailTableOrderButton = new NormalButton(ButtonNameUtil.ORDER);
    private final JTextArea jTextAreaMenu = new JTextArea();
    private final JTextField jTextFieldTotal = new JTextField();
    private TableSubPanel tablePanel;
   // private final JPanel ButtonPanelRight = new JPanel(new GridLayout(1, 1));
    private final JPanel ButtonPanelLeft = new JPanel(new GridLayout(1, 3));

    private final NormalButton leftButton = new NormalButton("left");
    private final NormalButton rightButton = new NormalButton("right");
    private JPanel menuPanel;
    private JPanel menuList;
    private JPanel centerPanel;
    private JTextField menuTitle;
    private int PageNum = 1;

    public static DetailTableSubPage getInstance() {
        return instance;
    }

    private DetailTableSubPage() {
        initializePage();
        ButtonPanelLeft.add(detailTablePayButton);
        ButtonPanelLeft.add(detailTableDiscountButton);
        ButtonPanelLeft.add(detailTableBackButton);
        add(ButtonPanelLeft);
        add(detailTableOrderButton);
        ButtonPanelLeft.setBounds(50, 425, 300, 100);
        detailTableOrderButton.setBounds(550, 425, 150, 100);
    }

    private void initializePage() {
        setLayout(null);
        setMenuPage();
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setMenuPage() {
        menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 400, 400);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        menuPanel.setLayout(null);
        add(menuPanel);
        menuTitle = new JTextField();
        menuTitle.setBounds(10, 0, 380, 50);
        menuTitle.setText("Page " + PageNum);
        menuTitle.setEditable(false);
        menuTitle.setHorizontalAlignment(JTextField.CENTER);
        menuPanel.add(menuTitle);
        centerPanel = new JPanel();
        centerPanel.setBounds(0, 50, 400, 300);
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);
        menuPanel.add(centerPanel);
        for (int y = 0; y < 4; y++) {     //지금은 패널을 계속 초기화해서 추가했지만 나중에 JPanel 배열하는법을 알면 수정 예정
            for (int x = 0; x < 4; x++) {
                menuList = new JPanel();
                menuList.setBounds(4 + 99 * x, 4 + 74 * y, 95, 70);
                menuList.setBorder(new LineBorder(Color.BLACK, 1));
                menuList.setBackground(Color.WHITE);
                centerPanel.add(menuList);
            }
        }
        leftButton.setBounds(10, 355, 100, 40);
        rightButton.setBounds(290, 355, 100, 40);
        menuPanel.add(leftButton);      //현재 버튼만 추가했고 세부 버튼 리스너는 작성하지 않은 상태
        menuPanel.add(rightButton);     //버튼을 누르면 centerPanel을 안보이게 하고 2번째 centerPanel을 생성하여 Page 2를 만들 예정

    }

    public void setTablePanel(TableSubPanel tablePanel) {
        this.tablePanel = new TableSubPanel(String.valueOf(tablePanel.getTableNumber()));
        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.add(jTextFieldTotal, BorderLayout.SOUTH);
        test.add(jTextAreaMenu, BorderLayout.CENTER);
        this.tablePanel.add(test);
        jTextAreaMenu.setColumns(34);
        jTextAreaMenu.setRows(21);//jTextfieldTable 넣고 싶으면 20으로
        jTextAreaMenu.setEditable(false);
        jTextFieldTotal.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextAreaMenu);
        test.add(jScrollPane);
        add(this.tablePanel);
        this.tablePanel.setBounds(410, 0, 400, 400);
    }


    public JButton getBackButton() {
        return detailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }

    public void clearjTextFieldTotal() {
        jTextFieldTotal.setText("");
    }

    public void clearjTextAreaMenu() {
        jTextAreaMenu.setText("");
    }
}