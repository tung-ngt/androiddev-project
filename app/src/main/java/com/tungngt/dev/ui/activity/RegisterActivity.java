package com.tungngt.dev.ui.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentRegisterBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {
    FragmentRegisterBinding fragmentRegisterBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(getLayoutInflater());
        setContentView(fragmentRegisterBinding.getRoot());
        fragmentRegisterBinding.signupButton.setOnClickListener(new View.OnClickListener(){
            private String first_password;
            private String second_password;
            TextInputEditText register_username = (TextInputEditText) findViewById(R.id.username_register);
            TextInputEditText register_nickname = (TextInputEditText) findViewById(R.id.nickname_register);

            TextInputEditText register_password = (TextInputEditText) findViewById(R.id.password_register);
            TextInputEditText register_retype_password = (TextInputEditText) findViewById(R.id.retypePassword_register);


            public void writeToFile(String data, Context context) {
                try {
                    FileOutputStream fos = context.openFileOutput("userInput.txt", Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public String getDirectory(Context context, String fileName) {
                File file = new File(context.getFilesDir(), fileName);
                return file.getAbsolutePath();
            }
            public String readFile(Context context, String fileName) {
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                            context.openFileInput(fileName)));
                    String inputString;
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuilder.append(inputString + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return stringBuilder.toString();
            }
            @Override
            public void onClick(View view) {
                first_password = register_password.getText().toString();
                second_password = register_retype_password.getText().toString();
                if(register_username.getText().toString().equals("")){
                    if(register_nickname.getText().toString().equals("")){
                        if(register_password.getText().toString().equals("")){
                            if(register_retype_password.getText().toString().equals("")){
                                if(!first_password.equals(second_password)) {
                                    Toast.makeText(getApplicationContext(), "Information invalid", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
                else {
                    String text = register_username.getText().toString() + "\n"
                            + register_nickname.getText().toString() + "\n"
                            + register_password.getText().toString() + "\n"
                            + register_retype_password.getText().toString();
                    writeToFile(text, getApplicationContext());
                    String directory = getDirectory(getApplicationContext(), "userInput.txt");
                    String content = readFile(getApplicationContext(), "userInput.txt");
                    Toast.makeText(getApplicationContext(), directory, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
                    openMainActivity();
                }
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
