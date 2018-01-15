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

    private Plaque plaque;

    public Column(Rect bounds){
        this.bounds = bounds;

        plaque = new Plaque( new Rect(
                bounds.centerX() - 75,
                (int) (Constants.SCREEN_HEIGHT * .75),
                bounds.centerX() + 75,
                (int) (Constants.SCREEN_HEIGHT * .75) + 75
        ), Constants.CURRENT_CONTEXT.getResources(), R.drawable.plaque);
        crates = new ArrayList<>();
    }

    public void add(Crate crate){
        int x = bounds.centerX();
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
        plaque.draw(canvas);
    }

    public int numberOfCratesHeld(){
        return crates.size();
    }
}
