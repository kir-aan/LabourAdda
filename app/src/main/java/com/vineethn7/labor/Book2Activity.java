package com.vineethn7.labor;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//book2activity --> List of labourers available(for the contractor to select)

public class Book2Activity extends AppCompatActivity {
    public class Item{
        boolean checked;
        String name,age;
        Item(String n, String a, boolean b){
            name=n;
            age=a;
            checked=b;
        }

        public boolean isChecked() {
            return checked;
        }
    }
    static class ViewHolder{
        CheckBox checkBox;
        TextView namev,agev;
    }
    public class ItemsListAdapter extends BaseAdapter{

        private Context context;
        private List<Item> list;

        ItemsListAdapter(Context c,List<Item> l)
        {
            context=c;
            list=l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public boolean isChecked(int position) {
            return list.get(position).checked;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = new ViewHolder();
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.row_layout, null);

                viewHolder.checkBox =  rowView.findViewById(R.id.cbSelectLabour);
                viewHolder.namev = rowView.findViewById(R.id.tvName);
                viewHolder.agev = rowView.findViewById(R.id.tvAge);
                rowView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) rowView.getTag();
            }
            viewHolder.checkBox.setChecked(list.get(position).checked);
            final String namevi = list.get(position).name;
            viewHolder.namev.setText(namevi);
            final String agevi = list.get(position).age;
            viewHolder.agev.setText(agevi);
            viewHolder.checkBox.setTag(position);
            viewHolder.checkBox.setChecked(isChecked(position));

            return rowView;
        }
    }
    Button btnHire;
    List<Item> items;
    ListView lvLabourer;
    ItemsListAdapter itemsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2);
        lvLabourer =findViewById(R.id.lvLabourer);
        btnHire = findViewById(R.id.btnHire);

        initItems();
        itemsListAdapter = new ItemsListAdapter(this, items);
        lvLabourer.setAdapter(itemsListAdapter);

        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "Check items:\n";

                for (int i=0; i<items.size(); i++){
                    if (items.get(i).isChecked()){
                        str += i + "\n";
                    }
                }
                Toast.makeText(Book2Activity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initItems(){
        items = new ArrayList<Item>();

        TypedArray arrayName = getResources().obtainTypedArray(R.array.namescol);
        TypedArray arrayAge = getResources().obtainTypedArray(R.array.agecol);

        for(int i=0; i<arrayAge.length(); i++){
            String s1 = arrayName.getString(i);
            String s2 = arrayAge.getString(i);
            boolean b = false;
            Item item = new Item(s1, s2, b);
            items.add(item);
        }

        arrayName.recycle();
        arrayAge.recycle();
    }
}
