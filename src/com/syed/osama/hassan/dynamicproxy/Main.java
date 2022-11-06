package com.syed.osama.hassan.dynamicproxy;

import com.syed.osama.hassan.dynamicproxy.external.DatabaseReader;
import com.syed.osama.hassan.dynamicproxy.external.HttpClient;
import com.syed.osama.hassan.dynamicproxy.external.impl.DatabaseReaderImpl;
import com.syed.osama.hassan.dynamicproxy.external.impl.HttpClientImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        HttpClient client = createProxyObject(new HttpClientImp());
        useHttpClient(client);
        DatabaseReader databaseReader = createProxyObject(new DatabaseReaderImpl());
        useDatabaseReader(databaseReader);
    }

    public static void useHttpClient(HttpClient httpClient) {
        httpClient.initialize();
        String response = httpClient.sendRequest("Some request");
        System.out.println("Response: " + response);
    }

    public static void useDatabaseReader(DatabaseReader databaseReader) throws InterruptedException {
        int rows = databaseReader.countRowsInTable("person");
        System.out.println(String.format("There are %s rows in table", rows));
        String [] data = databaseReader.readRow("SELECT * FROM person");
        System.out.println("Received: " + String.join(",", data));
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxyObject(Object originalObject) {
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();
        TimeMeasuringProxyHandler timeMeasuringProxyHandler = new TimeMeasuringProxyHandler(originalObject);
        return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), interfaces, timeMeasuringProxyHandler);
    }

    public static class TimeMeasuringProxyHandler implements InvocationHandler {
        private final Object object;

        public TimeMeasuringProxyHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            System.out.println("Measuring proxy: Before executing " + method.getName());

            long startTime = System.currentTimeMillis();
            try {
                result = method.invoke(object, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
            long endTime = System.currentTimeMillis();
            System.out.println();
            System.out.println(String.format("Measuring proxy: Execution of %s() took %dms \n",
                    method.getName(),
                    startTime-endTime));
            return result;
        }
    }

}
