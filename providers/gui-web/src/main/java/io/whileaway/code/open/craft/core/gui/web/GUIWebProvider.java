package io.whileaway.code.open.craft.core.gui.web;

import io.whileaway.code.open.craft.core.gui.GUIModule;
import io.whileaway.code.open.craft.core.gui.web.config.GUIConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleProvider;
import io.whileaway.code.open.craft.essential.modular.Service;
import io.whileaway.code.open.craft.essential.modular.annontion.Provide;

import java.lang.invoke.MethodHandles;
import java.util.Map;

@Provide(GUIModule.class)
public class GUIWebProvider extends ModuleProvider {

    private final GUIConfig config = new GUIConfig();

    public GUIWebProvider() {
        super(MethodHandles.lookup());
    }

    @Override
    public ModuleConfig providerConfig() {
        return config;
    }

    @Override
    public void prepare(Map<Class<? extends Service>, Service> provideServiceMap) {
        provideServiceMap.forEach((key, value) -> {
            System.out.println(key + ": " + value.getClass());
        });
        System.out.println("get Config name: " + config.getName());
    }
}
