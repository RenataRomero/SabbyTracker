package com.example.muertedecuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityEndDate extends AppCompatActivity {

    Button btnContinue;
    TextView dateStart, dateEnd;
    DatePicker date;

    String dateString;
    int yy, mm, dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_date);

        btnContinue = findViewById(R.id.historic_btn_continue);
        dateStart = findViewById(R.id.historic_txt_date_start);
        dateEnd = findViewById(R.id.historic_txt_date_end);
        date = findViewById(R.id.historic_date_end);

        Intent intent = getIntent();
        String sDateStart = intent.getStringExtra("dateStart");

        dateStart.setText("Fecha de inicio: " + sDateStart);


        final String currentYear = intent.getStringExtra("yearStart");
        final String currentMonth = intent.getStringExtra("monthStart");
        final String currentDay = intent.getStringExtra("dayStart");

        final String type = intent.getStringExtra("type");

        Log.e("TYPE", type);

        Log.e("FIRSTDATE", currentYear +"/"+currentMonth+"/"+currentDay);
        Calendar cal = Calendar.getInstance();
        Log.e("FIRSTDATE","PASO POR AQUI");
        int intDay = Integer.parseInt(currentDay);

        cal.set(Integer.parseInt(currentDay), (Integer.parseInt(currentMonth)-1), Integer.parseInt(currentYear));
        Log.e("FIRSTDATE", cal.toString());
        date.setMinDate(cal.getTimeInMillis());

        date.init(Integer.parseInt(currentYear), Integer.parseInt(currentMonth)-1, Integer.parseInt(currentDay), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                dateString = date.getDayOfMonth() + "/" + (date.getMonth()+1) + "/" + date.getYear();
                dateEnd.setText("Fecha de fin: " + dateString);

                yy = date.getYear();
                mm = date.getMonth();
                dd = date.getDayOfMonth();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, mm);
                cal.set(Calendar.DAY_OF_MONTH, dd);
                cal.set(Calendar.YEAR, yy);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEndDate.this, ActivityHistoricSplashScreen.class);

                Bundle endBundle = new Bundle();
                endBundle.putString("startDay", currentDay);
                endBundle.putString("startMonth", currentMonth);
                endBundle.putString("startYear", currentYear);
                endBundle.putString("endDay", Integer.toString(dd));
                endBundle.putString("endMonth", Integer.toString(mm+1));
                endBundle.putString("endYear", Integer.toString(yy));
                endBundle.putString("type", type);

                intent.putExtras(endBundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
