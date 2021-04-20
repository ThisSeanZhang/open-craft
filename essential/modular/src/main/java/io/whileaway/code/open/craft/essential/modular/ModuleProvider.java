package io.whileaway.code.open.craft.essential.modular;

import io.whileaway.code.open.craft.essential.modular.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public abstract class ModuleProvider {

    @Setter
    @Getter
    private ModuleManager manager;

    @Setter
    private ModuleDefine moduleDefine;

    @Setter
    private MethodHandles.Lookup lookup;

    public ModuleProvider(MethodHandles.Lookup lookup) {
        this.lookup = lookup;
    }

    public String name() {
        return this.getClass().getModule().getName();
    }

    private final Map<Class<? extends Service>, Service> services = new HashMap<>();

    public abstract ModuleConfig providerConfig();

    /**
     * prepare Service, register Service prepare to inject other module
     */
    public void prepare(Map<Class<? extends Service>, Service> provideServiceMap) {
        provideServiceMap.forEach(services::put);
    }

    /**
     * start Provider
     */
    public void start() throws ServiceException {

    }


    protected void initProviderConfig(Properties src) throws IllegalAccessException{
        ModuleConfig config = providerConfig();
        if (Objects.isNull(config)) {
            log.info("provider config is null, while ignore!");
            return;
        }
        Class<? extends ModuleConfig> destClass = config.getClass();
        List<Field> fields = Stream.of(destClass.getDeclaredFields())
                .filter(field -> src.containsKey(field.getName()))
                .collect(Collectors.toList());
        MethodHandles.Lookup privateLookupIn = MethodHandles.privateLookupIn(destClass, this.lookup);
        for (Field field : fields) {
                try {
                    VarHandle varHandle = privateLookupIn.findVarHandle(destClass, field.getName(), field.getType());
                    varHandle.set(config, src.get(field.getName()));
                } catch (NoSuchFieldException e) {
                    log.warn(field.getName() + " setting is not supported in " + name() + " provider of " + moduleDefine.name() + " module");
                }
            }

    }

    @SuppressWarnings("unchecked")
    public <T extends Service> T getService(Class<T> serviceType) throws ServiceException {
        Service service = services.get(serviceType);
        if (Objects.nonNull(service)) return (T) service;
        throw new ServiceException.ServiceNotProvidedException(serviceType.getName());
    }
}
