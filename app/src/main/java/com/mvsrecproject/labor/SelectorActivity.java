package com.mvsrecproject.labor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectorActivity extends AppCompatActivity {
    Button btnCont,btnLabour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        btnCont = findViewById(R.id.btnCont);
        btnLabour = findViewById(R.id.btnLabour);

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectorActivity.this, com.mvsrecproject.labor.ProfileActvity.class);
                intent.putExtra("code",1);
                String phoneNum = getIntent().getStringExtra("PhoneNum");
                intent.putExtra("phoneNum",phoneNum);
                startActivity(intent);
            }
        });

        btnLabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectorActivity.this, com.mvsrecproject.labor.ProfileActvity.class);
                intent.putExtra("code",2);
                String phoneNum = getIntent().getStringExtra("PhoneNum");
                intent.putExtra("phoneNum",phoneNum);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
