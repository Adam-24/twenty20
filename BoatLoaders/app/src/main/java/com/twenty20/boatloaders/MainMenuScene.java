package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This class is the main menu of the game. From here the user can acsess: Game, Options, Store.
 * User can also exit the game if back arrow (button on phone) is pressed.
 * **/

public class MainMenuScene implements Scene {

    private SceneManager manager;

    MainMenuScene(SceneManager manager){
        this.manager = manager;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void terminate() {
        manager.setScene(SceneEnum.GAME); //Or where ever it's supposed to go.
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}
