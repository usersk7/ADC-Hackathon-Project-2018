package com.adc.om.afterdeathcare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.Key;

public class Register extends AppCompatActivity {
    EditText editText;
    Button button;
    ProgressDialog progress;
    String EditTextValue, qrcodeText;
    private DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        progress = new ProgressDialog(Register.this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while checking the Aadhar Details");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditTextValue = editText.getText().toString();
                if (EditTextValue.equals("")) {
                    Toast.makeText(Register.this, "Please Enter a valid Aadhar Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                progress.show();
                rootRef = FirebaseDatabase.getInstance().getReference();
                rootRef.child("aadhar").child(EditTextValue).addValueEventListener(new ValueEventListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            if (dataSnapshot.getValue() != null) {
                                try {

                                    qrcodeText = dataSnapshot.getValue().toString();
                                    onSucess();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {

                                Toast.makeText(Register.this, "Please Enter a valid Aadhar Number", Toast.LENGTH_SHORT).show();
                                progress.dismiss();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onSucess() {
        try {
            progress.dismiss();

            Intent na=new Intent(Register.this,ImageUpload.class);
            startActivity(na);

        }
     catch (Exception e) {
        e.printStackTrace();
    }
    }




    }