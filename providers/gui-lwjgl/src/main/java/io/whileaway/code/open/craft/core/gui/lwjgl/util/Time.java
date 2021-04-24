package io.whileaway.code.open.craft.core.gui.lwjgl.util;

public class Time {

    public static float timeStarted = System.nanoTime();

    public static float getTime() {
        return (float) ((System.nanoTime() - timeStarted) * 1E-9);
    }
}
