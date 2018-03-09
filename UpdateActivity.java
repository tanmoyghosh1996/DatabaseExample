package com.example.root.databaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText tvName,tvEmail,tvPhone,tvid;
    private Button btn_update,btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tvid=(EditText)findViewById(R.id.tv_id);
        tvName=(EditText) findViewById(R.id.tv_name);
        tvEmail=(EditText) findViewById(R.id.tv_mail);
        tvPhone=(EditText) findViewById(R.id.tv_phone);
        btn_update=(Button)findViewById(R.id.btn_update);
        btn_delete=(Button)findViewById(R.id.btn_delete);

        String id=getIntent().getExtras().getString("Id");
        String name=getIntent().getExtras().getString("Name");
        String email=getIntent().getExtras().getString("Email");
        String phone=getIntent().getExtras().getString("Phone");

        tvid.setText(id);
        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid = tvid.getText().toString();
                DbManager manager=new DbManager(UpdateActivity.this);
                manager.DeleteData(eid);

                Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid = tvid.getText().toString();
                String uname = tvName.getText().toString();
                String uemail = tvEmail.getText().toString();
                String uphone = tvPhone.getText().toString();


                DbManager manager=new DbManager(UpdateActivity.this);
                boolean res= manager.UpdateData(uid,uname,uemail,uphone);
                if(res)
                {
                    Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
