package io.whileaway.code.open.craft.core.boot;

import io.whileaway.code.open.craft.core.boot.config.AppConfigLoader;
import io.whileaway.code.open.craft.essential.modular.AppConfig;
import io.whileaway.code.open.craft.essential.modular.ModuleManager;
import io.whileaway.code.open.craft.essential.util.BootPathUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class OpenCraftBoot {

    public static void main(String[] args){
        AppConfigLoader applicationConfigLoader = new AppConfigLoader();
        ModuleManager manager = new ModuleManager();
        try {
            File path = BootPathUtil.getPath(OpenCraftBoot.class);
            manager.setRuntimePath(path.toPath());
            AppConfig config = applicationConfigLoader.load();
            manager.init(config);

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }
}
