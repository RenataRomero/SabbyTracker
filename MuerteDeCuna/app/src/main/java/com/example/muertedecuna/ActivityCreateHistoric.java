package com.example.muertedecuna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityCreateHistoric extends AppCompatActivity {

    TextView dateStart, dateEnd, instructions;
    DatePicker date;
    Button btnContinue, btnEndDate;
    String dateString;
    String type;
    int dd, mm, yy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_historic);

        Intent intent = getIntent();

        type = intent.getStringExtra("type");
        instructions = findViewById(R.id.historic_txt_info);
        dateStart = findViewById(R.id.historic_txt_date_start);
        dateEnd = findViewById(R.id.historic_txt_date_end);
        date = findViewById(R.id.historic_date_start);
        btnEndDate = findViewById(R.id.historic_btn_date_start);
        btnContinue = findViewById(R.id.historic_no_date_end);

        Calendar currentTime = Calendar.getInstance();

        final int currentYear = currentTime.get(Calendar.YEAR);

        final int currentMonth = currentTime.get(Calendar.MONTH);

        final int currentDay = currentTime.get(Calendar.DAY_OF_MONTH);

        Log.e("CURRENTDATE",currentYear + "/" + currentMonth + "/" + currentDay);

        date.init(currentYear, currentMonth, currentDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                dateString = date.getDayOfMonth() + "/" + (date.getMonth()+1) + "/" + date.getYear();


                dateStart.setText("Fecha de inicio: " + dateString);

                yy = date.getYear();
                mm = date.getMonth();
                dd = date.getDayOfMonth();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, mm);
                cal.set(Calendar.DAY_OF_MONTH, dd);
                cal.set(Calendar.YEAR, yy);
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle mBundle = new Bundle();
                if(dateString == null){

                    dateString = currentDay + "/" + currentMonth + "/" + currentYear;
                    mBundle.putString("dateStart", dateString);
                    mBundle.putString("dayStart", Integer.toString(currentDay));
                    mBundle.putString("monthStart", Integer.toString(currentMonth+1));
                    mBundle.putString("yearStart", Integer.toString(currentYear));
                    mBundle.putString("type", type);
                }else{

                    mBundle.putString("dateStart", dateString);
                    mBundle.putString("dayStart", Integer.toString(dd));
                    mBundle.putString("monthStart", Integer.toString(mm+1));
                    mBundle.putString("yearStart", Integer.toString(yy));

                    mBundle.putString("type", type);
                }

                Intent intent = new Intent(ActivityCreateHistoric.this, ActivityEndDate.class);

                intent.putExtras(mBundle);
                startActivity(intent);
                finish();


            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                if(dateString == null){

                    dateString = currentDay + "/" + currentMonth + "/" + currentYear;
                    mBundle.putString("dateStart", dateString);
                    mBundle.putString("startDay", Integer.toString(currentDay));
                    mBundle.putString("startMonth", Integer.toString(currentMonth+1));
                    mBundle.putString("startYear", Integer.toString(currentYear));
                    mBundle.putString("endDay", "0");
                    mBundle.putString("endMonth", "0");
                    mBundle.putString("endYear", "0");
                    mBundle.putString("type", type);
                }else{

                    mBundle.putString("dateStart", dateString);
                    mBundle.putString("startDay", Integer.toString(dd));
                    mBundle.putString("startMonth", Integer.toString(mm+1));
                    mBundle.putString("startYear", Integer.toString(yy));
                    mBundle.putString("endDay", "0");
                    mBundle.putString("endMonth", "0");
                    mBundle.putString("endYear", "0");
                    mBundle.putString("type", type);
                }

                Intent intent = new Intent(ActivityCreateHistoric.this, ActivityHistoricSplashScreen.class);

                intent.putExtras(mBundle);
                startActivity(intent);
                finish();
            }
        });


    }
}
