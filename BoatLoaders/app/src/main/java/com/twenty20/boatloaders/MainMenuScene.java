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

    private Rect title;
    private Bitmap title_image;

    MainMenuScene(SceneManager manager){
        this.manager = manager;

        initRects();
    }

    private void initRects() {

        play_3 = new Button(
                "3 Crates", Color.BLACK, 50, //350 x 150
                new Rect(Constants.SCREEN.centerX() - 175, Constants.SCREEN.centerY() - 150, Constants.SCREEN.centerX() + 175, Constants.SCREEN.centerY()),
                Constants.CURRENT_CONTEXT.getResources(),
                R.drawable.button_back,
                R.drawable.button_overlay
        );

        play_5 = new Button(
                "5 Crates", Color.BLACK, 50,
                new Rect(Constants.SCREEN.centerX() - 175, Constants.SCREEN.centerY() + 25, Constants.SCREEN.centerX() + 175, Constants.SCREEN.centerY() + 175),
                Constants.CURRENT_CONTEXT.getResources(),
                R.drawable.button_back,
                R.drawable.button_overlay
        );

        play_8 = new Button(
                "8 Crates", Color.BLACK, 50,
                new Rect(Constants.SCREEN.centerX() - 175, Constants.SCREEN.centerY() + 200, Constants.SCREEN.centerX() + 175, Constants.SCREEN.centerY() + 350),
                Constants.CURRENT_CONTEXT.getResources(),
                R.drawable.button_back,
                R.drawable.button_overlay
        );

        background_image = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mm_background);
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        title_image = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.game_title);
        //w 400, h 200
        title = new Rect(Constants.SCREEN.centerX() - 700,
                Constants.SCREEN.centerY() - 400,
                Constants.SCREEN.centerX() + 700,
                Constants.SCREEN.centerY() - 200);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(background_image, null, background, new Paint());
        canvas.drawBitmap(title_image, null, title, new Paint());

        play_3.draw(canvas);
        play_5.draw(canvas);
        play_8.draw(canvas);
    }

    @Override
    public void terminateTo(SceneEnum nextScene) {
        manager.setScene(nextScene);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if (play_3.contains((int) event.getX(), (int) event.getY())) {
                Constants.NUMBER_OF_CRATES = 3;
                terminateTo(SceneEnum.GAME);
            } else if (play_5.contains((int) event.getX(), (int) event.getY())) {
                Constants.NUMBER_OF_CRATES = 5;
                terminateTo(SceneEnum.GAME);
            } else if (play_8.contains((int) event.getX(), (int) event.getY())) {
                Constants.NUMBER_OF_CRATES = 8;
                terminateTo(SceneEnum.GAME);
            }
        }
    }

    @Override
    public void reset() {

    }
}
