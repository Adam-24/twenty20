package com.twenty20.boatloaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Admin on 1/16/2018.
 */

public class SpriteImage{

    private Bitmap[] images;
    private AnimationManager animationManager;
    private Animation animation;

    public SpriteImage(Bitmap[] images, Rect rect) {
        this.images = images;

        animation = new Animation(images, 2);
        //animationManager = new AnimationManager()
    }

}
