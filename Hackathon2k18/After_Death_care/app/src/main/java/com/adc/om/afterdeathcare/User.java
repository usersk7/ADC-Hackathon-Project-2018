package com.adc.om.afterdeathcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    }
    public void onfairclick(View v){
        Intent u=new Intent(User.this,UserMap.class);
        startActivity(u);
    }

    public void onhospclick(View v){
        Intent ho=new Intent(User.this,UserSearchHosp.class);
        startActivity(ho);
    }
}
