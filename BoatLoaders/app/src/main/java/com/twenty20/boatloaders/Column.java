package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/9/2018.
 */

public class Column {
    private Rect bounds;
    private List<Crate> crates;

    public Column(Rect bounds){
        this.bounds = bounds;
        crates = new ArrayList<>();
    }

    public void add(Crate crate){
        int x = bounds.centerX();
        int y = (int) ((Constants.SCREEN_HEIGHT * .75) - ((crates.size() + 1) * crate.getRect().height()) + crate.getRect().height()/2);

        crate.updatePosition(x, y);

        crates.add(crate);
    }

    public boolean contains(final int x, final int y){
        return bounds.contains(x, y);
    }

    public void draw(Canvas canvas){
        for (Crate crate : crates) {
            crate.draw(canvas);
        }
    }

    public boolean isFull(){
        return crates.size() == Constants.NUMBER_OF_CRATES;
    }

    public Crate pickUp(){
        if(crates.size() == 0) return null;
        return crates.get(crates.size() - 1);
    }

    //TODO: Better way?
    public void removeTopCrate(){
        if(crates.size() > 0) crates.remove(crates.size() - 1);
    }

    public boolean canPlaceCrate(Crate crate){
        Crate lowerCrate = (crates.size() == 0) ? null : crates.get(crates.size() - 1);
        return !(lowerCrate != null && lowerCrate.isSmaller(crate));
    }

    public int numberOfCratesTEMP(){
        return crates.size();
    }
}
