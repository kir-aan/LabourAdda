package com.mvsrecproject.labor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HiredStatus extends AppCompatActivity {
    ArrayList<Labors> labors;
    RecyclerView recyclerview;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference hiredRootnode,laborRoot;
    FirebaseUser user;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hired_status);
        navigationView=findViewById(R.id.navigationView);

        recyclerview=findViewById(R.id.hiredList);
        recyclerview.setHasFixedSize(true);


        layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);


        laborRoot=FirebaseDatabase.getInstance().getReference("Labors");
        user= FirebaseAuth.getInstance().getCurrentUser();

        labors=new ArrayList<Labors>();
        hiredRootnode= FirebaseDatabase.getInstance().getReference("Hired");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Intent intent;
                switch (id){
                    case R.id.item1:    intent=new Intent(HiredStatus.this,LaborSkillSelector.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:    intent=new Intent(HiredStatus.this,HiredStatus.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            hiredRootnode.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                    if(dataSnapshot1.hasChild(user.getUid())){
                        for(final DataSnapshot ds:dataSnapshot1.child(user.getUid()).getChildren()){
                            laborRoot.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(ds.getKey().toString())){
                                        String labName=dataSnapshot.child(ds.getKey()).child("labourName").getValue().toString();
                                        String labNum=dataSnapshot.child(ds.getKey()).child("phoneNum").getValue().toString();
                                        Log.i("laborInfo",labName+"\n"+labNum);

                                        Labors l=new Labors(labName,labNum);
                                        labors.add(l);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                            }
                        }
                    }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        myAdapter=new HiredLaborAdapter(this,labors);
        recyclerview.setAdapter(myAdapter);

    }

}
