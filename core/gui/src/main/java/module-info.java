import io.whileaway.code.open.craft.core.gui.GUIModule;
import io.whileaway.code.open.craft.essential.modular.ModuleDefine;

module core.gui {
    requires transitive io.whileaway.code.open.craft.essential.modular;
    exports io.whileaway.code.open.craft.core.gui;
    provides ModuleDefine with GUIModule;
}