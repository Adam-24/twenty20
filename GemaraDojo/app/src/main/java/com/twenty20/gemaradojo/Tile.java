package com.twenty20.gemaradojo;

import android.graphics.Canvas;

/**
 * Created by Admin on 1/21/2018.
 */

public class Tile {

    private Button button;
    private boolean isPressed;

    public Tile(Button button){
        this.button = button;
        isPressed = false;
    }

    public boolean contains(final int x, final int y){
        return button.contains(x, y);
    }

    public void select(){
        isPressed = true;
        System.out.println("Selected ");
    }

    public void draw(Canvas canvas){
        button.draw(canvas);
    }
}
