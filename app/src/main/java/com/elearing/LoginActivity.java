package com.elearing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elearing.api.DatabaseHelper;
import com.elearing.room.UserApi;
import com.elearing.room.databasse.UserDatabase;
import com.elearing.room.entity.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {
    private TextView username;
    private TextView password;
    private DatabaseHelper db;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private UserDatabase userDatabase;

    public static String name = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        final TextView textView = findViewById(R.id.textView8);
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        db=new DatabaseHelper(this.getApplicationContext(),"",null,1);
        final Button loginButton = (Button) findViewById(R.id.sign_in);

        TextView sign_up = findViewById(R.id.sign_up_text);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        userDatabase = UserApi.getInstance(getApplicationContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("login_message", Context.MODE_PRIVATE);
                if(sharedPreferences.getBoolean("login",false)){
                    name = sharedPreferences.getString("username","1");
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else {
                    if (userDatabase.userDao().login(username.getText().toString(), password.getText().toString()) != null) {
                        sharedPreferences.edit().putBoolean("login", true);
                        sharedPreferences.edit().putString("username",username.getText().toString());
                        name = username.getText().toString();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "账号或密码错误", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
