package com.twenty20.boatloaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * This class directs control to the scenes in the arrayList.
 * **/

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public SceneEnum activeScene, previousScene = SceneEnum.SPLASHSCREEN;

    public SceneManager(){
        scenes.add(new SplashScene(this));
        scenes.add(new MainMenuScene(this));
        scenes.add(new GameplayScene(this));
        //scenes.add(new StoreScene(this));  //TODO: Add store scene.

        activeScene = SceneEnum.SPLASHSCREEN;
    }

    public void setScene(SceneEnum activeScene){
        //Transition to scene (parameter's value).
        previousScene = this.activeScene;

        //TODO: Can this method "swallow" left over MotionEvents from the previous scene?

        this.activeScene = activeScene;
    }

    public void receiveTouch(MotionEvent event){
        scenes.get(activeScene.getValue()).receiveTouch(event);
    }

    public void update(){
        scenes.get(activeScene.getValue()).update();
    }

    public void draw(Canvas canvas){
        scenes.get(activeScene.getValue()).draw(canvas);
    }

}
