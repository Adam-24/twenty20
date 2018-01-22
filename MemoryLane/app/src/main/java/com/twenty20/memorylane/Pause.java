package com.twenty20.memorylane;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * This class is an window that pops up when the player "pauses" the game.
 * Tap anywhere not on button to continue / close.
 * **/

public class Pause implements Popup {

    private Button restart;
    private Button mainMenu;

    private SceneEnum closeTo;
    private boolean isOpen;

    public Pause(){
        isOpen = false;

        restart = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Point(Constants.SCREEN.centerX(), Constants.SCREEN.centerY() - 100),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("Restart").setColor(Color.BLACK).setSize(50).build());

        mainMenu = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Point(Constants.SCREEN.centerX(), Constants.SCREEN.centerY() + 100),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("Main Menu").setColor(Color.BLACK).setSize(50).build());

        //TODO: Add sound toggle.
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(50, 0,0,0));
        canvas.drawRect(Constants.SCREEN, paint);

        restart.draw(canvas);
        mainMenu.draw(canvas);
    }

    @Override
    public boolean shouldClose(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(restart.contains((int)event.getX(), (int)event.getY())){
                closeTo = SceneEnum.GAME;
                return true;
            } else if(mainMenu.contains((int)event.getX(), (int)event.getY())){
                closeTo = SceneEnum.MAINMENU;
                return true;
            } else {
                closeTo = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void open() {
        closeTo = null;
        isOpen = true;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public SceneEnum close() {
        isOpen = false;
        return closeTo;
    }

    @Override
    public void update() {

    }
}
