package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Popup {
    public void update();
    public SceneEnum receiveTouch(MotionEvent event);
    public void draw(Canvas canvas);
    public void close();
    public void turnOn();
}
