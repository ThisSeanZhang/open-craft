package io.whileaway.code.open.craft.core.gui.lwjgl;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static final int KEY_NUMS = 350;
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[KEY_NUMS];

    private KeyListener(){}

    public static KeyListener get () {
        if (instance == null) {
            instance = new KeyListener();
        }
        return instance;
    }
    public static void keyCallBack(long window, int key, int scanCode, int action, int mods) {
        if ( key >= KEY_NUMS|| key < 0) return;
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int key) {
        return key >= 0 && key <= KEY_NUMS && get().keyPressed[key];
    }
}
