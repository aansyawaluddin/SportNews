package com.example.afinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.sqlite.DbConfig;

public class RegisterActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_register;
    private DbConfig dbConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbConfig = new DbConfig(this);

        et_username = findViewById(R.id.et_nimRegister);
        et_password = findViewById(R.id.et_passwordResgister);
        btn_register = findViewById(R.id.btn_register2);

        btn_register.setOnClickListener(view -> {
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (dbConfig.isUsernameExists(username)) {
                    et_username.setError("NIM already exists");
                    Toast.makeText(RegisterActivity.this, "NIM already exists. Please use a different NIM.", Toast.LENGTH_SHORT).show();
                } else {
                    dbConfig.insertData(username, password,0,"","0");
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
