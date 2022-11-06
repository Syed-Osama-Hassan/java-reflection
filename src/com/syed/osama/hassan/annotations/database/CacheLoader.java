package com.syed.osama.hassan.annotations.database;

import com.syed.osama.hassan.annotations.custom.InitializerClass;
import com.syed.osama.hassan.annotations.custom.InitializerMethod;

@InitializerClass
public class CacheLoader {

    @InitializerMethod
    public void loadCache() {
        System.out.println("Loading data from cache");
    }

    public void reload() {
        System.out.println("Reloading cache");
    }

}
