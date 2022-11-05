package com.syed.osama.hassan.json.serializer.jsonwriter;

import com.syed.osama.hassan.json.serializer.data.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Address address = new Address("abc", (short) 1);
        Company company = new Company("JJ", "bbc", address);
        Person person = new Person("John", true, 25, 125.45f, address, company);
        System.out.println("-------------------Person----------------------------------");
        System.out.println("Person: " + objectToJson(person));

        System.out.println("-------------------Actor------------------------------");
        Actor actor = new Actor("John Doe", new String[]{"Lord of the rings", "People Gift"});
        System.out.println(objectToJson(actor));

        System.out.println("-------------------Movie----------------------------------");
        Movie movie = new Movie("Lord of the rings",
                9.2f, new String[]{"Action", "Drama", "Comedy"},
                new Actor[]{actor});
        System.out.println(objectToJson(movie));
    }

    public static String objectToJson(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Field field : fields) {
            field.setAccessible(true);
            if(field.isSynthetic()) {
                continue;
            }
            sb.append(formatStringValue(field.getName()));
            sb.append(":");

            if(field.getType().isPrimitive()) {
                sb.append(formatPrimitiveValue(field.get(object), field.getType()));
            } else if(field.getType().equals(String.class)) {
                sb.append(formatStringValue(field.get(object).toString()));
            } else if(field.getType().isArray()) {
                sb.append(arrayToJson(field.get(object)));
            } else {
                sb.append(objectToJson(field.get(object)));
            }

            if(!fields[fields.length-1].equals(field)) {
                sb.append(",");
            }

        }

        sb.append("}");
        return sb.toString();
    }

    private static String arrayToJson(Object array) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        int length = Array.getLength(array);
        Class<?> componentType = array.getClass().getComponentType();

        sb.append("[");
        for(int i = 0; i < length; i++) {
            Object element = Array.get(array, i);

            if(element.getClass().isPrimitive()) {
                sb.append(formatPrimitiveValue(element, componentType));
            } else if(componentType.equals(String.class)) {
                sb.append(formatStringValue(element.toString()));
            } else {
                sb.append(objectToJson(element));
            }

            if(i != length - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private static String formatPrimitiveValue(Object instance, Class<?> type) throws IllegalAccessException {
        if(type.equals(boolean.class)
        || type.equals(int.class)
        || type.equals(short.class)
        || type.equals(long.class)) {
            return instance.toString();
        } else if(type.equals(double.class)
                || type.equals(float.class)) {
            return String.format("%.02f", instance);
        }

        throw new RuntimeException(String.format("Type: %s is not supported", type));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }

}
