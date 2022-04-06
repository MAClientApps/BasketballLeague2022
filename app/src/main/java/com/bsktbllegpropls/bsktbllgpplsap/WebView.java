package com.bsktbllegpropls.bsktbllgpplsap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bsktbllegpropls.bsktbllgpplsap.myutility.Content;

import java.net.InetAddress;
import java.util.Objects;

public class WebView extends AppCompatActivity {

    private android.webkit.WebView contentweb;
    LinearLayout lyouterror;
    String playUrl = null;
    Button btnarlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_webview);

        contentweb = findViewById(R.id.contentweb);
        lyouterror = findViewById(R.id.lyuteror);
        btnarlt = findViewById(R.id.btnalrt);
        Content.AdShowBanner(WebView.this, R.id.AdsBanner);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                playUrl = null;
            } else {
                playUrl = extras.getString("playUrl");
            }
        } else {
            playUrl = (String) savedInstanceState.getSerializable("playUrl");
        }

        new CheckConnection().execute();

        btnarlt.setOnClickListener(v -> new CheckConnection().execute());

    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    public class CheckConnection extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @SuppressWarnings("EqualsBetweenInconvertibleTypes")
        @SuppressLint("MissingPermission")
        @Override
        protected Boolean doInBackground(Void... voids) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                try {
                    InetAddress inetAddress = InetAddress.getByName("google.com");
                    return !inetAddress.equals("");
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean value) {
            super.onPostExecute(value);
            if (value) {
                contentweb.setVisibility(View.VISIBLE);
                lyouterror.setVisibility(View.GONE);
                initGlobal();
            } else {
                contentweb.setVisibility(View.GONE);
                lyouterror.setVisibility(View.VISIBLE);
            }

        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    public void initGlobal() {

        CookieManager.getInstance().setAcceptCookie(true);
        contentweb.getSettings().setJavaScriptEnabled(true);
        contentweb.getSettings().setUseWideViewPort(true);
        contentweb.getSettings().setLoadWithOverviewMode(true);
        contentweb.getSettings().setDomStorageEnabled(true);
        contentweb.getSettings().setPluginState(WebSettings.PluginState.ON);
        contentweb.setWebChromeClient(new WebChromeClient());

        contentweb.setVisibility(View.VISIBLE);


        contentweb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        contentweb.loadUrl(playUrl);

    }


    @Override
    public void onResume() {
        super.onResume();
        contentweb.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        contentweb.onPause();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (contentweb.canGoBack()) {
                    contentweb.goBack();
                } else {
                    finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        contentweb.loadUrl("about:blank");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}