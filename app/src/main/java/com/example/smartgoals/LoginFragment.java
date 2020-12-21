package com.example.smartgoals;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {

    EditText email;
    EditText password;
    Button loginButton;

    String correct_email = "daniel@gmail.com";
    String correct_pass = "daniel";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = view.findViewById(R.id.loginEmail);
        password = view.findViewById(R.id.loginPassword);
        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }
        });

        return view;
    }

    void checkDataEntered() {
        if (TextUtils.isEmpty(email.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString()) ||
                !(email.getText().toString().equals(correct_email)) ||
                !(password.getText().toString().equals(correct_pass))) {
            Toast invalid = Toast.makeText(getActivity(), "Invalid Login!", Toast.LENGTH_SHORT);
            email.setError("Invalid Credentials!");
            password.setError("Invalid Credentials!");
            invalid.show();
        }
        else {
            Toast loginSuccess = Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT);
            loginSuccess.show();
        }


    }
}