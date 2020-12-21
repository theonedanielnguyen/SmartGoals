package com.example.smartgoals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartgoals.daos.UserDao;
import com.example.smartgoals.databases.UserDatabase;
import com.example.smartgoals.models.User;

import static android.text.TextUtils.isEmpty;

public class RegisterFragment extends Fragment {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button registerButton;

    public RegisterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        registerButton = (Button) view.findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks validations
                checkDataEntered();

                // Creates new user
                User newUser = new User();
                newUser.setFirstName(firstName.getText().toString());
                newUser.setLastName(lastName.getText().toString());
                newUser.setEmail(email.getText().toString());
                newUser.setPassword(password.getText().toString());
                newUser.setConfirmPassword(confirmPassword.getText().toString());

                // Do Insert Operation
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getContext());
                final UserDao userDao = userDatabase.userDao();
                new Thread(new Runnable () {
                    @Override
                    public void run() {

                        // Registers the User
                        userDao.insert(newUser);
                        System.out.println("New user registered!");
//                        Toast.makeText(getContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                    }
                }).start();
            }
        });

        return view;
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    void checkDataEntered() {
        if (isEmpty(firstName.getText())) {
            firstName.setError("First name is required!");
            Toast tf = Toast.makeText(getActivity(), "You must enter first name to register!", Toast.LENGTH_SHORT);
            tf.show();
        }

        if (isEmpty(lastName.getText())) {
            Toast tl = Toast.makeText(getActivity(), "You must enter last name to register!", Toast.LENGTH_SHORT);
            lastName.setError("Last name is required!");
            tl.show();

        }

        if (!isEmail(email)) {
            Toast te = Toast.makeText(getActivity(), "You must enter email to register!", Toast.LENGTH_SHORT);
            email.setError("Enter valid email!");
            te.show();
        }

        if (password.length() < 8) {
            Toast tp = Toast.makeText(getActivity(), "Password must be at least 8 characters!", Toast.LENGTH_SHORT);
            password.setError("Enter valid password!");
            tp.show();
        }

        if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
            Toast tcp = Toast.makeText(getActivity(), "Passwords must match!", Toast.LENGTH_SHORT);
            confirmPassword.setError("Passwords must match!");
            tcp.show();
        }
    }

}