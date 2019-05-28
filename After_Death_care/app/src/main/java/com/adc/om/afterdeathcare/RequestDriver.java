package com.adc.om.afterdeathcare;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestDriver extends AppCompatActivity {
    private DatabaseReference rootRef;
    private List<String> hosp_name, source, dest,contact,body,pincode,accept;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String record="";
    private String[] split_data;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_driver);

        progress = new ProgressDialog(this);
        progress.setTitle("Request");
        progress.setMessage("Please wait All Request are Loading");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        hosp_name = new ArrayList<>();
        source = new ArrayList<>();
        dest = new ArrayList<>();
        contact = new ArrayList<>();
        body = new ArrayList<>();
        pincode = new ArrayList<>();
        accept = new ArrayList<>();
        onActivityLoad();

        }
    public void FillListView()
    {
        progress.dismiss();
        mRecyclerView = findViewById(R.id.developer_recycler);
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(RequestDriver.this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)

        mAdapter = new DeveloperCardViewAdapter(this ,hosp_name,source,dest,contact,body,pincode,accept);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter( mAdapter );
    }
    public void onActivityLoad()
    {
        progress.show();
        rootRef = FirebaseDatabase.getInstance().getReference("Hosp");


        rootRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {
                    record=childSnapShot.getValue().toString();

                    split_data = record.split("_");
                    hosp_name.add(split_data[0]);
                    source.add(split_data[1]);
                    dest.add(split_data[2]);
                    contact.add(split_data[3]);
                    body.add(split_data[4]);
                    pincode.add(split_data[5]);
                    accept.add(split_data[6]);
                }
                FillListView();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

