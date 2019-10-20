package com.mvsrecproject.labor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class labors_selection extends AppCompatActivity {

    public static final String TAG = "ListViewExample";
    DatabaseReference laborsRootNode,databaseRequests,contractorRootNode;
    FirebaseUser currentContractor;
    Button btnSendRequest;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labors_selection);

        btnSendRequest = findViewById(R.id.btnSendRequest);

        laborsRootNode= FirebaseDatabase.getInstance().getReference("Labors");

        contractorRootNode = FirebaseDatabase.getInstance().getReference("Contractors");

        currentContractor = FirebaseAuth.getInstance().getCurrentUser();

        databaseRequests = FirebaseDatabase.getInstance().getReference("Requests").child(currentContractor.getUid());

        listView = findViewById(R.id.listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                LaborAdapter user = (LaborAdapter)listView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });

        final String skillSelected=getIntent().getStringExtra("skillSelected").trim();
        final ArrayAdapter<LaborAdapter> arrayAdapter
                = new ArrayAdapter<LaborAdapter>(this, android.R.layout.simple_list_item_checked);
        laborsRootNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.child("labourSkill").getValue().equals(skillSelected)){
                        if(ds.child("availabilityStatus").getValue().toString()=="true"){
                            String laborName=ds.child("labourName").getValue().toString();
                            String laborAge=ds.child("labourAge").getValue().toString();
                            String labourUID=ds.getKey();
                            boolean b=false;
                            Log.i("labor_info"," "+laborName+" "+laborAge);
                            LaborAdapter labor = new LaborAdapter(labourUID,laborName,laborAge);
                            arrayAdapter.add(labor);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        LaborAdapter[] users = new LaborAdapter[]{};



        listView.setAdapter(arrayAdapter);

        for(int i=0;i< users.length; i++ )  {
            listView.setItemChecked(i,users[i].isActive());
        }

        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getCheckedItemCount()==0)
                    Toast.makeText(labors_selection.this, "Select atleast 1 labor", Toast.LENGTH_SHORT).show();
                SparseBooleanArray sp = listView.getCheckedItemPositions();
                contractorRootNode.child(currentContractor.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        databaseRequests.child("contName").setValue(dataSnapshot.child("name").getValue());
                        databaseRequests.child("phoneNum").setValue(dataSnapshot.child("phoneNum").getValue());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                for(int i=0;i<sp.size();i++){
                    if(sp.valueAt(i)){
                        LaborAdapter labor = (LaborAdapter) listView.getItemAtPosition(i);
                        final String laborName = labor.getLaborName();

                        databaseRequests.child(skillSelected)
                                .child(labor.getLabourUID()).child("LaborName").setValue(laborName);
                    }
                }
                Toast.makeText(labors_selection.this, "Request Sent!", Toast.LENGTH_SHORT).show();
            }
        });



    }


}
