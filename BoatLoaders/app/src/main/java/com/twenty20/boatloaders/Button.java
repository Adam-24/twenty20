package com.twenty20.boatloaders;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Admin on 1/8/2018.
 */

public class Button {

    private boolean hasText;
    private Rect rect;
    private Bitmap background;
    private Bitmap overlay;
    private String text;
    private Paint paint;

    private boolean hasOverlay;

    //TODO: Fix so that textSize is automatic (proportional to the given Rect)
    public Button(Rect bounds, Resources res, int backgroundID){

        this.rect = new Rect(bounds);
        this.background = BitmapFactory.decodeResource(res, backgroundID);

        hasText = false;
        hasOverlay = false;
    }

    public Button(Rect bounds, Resources res, int backgroundID, int overlayID){

        this.rect = new Rect(bounds);
        this.background = BitmapFactory.decodeResource(res, backgroundID);

        hasText = false;
        hasOverlay = true;
        this.overlay = BitmapFactory.decodeResource(res, overlayID);
    }

    public Button(String text, int textColor, int textSize, Rect bounds, Resources res, int backgroundID){

        hasText = true;
        this.text = text;

        this.paint = new Paint();
        this.paint.setColor(textColor);
        this.paint.setTextSize(textSize);

        this.rect = new Rect(bounds);
        this.background = BitmapFactory.decodeResource(res, backgroundID);

        hasOverlay = false;
    }

    public Button(String text, int textColor, int textSize, Rect bounds, Resources res, int backgroundID, int overlayID){

        hasText = true;
        this.text = text;

        this.paint = new Paint();
        this.paint.setColor(textColor);
        this.paint.setTextSize(textSize);

        this.rect = new Rect(bounds);
        this.background = BitmapFactory.decodeResource(res, backgroundID);

        hasOverlay = true;
        this.overlay = BitmapFactory.decodeResource(res, overlayID);
    }

    public boolean contains(int x, int y){
        return rect.contains(x, y);
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(background, null, rect, new Paint());

        if(hasText) drawCentered(text, rect, canvas, paint);

        if(hasOverlay) canvas.drawBitmap(overlay, null, rect, new Paint());
    }

    private void drawCentered(String text, Rect bounds, Canvas canvas, Paint paint) {
        int mTextWidth, mTextHeight;

        //Calculate the size of the text
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        mTextWidth = (int) paint.measureText(text);
        mTextHeight = textBounds.height();

        canvas.drawText(text,
                (bounds.centerX()) - (mTextWidth / 2f),
                (float) (bounds.centerY()) + (mTextHeight / 2f),
                paint
        );
    }
}
