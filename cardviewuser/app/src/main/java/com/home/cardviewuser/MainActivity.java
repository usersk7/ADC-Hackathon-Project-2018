package com.home.cardviewuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
        EditText dest;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dest=(EditText)findViewById(R.id.des);
        }
public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
        case R.id.radio_pirates:
        if (checked)
        // Pirates are the best

        dest.setVisibility(View.INVISIBLE);
        break;
        case R.id.radio_ninjas:
        if (checked)
        dest.setVisibility(View.VISIBLE);

        break;
        }
        }
        }
