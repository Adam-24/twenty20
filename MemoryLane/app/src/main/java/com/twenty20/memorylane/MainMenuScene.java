package com.twenty20.memorylane;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is the main menu of the game. From here the user can acsess: Game, Options, Store.
 * User can also exit the game if back arrow (button on phone) is pressed.
 * **/

public class MainMenuScene implements Scene {

    private SceneManager manager;

    private Button play_3;
    private Button play_4;
    private Button play_5;

    private Image background;

    MainMenuScene(SceneManager manager){
        this.manager = manager;

        initRects();
    }

    private void initRects() {
        play_3 = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Point(Constants.SCREEN.centerX(), Constants.SCREEN.centerY() - 100),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("3 Tiles").setColor(Color.BLACK).setSize(50).setIsBold().build());

        play_4 = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Point(Constants.SCREEN.centerX(), Constants.SCREEN.centerY() + 75),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("4 Tiles").setColor(Color.BLACK).setSize(50).setIsBold().build());

        play_5 = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Point(Constants.SCREEN.centerX(), Constants.SCREEN.centerY() + 250),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("5 Tiles").setColor(Color.BLACK).setSize(50).setIsBold().build());

        background = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mm_background, Constants.SCREEN);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas);

        play_3.draw(canvas);
        play_4.draw(canvas);
        play_5.draw(canvas);
    }

    @Override
    public void terminateTo(SceneEnum nextScene) {
        manager.setScene(nextScene);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if (play_3.contains((int) event.getX(), (int) event.getY())) {
                Constants.NUMBER_OF_TILES = 3;
                terminateTo(SceneEnum.GAME);
            } else if (play_4.contains((int) event.getX(), (int) event.getY())) {
                Constants.NUMBER_OF_TILES = 4;
                terminateTo(SceneEnum.GAME);
            } else if (play_5.contains((int) event.getX(), (int) event.getY())) {
                Constants.NUMBER_OF_TILES = 5;
                terminateTo(SceneEnum.GAME);
            }
        }
    }

    @Override
    public void reset() {

    }
}
