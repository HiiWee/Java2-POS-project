package com.project.view.sales;

import com.project.controller.dto.GraphDto;
import com.project.service.SellService;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

public class DailySalesVolume extends JPanel {
    private static final DailySalesVolume instance = new DailySalesVolume();
    private ArrayList<String> date;
    private ArrayList<Integer> dateValue;
    private int max;

    public static DailySalesVolume getInstance() {

        return instance;
    }

    private DailySalesVolume() {
    }
    SellService service =new SellService();
    public void paint(Graphics g) {
        dailySalesData(service.dailyList());
        int valueStandard = (max / 100 + 1) * 10;
        int valueLength = 600 / date.size();
        int rectLength = 200 / date.size();
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());    //좌표(x, y)에 크기 (width, height)만큼 배경 사각형을 그린다.
        g.drawLine(50, 500, 700, 500);      //좌표 (x1, y1)에서 좌표 (x2, y2)까지 선긋기
        for (int cnt = 1; cnt < 11; cnt++) {
            g.drawString(cnt * valueStandard + "", 25, 505 - 40 * cnt);       //그래프 좌측에 판매량 50단위로 String형태로 표시
            g.drawLine(50, 500 - 40 * cnt, 700, 500 - 40 * cnt);    //그래프 선긋기
        }
        g.drawString("판매량", 10, 40);
        g.drawString("날짜", 700, 520);
        g.drawLine(50, 50, 50, 500);    //그래프 y축 그리기
        for (int i = 0; i < date.size(); i++) {
            g.drawString(date.get(i), valueLength - 30 + i * valueLength, 520);
        }    //그래프 x축에 날짜 넣기
        g.setColor(Color.RED);
        for (int i = 0; i < date.size(); i++) {
            if (dateValue.get(i) > 0) {
                g.fillRect(valueLength - rectLength / 2 + i * valueLength,
                        500 - (4 * dateValue.get(i) / ((max / 100 + 1) )), rectLength,
                        4 * dateValue.get(i) / ((max / 100 + 1))); //사각 그래프 그리기
            }
        }

    }
    private void dailySalesData(List<GraphDto> graphDtos) {
        date = new ArrayList<>();
        dateValue = new ArrayList<>();
        for(GraphDto i:graphDtos){
            date.add(String.valueOf(i.getDate()));
            dateValue.add(i.getPrice());
        }
        max = Collections.max(dateValue);
    }

}