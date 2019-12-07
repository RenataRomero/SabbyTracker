package com.example.muertedecuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.muertedecuna.network.jsonBeans.Post;
import com.example.muertedecuna.json.JsonPlaceHolderApi;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityRegister extends AppCompatActivity {

    EditText email;
    TextView password, password2;
    JSONObject jsonObject = new JSONObject();
    CardView register;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.activity_log_in_email);
        password = findViewById(R.id.activity_log_in_pass);
        password2 = findViewById(R.id.activity_log_in_pass2);


        register = findViewById(R.id.activity_login_logIn);


        try {
            jsonObject.put("email", email.getText());
            jsonObject.put("password", password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://58yzesjje0.execute-api.us-east-1.amazonaws.com/test_post_users/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);



        register.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v) {

                Log.e("POST","CLICK!!!!!!!");

                if(email.getText().toString() == ""){

                    Snackbar.make(v, "Por favor inserte su correo electronico.", Snackbar.LENGTH_LONG).show();
                }

                else if(password.getText().toString() == ""){

                    Snackbar.make(v, "Por favor, inserte una contraseña.", Snackbar.LENGTH_LONG).show();
                }else{
                    createPost(v);
                }

            }
        });

    }

    private void createPost(final View v) {
        Post post = new Post(email.getText().toString(),password.getText().toString());

        Log.e("POST",post.toString());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ActivityRegister.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Estas siendo registrado, espera un momento por favor...");
        progressDialog.setTitle("Registrando Usuario");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();

        Call<Post> call = jsonPlaceHolderApi.createUser(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                Log.e("POST","SE ENVIO");
                //Post postResponse = response.body();
                //Log.e("POST",postResponse.getTitle());
                progressDialog.dismiss();

                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
                Snackbar.make(v, "Usuario Registrado correctamente!", Snackbar.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("POST","Fallo");

                Log.e("POST",t.toString());
                progressDialog.dismiss();
            }
        });
    }
}
