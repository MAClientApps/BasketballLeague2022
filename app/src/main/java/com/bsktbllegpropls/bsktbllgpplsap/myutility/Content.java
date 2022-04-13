package com.bsktbllegpropls.bsktbllgpplsap.myutility;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import com.appodeal.ads.Appodeal;


public class Content {
    public static final String App_KEY_AD_ID = "edc2e09dfeeb28d056234994ebdaa1f25dd6b188c00b59e7";

    public static Boolean App_KEY_AD_SHOW = true;
    public static boolean isNetworkAvailabe(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm != null && cm.getActiveNetworkInfo() != null) && cm
                .getActiveNetworkInfo().isConnectedOrConnecting();
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
}
