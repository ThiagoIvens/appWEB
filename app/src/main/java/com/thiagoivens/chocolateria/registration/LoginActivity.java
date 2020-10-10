package com.thiagoivens.chocolateria.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thiagoivens.chocolateria.MainActivity;
import com.thiagoivens.chocolateria.R;
import com.thiagoivens.chocolateria.models.user.User;
import com.thiagoivens.chocolateria.models.user.UserAPI;

import java.util.Base64;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LOG";
    public static final String API = "http://ivenstr.pythonanywhere.com/";
    SharedPreferences prefs;
    String token;
    int id;
    public static User user;
    EditText username;
    EditText password;
    Button btnLogin;
    TextView tvCadastrar;

    public static User getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("user_info", MODE_PRIVATE);
        token = prefs.getString("token", "No name defined");
        id = prefs.getInt("id", 0);
//        if(id != 0){
//            new LoginActivity().loginWithToken();
//        }

        tvCadastrar = findViewById(R.id.tvCadastrar);
        username = findViewById(R.id.etName);
        password = findViewById(R.id.etPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ENCODE FOR BASE64
                String nome = username.getText().toString();
                String senha = password.getText().toString();
                String base = nome + ":" + senha;
                String encoding = null;
                encoding = "Basic " + Base64.getEncoder().encodeToString( base.getBytes());


                Log.i("LOG encoding ", encoding);
                // parte do Retrofit


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API)
                        .addConverterFactory( GsonConverterFactory.create() )
                        .build();

                UserAPI userAPI = retrofit.create(UserAPI.class);

                Call<User> authUser = userAPI.login(nome, senha);

//                Call<String> authUser = userAPI.login( username.getText().toString(), password.getText().toString());
                authUser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        Log.i("LOG Response success = ", String.valueOf(response.isSuccess()));
                        Log.i("LOG Response code = ", String.valueOf(response.code()));
                        Log.i("LOG Response bpdy = ", String.valueOf(response.body()));
                        if (response.isSuccess()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Usuario ou senha incorretos!" , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.w("LOG FAILURE on Login.btnLogin.OnClick ", t.getMessage());
                    }
                });
            }
        });

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

//    public void loginWithToken(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API)
//                .addConverterFactory( GsonConverterFactory.create( ) )
//                .build();
//
//        // POST USER
//        UserAPI userAPI = retrofit.create(UserAPI.class);
//        Call<User> call = userAPI.sendToken(token);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Response<User> response, Retrofit retrofit) {
//                if (response.isSuccess()) {
//                    StringBuilder builder = new StringBuilder();
//                    JSONTokener tokener = new JSONTokener(builder.toString());
//                    try {
//                        JSONObject finalResult = new JSONObject(tokener);
//                        Log.e("result", finalResult.toString());
//
//                        token = finalResult.getString("token");
//
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    Toast.makeText(getApplicationContext(), "Usuario ou senha incorretos! " , Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("LOG FAILURE ON loginWithToken - ", t.getMessage());
//            }
//        });
//    }
}
