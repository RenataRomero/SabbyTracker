package com.example.muertedecuna.charts;

import com.anychart.chart.common.dataentry.ValueDataEntry;

public class CustomDataEntry extends ValueDataEntry {
    String x;
    Number value;

    public CustomDataEntry(String x, Number value) {
        super(x, value);
    }

}
