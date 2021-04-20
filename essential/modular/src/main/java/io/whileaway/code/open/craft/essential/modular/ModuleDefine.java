package io.whileaway.code.open.craft.essential.modular;

import io.whileaway.code.open.craft.essential.modular.annontion.Provide;
import io.whileaway.code.open.craft.essential.modular.exception.ProvideException;
import io.whileaway.code.open.craft.essential.modular.exception.ServiceException;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ModuleDefine {


    @Getter
    private String name;

    private ModuleProvider loadedProvider = null;


    public ModuleDefine() {
        this.name = this.getClass().getModule().getName();
    }
    public String name() {
        return name;
    }

    /**
     * Module Services for check Provider
     * @return Module services
     */
    public abstract Set<Class<? extends Service>> services();

    public void prepare(ModuleManager manager, AppConfig appConfig, ServiceLoader<ModuleProvider> providersLoader, ServiceLoader<Service> serviceLoader) throws ProvideException, IllegalAccessException, ServiceException {
        AppConfig.ModuleConfig moduleConfig = appConfig.getModuleConfig(name);
        List<ModuleProvider> providers = providersLoader.stream()
                .filter(this::isMyProvider)
                .map(ServiceLoader.Provider::get)
                .filter(provider -> moduleConfig.hasProviderConfig(provider.name()))
                .collect(Collectors.toList());
        if (providers.size() > 1) throw new ProvideException.DuplicateProviderException(name(), providers);
        if (providers.size() == 0) throw new ProvideException.ProviderNotFoundException(name());

        ModuleProvider provider = providers.get(0);
        provider.setManager(manager);
        provider.setModuleDefine(this);

        provider.initProviderConfig(moduleConfig.getProviderConfig(provider.name()));

//        List<Service> allService = serviceLoader.stream()
//                .map(ServiceLoader.Provider::get)
//                .collect(Collectors.toList());
//        services().stream()
//                .collect(Collectors.toMap(Function.identity(), s -> s.isAssignableFrom())
        Map<Class<? extends Service>, Service> provideServiceMap = serviceLoader.stream()
//                .filter(this::isMyService)
                // service的包名是否包含provider的包名 如果包含则表示是这个provider的服务
                .filter(s -> s.type().getPackageName().startsWith(provider.getClass().getPackageName()))
                .map(ServiceLoader.Provider::get)
                .filter(s -> s.getClass().getInterfaces().length > 0)
                .collect(HashMap::new, (map, service) -> {
//                    System.out.println(service.getClass().getInterfaces());
                    Stream.of(service.getClass().getInterfaces())
                            .filter(Service.class::isAssignableFrom)
                            .forEach(i -> map.put((Class<Service>)i, service));
                } , HashMap::putAll);
//        Collectors.toMap(Service::getClass, Function.identity());
        loadedProvider = provider;
        loadedProvider.prepare(provideServiceMap);
        loadedProvider.start();
    }

    private boolean isMyProvider(ServiceLoader.Provider<ModuleProvider> provider) {
        return provider.type().isAnnotationPresent(Provide.class) && Objects.equals(provider.type().getAnnotation(Provide.class).value(), this.getClass());
    }

    private boolean isMyService(ServiceLoader.Provider<Service> provider) {
        return !services().isEmpty() && services().contains(provider.type());
    }
}
