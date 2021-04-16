package io.whileaway.code.open.craft.core.boot;

import io.whileaway.code.open.craft.core.boot.config.AppConfigLoader;
import io.whileaway.code.open.craft.essential.modular.AppConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenCraftBoot {

    public static void main(String[] args){

        AppConfigLoader applicationConfigLoader = new AppConfigLoader();
        ModuleManager manager = new ModuleManager();
        try {
            AppConfig config = applicationConfigLoader.load();
            manager.init(config);

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }
}
