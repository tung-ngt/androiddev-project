package com.tungngt.dev.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentLoginBinding;

public class LoginActivity extends AppCompatActivity  {
    FragmentLoginBinding fragmentLogInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        fragmentLogInBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        setContentView(fragmentLogInBinding.getRoot());

        fragmentLogInBinding.loginButton.setOnClickListener(new View.OnClickListener(){

            TextInputEditText user_Name = (TextInputEditText) findViewById(R.id.user_name);
            TextInputEditText pass_Word = (TextInputEditText) findViewById(R.id.user_password);
            @Override
            public void onClick(View view) {
                if(user_Name.getText().toString().matches("Admin")){
                    if(pass_Word.getText().toString().matches("Admin")){
                        openMainActivity();
                    }
                }
                else{
                    if(user_Name.getText().toString().matches("") && pass_Word.getText().toString().matches("")){
                        Toast.makeText(getApplicationContext(), "Please fill in the field", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), "Username or Password not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fragmentLogInBinding.signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openRegisterFragment();
            }
        });

    }
    private void openRegisterFragment(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}