package com.twenty20.boatloaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is the main menu of the game. From here the user can acsess: Game, Options, Store.
 * User can also exit the game if back arrow (button on phone) is pressed.
 * **/

public class MainMenuScene implements Scene {

    private SceneManager manager;

    private Button play_3;
    private Button play_5;
    private Button play_8;

    private Rect background;
    private Bitmap background_image;

    MainMenuScene(SceneManager manager){
        this.manager = manager;

        initRects();
    }

    private void initRects() {

        play_3 = new Button(
                "Play (3 crates)", Color.BLACK, 50,
                new Rect(Constants.SCREEN_WIDTH-250, Constants.SCREEN_HEIGHT-125, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT),
                Constants.CURRENT_CONTEXT.getResources(),
                R.drawable.button_back,
                R.drawable.button_overlay
        );

        background_image = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mm_background);
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(background_image, null, background, new Paint());

        play_3.draw(canvas);
    }

    @Override
    public void terminateTo(SceneEnum nextScene) {
        manager.setScene(nextScene);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP && play_3.contains((int)event.getX(), (int)event.getY())){
            Constants.NUMBER_OF_CRATES = 3;
            terminateTo(SceneEnum.GAME);
        }
    }
}
