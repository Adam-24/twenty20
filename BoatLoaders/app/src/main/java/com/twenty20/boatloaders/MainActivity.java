package com.twenty20.boatloaders;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * This class is the heart of the application. (probably should just leave it alone)
 * **/

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.NUMBER_OF_CRATES = 3; // Default, not really needed (or shouldn't be)
        Constants.SCREEN = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Constants.FIRST_POSITION = new Rect(0, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);
        Constants.SECOND_POSITION = new Rect(Constants.FIRST_POSITION.right, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);
        Constants.THIRD_POSITION = new Rect(Constants.SECOND_POSITION.right, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);

        setContentView(new GamePanel(this));
    }
}
