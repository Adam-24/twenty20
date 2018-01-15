package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is an window that pops up when the player "pauses" the game.
 * Tap anywhere not on button to continue / close.
 * **/

public class Pause implements Popup {

    private Button restart;
    //private Button toggleSound;
    private Button mainMenu;
    private boolean isBeingUsed;

    public Pause(){
        restart = new Button("Restart", Color.BLACK, 50,
                new Rect(Constants.SCREEN.centerX() - 150,100,Constants.SCREEN.centerX() + 150,225),
                Constants.CURRENT_CONTEXT.getResources(), R.drawable.button_back, R.drawable.button_overlay);
        //toggleSound = new Button();
        mainMenu = new Button("Main Menu", Color.BLACK, 50,
                new Rect(Constants.SCREEN.centerX() - 150,300,Constants.SCREEN.centerX() + 150,425),
                Constants.CURRENT_CONTEXT.getResources(), R.drawable.button_back, R.drawable.button_overlay);
        isBeingUsed = false;
    }

    @Override
    public void update() {

    }

    public void turnOn(){
        isBeingUsed = true;
    }

    @Override
    public SceneEnum receiveTouch(MotionEvent event) {
        //if(! isBeingUsed) isBeingUsed = true;

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(restart.contains((int)event.getX(), (int)event.getY())){
                return SceneEnum.GAME;
            } else if(mainMenu.contains((int)event.getX(), (int)event.getY())){
                return SceneEnum.MAINMENU;
            } else {
                //This is a hack. Just want it working for now... TODO: FIX.
                return SceneEnum.SPLASHSCREEN;
            }
        }
        return null;
    }

    @Override
    public void draw(Canvas canvas) {
        if(isBeingUsed){
            Paint paint = new Paint();
            paint.setColor(Color.argb(50, 0,0,0));
            canvas.drawRect(Constants.SCREEN, paint);

            restart.draw(canvas);
            mainMenu.draw(canvas);
        }
    }

    @Override
    public void close() {
        isBeingUsed = false;
    }
}
