package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private int levelSelected = 0;
    public int activeScene, previousScene = 0;

    public SceneManager(){
        scenes.add(new SplashScene(this));
        scenes.add(new MainMenuScene(this));
        scenes.add(new GameplayScene(this));
        scenes.add(new OptionsScene(this));
        //scenes.add(new StoreScene(this));  //TODO: Add store scene.

        //TODO: Use enum class for arrayList positions, instead of numbers.
        activeScene = 0;
    }

    public void setScene(int activeScene){
        //Transition to scene (parameter's value).
        previousScene = this.activeScene;

        this.activeScene = activeScene;
    }

    public void receiveTouch(MotionEvent event){
        scenes.get(activeScene).receiveTouch(event);
    }

    public void update(){
        scenes.get(activeScene).update();
    }

    public void draw(Canvas canvas){
        scenes.get(activeScene).draw(canvas);
    }

}
