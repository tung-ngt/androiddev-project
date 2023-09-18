package com.tungngt.dev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentLoginBinding;
import com.tungngt.dev.ui.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity  {
    FragmentLoginBinding fragmentLogInBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        fragmentLogInBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        setContentView(fragmentLogInBinding.getRoot());
        fragmentLogInBinding.loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
        fragmentLogInBinding.signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openRegisterFragment();
            }
        });
    }
    public void openRegisterFragment(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
