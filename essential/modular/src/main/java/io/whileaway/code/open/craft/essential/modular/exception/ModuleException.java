package io.whileaway.code.open.craft.essential.modular.exception;

public class ModuleException extends Exception{

    public ModuleException(String message) {
        super(message);
    }

    public static class ModuleNotFoundException extends ModuleException {

        public ModuleNotFoundException(String moduleName) {
            super("in app configured module ,but cant not find [ " + moduleName + " ]");
        }
    }
}
