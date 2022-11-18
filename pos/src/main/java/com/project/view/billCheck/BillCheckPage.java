package com.project.view.billCheck;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BillCheckPage extends JFrame {
    private static final BillCheckPage instance = new BillCheckPage();

    public static BillCheckPage getInstance() {
        return instance;
    }

    private final NormalButton billCheckPageBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final NormalButton billCheckPageRefundButton = new NormalButton(ButtonNameUtil.REFUND);
    private final JTextArea detailOfBillArea = new JTextArea();
    private final Container container = getContentPane();
    private final JPanel scroll = new JPanel();

    public BillCheckPage() {
        initializePage();
        container.add(billCheckPageRefundButton);
        container.add(billCheckPageBackButton);
        scroll.setLayout(new BorderLayout());
        scroll.add(detailOfBillArea,BorderLayout.CENTER);
        detailOfBillArea.setRows(30);
        detailOfBillArea.setColumns(30);
        JScrollPane jScrollPane = new JScrollPane(detailOfBillArea);
        scroll.add(jScrollPane);
        container.add(scroll);
        billCheckPageBackButton.setBounds(650, 450, 150, 100);
        billCheckPageRefundButton.setBounds(650, 50, 150, 100);
        scroll.setBounds(10, 10, 600, 540);//https://woongbin96.tistory.com/105 Scroll은 Layout이 null이면 출력되지않음
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("POS SYSTEM");
    }

    public JButton getBackButtonOnBillCheckPage() {
        return billCheckPageBackButton;
    }

    public JButton getRefundButtonOnBillCheckPage() {
        return billCheckPageRefundButton;
    }

}
