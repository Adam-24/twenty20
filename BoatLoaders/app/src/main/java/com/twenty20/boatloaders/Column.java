package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/9/2018.
 */

public class Column {

    //contains arrayList of crates.

    private List<Crate> crates;
    private Rect bounds;

    public Column(Rect bounds){
        crates = new ArrayList<>();
        this.bounds = bounds;

        System.out.println("Bounds W:" + bounds.width());
    }

    public void add(Crate crate){
        int x = bounds.centerX();// - crate.getRect().width()/2;
        int y = (int) ((Constants.SCREEN_HEIGHT * .75) - ((crates.size() + 1) * crate.getRect().height()) + crate.getRect().height()/2);

        crate.reposition(x, y);

        crates.add(crate);
    }

    public boolean contains(int x, int y) {
        return bounds.contains(x, y);
    }

    public Crate pickUp() {
        if(crates.size() == 0) return null;

        Crate crate = crates.get(crates.size() - 1);
        crates.remove(crates.size() - 1);

        return crate;
    }

    public boolean placeCrate(Crate crate) {
        //TODO: Check here if drop is allowed. YES place, NO put back.
        Crate lowerCrate = (crates.size() == 0) ? null : crates.get(crates.size() - 1);

        if(lowerCrate != null && lowerCrate.isSmaller(crate)){
            System.out.println("The next one is smaller.");
            return false; //not allowed, return to previous position.
        }
        add(crate);
        return true;
    }

    public void draw(Canvas canvas) {
        for (Crate crate : crates) {
            crate.draw(canvas);
        }
    }

    public int numberOfCratesHeld(){
        return crates.size();
    }
    /*
    * if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Crate crate : crates) {
                if (crate.contains((int) event.getX(), (int) event.getY())){
                    crate.reposition(event);
                }
            }
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            for (Crate crate : crates) {
                if (crate.isMoving()){
                    crate.reposition(event);
                }
            }
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            for (Crate crate : crates) {
                if(crate.isMoving()) {
                    if(! crate.putDown(event)){
                        //if let go on boarders; does contains intersect or avoid?
                    }
                }
            }
        }
    *
    * */

}
