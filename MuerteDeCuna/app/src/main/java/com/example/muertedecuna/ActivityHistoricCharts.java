package com.example.muertedecuna;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.example.muertedecuna.charts.Chart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muertedecuna.controllers.AdapterChart;
import com.example.muertedecuna.network.jsonBeans.JsonValues;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistoricCharts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String type, startDay, startMonth, startYear, endDay, endMonth, endYear;

        Log.e("CHARTDATE", "ONCREATE DE AHC!!");
        super.onCreate(savedInstanceState);

        Log.e("CHARTDATE", "ONCREATE DE AHC!!");
        setContentView(R.layout.activity_historic_charts);

        RecyclerView recyclerView = findViewById(R.id.historic_charts_recycler);
        recyclerView.setHasFixedSize(true);

        type = getIntent().getStringExtra("type");
        startDay= getIntent().getStringExtra("startDay");
        startMonth = getIntent().getStringExtra("startMonth");
        startYear = getIntent().getStringExtra("startYear");
        endDay = getIntent().getStringExtra("endDay");
        endMonth = getIntent().getStringExtra("endMonth");
        endYear = getIntent().getStringExtra("endYear");

        Chart chart = new Chart(type,startDay,startMonth,startYear,endDay,endMonth,endYear);

        List<Chart> chartList = chart.createChartList();
        final AdapterChart adapter = new AdapterChart(this, chartList, type);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
    }

}
