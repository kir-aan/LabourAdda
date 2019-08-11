package com.vineethn7.labor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder>
    {
        private ArrayList<Skill> labor;

        public SkillAdapter(Context context, ArrayList<Skill> list)
        {
            labor = list;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView tvSkill;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                tvSkill = itemView.findViewById(R.id.tvSkill);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        }
        @NonNull
        @Override
        public SkillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.labour_skills, viewGroup, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SkillAdapter.ViewHolder viewHolder, int i) {

            viewHolder.itemView.setTag(labor.get(i));
            viewHolder.tvSkill.setText(labor.get(i).getSkill());

        }

        @Override
        public int getItemCount() {
            return labor.size();
        }
}
