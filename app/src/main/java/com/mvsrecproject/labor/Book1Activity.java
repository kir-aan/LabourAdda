package com.mvsrecproject.labor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

// Book1Activity --> Selecting the labourer's skill (that contractor is looking for)

public class Book1Activity extends AppCompatActivity {
    RadioGroup rgSkills;
    RadioButton rb;
    Button btnSubmit;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1);
        btnSubmit = findViewById(R.id.btnSubmit);
        rgSkills=findViewById(R.id.rgSkills);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //app was crashing if user doesnt select anything
                // since the code to get id=-1 if button not selected
                //was written inside rbcheck which gets called only when a radio button is pressed
                id=rgSkills.getCheckedRadioButtonId();
                rb=findViewById(id);

                if(id==-1)
                {
                    Toast.makeText(Book1Activity.this, "please select a skill!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String skill=rb.getText().toString();
                    Intent intent= new Intent(Book1Activity.this, com.mvsrecproject.labor.Book2Activity.class);
                    intent.putExtra("skill",skill);
                    startActivity(intent);
                }
            }
        });
    }
    public void rbCheck(View view) {
        //basith removed this code,check line 33 to know why
        //rgSkills= findViewById(R.id.rgSkills);
        //id=rgSkills.getCheckedRadioButtonId();
        //rb=findViewById(id);

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
