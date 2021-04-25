package io.whileaway.code.open.craft.core.gui.lwjgl.util;

public class Time {

    public static long timeStarted = System.nanoTime();

    public static long getTime() {
        return System.nanoTime() - timeStarted;
    }
}
