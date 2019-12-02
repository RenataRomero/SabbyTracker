package com.example.muertedecuna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.muertedecuna.charts.CustomDataEntry;
import com.example.muertedecuna.network.jsonBeans.JsonValues;

import java.util.ArrayList;
import java.util.List;

public class ActivityChartsHistoric extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts_historic);

        List<List<JsonValues>> data = (List<List<JsonValues>>) getIntent().getSerializableExtra("values");

        Log.e("VALUESCHARTS",data.toString());

        AnyChartView anyChartView = findViewById(R.id.historic_chart);
        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.");

        cartesian.yAxis(0).title("Number of Bottles Sold (thousands)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();


        seriesData.add(new CustomDataEntry("1986", 3.6));
        seriesData.add(new CustomDataEntry("1987", 7.1));
        seriesData.add(new CustomDataEntry("1988", 8.5));
        seriesData.add(new CustomDataEntry("1989", 9.2));
        seriesData.add(new CustomDataEntry("1990", 10.1));
        seriesData.add(new CustomDataEntry("1991", 11.6));
        seriesData.add(new CustomDataEntry("1992", 16.4));
        seriesData.add(new CustomDataEntry("1993", 18.0));
        seriesData.add(new CustomDataEntry("1994", 13.2));
        seriesData.add(new CustomDataEntry("1995", 12.0));
        seriesData.add(new CustomDataEntry("1996", 3.2));
        seriesData.add(new CustomDataEntry("1997", 4.1));
        seriesData.add(new CustomDataEntry("1998", 6.3));
        seriesData.add(new CustomDataEntry("1999", 9.4));
        seriesData.add(new CustomDataEntry("2000", 11.5));
        seriesData.add(new CustomDataEntry("2001", 13.5));
        seriesData.add(new CustomDataEntry("2002", 14.8));
        seriesData.add(new CustomDataEntry("2003", 16.6));
        seriesData.add(new CustomDataEntry("2004", 18.1));
        seriesData.add(new CustomDataEntry("2005", 17.0));
        seriesData.add(new CustomDataEntry("2006", 16.6));
        seriesData.add(new CustomDataEntry("2007", 14.1));
        seriesData.add(new CustomDataEntry("2008", 15.7));
        seriesData.add(new CustomDataEntry("2009", 12.0));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Brandy");
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
