package com.twenty20.boatloaders;

import android.graphics.Bitmap;
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

    //TODO: Create sprite class file.
    private Image background;
    private Image[] water;

    private AnimationManager animationManager;

    private Column[] columns;
    private Column columnReference;
    private Crate crateReference;

    private GameOver endOfGame;
    private Popup pause;

    private Image pauseButton;

    private Text moveCount;

    public GameplayScene(SceneManager manager){
        this.manager = manager;
        reset();
    }

    @Override
    public void reset() {
        endOfGame = new GameOver();
        pause = new Pause();
        moveCount = new Text.Builder("0")
                .setSize(100)
                .setColor(Color.WHITE)
                .setIsBold()
                .setPoint(new Point(10, 10))
                .build();

        initCrates();
        initButtons();
        initImgs();
        initAnimations();
    }

    private void initAnimations() {
        Animation waterAnim = new Animation(new Bitmap[]{water[0].getImage(), water[1].getImage(), water[2].getImage()}, 1f);
        animationManager = new AnimationManager(new Animation[]{waterAnim});
    }

    private void initButtons() {
        pauseButton = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pause, new Rect(
                Constants.SCREEN_WIDTH-125,
                25,
                Constants.SCREEN_WIDTH - 25,
                125)
        );
    }

    private void initCrates() {
        columns = new Column[3];

        columns[0] = new Column( new Rect(0, 0, (int)(Constants.SCREEN_WIDTH * .25), Constants.SCREEN_HEIGHT));
        columns[1] = new Column( new Rect((int)(Constants.SCREEN_WIDTH * .25), 0, (int)(Constants.SCREEN_WIDTH * .75), Constants.SCREEN_HEIGHT));
        columns[2] = new Column( new Rect((int)(Constants.SCREEN_WIDTH * .75), 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

        final int locationX = 0;
        final int locationY = 0;

        final int initialSize = 100;
        final int growth = 40;

        for(int i = Constants.NUMBER_OF_CRATES; i > 0; i--){
            columns[0].add(new Crate( new MovableImage(Constants.CURRENT_CONTEXT.getResources(), R.drawable.crate, new Rect(
                    locationX,
                    locationY,
                    locationX + initialSize + (i*growth),
                    locationY + initialSize
            ))));
        }

        //TODO: SYS.
        System.out.println(columns[0].numberOfCratesTEMP());
    }

    private void initImgs() {
        background = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gm_background, Constants.SCREEN);

        water = new Image[3];

        water[0] = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wave_sprite_1,
                new Rect(Constants.SCREEN_WIDTH/4, (int) (Constants.SCREEN_HEIGHT * .75), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT)
        );

        water[1] = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wave_sprite_2,
                new Rect(Constants.SCREEN_WIDTH/4, (int) (Constants.SCREEN_HEIGHT * .75), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT)
        );

        water[2] = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wave_sprite_3,
                new Rect(Constants.SCREEN_WIDTH/4, (int) (Constants.SCREEN_HEIGHT * .75), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT)
        );

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
                for (Column column : columns) {
                    if (column.contains((int) event.getX(), (int) event.getY())) {
                        crateReference = column.pickUp();
                        column.removeTopCrate();
                        columnReference = column;
                    }
                    //TODO: SYS
                    System.out.println("Column holds: "+ column.numberOfCratesTEMP());
                }
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (crateReference != null) {
                    crateReference.updatePosition((int) event.getX(), (int) event.getY());
                }
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                if(crateReference != null) {
                    for (Column column : columns) {
                        if (column.contains((int) event.getX(), (int) event.getY())) {
                            if (column.canPlaceCrate(crateReference)) {
                                column.add(crateReference);
                                crateReference = null;
                                if(columnReference != column) moveCount.setText(String.valueOf((Integer.parseInt(moveCount.getText())+1)));
                            } else {
                                //System.out.println("Did not work, putting back.");
                                columnReference.add(crateReference);
                                crateReference = null;
                            }
                        }
                    }
                }

                if (pauseButton.contains((int) event.getX(), (int) event.getY())) {
                    pause.open();
                }

                if (hasWon()) {
                    endOfGame.open();
                    endOfGame.setScore(Integer.parseInt(moveCount.getText()));
                }
            }
        }
    }

    private boolean hasWon() {
        return columns[2].isFull(); //Possibly pass in number, if not constant.
    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas);
        //water.draw(canvas);
        animationManager.draw(canvas, water[0].getRect()); //TODO: fix.

        pauseButton.draw(canvas);

        moveCount.draw(canvas);

        for (Column column : columns) {
            column.draw(canvas);
        }

        if (crateReference != null) crateReference.draw(canvas);

        if(pause.isOpen())pause.draw(canvas);
        if(endOfGame.isOpen()) endOfGame.draw(canvas);
    }

    @Override
    public void update() {
        animationManager.playAnim(0);
        animationManager.update();
    }
}
