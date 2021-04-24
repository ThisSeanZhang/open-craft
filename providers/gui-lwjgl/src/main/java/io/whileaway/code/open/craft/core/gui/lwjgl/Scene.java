package io.whileaway.code.open.craft.core.gui.lwjgl;

public abstract class Scene {

    protected GameUIServiceImpl window;

    public Scene(GameUIServiceImpl window) {
        this.window = window;
    }

    public abstract void update(float dt);
}
