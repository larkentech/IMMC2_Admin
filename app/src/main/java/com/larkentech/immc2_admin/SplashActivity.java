package com.larkentech.immc2_admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences userpref;
    String adminid;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userpref = getSharedPreferences("UserPref",MODE_PRIVATE);
        adminid = userpref.getString("loginID",null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (haveNetworkConnection())
                {
                    if (adminid == null )
                    {
                        Log.v("TAG","SharedPref2:"+getSharedPreferences("UserPref",MODE_PRIVATE).getString("loginId",null));
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Log.v("TAG","SharedPref3:"+getSharedPreferences("UserPref",MODE_PRIVATE).getString("loginId",null));
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                }
                else {
                    Intent i = new Intent(getApplicationContext(),NoInternetActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },3000);
    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
