import io.whileaway.code.open.craft.core.boot.ui.web.BootUIWebProvider;
import io.whileaway.code.open.craft.core.boot.ui.web.service.StartGameServiceImpl;
import io.whileaway.code.open.craft.essential.modular.ModuleProvider;
import io.whileaway.code.open.craft.essential.modular.Service;

module boot.ui.web {
    requires core.boot.ui;

    provides ModuleProvider with BootUIWebProvider;
    provides Service with StartGameServiceImpl;

}