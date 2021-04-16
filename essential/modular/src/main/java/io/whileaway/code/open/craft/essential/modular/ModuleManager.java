package io.whileaway.code.open.craft.essential.modular;

import io.whileaway.code.open.craft.essential.modular.exception.ModuleException;
import io.whileaway.code.open.craft.essential.modular.exception.ProvideException;
import io.whileaway.code.open.craft.essential.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ModuleManager {

    private final Map<String, ModuleDefine> loadedModules = new ConcurrentHashMap<>();

    public void init(AppConfig config) throws ModuleException, ProvideException, IllegalAccessException {
        List<String> withoutConfigModule = config.moduleList();
        ServiceLoader<ModuleDefine> moduleDefines = ServiceLoader.load(ModuleDefine.class);
        ServiceLoader<ModuleProvider> providersLoader = ServiceLoader.load(ModuleProvider.class);
        ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);
        List<ModuleDefine> allModule = moduleDefines.stream().map(ServiceLoader.Provider::get)
                .filter(config::hasModuleConfig)
                .peek(module -> withoutConfigModule.remove(module.name()))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(withoutConfigModule)) throw new ModuleException.ModuleNotFoundException(String.join(",", withoutConfigModule));

        for (ModuleDefine module: allModule) {
            module.prepare(this, config, providersLoader, serviceLoader);
            loadedModules.put(module.name(), module);
        }

    }
}
