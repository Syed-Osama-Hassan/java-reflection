package com.syed.osama.hassan.json.serializer.jsonwriter;

import com.syed.osama.hassan.json.serializer.data.Address;
import com.syed.osama.hassan.json.serializer.data.Company;
import com.syed.osama.hassan.json.serializer.data.Person;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Address address = new Address("abc", (short) 1);
        Company company = new Company("JJ", "bbc", address);
        Person person = new Person("John", true, 25, 125.45f, address, company);
        System.out.println("Person: " + objectToJson(person));
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
                sb.append(formatPrimitiveValue(field, object));
            } else if(field.getType().equals(String.class)) {
                sb.append(formatStringValue(field.get(object).toString()));
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

    private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {
        Class<?> type = field.getType();
        if(type.equals(boolean.class)
        || type.equals(int.class)
        || type.equals(short.class)
        || type.equals(long.class)) {
            return field.get(parentInstance).toString();
        } else if(type.equals(double.class)
                || type.equals(float.class)) {
            return String.format("%.02f", field.get(parentInstance));
        }

        throw new RuntimeException(String.format("Type: %s is not supported", type));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }

}
