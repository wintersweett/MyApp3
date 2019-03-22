package com.user.baselibrary.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {

    }
    private IServiceModuleA iServiceModuleA;
    private IServiceModuleB iServiceModuleB;

    public IServiceModuleA getiServiceModuleA() {
        return iServiceModuleA;
    }

    public void setiServiceModuleA(IServiceModuleA iServiceModuleA) {
        this.iServiceModuleA = iServiceModuleA;
    }

    public IServiceModuleB getiServiceModuleB() {
        return iServiceModuleB;
    }

    public void setiServiceModuleB(IServiceModuleB iServiceModuleB) {
        this.iServiceModuleB = iServiceModuleB;
    }
}
