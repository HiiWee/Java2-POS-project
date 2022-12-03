package com.project.view.sales;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DailySalesVolume extends JPanel {
    private static final DailySalesVolume instance = new DailySalesVolume();

    private String[] date = {"2022-11-30", "2022-12-01", "2022-12-02", "2022-12-03", "2022-12-04"}; //나중에 날짜 이름 넣을 데이터 배열(임시로 값을 부여)
    private int[] dateValue = {210, 250, 400, 300, 100};     //나중에 해당 날짜 판매량 넣을 데이터 배열
    public static DailySalesVolume getInstance() {
        return instance;
    }
    private int valueLength = 600/date.length;
    private int RectLength = 200/date.length;
    public DailySalesVolume() {

    }
    public void paint(Graphics g){
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());    //좌표(x, y)에 크기 (width, height)만큼 배경 사각형을 그린다.
        g.drawLine(50, 500, 700, 500);      //좌표 (x1, y1)에서 좌표 (x2, y2)까지 선긋기
        for(int cnt=1; cnt<11; cnt++){
            g.drawString(cnt*50+"", 25, 505-40*cnt);       //그래프 좌측에 판매량 50단위로 String형태로 표시
            g.drawLine(50, 500-40 *cnt, 700,500-40*cnt);    //그래프 선긋기
        }
        g.drawString("판매량", 10, 40);
        g.drawString("날짜", 700, 520);
        g.drawLine(50, 50, 50, 500);    //그래프 y축 그리기
        for(int i = 0; i < date.length; i++){ g.drawString(date[i], valueLength-30+i*valueLength, 520); }    //그래프 x축에 날짜 넣기
        g.setColor(Color.RED);
        for(int i = 0; i < date.length; i++){
            if(dateValue[i] > 0)
                g.fillRect(valueLength-RectLength/2+i*valueLength, 500-(4*dateValue[i]/5), RectLength, 4*dateValue[i]/5); //사각 그래프 그리기
        }

    }


}
