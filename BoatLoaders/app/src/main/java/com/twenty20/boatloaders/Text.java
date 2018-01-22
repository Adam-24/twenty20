package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;

/**
 * Created by Admin on 1/15/2018.
 */

public class Text {

    private Builder builder;

    private Text(Builder builder){
        this.builder = builder;
        if(builder.point != null){
            builder.setRect(createFromText(this, builder.point));
        }
    }

    public Paint getPaint(){
        return builder.paint;
    }

    public String getText(){
        return builder.text;
    }

    public void setText(String text){
        builder.text = text;
        if(builder.point != null){
            builder.setRect(createFromText(this, builder.point));
        }
    }

    public void draw(Canvas canvas){
        if(builder.rect != null) drawCentered(this, builder.rect, canvas);
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

        /*return new Rect(
                point.x - width/2 - buffer,
                point.y - height/2 - buffer,
                point.x + width/2 + buffer,
                point.y + height/2 + buffer
        );*/

        return new Rect(
                point.x - buffer,
                point.y - buffer,
                point.x + width + buffer,
                point.y + height + buffer
        );
    }

    public static class Builder{

        private String text;
        private Paint paint;
        private Rect rect;
        private Point point;

        public Builder(String text){
            this.text = text;

            paint = new Paint();
            paint.setTextSize(50);
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
        }

        public Builder setIsBold(){
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            return this;
        }

        public Builder setSize(int size){
            paint.setTextSize(size);
            return this;
        }

        public Builder setColor(int color){
            paint.setColor(color);
            return this;
        }

        public Builder setRect(Rect rect){
            this.rect = rect;
            return this;
        }

        public Builder setPoint(Point point){
            this.point = point;
            return this;
        }

        public Text build(){
            return new Text(this);
        }
    }
}
