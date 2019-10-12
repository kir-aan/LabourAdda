package com.mvsrecproject.labor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class labors_selection extends AppCompatActivity {

    public static final String TAG = "ListViewExample";
    DatabaseReference laborRef;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labors_selection);

        laborRef= FirebaseDatabase.getInstance().getReference("Labors");

        listView = (ListView)findViewById(R.id.listView);

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
        laborRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LaborAdapter[] labors;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.child("labourSkill").getValue().equals(skillSelected)){
                        if(ds.child("availabilityStatus").getValue().toString()=="true"){
                            String laborName=ds.child("labourName").getValue().toString();
                            String laborAge=ds.child("labourAge").getValue().toString();
                            boolean b=false;
                            Log.i("labor_info"," "+laborName+" "+laborAge);
                            LaborAdapter labor = new LaborAdapter(laborName,laborAge);
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

//        ArrayAdapter<LaborAdapter> arrayAdapter
//                = new ArrayAdapter<LaborAdapter>(this, android.R.layout.simple_list_item_checked , users);



        listView.setAdapter(arrayAdapter);

        for(int i=0;i< users.length; i++ )  {
            listView.setItemChecked(i,users[i].isActive());
        }
    }


    public void printSelectedItems(View view)  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();

        StringBuilder sb= new StringBuilder();

        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)){
                LaborAdapter user= (LaborAdapter) listView.getItemAtPosition(i);

                String s= user.getLaborName();
                sb = sb.append(" "+s);
            }
        }
        Toast.makeText(this, "Selected items are: "+sb.toString(), Toast.LENGTH_LONG).show();

    }
}