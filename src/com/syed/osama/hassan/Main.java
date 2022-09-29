package com.syed.osama.hassan;


import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        Map<String, Integer> stringIntegerMap = new HashMap<>();
        Class<?> hashMapClass = stringIntegerMap.getClass();

        Class<?> squareClass = Class.forName("com.syed.osama.hassan.Main$Square");

        printClassInfo(stringClass, hashMapClass, squareClass);
    }

    private static void printClassInfo(Class<?> ... classes) {
        for(Class<?> clazz : classes) {
            System.out.println("CLASS NAME: " + clazz.getSimpleName() +
                    " | CLASS PACKAGE: " + clazz.getPackage().getName());

            Class<?> [] implementedInterfaces = clazz.getInterfaces();
            for(Class<?> implementedInterface : implementedInterfaces) {
                System.out.println("Class " + clazz.getSimpleName() +
                        " implements " + implementedInterface.getSimpleName());
            }
            System.out.println("\n");
        }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 0;
        }

    }

    private static interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }

}
