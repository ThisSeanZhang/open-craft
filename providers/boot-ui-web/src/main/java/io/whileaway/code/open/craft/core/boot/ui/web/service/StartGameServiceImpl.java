package io.whileaway.code.open.craft.core.boot.ui.web.service;


import io.whileaway.code.open.craft.core.boot.ui.GUIController;

public class StartGameServiceImpl implements GUIController {

    private String aaa;

    @Override
    public void start() {
        System.out.println("start");
    }

//    public static StartGameServiceImpl provider() {
//        return new StartGameServiceImpl();
//    }
}
