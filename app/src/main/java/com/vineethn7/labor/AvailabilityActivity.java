package com.vineethn7.labor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

//Availability layout needs to be updated

public class AvailabilityActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch swAvailable;
    Button btnAccept,btnReject;
    ImageButton ibCall;
    TextView tvContInfo;
    LinearLayout llCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        swAvailable = findViewById(R.id.swAvailable);
        btnAccept= findViewById(R.id.btnAccept);
        btnReject= findViewById(R.id.btnReject);
        ibCall = findViewById(R.id.ibCall);
        tvContInfo=findViewById(R.id.tvContInfo);
        llCont = findViewById(R.id.llCont);
        tvContInfo.setVisibility(View.GONE);
        llCont.setVisibility(View.GONE);
        swAvailable.setOnCheckedChangeListener(this);

        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(swAvailable.isChecked()){
            tvContInfo.setVisibility(View.VISIBLE);
            llCont.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
