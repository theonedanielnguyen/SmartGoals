package com.example.smartgoals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;

    private static final String USER_SESSION = "user_session";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";

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

        sharedPreferences = getActivity().getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);

        // when activity is first opened - the checks if the shared preferences data is available or not:
        String user_name = sharedPreferences.getString(USER_NAME, null);
        if (user_name != null) {

            // if data is available --> it will redirect you back to the dashboard.
            Intent redirectToDash = new Intent(getContext(), DummyDashboard.class);
            startActivity(redirectToDash);
        }

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
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putLong(USER_ID, user.getId()).commit();
                                    editor.putString(USER_NAME, user.getFirstName());
                                    editor.apply();
                                    Intent dummyDashboard = new Intent(getContext(), DummyDashboard.class);
                                    startActivity(dummyDashboard);
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