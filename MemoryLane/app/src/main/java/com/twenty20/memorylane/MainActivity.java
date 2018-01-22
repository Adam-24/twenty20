package com.twenty20.memorylane;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * This class is the heart of the application. (probably should just leave it alone)
 * **/

public class MainActivity extends Activity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Set Ad
        MobileAds.initialize(this, "ca-app-pub-3562923192043286~1944774745");

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3562923192043286/7529764603");

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

        setAdWindow();

        Constants.SCREEN_WIDTH = dm.widthPixels; //TODO: Refactor & delete
        Constants.SCREEN_HEIGHT = dm.heightPixels; //TODO: Refactor & delete
        Constants.SCREEN = new Rect(0, 0, dm.widthPixels, dm.heightPixels);

        setContentView(new GamePanel(this));
    }

    private void setAdWindow() {
        WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.BOTTOM;
        windowParams.x = 0;
        windowParams.y = 0;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.windowAnimations = 0;

        getWindowManager().addView(adView, windowParams);
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {adView.destroy(); }
        super.onDestroy();
    }
}
