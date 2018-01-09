package com.twenty20.boatloaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is the scene that displays when the game is loading.
 * TODO: put loading here...
 * After finished, user tap sets scene to Main Menu.
 * **/

public class SplashScene implements Scene{

    private SceneManager manager;

    private final Bitmap splashScreen;
    private final Rect splashScreenRect;

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

        drawCentered("Tap to Start", new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), canvas, paint);
    }

    private void drawCentered(String text, Rect bounds, Canvas canvas, Paint paint) {
        int mTextWidth, mTextHeight;

        //Calculate the size of the text
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        mTextWidth = (int) paint.measureText(text);
        mTextHeight = textBounds.height();

        canvas.drawText(text,
            (bounds.centerX()) - (mTextWidth / 2f),
            (float) (bounds.height()*.75) + (mTextHeight / 2f),
            paint
        );
    }

    @Override
    public void terminateTo(SceneEnum nextScene) {
        manager.setScene(nextScene);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        //On any event? tap screen...

        if(event.getAction() == MotionEvent.ACTION_UP) terminateTo(SceneEnum.MAINMENU);
    }
}
