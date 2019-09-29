package com.mvsrecproject.labor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActvity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etName,etID,etAge,etLoc;
    Spinner spSkillset;
    Button btnDone;
    String spinnerValue="";
    int code;

    DatabaseReference databaseLabors,databaseContractors;


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
        databaseLabors = FirebaseDatabase.getInstance().getReference("Labors");
        databaseContractors = FirebaseDatabase.getInstance().getReference("Contractors");

        code = getIntent().getIntExtra("code",0);
        //code == 1 --> contractor
        if(code==1)
        {
            spSkillset.setVisibility(View.GONE);
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addUser();
                    Intent intent = new Intent(ProfileActvity.this,com.mvsrecproject.labor.Book1Activity.class);
                    startActivity(intent);
                }
            });
        }
        // code == 2 -->Labour
        if(code==2)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.skillset, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSkillset.setAdapter(adapter);
            spSkillset.setOnItemSelectedListener(this);

            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addUser();
                    Intent intent = new Intent(ProfileActvity.this,com.mvsrecproject.labor.AvailabilityActivity.class);
                    startActivity(intent);

                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerValue = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//spinnerValue.isEmpty()||spinnerValue.equals("Choose Skill")
    private void addUser(){
        String name = etName.getText().toString().trim();
        int age = Integer.parseInt(etAge.getText().toString().trim());
        String id = etID.getText().toString().trim();
        String location = etLoc.getText().toString().trim();
        if(name.isEmpty()||age<=0||id.isEmpty()||location.isEmpty()){
            Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show();
        }
        else{
            //contractor
            if(code==1){
                String uniqueID= databaseContractors.push().getKey();
                Contractor contractor = new Contractor(name,age,id,location);
            }
            //Labourer
            if(code==2){
                if(spinnerValue.isEmpty()||spinnerValue.equals("Choose Skill")){
                    Toast.makeText(this, "Please select valid skillset", Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }
        }


    }
}
