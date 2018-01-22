package com.twenty20.memorylane;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is where the game is actually played.
 * On win/loss/quit "GameOver" is used. (not sure how yet...)
 * **/

public class GameplayScene implements Scene {

    private SceneManager manager;

    private GameOver endOfGame;
    private Popup pause;

    private Image background;
    private Image pauseButton;

    private Text scoreCount;

    private Computer computer;
    private Tile[] tiles;

    private String playerTurnHistory;

    public GameplayScene(SceneManager manager){
        this.manager = manager;
        reset();
    }

    @Override
    public void reset() {
        initButtons();
        initImgs();
        initTiles();

        playerTurnHistory = "";
        computer = new Computer(tiles);
        endOfGame = new GameOver();
        pause = new Pause();
        scoreCount = new Text.Builder("0")
                .setSize(100)
                .setColor(Color.WHITE)
                .setIsBold()
                .setPoint(new Point(10, 10))
                .build();


    }

    private void initButtons() {
        pauseButton = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pause, new Rect(
                Constants.SCREEN_WIDTH-125,
                25,
                Constants.SCREEN_WIDTH - 25,
                125)
        );
    }

    private void initTiles() {
        tiles = new Tile[Constants.NUMBER_OF_TILES];

        Point center = new Point(Constants.SCREEN.centerX(), Constants.SCREEN.centerY());

        //TODO: Temp.
        Point[] tileCenters = new Point[tiles.length];
        final double angle = 360f / Constants.NUMBER_OF_TILES;

        final int radius = Constants.SCREEN.height()/4;

        for(int i = 0; i < tileCenters.length; i++){
            tileCenters[i] = new Point(
                    (int)(radius * Math.cos(Math.toRadians((angle * i) + (-90))) + center.x),
                    (int)(radius * Math.sin(Math.toRadians((angle * i) + (-90))) + center.y)
            );
        }

        int size = radius/3;
        int[] drawable = new int[]{
                R.drawable.tile_5,
                R.drawable.tile_2,
                R.drawable.tile_3,
                R.drawable.tile_4,
                R.drawable.tile_1
        };

        int[] sounds = new int[]{
                R.raw.orb_sound_1,
                R.raw.orb_sound_2,
                R.raw.orb_sound_3,
                R.raw.orb_sound_4,
                R.raw.orb_sound_5
        };

        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile(new Image(Constants.CURRENT_CONTEXT.getResources(), drawable[i],
                    new Rect(
                            tileCenters[i].x - size,
                            tileCenters[i].y - size,
                            tileCenters[i].x + size,
                            tileCenters[i].y + size
                    )), R.drawable.tile_overlay, sounds[i]);
        }
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
        } else if (endOfGame.isOpen()) {
            if (endOfGame.shouldClose(event)) {
                terminateTo(endOfGame.close());
            }
        } else if(! computer.isGoing()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {

                for(int i = 0; i < tiles.length; i++){
                    if(tiles[i].contains((int) event.getX(), (int) event.getY())){

                        playerTurnHistory += String.valueOf(i);
                        tiles[i].touched();

                        if(isGameOver()){
                            endOfGame.open();
                            endOfGame.setScore(Integer.parseInt(scoreCount.getText()));
                        } else if(playerTurnHistory.length() == computer.getTurnHistory().length()){
                            scoreCount.setText(String.valueOf(Integer.parseInt(scoreCount.getText())+1));
                            playerTurnHistory = "";
                            computer.startTurn();
                        }
                    }
                }
            }
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {

            if (pauseButton.contains((int) event.getX(), (int) event.getY())) {
                //TODO: FIX? temp?
                if(! computer.isGoing())
                pause.open();
            }
        }

    }

    private boolean isGameOver() {
        return ! playerTurnHistory.equals(computer.getTurnHistory().substring(0, playerTurnHistory.length()));
    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas);
        pauseButton.draw(canvas);

        scoreCount.draw(canvas);

        for(Tile tile : tiles){
            tile.draw(canvas);
        }

        if(pause.isOpen())pause.draw(canvas);
        if(endOfGame.isOpen()) endOfGame.draw(canvas);
    }

    @Override
    public void update() {
        if(computer.isGoing()) computer.update();
        if(computer.getTurnHistory().length() == 0) computer.startTurn();
    }
}
