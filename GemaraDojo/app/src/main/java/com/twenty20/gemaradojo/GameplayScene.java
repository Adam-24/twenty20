package com.twenty20.gemaradojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * This class is where the game is actually played.
 * On win/loss/quit "GameOver" is used. (not sure how yet...)
 * **/

public class GameplayScene implements Scene {

    private SceneManager manager;

    private Image background, pauseButton;

    private Tile[] words, definitions;

    private GameOver endOfGame;
    private Popup pause;

    public GameplayScene(SceneManager manager){
        this.manager = manager;
        reset();
    }

    @Override
    public void reset() {
        initButtons();
        initImgs();
        initTiles();
    }

    private void initTiles() {
        words = new Tile[Constants.NUMBER_OF_CRATES];
        definitions = new Tile[Constants.NUMBER_OF_CRATES];

        //TODO: Generate random from list.
        String[] wordsStr = {"Ahmr", "Mahr", "Lohw"};
        String[] definitionsStr = {"Says", "No; Not", "Master"};

        for(int i = 0; i < words.length; i++){
            words[i] = new Tile(new Button(Constants.CURRENT_CONTEXT.getResources(),
                    new Point(),
                    R.drawable.button_back, R.drawable.button_overlay, new Text.Builder(wordsStr[i]).build()));
        }

        for(int i = 0; i < definitions.length; i++){
            definitions[i] = new Tile(new Button(Constants.CURRENT_CONTEXT.getResources(),
                    new Point(),
                    R.drawable.button_back, R.drawable.button_overlay, new Text.Builder(definitionsStr[i]).build()));
        }
    }

    private void initButtons() {
        pauseButton = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pause, new Rect(
                Constants.SCREEN_WIDTH-125,
                25,
                Constants.SCREEN_WIDTH - 25,
                125)
        );
    }

    private void initImgs() {
        background = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gm_background, Constants.SCREEN);
    }

    @Override
    public void terminateTo(SceneEnum nextScene) {
        manager.setScene(nextScene);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if(pause.isOpen()) {
            if(pause.shouldClose(event)) {
                SceneEnum terminateTo = pause.close();
                if(terminateTo != null) terminateTo(terminateTo);
            }
        } else if(endOfGame.isOpen()){
            if(endOfGame.shouldClose(event)) {
                terminateTo(endOfGame.close());
            }
        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                for(Tile tile : words){
                    if(tile.contains((int)event.getX(), (int)event.getY())){
                        tile.select();
                    }
                }
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {

            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (pauseButton.contains((int) event.getX(), (int) event.getY())) {
                    pause.open();
                }

                if (hasWon()) {
                    endOfGame.open();
                    //endOfGame.setScore(Integer.parseInt(moveCount.getText()));
                }
            }
        }
    }

    private boolean hasWon() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas);

        pauseButton.draw(canvas);

        for(Tile tile : words){
            tile.draw(canvas);
        }

        for(Tile tile : definitions){
            tile.draw(canvas);
        }

        if(pause.isOpen())pause.draw(canvas);
        if(endOfGame.isOpen()) endOfGame.draw(canvas);
    }

    @Override
    public void update() {

    }
}
