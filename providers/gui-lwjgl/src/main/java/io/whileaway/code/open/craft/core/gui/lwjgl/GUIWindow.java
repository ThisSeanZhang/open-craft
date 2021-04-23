package io.whileaway.code.open.craft.core.gui.lwjgl;

import lombok.Getter;
import org.lwjgl.glfw.GLFWVidMode;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GUIWindow {

    @Getter
    private long window;

    public GUIWindow(GUIConfig config) {
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        if (config.isFullScreen()) {
            glfwWindowHint(GLFW_FLOATING, GLFW_TRUE);
            glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
        }
        long monitor = glfwGetPrimaryMonitor();
        GLFWVidMode vidmode = glfwGetVideoMode(monitor);
        int width = (int) (Objects.requireNonNull(vidmode).width() * (config.isFullScreen() ? 1 : 0.8f));
        int height = (int) (vidmode.height() * (config.isFullScreen() ? 1 : 0.8f));
        window = glfwCreateWindow(width, height, "Oh, Open Craft!!!", config.isFullScreen() ? monitor : NULL, NULL);
//        if (GRAB_CURSOR) {
//            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
//        }
        if (window == NULL)
            throw new AssertionError("Failed to create the GLFW window");
    }

    public void show() {
        glfwShowWindow(window);
    }

    public void destroy() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


//    public static class GUIWindowBuilder {
//
//        private GUIWindow window = new GUIWindow();
//
//        public GUIWindowBuilder setConfig(GUIConfig config) {
//
//            return this;
//        }
//
//        public GUIWindow build() {
//            return window;
//        }
//    }
}
