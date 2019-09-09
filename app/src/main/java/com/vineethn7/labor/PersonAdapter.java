package com.vineethn7.labor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Person> people;

    public PersonAdapter(Context context, ArrayList<Person> people) {
        this.context = context;
        this.people = people;
    }

    public int getCount() {
        return people.size();
    }

    public Person getItem(int position) {
        return people.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public boolean isChecked(int position) {
        return people.get(position).checked;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView tvName, tvAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);

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

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int i) {
        try {
            viewHolder.itemView.setTag(people.get(i));
            viewHolder.checkBox.setChecked(people.get(i).checked);
            viewHolder.tvName.setText(people.get(i).getName());
            viewHolder.tvAge.setText(people.get(i).getAge());
            viewHolder.checkBox.setTag(i);
            viewHolder.checkBox.setChecked(isChecked(i));

        }catch (Exception e)
        {

        }
    }
}
