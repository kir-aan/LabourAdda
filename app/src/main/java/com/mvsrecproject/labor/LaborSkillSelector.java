package com.mvsrecproject.labor;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

// LaborSkillSelector --> Selecting the labourer's skill (that contractor is looking for)

public class LaborSkillSelector extends AppCompatActivity {
    RadioGroup rgSkills;
    RadioButton rbtn;
    Button btnSubmit;
    private Toolbar toolbar;
    NavigationView navigationView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laborskillselector);
        btnSubmit = findViewById(R.id.btnSubmit);
        rgSkills= findViewById(R.id.rgSkills);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView=findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Intent intent;
                switch (id){
                    case R.id.item1:    intent=new Intent(LaborSkillSelector.this,LaborSkillSelector.class);
                                        startActivity(intent);
                                        break;
                    case R.id.item2:    intent=new Intent(LaborSkillSelector.this,HiredStatus.class);
                                        startActivity(intent);
                                        break;
                }
                return false;
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=rgSkills.getCheckedRadioButtonId();


                if(id==-1)
                {
                    Toast.makeText(LaborSkillSelector.this, "please select a skill!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    rbtn=findViewById(id);
                    String skill=rbtn.getText().toString();
                    Intent intent= new Intent(LaborSkillSelector.this, com.mvsrecproject.labor.labors_selection.class);
                    intent.putExtra("skillSelected",skill);
                    startActivity(intent);
                    Toast.makeText(LaborSkillSelector.this, "Selected skill:"+skill, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
