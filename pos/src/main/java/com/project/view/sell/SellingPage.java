package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.utils.TableNumberUtil;
import com.project.view.common.NormalButton;
import com.project.view.manegement.ManegementMainPage;
import com.project.view.sales.SalesMainPage;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SellingPage extends JFrame {
    private static final SellingPage instance = new SellingPage();
    private final JPanel tabPanel=new JPanel();
    private final JPanel buttonPanel=new JPanel();
    private final JPanel tablePanel=new JPanel();
    private TablePanel[] tablePanels = new TablePanel[TableNumberUtil.NUMBER_OF_TABLE];
   // private final JTabbedPane tab=new JTabbedPane();
    private final Container container = getContentPane();
    private final NormalButton closeButton = new NormalButton(ButtonNameUtil.CLOSE_POS);
    private final NormalButton salesButton = new NormalButton("영업");
    private final NormalButton manegerButton = new NormalButton("관리");
    private final NormalButton orderButton = new NormalButton(ButtonNameUtil.ORDER);
    private final NormalButton billButton = new NormalButton(
            ButtonNameUtil.BILL_CHECK);

    public static SellingPage getInstance() {
        return instance;
    }

    public TablePanel[] getTablePanels() {
        return tablePanels;
    }

    private SellingPage() {
        initializePage();
        buttonPanel.setLayout(null);
        buttonPanel.add(closeButton);
        buttonPanel.add(billButton);
        buttonPanel.add(orderButton);
        tablePanel.setLayout(new GridLayout(3, 3, 3, 3));

        for (int i = 0; i < TableNumberUtil.NUMBER_OF_TABLE; i++) {
            tablePanels[i] = new TablePanel(String.valueOf(i + 1));
            tablePanel.add(tablePanels[i]);
        }
        tabPanel.setLayout(new FlowLayout());
        tabPanel.add(salesButton);
        tabPanel.add(manegerButton);
        container.add(tabPanel);
        container.add(tablePanel);
        container.add(buttonPanel);
        tabPanel.setBounds(0,0,150,30);
        tablePanel.setBounds(0, 30, 500, 500);
        billButton.setBounds(500, 430, 150, 100);
        orderButton.setBounds(650, 430, 150, 100);
        closeButton.setBounds(650, 30, 150, 100);
        //tab.addTab("영업",SalesMainPage.getInstance().getContentPane());
        //tab.addTab("관리",ManegementMainPage.getinstance().getContentPane() );
        //tab.addTab("판매",instance.getContentPane());//null 값....
        //container.add(tab); 탭 넣으면 버튼 사라짐.. 이상하게도 테이블은 계속 남아있는다
    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getBillButton() {
        return billButton;
    }

    public JButton getSalesButton(){return salesButton;}
    public JButton getManegementButton(){return manegerButton;}

}
