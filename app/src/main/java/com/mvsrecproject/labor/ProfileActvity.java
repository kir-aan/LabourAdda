package com.mvsrecproject.labor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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
                    int allfieldsvalidflag=0;
                    if (etAge.getText().toString().trim().isEmpty() || etID.getText().toString().trim().isEmpty() || etName.getText().toString().trim().isEmpty()
                            || etLoc.getText().toString().trim().isEmpty()) {
                        Toast.makeText(ProfileActvity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    }

                    //code added by basith to validate age and name
                    else {
                        if (Integer.parseInt(etAge.getText().toString()) > 150) {
                            etAge.setError("Enter a valid Age");
                            allfieldsvalidflag=1;
                        }
                        if(!isNameValid(etName.getText().toString().trim())){
                            etName.setError("Enter a valid name");
                            allfieldsvalidflag=1;
                        }
                        if(allfieldsvalidflag!=1) {
                            Intent intent = new Intent(ProfileActvity.this, com.mvsrecproject.labor.Book1Activity.class);
                            startActivity(intent);
                        }
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
                    int allfieldsvalidflag=0;
                    if(etAge.getText().toString().trim().isEmpty()||etID.getText().toString().trim().isEmpty()||etName.getText().toString().trim().isEmpty()
                            ||text.isEmpty()||text.equals("Choose Skill"))
                    {
                        Toast.makeText(ProfileActvity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    }

                    //code added by basith to validate age and name
                    else {
                        if (Integer.parseInt(etAge.getText().toString()) > 150) {
                            etAge.setError("Enter a valid Age");
                            allfieldsvalidflag=1;
                        }
                        if(!isNameValid(etName.getText().toString().trim())){
                            etName.setError("Enter a valid name");
                            allfieldsvalidflag=1;
                        }
                        if(allfieldsvalidflag!=1)
                        {
                            Intent intent = new Intent(ProfileActvity.this,com.mvsrecproject.labor.AvailabilityActivity.class);
                            startActivity(intent);
                        }
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

    //boolean validatename()
    //{
    //Patterns.EMAIL_ADDRESS.matcher((CharSequence) etName).matches();
    //}
    static boolean isNameValid(String name){
        //name valid only if it contains letters+Spaces at beginning,middle and end
        if(name.matches("^[ a-zA-Z ]+$")){
            return true;
        }
        return false;
    }
}
