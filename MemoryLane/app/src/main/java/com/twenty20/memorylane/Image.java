package com.twenty20.memorylane;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Admin on 1/15/2018.
 */

public class Image {
    private Bitmap image;
    private Rect rect;

    public Image(Resources res, int imageID, Rect rect){
        this.rect = rect;
        image = BitmapFactory.decodeResource(res, imageID);
    }

    public boolean contains(final int x, final int y){
        return rect.contains(x, y);
    }

    public Rect getRect(){
        return new Rect(rect);
    }

    public Bitmap getImage(){
        return image;
    }

    //TODO: Not supposed to be here.
    public void setRect(Rect rect){
        this.rect = rect;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, null, rect, new Paint());
    }
}
