package com.mvsrecproject.labor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ContractorAdapter extends RecyclerView.Adapter<ContractorAdapter.ViewHolder>
{
public ArrayList<Contractor> contractors;
Context context;
DatabaseReference requestStatusRoot,laborRoot;
FirebaseUser user;
Switch sw;
public ContractorAdapter(Context context,ArrayList<Contractor> list)
{
    this.context=context;
    contractors=list;
}
public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvContName;
    ImageView imgCall,imgAccept;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        tvContName = itemView.findViewById(R.id.tvContName);
        imgCall=itemView.findViewById(R.id.imgCall);
        imgAccept=itemView.findViewById(R.id.imgAccept);
        requestStatusRoot=FirebaseDatabase.getInstance().getReference("Hired");
        laborRoot=FirebaseDatabase.getInstance().getReference("Labors");
        user= FirebaseAuth.getInstance().getCurrentUser();

        sw=itemView.findViewById(R.id.Avail);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}


    @NonNull
    @Override
    public ContractorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout2,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContractorAdapter.ViewHolder viewHolder, int i) {

       viewHolder.itemView.setTag(contractors.get(i));
       viewHolder.tvContName.setText(contractors.get(i).getName());
       final String contPhoneNum=contractors.get(i).getPhoneNum().toString();
       final String contID=contractors.get(i).getUID();
       final String contName=contractors.get(i).getName();
       viewHolder.imgCall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              //Intent intent=new Intent(viewHolder.itemView.getContext(),AvailabilityActivity.class);
               Intent intent1=new Intent(Intent.ACTION_DIAL);
               context.startActivity(intent1);



           }
       });

        viewHolder.imgAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestStatusRoot.child(contID).child("contNum").setValue(contPhoneNum);

                laborRoot.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(user.getUid())){
                            requestStatusRoot.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if(dataSnapshot1.exists()){
                                    requestStatusRoot.child(contID).child(user.getUid()).child("labourskill").setValue(dataSnapshot.child(user.getUid()).child("labourSkill").getValue().toString());
                                    requestStatusRoot.child(contID).child(user.getUid()).child("labourNum").setValue(dataSnapshot.child(user.getUid()).child("phoneNum").getValue().toString());}
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                contractors.clear();
                viewHolder.itemView.setVisibility(View.GONE);
                laborRoot.child(user.getUid()).child("availabilityStatus").setValue(false);

                Toast.makeText(viewHolder.itemView.getContext(), "Hired for "+contName, Toast.LENGTH_SHORT).show();
//                sw.setChecked(false);
            }
        });






    }

    @Override
    public int getItemCount() {

        return contractors.size();
    }
}
