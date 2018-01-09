package com.twenty20.boatloaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Admin on 1/8/2018.
 */

public class Crate{

    private final Bitmap background;
    private Rect rect;

    //private enum Position {FIRST, SECOND, THIRD}

    //private Position position;

    private Column column;
    private boolean isHeld;

    public Crate(Rect bounds, Column column) {
        System.out.println("Crate bounds W:" + bounds.width());
        this.rect = new Rect(bounds);
        this.background = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.crate);
        this.column = column;
        //position = Position.FIRST;
        isHeld = false;
    }

    public boolean isMoving(){
        return isHeld;//currentPosition == Position.HELD;
    }

    public Rect getRect(){
        return rect;
    }

    public boolean contains(int x, int y){
        return rect.contains(x, y);
    }

    //public void reposition(MotionEvent event){
    public void reposition(int x, int y){
        //currentPosition = Position.HELD;

        //final int x = (int) event.getX();
        //final int y = (int) event.getY();

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

    /* void putDown(MotionEvent event){
        //TODO: !!! SHOULD HAVE CRATE IN COLUMN OBJECT?
        isHeld = false;
        final int x = (int) event.getX();
        final int y = (int) event.getY();

        if(Constants.FIRST_POSITION.contains(x, y)){
            position = Position.FIRST;
        } else if(Constants.SECOND_POSITION.contains(x, y)){
            position = Position.SECOND;
        } else if(Constants.THIRD_POSITION.contains(x, y)){
            position = Position.THIRD;
        } else {
            //throw something?
        }
    }*/

    public boolean isSmaller(Crate crate){
        return this.rect.width() < crate.rect.width();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background, null, rect, new Paint());
    }


}
