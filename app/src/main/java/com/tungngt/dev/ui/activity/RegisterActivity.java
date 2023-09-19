package com.tungngt.dev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentLoginBinding;
import com.tungngt.dev.databinding.FragmentRegisterBinding;


public class RegisterActivity extends AppCompatActivity {
    FragmentRegisterBinding fragmentRegisterBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(getLayoutInflater());
        setContentView(fragmentRegisterBinding.getRoot());
        fragmentRegisterBinding.signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
