package com.twenty20.boatloaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Admin on 1/8/2018.
 */

public class Button {

    private Bitmap background;
    private Bitmap foreground;
    private Bitmap overlay;

    private Rect rect;
    private Text text;

    //For a common image button, i.e. close.
    //public Button(Resources res, Rect img_rect, int buttonID, int overlayID){}

    public Button(Resources res, Rect rect, int backgroundID, int foregroundID, int overlayID){
        this.rect = rect;

        background = BitmapFactory.decodeResource(res, backgroundID);
        foreground = BitmapFactory.decodeResource(res, foregroundID);
        overlay = BitmapFactory.decodeResource(res, overlayID);
    }

    public Button(Resources res, Point point, int backgroundID, int overlayID, Text text){
        this.text = text;
        rect = new Rect(createFromText(this.text, point));

        background = BitmapFactory.decodeResource(res, backgroundID);
        overlay = BitmapFactory.decodeResource(res, overlayID);
    }

    public Button(Resources res, Rect rect, int backgroundID, int overlayID, Text text){
        this.text = text;
        this.rect = rect;

        background = BitmapFactory.decodeResource(res, backgroundID);
        overlay = BitmapFactory.decodeResource(res, overlayID);
    }

    public boolean contains(int x, int y){
        return rect.contains(x, y);
    }

    //TODO: Implement a reaction for a button being pushed.
    //public void push(){}

    public void draw(Canvas canvas){

        canvas.drawBitmap(background, null, rect, new Paint());

        if(text != null) drawCentered(text, rect, canvas);
        if(foreground != null) canvas.drawBitmap(overlay, null, rect, new Paint());

        canvas.drawBitmap(overlay, null, rect, new Paint());
    }

    private void drawCentered(Text text, Rect rect, Canvas canvas) {
        int mTextWidth, mTextHeight;

        //Calculate the size of the text
        Rect textBounds = new Rect();
        text.getPaint().getTextBounds(text.getText(), 0, text.getText().length(), textBounds);
        mTextWidth = (int) text.getPaint().measureText(text.getText());
        mTextHeight = textBounds.height();

        canvas.drawText(text.getText(),
                (rect.centerX()) - (mTextWidth / 2f),
                (float) (rect.centerY()) + (mTextHeight / 2f),
                text.getPaint()
        );
    }

    private Rect createFromText(Text text, Point point){
        final int width, height, buffer;

        //Calculate the size of the text
        Rect textBounds = new Rect();
        text.getPaint().getTextBounds(text.getText(), 0, text.getText().length(), textBounds);
        width = (int) text.getPaint().measureText(text.getText());
        height = textBounds.height();
        buffer = height;

        return new Rect(
                point.x - width/2 - buffer,
                point.y - height/2 - buffer,
                point.x + width/2 + buffer,
                point.y + height/2 + buffer
        );

        /*return new Rect(
                point.x - buffer,
                point.y - buffer,
                point.x + width + buffer,
                point.y + height + buffer
        );*/
    }
}
