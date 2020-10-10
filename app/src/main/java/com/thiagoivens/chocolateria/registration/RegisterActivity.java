package com.thiagoivens.chocolateria.registration;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thiagoivens.chocolateria.R;
import com.thiagoivens.chocolateria.models.user.User;
import com.thiagoivens.chocolateria.models.user.UserAPI;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "LOG";
    public static final String API = "http://thiagoivens.pythonanywhere.com/";
    EditText username;
    EditText email;
    EditText password;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser(){
        String nome = username.getText().toString();
        String emailAddress = email.getText().toString();
        String senha = password.getText().toString();
        User user = new User(
                nome,
                emailAddress,
                senha
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ivenstr.pythonanywhere.com/")
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);

        Call<User> call = userAPI.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {

                if (!response.isSuccess()){
                    Log.i("LOG: Code dont success:", String.valueOf(response.code()));
                    Log.i("Code dont success:", String.valueOf(response.body()));
                    return;
                }
                Toast.makeText(getApplicationContext(), "Usuario cadastrado! " , Toast.LENGTH_LONG).show();
                Log.i("LOG: Code -", String.valueOf(response.code()));
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Deu Ruim! " , Toast.LENGTH_LONG).show();
                Log.i("OnFailureRegister:", t.getMessage());
            }
        });
    }

}