package com.tungngt.dev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentLoginBinding;
import com.tungngt.dev.ui.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity {
    FragmentLoginBinding fragmentLogInBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        fragmentLogInBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        setContentView(fragmentLogInBinding.getRoot());
        fragmentLogInBinding.signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.register_fragment, new RegisterFragment()).commit();
            }
        });
        fragmentLogInBinding.loginButton.setOnClickListener(new View.OnClickListener(){
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
