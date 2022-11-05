package com.syed.osama.hassan.json.serializer.arrays;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        int [] oneDArray = {1,2};
        inspectArrayObject(oneDArray);
        double [][] twoDArray = {{1.0, 2.0}, {2.3, 4.5}};
        inspectArrayObject(twoDArray);
        inspectArrayValues(oneDArray);
        System.out.println();
        inspectArrayValues(twoDArray);
    }

    public static void inspectArrayValues(Object array) {
        int length = Array.getLength(array);

        System.out.print("[");
        for(int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if(element.getClass().isArray()) {
                inspectArrayValues(element);
            } else {
                System.out.print(element);
            }
            if(i != length-1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static void inspectArrayObject(Object arrayObject) {
        Class<?> clazz = arrayObject.getClass();

        System.out.println(String.format("Is array: %s", clazz.isArray()));
        Class<?> arrayComponentType = clazz.getComponentType();
        System.out.println(String.format("This is an array of type %s",
                arrayComponentType.getTypeName()));

    }

}
