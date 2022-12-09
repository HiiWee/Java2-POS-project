package com.project.view.sales;

import com.project.controller.dto.GraphDto;
import com.project.service.GraphService;
import com.project.utils.DateData;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

public class MonthlySales extends JPanel {
    private final static MonthlySales instance = new MonthlySales();

    private ArrayList<String> date;
    private ArrayList<Integer> dateValue;
    private int max;
    GraphService graphService = new GraphService();

    public static MonthlySales getInstance() {
        return instance;
    }

    private MonthlySales() {
    }

    public void paint(Graphics g) {
        DateData dateData = new DateData();
        dateData.monthlySalesData();
        monthlySalesData(graphService.monthlyList());
        int valueStandard = (max / 500 + 1) * 50;
        int valueLength = 600 / date.size();
        int RectLength = 200 / dateValue.size();
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());    //좌표(x, y)에 크기 (width, height)만큼 배경 사각형을 그린다.
        g.drawLine(50, 500, 700, 500);      //좌표 (x1, y1)에서 좌표 (x2, y2)까지 선긋기
        for (int cnt = 1; cnt < 11; cnt++) {
            g.drawString(cnt * valueStandard + "", 10, 505 - 40 * cnt);       //그래프 좌측에 판매량 50단위로 String형태로 표시
            g.drawLine(50, 500 - 40 * cnt, 700, 500 - 40 * cnt);    //그래프 선긋기
        }
        g.drawString("판매량( 만)", 10, 40);
        g.drawString("날짜", 700, 520);
        g.drawLine(50, 50, 50, 500);    //그래프 y축 그리기
        for (int i = 0; i < date.size(); i++) {
            g.drawString(date.get(i), valueLength - 23 + i * valueLength, 520);
        }    //그래프 x축에 날짜 넣기
        g.setColor(Color.YELLOW);
        for (int i = 0; i < date.size(); i++) {
            g.fillRect(valueLength - RectLength / 2 + i * valueLength,
                    500 - (4 * dateValue.get(i) / ((max / 500 + 1) * 5)), RectLength,
                    (4 * dateValue.get(i) / ((max / 500 + 1) * 5))); //사각 그래프 그리기
        }
    }

    public void monthlySalesData(List<GraphDto> graphDtos) {
        date = new ArrayList<>();
        dateValue = new ArrayList<>();
        for (GraphDto i : graphDtos) {
            date.add(String.valueOf(i.getDate()));
            dateValue.add(i.getPrice());
        }
        max = Collections.max(dateValue);
    }
}