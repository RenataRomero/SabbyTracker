package com.example.muertedecuna;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.cardview.widget.CardView;

import com.facebook.AccessToken;


public class ActivityLogin extends AppCompatActivity {

    private CardView card;
    private CardView reg;
    private EditText email;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CardView logIn = findViewById(R.id.activity_login_logIn);
        CardView register = findViewById(R.id.activity_login_logIn2);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
                finish();
            }
        });

        email = findViewById(R.id.activity_log_in_email);
        pwd = findViewById(R.id.activity_log_in_pass);
        card = findViewById(R.id.activity_login_logIn);
        reg = findViewById(R.id.activity_login_logIn2);

        email.setHintTextColor(getResources().getColor(R.color.colorGreen));
        pwd.setHintTextColor(getResources().getColor(R.color.colorGreen));

        /*card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("") || pwd.getText().toString().equals("") ) {
                    Toast warning = Toast.makeText(ActivityLogin.this, "Ni email, ni Password pueden ser vacíos!",
                            Toast.LENGTH_SHORT);
                    warning.show();
                } else {
                    Bundle bundle = getIntent().getExtras();
                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        //fetchUserInfo();
    }

    private void fetchUserInfo (){

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null) {
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            startActivity(intent);
            finish();
        }
    }


}