package io.whileaway.code.open.craft.core.gui.lwjgl;

import io.whileaway.code.open.craft.core.gui.GUIModule;
import io.whileaway.code.open.craft.core.gui.GameUIService;
import io.whileaway.code.open.craft.essential.modular.ModuleConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleProvider;
import io.whileaway.code.open.craft.essential.modular.annontion.Provide;
import io.whileaway.code.open.craft.essential.modular.exception.ServiceException;

import java.lang.invoke.MethodHandles;
import java.util.Map;

@Provide(GUIModule.class)
public class LWJGLGUIProvider extends ModuleProvider {
    public LWJGLGUIProvider() {
        super(MethodHandles.lookup());
    }

    @Override
    public ModuleConfig providerConfig() {
        return null;
    }

//    @Override
//    public void prepare(Map<Class<? extends Service>, Service> provideServiceMap) {
//        provideServiceMap.forEach((key, value) -> {
//            System.out.println(key + ": " + value.getClass());
//        });
//    }

    @Override
    public void start() throws ServiceException {
        GameUIService service = getService(GameUIService.class);
        service.createGameUI();
    }
}
