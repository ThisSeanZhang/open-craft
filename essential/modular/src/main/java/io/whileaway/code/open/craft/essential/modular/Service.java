package io.whileaway.code.open.craft.essential.modular;

public interface Service {


    static Service provider() {

        return new Service() {};
    }
}
