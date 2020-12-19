package com.example.smartgoals;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.redirectLogin);

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });

        login.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginRedirect = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginRedirect);
            }});

        }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(firstName)) {
            firstName.setError("First name is required!");
            Toast tf = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            tf.show();
        }

        if (isEmpty(lastName)) {
            Toast tl = Toast.makeText(this, "You must enter last name to register!", Toast.LENGTH_SHORT);
            lastName.setError("Last name is required!");
            tl.show();

        }

        if (!isEmail(email)) {
            Toast te = Toast.makeText(this, "You must enter email to register!", Toast.LENGTH_SHORT);
            email.setError("Enter valid email!");
            te.show();
        }

        if (password.length() < 8) {
            Toast tp = Toast.makeText(this, "Password must be at least 8 characters!", Toast.LENGTH_SHORT);
            password.setError("Enter valid password!");
            tp.show();
        }

        if (!confirmPassword.equals(password)) {
            Toast tcp = Toast.makeText(this, "Passwords must match!", Toast.LENGTH_SHORT);
            confirmPassword.setError("Passwords must match!");
            tcp.show();
        }
    }

}
