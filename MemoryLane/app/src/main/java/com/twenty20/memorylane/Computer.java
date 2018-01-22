package com.twenty20.memorylane;

import java.util.Random;

/**
 * Created by Admin on 1/17/2018.
 */

public class Computer {

    private int currentTimeForTurn;

    private long previousTurnMilli;

    private int counter;

    private String turnHistory;
    private Random random;

    private Tile[] tiles;

    private boolean isGoing;

    public Computer(Tile[] allTiles){
        this.tiles = allTiles;
        turnHistory = "";
        counter = 0;
        random = new Random(System.currentTimeMillis());
    }

    private void stopTurn(){
        isGoing = false;
    }

    //TODO: public void pauseTurn(){}

    public void startTurn(){
        isGoing = true;
        counter = 0;
        previousTurnMilli = System.currentTimeMillis() + 500;
        turnHistory += generateNewRandomNumber(turnHistory);
        currentTimeForTurn = 500;
    }

    private String generateNewRandomNumber(final String turnHistory) {
        String next = String.valueOf(random.nextInt(tiles.length));

        if(turnHistory.length() < 2) return next;

        String temp = turnHistory.substring(turnHistory.length()-2, turnHistory.length());

        if(temp.charAt(0) == temp.charAt(1) && temp.charAt(0) == next.charAt(0)) {

            while(next.charAt(0) == temp.charAt(0)){
                next = String.valueOf(random.nextInt(tiles.length));
            }
        }

        return next;
    }

    public void update(){
            boolean isNextTurn = System.currentTimeMillis() >= previousTurnMilli + currentTimeForTurn;

            if (isNextTurn) {
                tiles[Integer.parseInt(turnHistory.substring(counter, ++counter))].touched();
                previousTurnMilli = System.currentTimeMillis();
            }
            if (counter >= turnHistory.length()) stopTurn();
    }

    public boolean isGoing() {
        return isGoing;
    }

    public String getTurnHistory(){
        return turnHistory;
    }
}
