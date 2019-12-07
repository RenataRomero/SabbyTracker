package com.example.muertedecuna;

import android.app.ProgressDialog;
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

import com.example.muertedecuna.json.JsonPlaceHolderApi;
import com.example.muertedecuna.network.jsonBeans.JsonLogin;
import com.example.muertedecuna.network.jsonBeans.Post;
import com.facebook.AccessToken;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActivityLogin extends AppCompatActivity {

    private CardView reg, logIn;
    private EditText email;
    private EditText password;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.activity_log_in_email);
        password = findViewById(R.id.activity_log_in_pass);
        logIn = findViewById(R.id.activity_login_logIn);
        reg = findViewById(R.id.activity_login_logIn2);


        reg.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
                finish();
            }
        });
        email.setHintTextColor(getResources().getColor(R.color.colorGreen));
        password.setHintTextColor(getResources().getColor(R.color.colorGreen));

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://58yzesjje0.execute-api.us-east-1.amazonaws.com/test_post_users/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);



        logIn.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v) {

                Log.e("POST","CLICK!!!!!!!");

                if(email.getText().toString() == ""){

                    Snackbar.make(v, "Por favor inserte su correo electronico.", Snackbar.LENGTH_LONG).show();
                }

                else if(password.getText().toString() == ""){

                    Snackbar.make(v, "Por favor, inserte una contraseña.", Snackbar.LENGTH_LONG).show();
                }else{

                    Log.e("POST","BUTTON");
                    authUser(v);
                }

            }
        });
    }

    private void authUser(final View v) {
        Log.e("POST","Inside authUser");
        Post post = new Post(email.getText().toString(),password.getText().toString());

        Log.e("POST",post.getEmail());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ActivityLogin.this);
        progressDialog.setMessage("Un momento por favor...");
        progressDialog.setTitle("Iniciando Sesion");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();

        Call<JsonLogin> call = jsonPlaceHolderApi.authUser(post);

        Log.e("POST","Trajo la llamada");

        call.enqueue(new Callback<JsonLogin>() {
            @Override
            public void onResponse(Call<JsonLogin> call, Response<JsonLogin> response) {

                progressDialog.dismiss();

                if(response.body().getMessage().equals("Correct")){

                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }else if (response.body().getMessage().equals("User")){
                    Snackbar.make(v, "Email incorrecto, por favor intente de nuevo", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(v, "Contraseña incorrecta, por favor intente de nuevo", Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<JsonLogin> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

}