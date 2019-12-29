package com.elearing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elearing.api.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private TextView username;
    private TextView password;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        db=new DatabaseHelper(this.getApplicationContext(),"",null,1);
        final Button loginButton = (Button) findViewById(R.id.sign_in);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(db.login(username.getText().toString(),password.getText().toString())){
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
               }else{
                   Toast toast=Toast.makeText(getApplicationContext(),"账号或密码错误",Toast.LENGTH_SHORT);
                   toast.show();
               }
            }
        });

    }
}
