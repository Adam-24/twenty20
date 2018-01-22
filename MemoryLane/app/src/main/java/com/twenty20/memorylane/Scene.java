package com.twenty20.memorylane;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminateTo(SceneEnum nextScene);
    public void receiveTouch(MotionEvent event);
    public void reset();
}
