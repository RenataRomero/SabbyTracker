package com.example.muertedecuna;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.muertedecuna.R.color.colorAccent;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMetrics extends Fragment {



    public FragmentMetrics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_metrics, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView pulse = getView().findViewById(R.id.metrics_img_pulse);
        ImageView temp = getView().findViewById(R.id.metrics_img_temperature);
        ImageView sound = getView().findViewById(R.id.metrics_img_sound);
        ImageView position = getView().findViewById(R.id.metrics_img_position);

        pulse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateHistoric.class);
                Bundle typeBundle = new Bundle();
                typeBundle.putString("type", "pulse");

                intent.putExtras(typeBundle);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateHistoric.class);
                Bundle typeBundle = new Bundle();
                typeBundle.putString("type", "temp");

                intent.putExtras(typeBundle);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateHistoric.class);
                Bundle typeBundle = new Bundle();
                typeBundle.putString("type", "sound");

                intent.putExtras(typeBundle);
                startActivity(intent);
               //getActivity().finish();
            }
        });

        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateHistoric.class);
                Bundle typeBundle = new Bundle();
                typeBundle.putString("type", "position");

                intent.putExtras(typeBundle);
                startActivity(intent);
                //getActivity().finish();
            }
        });

    }
}
