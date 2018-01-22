package com.twenty20.memorylane;

import android.content.res.Resources;
import android.graphics.Rect;

/**
 * Created by Admin on 1/15/2018.
 */

public class MovableImage extends Image{

    public MovableImage(Resources res, int imageID, Rect rect) {
        super(res, imageID, rect);
    }

    public void updatePosition(final int x, final int y){
        //TODO: Why cant pass reference?
        Rect rect = getRect();

        final int bufferX = rect.width()/2;
        final int bufferY = rect.height()/2;

        rect = new Rect(
                x - bufferX,
                y - bufferY,
                x + bufferX,
                y + bufferY
        );

        setRect(rect);
    }
}
