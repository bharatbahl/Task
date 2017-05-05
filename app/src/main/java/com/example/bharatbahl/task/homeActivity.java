package com.example.bharatbahl.task;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class homeActivity extends AppCompatActivity {
DBAdapter adapter;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        adapter=new DBAdapter(this);
        tv=(TextView)findViewById(R.id.textView);
        ArrayList<String> list = new ArrayList<String>();
        Cursor c=adapter.showAllRecords();
        while(c.moveToNext()) {
            list.add("Username : "+c.getString(0));

        }
        Cursor c1=adapter.showRecord("bharat");
        while(c1.moveToNext()) {
            Toast.makeText(getBaseContext(), c1.getString(0), Toast.LENGTH_LONG).show();
        }

        //instantiate custom adapter
        final MyCustomAdapter adapter = new MyCustomAdapter(list, this);

        //handle listview and assign adapter
        final ListView lView = (ListView)findViewById(R.id.lv);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,int position, long arg3) {
                        String selectedSweet = lView.getItemAtPosition(position).toString();
                        TextView textView = (TextView) view.findViewById(R.id.list_item_string);
                        String text = textView.getText().toString();
                        Toast.makeText(getApplicationContext(), "Selected item: " + selectedSweet + " - " + position, Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
}
