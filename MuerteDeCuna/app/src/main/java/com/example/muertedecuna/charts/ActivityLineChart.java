package com.example.muertedecuna.charts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.muertedecuna.R;
import com.example.muertedecuna.json.JsonPlaceHolderApi;
import com.example.muertedecuna.network.jsonBeans.JsonFilters;
import com.example.muertedecuna.network.jsonBeans.JsonValues;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityLineChart extends AppCompatActivity {

    String type, date, dd, mm, yy;
    ArrayList<ArrayList<JsonValues>> data;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        Log.e("LINECHART","ENTRO A LINE CHART");
        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        date = getIntent().getStringExtra("date");
        Log.e("LINECHART", date);

        String[] separated = date.split("/");

        dd = separated[0];
        mm = separated[1];
        yy = separated[2];

        Log.e("LINECHART", dd);
        Log.e("LINECHART", mm);
        Log.e("LINECHART", yy);

        type = getIntent().getStringExtra("type");


        Log.e("TYPE",type);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://58yzesjje0.execute-api.us-east-1.amazonaws.com/beta/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getVariables();

    }


    public void getVariables(){

        JsonFilters filters = new JsonFilters(dd,mm, yy);

        Log.e("TYPE",filters.toString());


        Call<ArrayList<ArrayList<JsonValues>>> call = jsonPlaceHolderApi.getValues(filters);


        switch (type){
            case "temp":
                call = jsonPlaceHolderApi.getValuesTemp(filters);
                break;
            case "sound":
                call = jsonPlaceHolderApi.getValuesSound(filters);
                break;
            case "pulse":
                call = jsonPlaceHolderApi.getValuesPulse(filters);
                break;
            case "position":
                call = jsonPlaceHolderApi.getValuesPositionX(filters);
                break;
        }

        call.enqueue(new Callback<ArrayList<ArrayList<JsonValues>>>() {

            @Override
            public void onResponse(Call<ArrayList<ArrayList<JsonValues>>> call, Response<ArrayList<ArrayList<JsonValues>>> response) {

                data = response.body();
                setChart();
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<JsonValues>>> call, Throwable t) {
                Log.e("VALUESERROR", t.toString());
            }
        });
    }

    public void setChart(){
        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        switch(type){
            case "temp":
                cartesian.title("Temperatura del dia " + date);
                cartesian.yAxis(0).title("Temperatura");
                break;
            case "pulse":
                cartesian.title("Pulso del dia " + date);
                cartesian.yAxis(0).title("Pulso");
                break;
            case "sound":
                cartesian.title("Llanto de tu bebe del dia " + date);
                cartesian.yAxis(0).title("Sonido");
                break;
            default:
                cartesian.title("N/A");
                break;
        }



        List<DataEntry> seriesData = new ArrayList<>();
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
        cartesian.xAxis(0).title("Hora");

        if(data.size() == 0){
            for(int i = 0; i < 24; i++){

                String hour = Integer.toString(i);

                seriesData.add(new CustomDataEntry(hour+":10", 0));
                seriesData.add(new CustomDataEntry(hour+":20", 0));
                seriesData.add(new CustomDataEntry(hour+":30", 0));
                seriesData.add(new CustomDataEntry(hour+":40", 0));
                seriesData.add(new CustomDataEntry(hour+":50", 0));
                seriesData.add(new CustomDataEntry(hour+":59", 0));

            }
        }else{
            for(int i = 0; i < 24; i++){
                Map<Integer, Number> valuesTen = new HashMap<>();
                valuesTen.put(0, 0);
                valuesTen.put(1, 0);
                valuesTen.put(2, 0);
                valuesTen.put(3, 0);
                valuesTen.put(4, 0);
                valuesTen.put(5, 0);
                valuesTen.put(6, 0);
                valuesTen.put(7, 0);
                valuesTen.put(8, 0);
                valuesTen.put(9, 0);
                valuesTen.put(10, 0);

                Map<Integer, Number> valuesTwenty = new HashMap<>();
                valuesTwenty.put(11, 0);
                valuesTwenty.put(12, 0);
                valuesTwenty.put(13, 0);
                valuesTwenty.put(14, 0);
                valuesTwenty.put(15, 0);
                valuesTwenty.put(16, 0);
                valuesTwenty.put(17, 0);
                valuesTwenty.put(18, 0);
                valuesTwenty.put(19, 0);
                valuesTwenty.put(20, 0);

                Map<Integer, Number> valuesThirty = new HashMap<>();
                valuesThirty.put(21, 0);
                valuesThirty.put(22, 0);
                valuesThirty.put(23, 0);
                valuesThirty.put(24, 0);
                valuesThirty.put(25, 0);
                valuesThirty.put(26, 0);
                valuesThirty.put(27, 0);
                valuesThirty.put(28, 0);
                valuesThirty.put(29, 0);
                valuesThirty.put(30, 0);

                Map<Integer, Number> valuesForty = new HashMap<>();
                valuesForty.put(31, 0);
                valuesForty.put(32, 0);
                valuesForty.put(33, 0);
                valuesForty.put(34, 0);
                valuesForty.put(35, 0);
                valuesForty.put(36, 0);
                valuesForty.put(37, 0);
                valuesForty.put(38, 0);
                valuesForty.put(39, 0);
                valuesForty.put(30, 0);

                Map<Integer, Number> valuesFifty = new HashMap<>();
                valuesFifty.put(41, 0);
                valuesFifty.put(42, 0);
                valuesFifty.put(43, 0);
                valuesFifty.put(44, 0);
                valuesFifty.put(45, 0);
                valuesFifty.put(46, 0);
                valuesFifty.put(47, 0);
                valuesFifty.put(48, 0);
                valuesFifty.put(49, 0);
                valuesFifty.put(50, 0);

                Map<Integer, Number> valuesSixty = new HashMap<>();
                valuesSixty.put(51, 0);
                valuesSixty.put(52, 0);
                valuesSixty.put(53, 0);
                valuesSixty.put(54, 0);
                valuesSixty.put(55, 0);
                valuesSixty.put(56, 0);
                valuesSixty.put(57, 0);
                valuesSixty.put(58, 0);
                valuesSixty.put(59, 0);

                for(ArrayList<JsonValues> list : data){

                    for(JsonValues value : list){

                        if(Integer.valueOf(value.getHour()) == i){

                            Number intValue = 0;
                            if(type.equals("position")){
                                intValue = Double.valueOf(value.getVariable());
                            }else{
                                intValue = Integer.valueOf(value.getVariable());
                            }
                            int min = Integer.valueOf(value.getMinute());

                            if(min <= 10){
                                valuesTen.put(min, intValue);
                            }else if(min > 10 && min <= 20){
                                valuesTwenty.put(min, intValue);
                            }else if(min > 20 && min <= 30){
                                valuesThirty.put(min, intValue);
                            }else if(min > 30 && min <= 40){
                                valuesForty.put(min, intValue);
                            }else if(min > 40 && min <= 50){
                                valuesFifty.put(min, intValue);
                            }else if(min > 50 && min <= 60){
                                valuesSixty.put(min, intValue);
                            }

                        }

                    }

                    Number averageTen = 0;
                    Number averageTwenty = 0;
                    Number averageThirty = 0;
                    Number averageForty = 0;
                    Number averageFifty = 0;
                    Number averageSixty = 0;

                    for(Number number : valuesTen.values()){
                        averageTen = averageTen.intValue() + number.intValue();
                    }

                    for(Number number : valuesTwenty.values()){
                        averageTwenty = averageTwenty.intValue() + number.intValue();
                    }

                    for(Number number : valuesSixty.values()){
                        averageThirty = averageThirty.intValue() + number.intValue();
                    }

                    for(Number number : valuesThirty.values()){
                        averageForty = averageForty.intValue() + number.intValue();
                    }

                    for(Number number : valuesForty.values()){
                        averageFifty = averageFifty.intValue() + number.intValue();
                    }

                    for(Number number : valuesFifty.values()){
                        averageSixty = averageSixty.intValue() + number.intValue();
                    }

                    String hour = Integer.toString(i);

                    seriesData.add(new CustomDataEntry(hour+":10", (averageTen.intValue()/valuesTen.size())));
                    seriesData.add(new CustomDataEntry(hour+":20", (averageTwenty.intValue()/valuesTwenty.size())));
                    seriesData.add(new CustomDataEntry(hour+":30", averageThirty.intValue()/valuesThirty.size()));
                    seriesData.add(new CustomDataEntry(hour+":40", averageForty.intValue()/valuesForty.size()));
                    seriesData.add(new CustomDataEntry(hour+":50", averageFifty.intValue()/valuesFifty.size()));
                    seriesData.add(new CustomDataEntry(hour+":59", averageSixty.intValue()/valuesSixty.size()));
                }

            }

        }

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(type);
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }

}