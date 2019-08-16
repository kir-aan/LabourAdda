package com.vineethn7.labor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Book2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Person> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2);

        recyclerView=findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        people=new ArrayList<Person>();

        people.add(new Person("Labour1", "25"));
        people.add(new Person("Labour2", "40"));
        people.add(new Person("Labour3", "55"));
        people.add(new Person("Labour4", "30"));
        people.add(new Person("Labour5", "20"));
        people.add(new Person("Labour6", "25"));

        myAdapter=new PersonAdapter(this ,people);

        recyclerView.setAdapter(myAdapter);

    }
}
