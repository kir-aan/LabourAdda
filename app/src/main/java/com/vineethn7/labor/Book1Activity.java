package com.vineethn7.labor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rb.isChecked())
                {
                    Toast.makeText(Book1Activity.this, "please select a skill!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String skill=rb.getText().toString();
                    Intent intent= new Intent(Book1Activity.this, com.vineethn7.labor.Book2Activity.class);
                    intent.putExtra("skill",skill);
                    startActivity(intent);
                }
            }
        });
    }
    public void rbCheck(View view) {
        rgSkills= findViewById(R.id.rgSkills);
        int id=rgSkills.getCheckedRadioButtonId();
        rb=findViewById(id);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
