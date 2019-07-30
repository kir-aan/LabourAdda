package com.vineethn7.labor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileActvity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etName,etID,etAge,etLoc;
    Spinner spSkillset;
    Button btnDone;
    String text="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etAge = findViewById(R.id.etAge);
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etLoc = findViewById(R.id.etLoc);
        btnDone = findViewById(R.id.btnDone);
        spSkillset= findViewById(R.id.spSkillset);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.skillset, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSkillset.setAdapter(adapter);
        spSkillset.setOnItemSelectedListener(this);

        int code= getIntent().getIntExtra("code",0);
        //code == 1 --> contractor
        if(code==1)
        {
            spSkillset.setVisibility(View.GONE);
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etAge.getText().toString().isEmpty()||etID.getText().toString().isEmpty()||etName.getText().toString().isEmpty()
                    ||etLoc.getText().toString().isEmpty())
                    {
                        Toast.makeText(ProfileActvity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent = new Intent(ProfileActvity.this,com.vineethn7.labor.Book1Activity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        // code == 2 -->Labour
        if(code==2)
        {
            etLoc.setVisibility(View.GONE);
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etAge.getText().toString().isEmpty()||etID.getText().toString().isEmpty()||etName.getText().toString().isEmpty()
                            ||text.isEmpty()||text.equals("Choose Skill"))
                    {
                        Toast.makeText(ProfileActvity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent = new Intent(ProfileActvity.this,com.vineethn7.labor.AvailabilityActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
