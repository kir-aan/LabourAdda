package com.vineethn7.labor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PersonAdapter extends
        RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> people;

    public PersonAdapter(ArrayList<Person> people) {
        this.people = people;
    }


    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_items, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.tvName.setText(people.get(position).getName());

        viewHolder.tvAge.setText(people.get(position).getAge());

        viewHolder.checkBox.setChecked(people.get(position).isChecked());

        viewHolder.checkBox.setTag(people.get(position));


        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Person contact = (Person) cb.getTag();

                contact.setChecked(cb.isChecked());
                people.get(pos).setChecked(cb.isChecked());

                Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return people.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView tvName, tvAge;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            checkBox = (CheckBox) itemView.findViewById(R.id.cbSelectLabour);

        }
    }
    public ArrayList<Person> getPersonist() {
        return people;
    }
}
