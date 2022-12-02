package com.project.view.billcheck;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.Container;
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

    private final NormalButton billCheckPageBackButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton billCheckPageRefundButton = new NormalButton(ButtonNameMessage.REFUND);
    private final JTextArea detailOfBillArea = new JTextArea();
    private final Container container = getContentPane();
    private final JPanel scroll = new JPanel();

    public BillCheckPage() {
        initializePage();
        container.add(billCheckPageRefundButton);
        container.add(billCheckPageBackButton);
        scroll.setLayout(new BorderLayout());
        scroll.add(detailOfBillArea, BorderLayout.CENTER);
        detailOfBillArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(detailOfBillArea);
        scroll.add(jScrollPane);
        container.add(scroll);
        billCheckPageBackButton.setBounds(650, 435, 150, 100);
        billCheckPageRefundButton.setBounds(650, 50, 150, 100);
        scroll.setBounds(10, 10, 500, 525);//https://woongbin96.tistory.com/105
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
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
