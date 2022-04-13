package com.bsktbllegpropls.bsktbllgpplsap;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.bsktbllegpropls.bsktbllgpplsap.myutility.Content;

public class DashActivity extends AppCompatActivity {


    Button btnplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        btnplay = findViewById(R.id.btnplay);

        btnplay.setOnClickListener(v -> {

            Intent intent = new Intent(DashActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        if (Content.isNetworkAvailabe(this)) {
            Content.AdInitializetion(DashActivity.this);
            Content.AdShowBanner(DashActivity.this, R.id.AdsBanner);
        }

        if (isInternetConnection()) {
        } else {
            Toast.makeText(this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInternetConnection() {
        return ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }


}