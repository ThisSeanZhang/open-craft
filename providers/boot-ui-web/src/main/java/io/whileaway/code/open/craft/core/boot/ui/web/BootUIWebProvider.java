package io.whileaway.code.open.craft.core.boot.ui.web;

import io.whileaway.code.open.craft.core.boot.ui.BootUIModule;
import io.whileaway.code.open.craft.core.boot.ui.web.config.GUIConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleProvider;
import io.whileaway.code.open.craft.essential.modular.Service;
import io.whileaway.code.open.craft.essential.modular.annontion.Provide;

import java.lang.invoke.MethodHandles;
import java.util.Map;

@Provide(BootUIModule.class)
public class BootUIWebProvider extends ModuleProvider {

    private final GUIConfig config = new GUIConfig();

    public BootUIWebProvider() {
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
