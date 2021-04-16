package io.whileaway.code.open.craft.core.gui;

import io.whileaway.code.open.craft.essential.modular.ModuleDefine;
import io.whileaway.code.open.craft.essential.modular.Service;

import java.util.Set;

public class GUIModule extends ModuleDefine {

    @Override
    public Set<Class<? extends Service>> services() {
        return Set.of(GUIController.class, StartGameService.class);
    }
}
