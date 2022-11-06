package com.syed.osama.hassan.dynamicproxy.external.impl;

import com.syed.osama.hassan.dynamicproxy.external.DatabaseReader;

public class DatabaseReaderImpl implements DatabaseReader {

    @Override
    public int countRowsInTable(String tableName) throws InterruptedException {
        System.out.println("Counting rows in table " + tableName);
        Thread.sleep(1000);
        return 50;
    }

    @Override
    public String[] readRow(String sqlQuery) throws InterruptedException {
        System.out.println("Executing sql query " + sqlQuery);
        Thread.sleep(1000);
        return new String[]{"column1", "column2", "column3"};
    }
}
