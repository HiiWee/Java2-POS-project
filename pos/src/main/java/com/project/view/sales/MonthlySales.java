package com.project.view.sales;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MonthlySales extends JPanel {
    private static final MonthlySales instance = new MonthlySales();
    public static MonthlySales getInstance() {
        return instance;
    }
    private String[] date = {"1월", "2월", "3월", "4월", "5월", "6월"}; //나중에 날짜 이름 넣을 데이터 배열(임시로 값을 부여)
    private int[] dateValue = {6300, 7500, 12000, 9000, 3000, 2000};
    private int valueLength = 600/date.length;
    private int RectLength = 200/date.length;
    public MonthlySales() {

    }
    public void paint(Graphics g){
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());    //좌표(x, y)에 크기 (width, height)만큼 배경 사각형을 그린다.
        g.drawLine(50, 500, 700, 500);      //좌표 (x1, y1)에서 좌표 (x2, y2)까지 선긋기
        for(int cnt=1; cnt<11; cnt++){
            g.drawString(cnt*1500+"", 10, 505-40*cnt);       //그래프 좌측에 판매량 50단위로 String형태로 표시
            g.drawLine(50, 500-40 *cnt, 700,500-40*cnt);    //그래프 선긋기
        }
        g.drawString("판매량", 10, 40);
        g.drawString("날짜", 700, 520);
        g.drawLine(50, 50, 50, 500);    //그래프 y축 그리기
        for(int i = 0; i < date.length; i++){ g.drawString(date[i], valueLength-10+i*valueLength, 520); }    //그래프 x축에 날짜 넣기
        g.setColor(Color.RED);
        for(int i = 0; i < date.length; i++){
            if(dateValue[i] > 0)
                g.fillRect(valueLength-RectLength/2+i*valueLength, 500-(4*dateValue[i]/150), RectLength, 4*dateValue[i]/150); //사각 그래프 그리기
        }

    }
}
