package io.whileaway.code.open.craft.core.gui.lwjgl;

import static java.awt.event.KeyEvent.VK_SPACE;

public class LevelEditorScene extends Scene{

    private boolean changeScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene(GameUIServiceImpl window) {
        super(window);
        System.out.println("Inside LevelEditorScene Scene");
    }

    @Override
    public void update(long dt) {

        System.out.println("" + (1E9f / dt) + "FPS");

        if(!changeScene && KeyListener.isKeyPressed(VK_SPACE)) {
            changeScene = true;
        }

        if (changeScene && timeToChangeScene > 0) {
            timeToChangeScene -=dt;
            window.r -= dt * 5.0f;
            window.g -= dt * 5.0f;
            window.b -= dt * 5.0f;
            window.a -= dt * 5.0f;
        } else if (changeScene) {
           window.changeScene(1);
        }
    }
}

