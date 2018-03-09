package com.example.root.databaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<SetGet> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        DbManager manage=new DbManager(ShowActivity.this);
        listView=(ListView)findViewById(R.id.listview);

        arrayList=manage.getAllData();
        listView.setAdapter(new CustomAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Intent intent=new Intent(getApplicationContext(),UpdateActivity.class);
                intent.putExtra("Id",arrayList.get(position).getEmpid());
                intent.putExtra("Name",arrayList.get(position).getEmpname());
                intent.putExtra("Email",arrayList.get(position).getEmpmail());
                intent.putExtra("Phone",arrayList.get(position).getEmpphone());
                startActivity(intent);

            }
        });


    }

    public class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.list_row,parent,false);
            TextView name=(TextView)view.findViewById(R.id.tv1);
            TextView email=(TextView)view.findViewById(R.id.tv2);
            TextView phone=(TextView)view.findViewById(R.id.tv3);
            TextView empid=(TextView)view.findViewById(R.id.tv4) ;

            name.setText(arrayList.get(position).getEmpname());
            email.setText(arrayList.get(position).getEmpmail());
            phone.setText(arrayList.get(position).getEmpphone());
            empid.setText(arrayList.get(position).getEmpid());

            return view;
        }
    }
}
