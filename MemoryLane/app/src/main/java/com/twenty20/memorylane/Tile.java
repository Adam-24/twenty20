package com.twenty20.memorylane;

import android.graphics.Canvas;
import android.media.MediaPlayer;

/**
 * Created by Admin on 1/17/2018.
 */

public class Tile {

    private Image image;
    private Image overlay;
    private boolean isLit;
    private long timeStarted;

    private MediaPlayer audio;
    private int soundID;

    public Tile(Image image, int overlay, int soundID){
        this.image = image;
        this.overlay = new Image(Constants.CURRENT_CONTEXT.getResources(), overlay, image.getRect());

        this.audio = MediaPlayer.create(Constants.CURRENT_CONTEXT, soundID);
        this.soundID = soundID;
    }

    public boolean contains(int x, int y){
        return image.getRect().contains(x, y);
    }

    public void touched(){
        if(audio.isPlaying()) resetPlaying();
        audio.start();
        lightUp();
        //TODO: Add sound.
    }

    private void resetPlaying() {
        if (audio != null) {
            try {
                audio.pause();
                audio.seekTo(0);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void lightUp(){
        isLit = true;
        timeStarted = System.currentTimeMillis();
        //TODO: Animate glow. Now, light while true.
    }

    public void draw(Canvas canvas){
        image.draw(canvas);
        if(isLit) {
            overlay.draw(canvas);
            if(System.currentTimeMillis() > timeStarted + 300) {
                isLit = false;
            }
        }
    }
}
