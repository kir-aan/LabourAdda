package com.mvsrecproject.labor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ContractorAdapter extends RecyclerView.Adapter<ContractorAdapter.ViewHolder>
{
    public ArrayList<Contractor> contractors;
    Context context;

    public ContractorAdapter(Context context,ArrayList<Contractor> list)
    {
        this.context=context;
        contractors=list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContName;
        ImageView imgCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvContName = itemView.findViewById(R.id.tvContName);
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
    public ContractorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout2,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContractorAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(contractors.get(i));
        viewHolder.tvContName.setText(contractors.get(i).getName());

        viewHolder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Intent.ACTION_DIAL);
                context.startActivity(intent1);



            }
        });







    }

    @Override
    public int getItemCount() {

        return contractors.size();
    }
}