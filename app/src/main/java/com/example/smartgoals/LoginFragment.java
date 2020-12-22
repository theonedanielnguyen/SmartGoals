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

import com.example.smartgoals.databases.UserDatabase;
import com.example.smartgoals.models.User;
import com.example.smartgoals.daos.UserDao;

import org.mindrot.jbcrypt.BCrypt;


public class LoginFragment extends Fragment {

    EditText email;
    EditText password;
    Button loginButton;

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

        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getContext());
                UserDao userDao = userDatabase.userDao();

                String validate = checkDataEntered();

                if (validate == "false") {
                }
                else {
                    new Thread(new Runnable () {
                        @Override
                        public void run() {
                            UserDatabase userDatabase = UserDatabase.getUserDatabase(getContext());
                            final UserDao userDao = userDatabase.userDao();

                            String emailText = email.getText().toString().trim();
                            String passwordText = password.getText().toString().trim();
                            User user = userDao.getUserByEmail(emailText);
                            if (user == null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                            public void run(){
                                        Toast.makeText(getContext(), "Invalid login", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else {
                                if(BCrypt.checkpw(passwordText, user.getPassword())) {
                                    Intent goalsRedirect = new Intent(getContext(), GoalsActivity.class);
                                    startActivity(goalsRedirect);
                                }
                            }
                        }
                    }).start();

                }
            }
        });

        return view;
    }

    String checkDataEntered() {
        if (TextUtils.isEmpty(email.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString())) {
            Toast invalid = Toast.makeText(getActivity(), "Invalid Login!", Toast.LENGTH_SHORT);
            email.setError("Invalid Credentials!");
            password.setError("Invalid Credentials!");
            invalid.show();
            return "false";
        }
        else {
            return "true";
        }


    }
}