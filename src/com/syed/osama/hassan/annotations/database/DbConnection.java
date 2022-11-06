package com.syed.osama.hassan.annotations.database;

import com.syed.osama.hassan.annotations.custom.InitializerClass;
import com.syed.osama.hassan.annotations.custom.InitializerMethod;
import com.syed.osama.hassan.annotations.custom.RetryOperation;

import java.io.IOException;

@InitializerClass
public class DbConnection {
    private int retryCount = 5;

    @InitializerMethod
    @RetryOperation(numberOfRetries = 10, durationBetweenRetriesMs = 1000,
            retryExceptions = IOException.class,
            failureMessage = "Connection to db 1 failed after retries")
    public void connectToDb1() throws IOException {
        if(retryCount > 0) {
            retryCount--;
            throw new IOException("Unable to connect with db");
        }
        System.out.println("Connecting to database 1");
    }

    @InitializerMethod
    public void connectToDb2() {
        System.out.println("Connecting to database 2");
    }
}
