package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This class is where the game is actually played.
 * On win/loss/quit "GameOver" is used. (not sure how yet...)
 * **/

public class GameplayScene implements Scene {

    private SceneManager manager;

    public GameplayScene(SceneManager manager){
        this.manager = manager;
    }

    @Override
    public void terminate() {
        manager.setScene(SceneEnum.MAINMENU);
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }
}
