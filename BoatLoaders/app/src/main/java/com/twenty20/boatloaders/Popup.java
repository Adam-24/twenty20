package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Popup {
    public boolean shouldClose(MotionEvent event);
    public void open();
    public boolean isOpen();
    public SceneEnum close();
    public void draw(Canvas canvas);
    public void update();
}
