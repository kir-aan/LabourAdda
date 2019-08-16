package com.mvsrecproject.labor;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class availfrag2 extends Fragment {


RecyclerView recyclerview;
RecyclerView.Adapter myAdapter;
RecyclerView.LayoutManager layoutManager;
View view;



    public availfrag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view=inflater.inflate(R.layout.fragment_availfrag2,container,false);

       return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerview=view.findViewById(R.id.list);
        recyclerview.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerview.setLayoutManager(layoutManager);

        myAdapter=new ContractorAdapter(this.getActivity(),ApplicationClass.contractors);
        recyclerview.setAdapter(myAdapter);
    }



}
