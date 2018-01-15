package com.twenty20.boatloaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is an window that pops up when the player wins, loses, or ends the game.
 * When closed, returns control back to the scene.
 * **/

public class GameOver implements Popup {

    private Bitmap background;
    private Rect rect;

    private Button playAgain;
    private Button back;

    public GameOver(){
        rect = new Rect(
                Constants.SCREEN_WIDTH/4,
                Constants.SCREEN_HEIGHT/4,
                Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4,
                Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/4);

        background = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gm_win);

        playAgain = new Button("Play Again", Color.BLACK, 50,
                new Rect(rect.left, rect.bottom - 50, rect.centerX() - 15, rect.bottom + 50),
                Constants.CURRENT_CONTEXT.getResources(), R.drawable.button_back, R.drawable.button_overlay);

        back = new Button("Back", Color.BLACK, 50,
                new Rect(rect.centerX() + 15, rect.bottom - 50, rect.right, rect.bottom + 50),
                Constants.CURRENT_CONTEXT.getResources(), R.drawable.button_back, R.drawable.button_overlay);
    }

    @Override
    public void update() {

    }

    @Override
    public SceneEnum receiveTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(playAgain.contains((int) event.getX(), (int) event.getY())){
                return SceneEnum.GAME;
            }

            if(back.contains((int) event.getX(), (int) event.getY())) {
                return SceneEnum.MAINMENU;
            }
        }

        return null;
    }

    @Override
    public void turnOn(){
        //TODO: NECESSARY?
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(50, 0,0,0));

        canvas.drawRect(Constants.SCREEN, paint);
        canvas.drawBitmap(background, null, rect, new Paint());

        playAgain.draw(canvas);
        back.draw(canvas);
    }

    @Override
    public void close() {

    }
}
