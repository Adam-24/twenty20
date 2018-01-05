package com.twenty20.boatloaders;

/**
 * This enum class makes life less confusing for most of us.
 * **/

public enum SceneEnum {
    SPLASHSCREEN(0), MAINMENU(1), STORE(2), GAME(3);

    private final int value;

    SceneEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
