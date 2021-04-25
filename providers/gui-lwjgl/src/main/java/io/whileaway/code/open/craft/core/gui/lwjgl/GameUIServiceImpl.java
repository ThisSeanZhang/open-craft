package io.whileaway.code.open.craft.core.gui.lwjgl;

import io.whileaway.code.open.craft.core.gui.GameUIService;

import io.whileaway.code.open.craft.core.gui.lwjgl.util.Time;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;

@Slf4j
public class GameUIServiceImpl implements GameUIService {

    private long window;
    @Setter
    private GUIConfig config;
    GUIWindow guiWindow;

    protected float r,g,b,a;
    private boolean fadeToBlack = false;

    private static Scene currentScene = null;

    @Override
    public void createGameUI() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        guiWindow.destroy();
    }

    public void changeScene(int newScene) {
        switch (newScene) {
            case 0: currentScene = new LevelEditorScene(this);break;
            case 1: currentScene = new LevelScene(this);break;
            default:
                System.out.print("Unknown Scene");
        }
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

//        // Configure GLFW
//        glfwDefaultWindowHints(); // optional, the current window hints are already the default
//        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
//        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
//
//        // Create the window
//        window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
//        if ( window == NULL )
//            throw new RuntimeException("Failed to create the GLFW window");

        guiWindow = new GUIWindow(config);
        r = g = b = a = 1.0f;
        window = guiWindow.getWindow();
        glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallBack);

        glfwSetKeyCallback(window, KeyListener::keyCallBack);

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
//        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
//            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
//                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
//        });

        // Get the thread stack and push a new frame
//        try ( MemoryStack stack = stackPush() ) {
//            IntBuffer pWidth = stack.mallocInt(1); // int*
//            IntBuffer pHeight = stack.mallocInt(1); // int*
//
//            // Get the window size passed to glfwCreateWindow
//            glfwGetWindowSize(window, pWidth, pHeight);
//
//            // Get the resolution of the primary monitor
//            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//
//            // Center the window
//            glfwSetWindowPos(
//                    window,
//                    (vidmode.width() - pWidth.get(0)) / 2,
//                    (vidmode.height() - pHeight.get(0)) / 2
//            );
//        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
//        glfwShowWindow(window);
        guiWindow.show();

        changeScene(0);
    }

    private void loop() {

        long beginTime = Time.getTime();
        long endTime;
        long dt = -1;
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();


        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            // Set the clear color
            glClearColor(r,g,b,a);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            if (dt > 0) currentScene.update(dt);

            glfwSwapBuffers(window); // swap the color buffers

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    @Override
    public void closeGameUI() {

    }
}
