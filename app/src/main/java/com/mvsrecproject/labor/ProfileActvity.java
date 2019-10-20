package com.mvsrecproject.labor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActvity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etName,etID,etAge,etLoc;
    Spinner spSkillset;
    Button btnDone;
//    String spinnerValue="";
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

        if(code==1)
            spSkillset.setVisibility(View.GONE);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        spinnerValue = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addUser(){
        String name = etName.getText().toString().trim();
        int age;
        if(etAge.getText().toString().isEmpty())
            age=0;
        else
            age = Integer.parseInt(etAge.getText().toString().trim());
        String id = etID.getText().toString().trim();
        String location = etLoc.getText().toString().trim();
        if(name.isEmpty()||age<=0||id.isEmpty()||location.isEmpty()){
            Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show();
        }
        else{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uniqueID = user.getUid();
            String phoneNumber = user.getPhoneNumber();
            //contractor
            if(code==1){
                Contractor contractor = new Contractor(uniqueID,name,phoneNumber,age,id,location);
                databaseContractors.child(uniqueID).setValue(contractor);
            }
            //Labourer
            if(code==2){
                String spinnerValue=spSkillset.getSelectedItem().toString();
                if(spinnerValue.isEmpty()||spinnerValue.equals("Choose Skill")){
                    Toast.makeText(this, "Please select valid skillset", Toast.LENGTH_SHORT).show();
                }
                else{
                    Labors labour = new Labors(uniqueID,name,phoneNumber,age,spinnerValue,id,location);
                    databaseLabors.child(uniqueID).setValue(labour);
                }
            }
            Toast.makeText(this, "User Added!", Toast.LENGTH_SHORT).show();
            if(code==1){
                Intent intent = new Intent(ProfileActvity.this, LaborSkillSelector.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else if(code==2){
                Intent intent = new Intent(ProfileActvity.this,AvailabilityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }

    }
}
