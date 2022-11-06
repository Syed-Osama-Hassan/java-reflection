package com.syed.osama.hassan.annotations.saver;

import com.syed.osama.hassan.annotations.custom.InitializerClass;
import com.syed.osama.hassan.annotations.custom.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThread() {
        System.out.println("Starting automatic data saving to disk");
    }

}
