package com.twenty20.memorylane;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class directs control to the scenes in the arrayList.
 * **/

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public SceneEnum activeScene;

    public SceneManager(){
        scenes.add(new SplashScene(this));
        scenes.add(new MainMenuScene(this));
        scenes.add(new GameplayScene(this));
        //scenes.add(new StoreScene(this));  //TODO: Add store scene.

        activeScene = SceneEnum.SPLASHSCREEN;
    }

    public void setScene(SceneEnum activeScene){
        //Transition to scene (parameter's value).
        //previousScene = this.activeScene;

        //TODO: Can this method "swallow" left over MotionEvents from the previous scene?

        //Toast.makeText(Constants.CURRENT_CONTEXT, activeScene.toString(), Toast.LENGTH_SHORT).show(); //To show where user is, DEBUG.
        scenes.get(activeScene.getValue()).reset();
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
