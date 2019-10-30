package com.mvsrecproject.labor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HiredLaborAdapter extends RecyclerView.Adapter<HiredLaborAdapter.ViewHolder> {
    public ArrayList<Labors> labors;
    Context context;
    public HiredLaborAdapter(Context context,ArrayList<Labors> list){
        this.context=context;
        labors=list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLabName;
        ImageView imgCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLabName = itemView.findViewById(R.id.tvLabName);
            imgCall=itemView.findViewById(R.id.imgCall);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
    @NonNull
    @Override
    public HiredLaborAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HiredLaborAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(labors.get(position));
        holder.tvLabName.setText(labors.get(position).getLabourName());
        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return labors.size();
    }
}
