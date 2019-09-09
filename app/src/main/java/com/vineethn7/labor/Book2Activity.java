package com.vineethn7.labor;

import android.content.res.TypedArray;
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
    //RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2);

        recyclerView=findViewById(R.id.list);
        btnHire=findViewById(R.id.btnHire);
        initItems();
        //recyclerView.setHasFixedSize(true);

        /*layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/

        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = "Check items:\n";

                for (int i=0; i<people.size(); i++){
                    if (people.get(i).isChecked()){
                        str += i + "\n";
                    }
                }
                Toast.makeText(Book2Activity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        people=new ArrayList<Person>();
        myAdapter=new PersonAdapter(this,people);

    }

    private void initItems() {
        people=new ArrayList<Person>();

        TypedArray arrayName = getResources().obtainTypedArray(R.array.namescol);
        TypedArray arrayAge = getResources().obtainTypedArray(R.array.agecol);

        for(int i=0; i<arrayAge.length(); i++){
            String s1 = arrayName.getString(i);
            String s2 = arrayAge.getString(i);
            boolean b = false;
            Person item = new Person(s1, s2, b);
            people.add(item);
        }

        arrayName.recycle();
        arrayAge.recycle();
    }
}
