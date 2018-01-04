package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GameplayScene implements Scene {

    private SceneManager manager;

    public GameplayScene(SceneManager manager){
        this.manager = manager;
    }

    @Override
    public void terminate() {
        manager.setScene(2);
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
