package com.vineethn7.labor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> people;

    public PersonAdapter(Context context,ArrayList<Person> list) {
        people=list;
    }

   public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName ,tvAge ;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           tvName=itemView.findViewById(R.id.tvName);
           tvAge=itemView.findViewById(R.id.tvAge);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

               }
           });
       }
   }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items,viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(people.get(i));
        viewHolder.tvName.setText(people.get(i).getName());
        viewHolder.tvAge.setText(people.get(i).getAge());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
