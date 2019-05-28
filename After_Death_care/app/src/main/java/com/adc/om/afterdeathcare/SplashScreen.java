package com.adc.om.afterdeathcare;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, LoginAll.class);
                    startActivity(i);
                    //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();

                }
            }, SPLASH_SCREEN_OUT);
        } else {
            Toast.makeText(getApplicationContext(), "please Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
