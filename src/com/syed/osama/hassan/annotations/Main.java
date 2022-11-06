package com.syed.osama.hassan.annotations;

import com.syed.osama.hassan.annotations.custom.InitializerClass;
import com.syed.osama.hassan.annotations.custom.InitializerMethod;
import com.syed.osama.hassan.annotations.custom.RetryOperation;
import com.syed.osama.hassan.annotations.custom.ScanPackages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@ScanPackages(value = {"database", "config", "http", "saver"})
public class Main {
    private static final String PACKAGE_NAME = "com.syed.osama.hassan.annotations";

    public static void main(String[] args) throws Throwable {
        initialize();
    }

    public static void initialize() throws Throwable {
        ScanPackages scanPackages = Main.class.getAnnotation(ScanPackages.class);

        if(scanPackages == null || scanPackages.value().length == 0) {
            return;
        }

        List<Class<?>> classes = getAllClasses(scanPackages.value());

        for(Class<?> clazz : classes) {
            if(!clazz.isAnnotationPresent(InitializerClass.class)) {
                continue;
            }

            List<Method> methods = getAllInitializingMethods(clazz);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for(Method method : methods) {
                callInitializingMethod(instance, method);
            }

        }
    }

    private static void callInitializingMethod(Object instance, Method method) throws Throwable {
        RetryOperation retryOperation = method.getAnnotation(RetryOperation.class);
        int numberOfRetries = retryOperation == null? 0 : retryOperation.numberOfRetries();

        while (true) {
            try {
                method.invoke(instance);
                break;
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException();

                if(numberOfRetries > 0 &&
                        Arrays.asList(retryOperation.retryExceptions()).contains(targetException.getClass())) {
                    numberOfRetries--;
                    System.out.println("Retrying...");
                    Thread.sleep(retryOperation.durationBetweenRetriesMs());
                } else if(retryOperation != null) {
                    throw new Exception(retryOperation.failureMessage(), targetException);
                } else {
                    throw targetException;
                }
            }
        }

    }

    private static List<Class<?>> getAllClasses(String ... packageName) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for(String name : packageName) {
            String packageRelativePath = name.replaceAll("\\.", "/");
            URI packageUri = Main.class.getResource(packageRelativePath).toURI();

            if(packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath, name));
            } else if(packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());
                Path packageFullPathInJar = fileSystem.getPath(packageRelativePath);
                allClasses.addAll(getAllPackageClasses(packageFullPathInJar, name));
                fileSystem.close();
            }
        }

        return allClasses;
    }

    private static List<Method> getAllInitializingMethods(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredMethods()).stream()
                .filter(method -> method.isAnnotationPresent(InitializerMethod.class))
                .collect(Collectors.toList());
    }

    private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws IOException, ClassNotFoundException {
        if(!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> paths = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();
        for(Path filepath : paths) {
            String fileName = filepath.getFileName().toString();

            if(fileName.endsWith(".class")) {
                String classFullName = PACKAGE_NAME + "."
                        + packageName + "." + fileName.replaceFirst("\\.class$", "");
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }

        return classes;
    }

}
