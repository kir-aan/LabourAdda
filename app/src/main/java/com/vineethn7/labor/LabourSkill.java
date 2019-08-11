package com.vineethn7.labor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

// LabourSkill --> Selecting the labourer's skill (that contractor is looking for)

public class LabourSkill extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Skill> labor;

    RadioGroup rgSkills;
    RadioButton rb;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        recyclerView = findViewById(R.id.list_item);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        labor = new ArrayList<>();
        labor.add(new Skill("Masonry"));
        labor.add(new Skill("Carpentry"));
        labor.add(new Skill("Plumbing"));
        labor.add(new Skill("Electrician"));
        labor.add(new Skill("Painting"));

        myAdapter = new SkillAdapter(this, labor);

        recyclerView.setAdapter(myAdapter);

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rb.isChecked())
                {
                    Toast.makeText(LabourSkill.this, "please select a skill!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String skill=rb.getText().toString();
                    Intent intent= new Intent(LabourSkill.this, Book2Activity.class);
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
