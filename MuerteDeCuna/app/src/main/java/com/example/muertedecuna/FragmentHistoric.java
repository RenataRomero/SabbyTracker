package com.example.muertedecuna;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.time.Year;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHistoric.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHistoric#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHistoric extends Fragment implements DatePickerDialog.OnDateSetListener{


    Button btnDateStart;
    Button btnDateEnd;
    TextView txtDateStart;
    TextView txtDateEnd;
    DatePickerDialog dpEnd;


    public FragmentHistoric() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historic, container, false);

        btnDateStart = view.findViewById(R.id.historic_btn_date_start);
        btnDateEnd = view.findViewById(R.id.historic_btn_date_end);

        txtDateStart = view.findViewById(R.id.historic_txt_date_start);
        txtDateEnd = view.findViewById(R.id.historic_txt_date_end);

        btnDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpStart = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getTargetFragment(), Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                dpStart.show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        Log.e("DATESTART",date);
        txtDateStart.setText(date);
    }
}
