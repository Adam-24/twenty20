package com.twenty20.boatloaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Admin on 1/8/2018.
 */

public class Crate{

    private MovableImage image;

    public Crate(MovableImage image){
        this.image = image;
    }

    public void updatePosition(final int x, final int y){
        image.updatePosition(x, y);
    }

    public void draw(Canvas canvas){
        image.draw(canvas);
    }

    public Rect getRect(){
        return new Rect(image.getRect());
    }

    public boolean isSmaller(Crate crate){
        return this.image.getRect().width() < crate.image.getRect().width();
    }

    public void update(){

    }
}
