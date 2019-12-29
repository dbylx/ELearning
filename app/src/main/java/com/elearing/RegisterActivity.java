package com.elearing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elearing.api.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private TextView username;
    private TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = (Button) findViewById(R.id.sign_up);

        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);

        db=new DatabaseHelper(this.getApplicationContext(),"",null,1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.insert(username.getText().toString(),password.getText().toString())){
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
