package com.mvsrecproject.labor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.math.BigInteger;

public class LoginActivity extends AppCompatActivity {
    EditText etPhone;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone = findViewById(R.id.etPhone);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPhone.getText().toString().isEmpty() || etPhone.getText().toString().trim().length() != 10)
                {
                    Toast.makeText(LoginActivity.this, "Please enter valid phone number!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    BigInteger phoneno = new BigInteger(etPhone.getText().toString().trim());
                    Intent intent= new Intent(LoginActivity.this, com.mvsrecproject.labor.SelectorActivity.class);
                    intent.putExtra("PhoneNum",phoneno);
                    startActivity(intent);
                }
            }
        });
    }
}
