package com.vineethn7.labor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Book2Activity extends AppCompatActivity
{
    Button btnHire;
    ArrayList<Person> people;
    RecyclerView recyclerView;

    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2);

        people=new ArrayList<Person>();

        recyclerView=findViewById(R.id.list);
        btnHire=findViewById(R.id.btnHire);


        people.add(new Person("labour1","35",false));
        people.add(new Person("labour2","20",false));
        people.add(new Person("labour3","45",false));
        people.add(new Person("labour4","50",false));
        people.add(new Person("labour5","25",false));
        people.add(new Person("labour6","48",false));
        people.add(new Person("labour7","25",false));
        people.add(new Person("labour8","23",false));
        people.add(new Person("labour9","29",false));


        myAdapter=new PersonAdapter(people);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = "";
                ArrayList<Person> people= ((PersonAdapter) myAdapter)
                        .getPersonist();

                for (int i = 0; i < people.size(); i++) {
                    Person singlePerson = people.get(i);
                    if (singlePerson.isChecked() == true) {

                        data = data + "\n" + singlePerson.getName().toString();
                    }
                    Toast.makeText(Book2Activity.this, data, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
