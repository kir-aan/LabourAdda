package com.mvsrecproject.labor;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class availfrag1 extends Fragment {
    FirebaseUser user;
    DatabaseReference availableLabors;
    Boolean status;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_availfrag1,container,false);
        availableLabors= FirebaseDatabase.getInstance().getReference("Labors");
        user =FirebaseAuth.getInstance().getCurrentUser();

        final Switch Avail =(Switch)view.findViewById(R.id.Avail);
        availableLabors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user.getUid())){
                    Labors l=dataSnapshot.child(user.getUid()).getValue(Labors.class);
                    Avail.setChecked(l.isAvailabilityStatus());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Avail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();

                fr.setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out);
                if (isChecked)
                {
                    status=true;
                    // FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.add(R.id.fragment_container2, new availfrag2());
                    fr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fr.commit();


                }


                else
                {
                    status=false;
                    //  fr.remove(new availfrag2());
                    //fr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    fr.replace(R.id.fragment_container2,new BlankFragment());
                    // fr.addToBackStack(null);
                    fr.commit();

                    //   fr.hide(R.id.fragment_container2, new availfrag2())
                }

                availableLabors.child(user.getUid()).child("availabilityStatus").setValue(status);

            }

        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}






