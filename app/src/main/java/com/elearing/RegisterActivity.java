package com.elearing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elearing.api.DatabaseHelper;
import com.elearing.room.UserApi;
import com.elearing.room.databasse.UserDatabase;
import com.elearing.room.entity.User;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private TextView username;
    private TextView password;
    private UserDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = (Button) findViewById(R.id.sign_up);

        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);

        db=new DatabaseHelper(this.getApplicationContext(),"",null,1);

        userDatabase = UserApi.getInstance(getApplicationContext());

        TextView sign_in = findViewById(R.id.sign_in_text);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userDatabase.userDao().usernameExist(username.getText().toString())==null){
                    User user = new User();
                    user.username = username.getText().toString();
                    user.password = password.getText().toString();
                    userDatabase.userDao().register(user);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"账户已存在",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}
