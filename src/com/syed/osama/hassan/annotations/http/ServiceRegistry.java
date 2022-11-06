package com.syed.osama.hassan.annotations.http;

import com.syed.osama.hassan.annotations.custom.InitializerClass;
import com.syed.osama.hassan.annotations.custom.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService() {
        System.out.println("Service registering success");
    }

}
