package com.tungngt.dev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.tungngt.dev.databinding.FragmentLoginBinding;

public class LoginActivity extends AppCompatActivity  {
    FragmentLoginBinding fragmentLogInBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
