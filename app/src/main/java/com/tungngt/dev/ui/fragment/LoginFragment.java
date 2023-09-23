package com.tungngt.dev.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentLoginBinding;
import com.tungngt.dev.ui.activity.MainActivity;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding fragmentLoginBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());

        fragmentLoginBinding.signupButton.setOnClickListener(this::signup);
        fragmentLoginBinding.loginButton.setOnClickListener(this::login);

        EditText username = fragmentLoginBinding.usernameTextField.getEditText();
        EditText password = fragmentLoginBinding.passwordTextField.getEditText();
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragmentLoginBinding.usernameTextField.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragmentLoginBinding.passwordTextField.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


    }

    private void login(View view) {
        EditText username = fragmentLoginBinding.usernameTextField.getEditText();
        EditText password = fragmentLoginBinding.passwordTextField.getEditText();

        assert username != null;
        assert password != null;

        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        if(usernameText.isEmpty()) {
            fragmentLoginBinding.usernameTextField.setError("Please fill in the field");
        }

        if(passwordText.isEmpty()) {
            fragmentLoginBinding.passwordTextField.setError("Please fill in the field");
        }


        if(usernameText.matches("Admin") && passwordText.matches("Admin")) {
            openMainActivity();
        } else {
            Toast.makeText(getContext(), "Username or Password not correct", Toast.LENGTH_SHORT).show();
        }

    }

    private void signup(View view) {
        NavController navController = NavHostFragment.findNavController(
                Objects.requireNonNull(
                        requireActivity()
                                .getSupportFragmentManager()
                                .findFragmentById(R.id.nav_host_fragment_container)
                )
        );

        navController.navigate(R.id.action_LoginFragment_to_RegisterFragment);
    }

    private void openMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentLoginBinding.getRoot();
    }

}