package com.twenty20.boatloaders;

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

        MobileAds.initialize(this, "ca-app-pub-3562923192043286~2799661613");
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3562923192043286/9118314497");

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

        WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
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

        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        //Constants.NUMBER_OF_CRATES = 5; // Default, not really needed (or shouldn't be)
        Constants.SCREEN = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Constants.FIRST_POSITION = new Rect(0, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);
        Constants.SECOND_POSITION = new Rect(Constants.FIRST_POSITION.right, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);
        Constants.THIRD_POSITION = new Rect(Constants.SECOND_POSITION.right, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);

        setContentView(new GamePanel(this));
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
