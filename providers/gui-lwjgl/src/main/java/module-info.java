import io.whileaway.code.open.craft.core.gui.lwjgl.GameUIServiceImpl;
import io.whileaway.code.open.craft.core.gui.lwjgl.LWJGLGUIProvider;
import io.whileaway.code.open.craft.essential.modular.ModuleProvider;
import io.whileaway.code.open.craft.essential.modular.Service;

module gui.lwjgl {
    requires core.gui;
    requires org.lwjgl.natives;
    requires org.lwjgl;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;

    provides Service with GameUIServiceImpl;
    provides ModuleProvider with LWJGLGUIProvider;
}