package com.twenty20.memorylane;

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
    private final Image image;
    private final Text text;

    SplashScene(SceneManager manager){
        this.manager = manager;
        image = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.splashscreen, Constants.SCREEN);
        text = new Text.Builder("Tap to start").build();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        image.draw(canvas);
        drawText(text.getText(), Constants.SCREEN, canvas, text.getPaint());
    }

    private void drawText(String text, Rect bounds, Canvas canvas, Paint paint) {
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
        if(event.getAction() == MotionEvent.ACTION_UP) terminateTo(SceneEnum.MAINMENU);
    }

    @Override
    public void reset() {

    }


}
