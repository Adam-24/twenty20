package com.twenty20.memorylane;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * This class is an window that pops up when the player wins, loses, or ends the game.
 * When closed, returns control back to the scene.
 * **/

public class GameOver implements Popup {

    private Image image;

    private Button playAgain;
    private Button back;

    private SceneEnum closeTo;
    private boolean isOpen;

    private Text score;

    public GameOver(){
        isOpen = false;

        image = new Image(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gm_win,
                new Rect(
                Constants.SCREEN_WIDTH/4,
                Constants.SCREEN_HEIGHT/4,
                Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4,
                Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/4)
        );

        playAgain = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Rect(image.getRect().left, image.getRect().bottom - 50, image.getRect().centerX() - 15, image.getRect().bottom + 50),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("Play Again").setColor(Color.BLACK).setSize(50).build());

        back = new Button(Constants.CURRENT_CONTEXT.getResources(),
                new Rect(image.getRect().centerX() + 15, image.getRect().bottom - 50, image.getRect().right, image.getRect().bottom + 50),
                R.drawable.button_back, R.drawable.button_overlay,
                new Text.Builder("Back").setColor(Color.BLACK).setSize(50).build());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(50, 0,0,0));
        canvas.drawRect(Constants.SCREEN, paint);

        image.draw(canvas);
        score.draw(canvas);
        playAgain.draw(canvas);
        back.draw(canvas);
    }

    @Override
    public boolean shouldClose(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(playAgain.contains((int) event.getX(), (int) event.getY())){
                closeTo = SceneEnum.GAME;
                return true;
            }

            if(back.contains((int) event.getX(), (int) event.getY())) {
                closeTo = SceneEnum.MAINMENU;
                return true;
            }
        }
        return false;
    }

    @Override
    public void open() {
        closeTo = null;
        isOpen = true;
    }

    public void setScore(int score){
        String compliments = "Very Good!";
        if(score > 15) compliments = "Awesome!";
        else if(score > 11) compliments = "Excellent!";
        else if(score > 6) compliments = "Great!";
        else if(score < 3) compliments = "Nice Try!";

        this.score = new Text.Builder(String.valueOf(score) + " turns, " + compliments)
            .setSize(100)
            .setColor(Color.WHITE)
            .setIsBold()
            .setRect(new Rect(
                    image.getRect().left,
                    image.getRect().centerY(),
                    image.getRect().right,
                    image.getRect().bottom - image.getRect().height()/4))
            .build();
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public SceneEnum close() {
        isOpen = false;
        return closeTo;
    }

    @Override
    public void update() {

    }
}
