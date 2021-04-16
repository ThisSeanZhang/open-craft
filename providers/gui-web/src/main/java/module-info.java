import io.whileaway.code.open.craft.core.gui.web.GUIWebProvider;
import io.whileaway.code.open.craft.core.gui.web.service.StartGameServiceImpl;
import io.whileaway.code.open.craft.essential.modular.ModuleProvider;
import io.whileaway.code.open.craft.essential.modular.Service;

module gui.web {
    requires core.gui;

    provides ModuleProvider with GUIWebProvider;
    provides Service with StartGameServiceImpl;

}