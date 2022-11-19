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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class DetailTableSubPage extends JFrame implements ActionListener{
    private static final DetailTableSubPage instance = new DetailTableSubPage();
    private final NormalButton detailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final NormalButton detailTableDiscountButton = new NormalButton(ButtonNameUtil.DISCOUNT);
    private final NormalButton detailTablePayButton = new NormalButton(ButtonNameUtil.PAYMENT);
    private final NormalButton detailTableOrderButton = new NormalButton(ButtonNameUtil.ORDER);
    private final NormalButton detailTableBillButton = new NormalButton(ButtonNameUtil.BILL_CHECK);
    private final JTextArea jTextAreaMenu = new JTextArea();
    private final JTextField jTextFieldTotal = new JTextField();
    private TableSubPanel tablePanel;
    private final JPanel ButtonPanelRight = new JPanel(new GridLayout(1, 2));
    private final JPanel ButtonPanelLeft = new JPanel(new GridLayout(1, 3));

    private final NormalButton leftButton = new NormalButton("left");
    private final NormalButton rightButton = new NormalButton("right");
    private JPanel menuPanel;
    private JPanel menuList;
    private CenterPanel[] centerPanel = new CenterPanel[3];
    private JTextField menuTitle;
    private int pageNum = 1;

    public static DetailTableSubPage getInstance() {
        return instance;
    }

    private DetailTableSubPage() {
        initializePage();
        ButtonPanelRight.add(detailTableOrderButton);
        ButtonPanelRight.add(detailTableBillButton);
        ButtonPanelLeft.add(detailTablePayButton);
        ButtonPanelLeft.add(detailTableDiscountButton);
        ButtonPanelLeft.add(detailTableBackButton);
        add(ButtonPanelRight);
        add(ButtonPanelLeft);
        ButtonPanelLeft.setBounds(50, 425, 300, 100);
        ButtonPanelRight.setBounds(550, 425, 200, 100);
    }

    private void initializePage() {
        setLayout(null);
        setMenuPage();
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
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
        menuTitle.setText("Page " + pageNum);
        menuTitle.setEditable(false);
        menuTitle.setHorizontalAlignment(JTextField.CENTER);
        menuPanel.add(menuTitle);
        for(int i = 0; i < 3; i++){
            centerPanel[i] = new CenterPanel();
        }
        for(int i = 1; i < 3; i++){
            centerPanel[i].setVisible(false);
        }
          //버튼을 누르면 centerPanel을 안보이게 하고 2번째 centerPanel을 생성하여 Page 2를 만들 예정
        ButtonAction();
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

    public void ButtonAction(){

        leftButton.setBounds(10, 355, 100, 40);
        rightButton.setBounds(290, 355, 100, 40);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        menuPanel.add(leftButton);      //현재 버튼만 추가했고 세부 버튼 리스너는 작성하지 않은 상태
        menuPanel.add(rightButton);


    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == leftButton){
            if(pageNum != 1){
                centerPanel[pageNum-1].setVisible(false);
                centerPanel[pageNum-2].setVisible(true);
                pageNum--;
                menuTitle.setText("Page " + pageNum);

            }
        }else if(e.getSource() == rightButton){
            if(pageNum != 3){
                centerPanel[pageNum-1].setVisible(false);
                centerPanel[pageNum].setVisible(true);
                pageNum++;
                menuTitle.setText("Page " + pageNum);
            }
        }
    }

    private class CenterPanel extends JPanel{
        CenterPanel(){
            this.setBounds(0, 50, 400, 300);
            this.setLayout(null);
            this.setBackground(Color.WHITE);
            for (int y = 0; y < 4; y++) {     //지금은 패널을 계속 초기화해서 추가했지만 나중에 JPanel 배열하는법을 알면 수정 예정
                for (int x = 0; x < 4; x++) {
                    menuList = new JPanel();
                    menuList.setBounds(4 + 99 * x, 4 + 74 * y, 95, 70);
                    menuList.setBorder(new LineBorder(Color.BLACK, 1));
                    menuList.setBackground(Color.WHITE);
                    this.add(menuList);
                }
            }
            menuPanel.add(this);

        }
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