package com.bsktbllegpropls.bsktbllgpplsap.myutility;

import static android.content.Context.MODE_PRIVATE;
import static com.adjust.sdk.Util.md5;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Base64;

import com.appodeal.ads.Appodeal;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

public class Content {
    public static final String App_KEY_AD_ID = "edc2e09dfeeb28d056234994ebdaa1f25dd6b188c00b59e7";
    public static int App_KEY_AD_COUNTER = 0;
    public static Boolean App_KEY_AD_SHOW = true;
    public static final String App_KEY_PREFERENCE = "basketballleague2022";
    public static final String App_KEY_USER_UUID = "user_uuid";
    public static final String App_KEY_CONFIG_VALUE = "config_value";
    public static final String App_KEY_FIREBASE_REMOTE_CONFIG = "apbalbaskt";
    public static final String App_KEY_ADJUST_ATTRIBUTES = "adjust_attribute";
    public static final String APP_KEY_ADJUST_TOKEN = "b6lg8i8qpj40";
    public static final String APP_KEY_PUSH_TOKEN = "fks1mu";
    public static final String App_KEY_FIREBASE_REMOTE_CONFIG_TIMERS = "timer";
    public static final String App_PACKAGE_NAME = "com.bsktbllapp.apbalbaskt";

    public static boolean isNetworkAvailabe(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm != null && cm.getActiveNetworkInfo() != null) && cm
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static String genrateUUUID(Context context) {
        String md5uuid = getU_UID(context);
        if (md5uuid == null || md5uuid.isEmpty()) {
            String guid = "";
            final String uniqueID = UUID.randomUUID().toString();
            Date date = new Date();
            long timeMilli = date.getTime();
            guid = uniqueID + timeMilli;
            md5uuid = md5(guid);
            setU_UID(context, md5uuid);
        }
        return md5uuid;
    }

    public static String genrateApplinks(Context context) {
        String links ="";
        try {
            String pkgurl =  Content.App_PACKAGE_NAME+"-"+ genrateUUUID(context);
            String base64 = Base64.encodeToString(pkgurl.getBytes("UTF-8"), Base64.DEFAULT);
            links = getEndp(context)+"?"+base64+";2;";
            links = links + URLEncoder.encode(getReceivedAttribution(context), "utf-8");

        }catch (Exception e){

        }
        return links;
    }


    public static void AdInitializetion(Activity activity) {
        Appodeal.initialize(activity, App_KEY_AD_ID,
                Appodeal.REWARDED_VIDEO | Appodeal.BANNER);
    }


    public static void AdShowBanner(Activity activity, int layoutBannerAds) {
        try {
            if (Content.App_KEY_AD_SHOW && Content.isNetworkAvailabe(activity)) {
                Appodeal.setBannerViewId(layoutBannerAds);
                Appodeal.show(activity, Appodeal.BANNER_VIEW);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adsShowRewarded(final Activity activity) {
        try {
            if (Content.App_KEY_AD_SHOW && Content.isNetworkAvailabe(activity)) {
                Content.App_KEY_AD_COUNTER++;
                if (Content.App_KEY_AD_COUNTER == 3) {
                    if (Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
                        Appodeal.show(activity, Appodeal.REWARDED_VIDEO);
                    }
                    Content.App_KEY_AD_COUNTER = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AdShowVideo(final Activity activity) {
        try {
            if (Content.App_KEY_AD_SHOW && Content.isNetworkAvailabe(activity)) {
                if (Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
                    Appodeal.show(activity, Appodeal.REWARDED_VIDEO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setU_UID(Context context, String value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(App_KEY_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(App_KEY_USER_UUID, value);
            editor.apply();
        }
    }

    public static String getU_UID(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(App_KEY_PREFERENCE, MODE_PRIVATE);
        return preferences.getString(App_KEY_USER_UUID, "");
    }

    public static void setEndP(Context context, String value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(App_KEY_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(App_KEY_CONFIG_VALUE, value);
            editor.apply();
        }
    }

    public static String getEndp(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(App_KEY_PREFERENCE, MODE_PRIVATE);
        return preferences.getString(App_KEY_CONFIG_VALUE, "");
    }
    public static JSONObject loadJSONFromAsset(final Context context, final String filename) {
        JSONObject json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            final String jsonStrings = new String(buffer, "UTF-8");
            json =  new JSONObject(jsonStrings);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void setReceivedAttribution(Context context, String value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(App_KEY_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(App_KEY_ADJUST_ATTRIBUTES, value);
            editor.apply();
        }
    }

    public static String getReceivedAttribution(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(App_KEY_PREFERENCE, MODE_PRIVATE);
        return preferences.getString(App_KEY_ADJUST_ATTRIBUTES, "");
    }
}
