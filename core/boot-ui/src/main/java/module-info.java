import io.whileaway.code.open.craft.core.boot.ui.BootUIModule;
import io.whileaway.code.open.craft.essential.modular.ModuleDefine;

module core.boot.ui {
    requires transitive io.whileaway.code.open.craft.essential.modular;
    exports io.whileaway.code.open.craft.core.boot.ui;
    provides ModuleDefine with BootUIModule;

}