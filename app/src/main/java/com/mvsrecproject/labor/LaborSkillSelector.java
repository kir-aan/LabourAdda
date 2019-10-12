package com.mvsrecproject.labor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

// LaborSkillSelector --> Selecting the labourer's skill (that contractor is looking for)

public class LaborSkillSelector extends AppCompatActivity {
    RadioGroup rgSkills;
    RadioButton rbtn;
    Button btnSubmit;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laborskillselector);
        btnSubmit = findViewById(R.id.btnSubmit);
        rgSkills= findViewById(R.id.rgSkills);

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
                }
            }
        });
    }
    

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
