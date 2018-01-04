package com.twenty20.boatloaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class SplashScene implements Scene{

    private final Bitmap splashScreen;
    private final Rect splashScreenRect;

    private SceneManager manager;

    SplashScene(SceneManager manager){
        this.manager = manager;

        splashScreen = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.splashscreen);
        splashScreenRect = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawBitmap(splashScreen, null, splashScreenRect, new Paint());

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Tap to Start", (float) (Constants.SCREEN_WIDTH*.60), (float) (Constants.SCREEN_HEIGHT*.75), paint);
    }

    @Override
    public void terminate() {
        manager.setScene(0);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        //On any event? tap screen...

        if(event.getAction() == MotionEvent.ACTION_UP) manager.setScene(1);
    }
}
