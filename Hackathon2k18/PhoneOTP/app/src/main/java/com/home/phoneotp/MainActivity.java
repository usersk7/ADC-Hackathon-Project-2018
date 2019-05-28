package com.home.phoneotp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Firebase firebaseRef;
    private EditText etRestaurantName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //_______________________________ Firebase Connection _________________________________
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase("https://fir-datamodelingprac.firebaseio.com/");
        //_______________________________ Views _________________________________
        etRestaurantName = (EditText) findViewById(R.id.etRestaurantName);
        Button btnGetData = (Button) findViewById(R.id.btnGetData);
        //_______________________________ setting OnClickListener() _________________________________
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //_______________________________ Query to get the all the data of a specific Restaurant w.r.t its name _________________________________
                Query mobileNumExistanceQuery = firebaseRef.orderByChild("name").equalTo(etRestaurantName.getText().toString());
                mobileNumExistanceQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Restaurants restaurants = null;
                        //_______________________________ check if server return null _________________________________
                        if (dataSnapshot.exists()) {
                            //getting the data at specific node which is selected by input Restaurant name
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                restaurants = child.getValue(Restaurants.class);
                            }
                            String addressOfRestaurant = restaurants.getLocation().getAddress().toString();
                            Toast.makeText(getApplicationContext(), "Address of Restaurant is: " + addressOfRestaurant, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "This Restaurant name does'nt exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), firebaseError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}