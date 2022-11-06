package com.syed.osama.hassan.methods.invocation;

import com.syed.osama.hassan.methods.invocation.database.DatabaseClient;
import com.syed.osama.hassan.methods.invocation.http.HttpClient;
import com.syed.osama.hassan.methods.invocation.logging.FileLogger;
import com.syed.osama.hassan.methods.invocation.udp.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Throwable {
        DatabaseClient databaseClient = new DatabaseClient();
        HttpClient httpClient1 = new HttpClient("192.168.0.2");
        HttpClient httpClient2 = new HttpClient("192.168.0.1");
        FileLogger fileLogger = new FileLogger();
        UdpClient udpClient = new UdpClient();

        List<Object> executors = Arrays.asList(databaseClient, httpClient1, httpClient2, fileLogger, udpClient);
        List<Class<?>> methodParameterTypes = Arrays.asList(new Class[]{String.class});
        String data = "Request data";

        Map<Object, Method> objectMethodMap = groupExecutors(executors, methodParameterTypes);
        executeAll(objectMethodMap, data);
    }

    public static void executeAll(Map<Object, Method> requestExecutors, String requestBody) throws Throwable {
        try {
            for(Map.Entry<Object, Method> entry : requestExecutors.entrySet()) {
                Object executor = entry.getKey();
                Method method = entry.getValue();

                Boolean result = (Boolean) method.invoke(executor, requestBody);
                if(result == null || result.equals(Boolean.FALSE)) {
                    System.out.println("Aborting execution...");
                    return;
                }
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    public static Map<Object, Method> groupExecutors(List<Object> requestExecutors,
                                                     List<Class<?>> methodParameterTypes) {
        Map<Object, Method> instanceToMethod = new HashMap<>();

        for (Object object : requestExecutors) {
            Method[] methods = object.getClass().getMethods();

            for(Method method : methods) {
                if(Arrays.asList(method.getParameterTypes()).equals(methodParameterTypes)) {
                    instanceToMethod.put(object, method);
                }
            }
        }

        return instanceToMethod;
    }

}
