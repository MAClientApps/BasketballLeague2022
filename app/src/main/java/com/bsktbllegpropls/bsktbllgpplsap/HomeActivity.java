package com.bsktbllegpropls.bsktbllgpplsap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsktbllegpropls.bsktbllgpplsap.myutility.Content;


public class HomeActivity extends AppCompatActivity {

    ImageView imggame, imggame1, imggame2, imggame3, imggame4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        imggame = findViewById(R.id.gameimg1);
        imggame1 = findViewById(R.id.gameimg2);
        imggame2 = findViewById(R.id.gameimg3);
        imggame3 = findViewById(R.id.gameimg4);
        imggame4 = findViewById(R.id.gameimg5);
        Content.AdShowBanner(HomeActivity.this, R.id.AdsBanner);
        imggame.setOnClickListener(v -> {
            Content.AdShowVideo(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, WebView.class);
            intent.putExtra("playUrl", "https://g.h5games.online/ultimate-swish/");
            startActivity(intent);

        });

        imggame1.setOnClickListener(v -> {
            Content.AdShowVideo(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, WebView.class);
            intent.putExtra("playUrl", "https://play.famobi.com/slam-dunk-basketball/A-GPSNV");
            startActivity(intent);

        });

        imggame2.setOnClickListener(v -> {
            Content.AdShowVideo(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, WebView.class);
            intent.putExtra("playUrl", "https://g.h5games.online/basketball/index.html");
            startActivity(intent);

        });

        imggame3.setOnClickListener(v -> {
            Content.AdShowVideo(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, WebView.class);
            intent.putExtra("playUrl", "https://play.famobi.com/3d-basketball/A-GPSNV");
            startActivity(intent);

        });

        imggame4.setOnClickListener(v -> {
            Content.AdShowVideo(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, WebView.class);
            intent.putExtra("playUrl", "https://g.h5games.online/street-shot/index.html");
            startActivity(intent);

        });
    }


}