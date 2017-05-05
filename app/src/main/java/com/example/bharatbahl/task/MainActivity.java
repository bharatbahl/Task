package com.example.bharatbahl.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeClipBounds;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1,cb2;
    Button b3;
    EditText username;
    EditText password;
    EditText email;
    String gender;
    String uname;
    String upass;
    String eid;
    DBAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new DBAdapter(this);
        setTitle("Register");
        cb1=(CheckBox)findViewById(R.id.checkBox);
        cb2=(CheckBox)findViewById(R.id.checkBox2);
        username=(EditText)findViewById(R.id.editText3);
        password=(EditText)findViewById(R.id.editText4);
        email=(EditText)findViewById(R.id.editText5);

        b3=(Button)findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked() && cb2.isChecked()) {
                    Toast.makeText(getBaseContext(), "Select Gender Properly", Toast.LENGTH_SHORT).show();
                } else if(cb1.isChecked())
                {
                    gender=cb1.getText().toString();
                }
                else
                {
                    gender=cb2.getText().toString();
                }
                uname=username.getText().toString();
                upass=password.getText().toString();
                eid=email.getText().toString();
                long l=adapter.insertData(uname,upass,gender,eid);
                if(l>0)
                {
                    username.setText("");
                    password.setText("");
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    email.setText("");
                    startActivity(new Intent(getBaseContext(),homeActivity.class));
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}