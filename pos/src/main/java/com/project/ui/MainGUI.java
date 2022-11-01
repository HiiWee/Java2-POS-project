package com.project.ui;

import javax.swing.*;




public class MainGUI extends JFrame{
	MainGUI(){
		super("음식포스기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500,1200);
		setVisible(true);
		JTabbedPane pane = createTabbedPane();
		add(pane);
		setResizable(false);
	}
	
	
	public JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		
		pane.addTab("판매", new SellPanel());		//판매탭 
		pane.addTab("영업", new SalesGUI());		//영업탭
		pane.addTab("관리자", new ManageGUI());		//관리자 탭
		
		return pane;
	}
	
	class SellPanel extends JPanel{								//GUI만 구성해두고 영업탭, 관리자 탭, 그 이외의 모든 테이블과 주문, 영수증 조회, 마감버튼 구현 x
		String[] tablenum = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		JButton[] table = new JButton[9];
		SellPanel(){
			setLayout(null);
			for(int i = 0; i < 3; i++) {		//테이블 버튼 생성과 위치 배정
				for(int j = 0; j < 3; j++) {
					table[3*i+j] = new JButton(tablenum[3*i+j]);
					table[3*i+j].setBounds(100 + 250*j, 100 + 250*i, 150, 150);
					
				}
			}
			for(int i = 0; i < 9; i++) {
				add(table[i]);
			}
			
			JButton end = new JButton("마감");
			end.setBounds(1250, 100, 150, 100);
			JButton order = new JButton("주문");
			order.setBounds(1000, 1000, 150, 100);
			JButton lookup = new JButton("영수증 조회");
			lookup.setBounds(1250, 1000, 150, 100);
			add(end);
			add(order);
			add(lookup);
		}
		
	}
	
}

