package com.mvsrecproject.labor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;


public class labors_selection extends AppCompatActivity {

    public static final String TAG = "ListViewExample";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labors_selection);


        listView = (ListView)findViewById(R.id.listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                LaborAdapter user = (LaborAdapter)listView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });
        //

        LaborAdapter labor1 = new LaborAdapter("labor1",25);
        LaborAdapter labor2 = new LaborAdapter("labor2",20);
        LaborAdapter labor3 = new LaborAdapter("labor3",30,true);

        LaborAdapter[] users = new LaborAdapter[]{labor1,labor2, labor3};


        ArrayAdapter<LaborAdapter> arrayAdapter
                = new ArrayAdapter<LaborAdapter>(this, android.R.layout.simple_list_item_checked , users);


        listView.setAdapter(arrayAdapter);

        for(int i=0;i< users.length; i++ )  {
            listView.setItemChecked(i,users[i].isActive());
        }
    }


    public void printSelectedItems(View view)  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();

        StringBuilder sb= new StringBuilder();

        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)){
                LaborAdapter user= (LaborAdapter) listView.getItemAtPosition(i);
                // Or:
                // String s = ((CheckedTextView) listView.getChildAt(i)).getText().toString();
                String s= user.getLaborName();
                sb = sb.append(" "+s);
            }
        }
        Toast.makeText(this, "Selected items are: "+sb.toString(), Toast.LENGTH_LONG).show();

    }
}