package com.syed.osama.hassan.annotations.config;

import com.syed.osama.hassan.annotations.custom.InitializerClass;
import com.syed.osama.hassan.annotations.custom.InitializerMethod;

@InitializerClass
public class ConfigLoader {

    @InitializerMethod
    public void loadAllConfig() {
        System.out.println("Loading all configuration files");
    }
}
