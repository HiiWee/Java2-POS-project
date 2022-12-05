package com.project.view.sales;

import com.project.utils.DateData;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MonthlySales extends JPanel {
    private static final MonthlySales instance = new MonthlySales();

    public static MonthlySales getInstance() {
        return instance;
    }

    public MonthlySales() {

    }

    public void paint(Graphics g) {
        DateData dateData = new DateData();
        dateData.monthlySalesData();
        int valueStandard = (dateData.max / 1000 + 1) * 100;
        int valueLength = 600 / dateData.date.size();
        int RectLength = 200 / dateData.dateValue.size();
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());    //좌표(x, y)에 크기 (width, height)만큼 배경 사각형을 그린다.
        g.drawLine(50, 500, 700, 500);      //좌표 (x1, y1)에서 좌표 (x2, y2)까지 선긋기
        for (int cnt = 1; cnt < 11; cnt++) {
            g.drawString(cnt * valueStandard + "", 10, 505 - 40 * cnt);       //그래프 좌측에 판매량 50단위로 String형태로 표시
            g.drawLine(50, 500 - 40 * cnt, 700, 500 - 40 * cnt);    //그래프 선긋기
        }
        g.drawString("판매량", 10, 40);
        g.drawString("날짜", 700, 520);
        g.drawLine(50, 50, 50, 500);    //그래프 y축 그리기
        for (int i = 0; i < dateData.date.size(); i++) {
            g.drawString(dateData.date.get(i), valueLength - 10 + i * valueLength, 520);
        }    //그래프 x축에 날짜 넣기
        g.setColor(Color.RED);
        for (int i = 0; i < dateData.date.size(); i++) {
            if (dateData.dateValue.get(i) > 0) {
                g.fillRect(valueLength - RectLength / 2 + i * valueLength,
                        500 - (4 * dateData.dateValue.get(i) / ((dateData.max / 1000 + 1) * 10)), RectLength,
                        (4 * dateData.dateValue.get(i) / ((dateData.max / 1000 + 1) * 10))); //사각 그래프 그리기
            }
        }

    }
}