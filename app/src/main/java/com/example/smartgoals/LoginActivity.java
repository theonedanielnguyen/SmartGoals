package com.example.smartgoals;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgoals.daos.UserDao;
import com.example.smartgoals.databases.UserDatabase;
import com.example.smartgoals.models.User;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    Button registerButton;

    String correct_email = "daniel@gmail.com";
    String correct_pass = "daniel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.loginRegister);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }
        });

        registerButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginRedirect = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(loginRedirect);
            }});

    }

    void checkDataEntered() {
        if (TextUtils.isEmpty(email.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString()) ||
                !(email.getText().toString().equals(correct_email)) ||
                !(password.getText().toString().equals(correct_pass))) {
            Toast invalid = Toast.makeText(this, "Invalid Login!", Toast.LENGTH_SHORT);
            email.setError("Invalid Credentials!");
            password.setError("Invalid Credentials!");
            invalid.show();
        }
        else {
            Toast loginSuccess = Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT);
            loginSuccess.show();
        }


    }
}