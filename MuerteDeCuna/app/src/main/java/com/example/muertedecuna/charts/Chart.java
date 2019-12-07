package com.example.muertedecuna.charts;


import android.util.Log;

import com.example.muertedecuna.network.jsonBeans.JsonValues;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Chart {

    private String name;
    private Class activityClass;

    private String type, startDay, startMonth, startYear, endDay, endMonth, endYear;

    public Chart(String type, String startDay, String startMonth, String startYear, String endDay, String endMonth, String endYear) {
        this.type = type;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Chart(String name, Class activityClass) {
        this.name = name;
        this.activityClass = activityClass;
    }

    public String getName() {
        return name;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public ArrayList<Chart> createChartList() {
        Log.e("CHARTDATE", "Entro al CREATE!!");
        ArrayList<Chart> chartList = new ArrayList<>();

        Log.e("CHARTDATE", "CREO LA LISTA!!");

        Calendar startCal = Calendar.getInstance();
        startCal.set(Integer.parseInt(startYear), Integer.parseInt(startMonth)-1, Integer.parseInt(startDay));

        Log.e("CHARTDATE", startCal.toString());


        Calendar endCal = Calendar.getInstance();
        endCal.set(Integer.parseInt(endYear), Integer.parseInt(endMonth)-1, Integer.parseInt(endDay));
        Log.e("CHARTDATE", endCal.toString());

        for(Calendar i = startCal; i.before(endCal) || i.equals(endCal); i.add(Calendar.DATE, 1)){

            Log.e("CHARTDATE", "Entro al for");

            String title = i.get(Calendar.DAY_OF_MONTH) + "/" + (i.get(Calendar.MONTH)+1) + "/" + i.get(Calendar.YEAR);

            chartList.add(new Chart(title, ActivityLineChart.class));

        }
        return chartList;
    }

}