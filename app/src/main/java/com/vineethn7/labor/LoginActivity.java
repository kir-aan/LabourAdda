package com.vineethn7.labor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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


        //code Added by basith to disable button until a valid phone no. is entered
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tempPhone=etPhone.getText().toString().trim();
                if(tempPhone.length()==10)
                    btnLogin.setEnabled(true);
                else
                    btnLogin.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*we need to check if no. is valid once again
                if(etPhone.getText().toString().trim().isEmpty() || etPhone.getText().toString().trim().length() != 10)
                {
                    Toast.makeText(LoginActivity.this, "Please enter valid phone number!", Toast.LENGTH_SHORT).show();
                }
                else
                {*/
                    BigInteger phoneno = new BigInteger(etPhone.getText().toString().trim());
                    Intent intent= new Intent(LoginActivity.this, com.vineethn7.labor.SelectorActivity.class);
                    intent.putExtra("PhoneNum",phoneno);
                    startActivity(intent);
                //}
            }
        });
    }
}
