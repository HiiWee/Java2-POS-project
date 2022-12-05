package com.project.utils;
// ...
import java.util.ArrayList;
import java.util.Collections;
public class DateData {
    public ArrayList<String> date = new ArrayList<>();
    public ArrayList<Integer> dateValue = new ArrayList<>();
    public int max;
    public DateData(){

    }
    public void dailySalesData(){
        date.add("2022-12-01");
        date.add("2022-12-02");
        date.add("2022-12-03");
        date.add("2022-12-04");
        date.add("2022-12-05");
        dateValue.add(200);
        dateValue.add(300);
        dateValue.add(100);
        dateValue.add(50);
        dateValue.add(400);
        max = Collections.max(dateValue);
    }
    public void monthlySalesData(){
        date.add("1월");
        date.add("2월");
        date.add("3월");
        date.add("4월");
        date.add("5월");
        dateValue.add(6000);
        dateValue.add(9000);
        dateValue.add(3000);
        dateValue.add(1500);
        dateValue.add(12000);
        max = Collections.max(dateValue);
    }
    public void salesVolumeByItem(){
        date.add("2022-12-01");
        date.add("2022-12-02");
        date.add("2022-12-03");
        date.add("2022-12-04");
        date.add("2022-12-05");
        dateValue.add(200);
        dateValue.add(300);
        dateValue.add(100);
        dateValue.add(50);
        dateValue.add(400);

        max = Collections.max(dateValue);
    }



}