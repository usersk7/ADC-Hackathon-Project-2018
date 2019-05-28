package com.home.webview_payment;
        import android.content.Intent;
        import android.content.SharedPreferences;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnSbi = (Button) findViewById(R.id.btn_sbi);
        Button btnPaytm = (Button) findViewById(R.id.btn_paytm);

        btnPaytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent displayScreen = new Intent(MainActivity.this, Paytm.class);
                startActivity(displayScreen);
            }
        });


        btnSbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sbiScreen = new Intent(MainActivity.this, Sbi.class);
                startActivity(sbiScreen);
            }
        });
    }



}