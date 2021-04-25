package io.whileaway.code.open.craft.core.gui.lwjgl;

public class LevelScene extends Scene{

    public LevelScene(GameUIServiceImpl window) {
        super(window);
        System.out.println("Inside LevelScene Scene");
        window.r = 1;
        window.g = 1;
        window.b = 1;
        window.a = 1;
    }

    @Override
    public void update(long dt) {
    }
}

