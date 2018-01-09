package com.twenty20.boatloaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * This class is where the game is actually played.
 * On win/loss/quit "GameOver" is used. (not sure how yet...)
 * **/

public class GameplayScene implements Scene {

    private SceneManager manager;

    private Bitmap background_image;
    private Rect background;

    private Crate crate;

    private Column[] columns;

    private Button pauseButton;

    public GameplayScene(SceneManager manager){
        this.manager = manager;

        initCrates();
        initButtons();
        initImgs();
    }

    private void initButtons() {
        pauseButton = new Button( new Rect(
                Constants.SCREEN_WIDTH-100,
                0,
                Constants.SCREEN_WIDTH,
                100),
                Constants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
    }

    private void initCrates() {
        columns = new Column[3];

        //TEMP:
        Rect[] temp = new Rect[3];
        temp[0] = new Rect(0, 0, Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);
        temp[1] = new Rect(temp[0].right, 0, temp[0].right + Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT);
        temp[2] = new Rect(temp[1].right, 0, temp[1].right + Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);

        for (int i = 0; i < 3; i++) {
            columns[i] = new Column(temp[i]);
        }

        final int locationX = 0;
        final int locationY = 0;

        final int initialSize = 100;
        final int growth = 40;

        for(int i = Constants.NUMBER_OF_CRATES; i > 0; i--){
            columns[0].add(new Crate( new Rect(
                    locationX,
                    locationY,
                    locationX + initialSize + (i*growth),
                    locationY + initialSize
            ), columns[0]));
        }
    }

    private void initImgs() {
        background_image = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gm_background);
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    @Override
    public void terminateTo(SceneEnum nextScene) {
        manager.setScene(nextScene);
    }

    private void reset() {
        initCrates();
        initButtons();
        initImgs();
    }

    @Override
    public void receiveTouch(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Column column : columns) {
                if (column.contains((int) event.getX(), (int) event.getY())){
                    crate = column.pickUp();
                    if(crate != null) crate.pickedUp(column);
                }
            }
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            if ((crate != null) && crate.isMoving()){
                crate.reposition((int) event.getX(), (int) event.getY());
            }
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            for (Column column : columns) {
                if ((crate != null) && column.contains((int) event.getX(), (int) event.getY())){
                    if(! column.placeCrate(crate)){
                        //put back.
                        System.out.println("Did not work, putting back.");
                        crate.pickedUpFrom().placeCrate(crate);
                    }
                    crate = null;
                }
            }

            if(hasWon()){
                //TODO: Win object == visible
                Toast.makeText(Constants.CURRENT_CONTEXT, "Win!", Toast.LENGTH_LONG).show();
                terminateTo(SceneEnum.MAINMENU);
            }

            if(pauseButton.contains((int) event.getX(), (int) event.getY())) {
                terminateTo(SceneEnum.MAINMENU); // TODO: for now to Main menu, later, view "pause" object.
                reset(); // on leaving game screen (not saving games yet...)
            }

            outputInfo(event);
        }
    }

    private boolean hasWon() {
        return (columns[2].numberOfCratesHeld() == Constants.NUMBER_OF_CRATES);
    }

    private void outputInfo(MotionEvent event) {
        for (int i = 0; i < 3; i++) {
            if (columns[i].contains((int) event.getX(), (int) event.getY())){
                System.out.println("Let go @: "+ (i+1));
            }
        }

        System.out.println("Col 1: "+columns[0].numberOfCratesHeld());
        System.out.println("Col 2: "+columns[1].numberOfCratesHeld());
        System.out.println("Col 3: "+columns[2].numberOfCratesHeld());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        canvas.drawRect(Constants.SCREEN, paint);
        canvas.drawBitmap(background_image, null, background, new Paint());

        pauseButton.draw(canvas);

        for (Column column : columns) {
            column.draw(canvas);
        }

        if (crate != null) crate.draw(canvas);
    }

    @Override
    public void update() {

    }
}
