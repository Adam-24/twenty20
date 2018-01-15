package com.twenty20.boatloaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Admin on 1/10/2018.
 */

public class Plaque {

    private Rect rect;
    private Bitmap image;

    public Plaque(Rect bounds, Resources res, int imageID){

        this.rect = new Rect(bounds);
        this.image = BitmapFactory.decodeResource(res, imageID);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, null, rect, new Paint());
    }
}
