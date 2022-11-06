package com.syed.osama.hassan.methods.invocation.database;

public class DatabaseClient {

    public boolean storeDate(String data) {
        System.out.println(String.format("Data: %s successfully stored in the database", data));
        return true;
    }

}
