package com.mvsrecproject.labor;

import android.app.Application;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Contractor> contractors;

    FirebaseUser labor;
    DatabaseReference laborRootNode,requestRootNode;


    @Override
    public void onCreate() {
        super.onCreate();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            contractors=new ArrayList<Contractor>();
            labor = FirebaseAuth.getInstance().getCurrentUser();
            laborRootNode = FirebaseDatabase.getInstance()
                    .getReference("Labors").child(labor.getUid()).child("labourSkill");
            requestRootNode = FirebaseDatabase.getInstance().getReference("Requests");

            laborRootNode.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    dataSnapshot.getValue();
                    requestRootNode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                            for (DataSnapshot ds1 : dataSnapshot1.getChildren()) {
                                if (ds1.hasChild(dataSnapshot.getValue().toString())) {
                                    if (ds1.child(dataSnapshot.getValue().toString()).hasChild(labor.getUid())) {
                                        System.out.println("Found labour in requests");
                                        System.out.println(ds1.child("contName").getValue());
                                        System.out.println(ds1.child("phoneNum").getValue());
                                        String contName = ds1.child("contName").getValue().toString();
                                        String phoneNum = ds1.child("phoneNum").getValue().toString();
                                        contractors.add(new Contractor(contName,phoneNum));
                                    } else {
                                        System.out.println("labour not found in requests");
                                    }
                                } else {
                                    System.out.println("L not Found");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
