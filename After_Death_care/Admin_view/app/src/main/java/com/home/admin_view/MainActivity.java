package com.home.admin_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_admin = (Button) findViewById(R.id.btn_admin);




       btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent displayScreen = new Intent(MainActivity.this, admin.class);
                startActivity(displayScreen);
            }
        });



    }



}