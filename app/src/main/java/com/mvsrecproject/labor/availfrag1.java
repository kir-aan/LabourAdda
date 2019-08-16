package com.mvsrecproject.labor;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class availfrag1 extends Fragment{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_availfrag1,container,false);



                Switch Avail =(Switch)view.findViewById(R.id.Avail);
                Avail.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.add(R.id.fragment_container2, new availfrag2());
                        fr.commit();

                    }
                });


        return view;
    }



}






