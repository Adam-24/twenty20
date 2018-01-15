package com.twenty20.boatloaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Admin on 1/8/2018.
 */

public class Crate{

    private final Bitmap background;
    private Rect rect;

    private Column column;
    private boolean isHeld;

    public Crate(Rect bounds, Column column) {
        this.rect = new Rect(bounds);
        this.background = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.crate);
        this.column = column;
        isHeld = false;
    }

    public boolean isMoving(){
        return isHeld;
    }

    public Rect getRect(){
        return rect;
    }

    public boolean contains(int x, int y){
        return rect.contains(x, y);
    }

    public void reposition(int x, int y){

        final int bufferX = rect.width()/2;
        final int bufferY = rect.height()/2;

        rect = new Rect(
                x - bufferX,
                y - bufferY,
                x + bufferX,
                y + bufferY
        );
    }
    public void pickedUp(Column column) {
        this.column = column;
        isHeld = true;
    }

    public Column pickedUpFrom() {
        return this.column;
    }

    public boolean isSmaller(Crate crate){
        return this.rect.width() < crate.rect.width();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background, null, rect, new Paint());
    }


}
