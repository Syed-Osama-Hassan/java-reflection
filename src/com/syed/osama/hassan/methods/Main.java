package com.syed.osama.hassan.methods;

import com.syed.osama.hassan.methods.data.Cloth;
import com.syed.osama.hassan.methods.data.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        testGetters(Cloth.class);
        testSetters(Cloth.class);
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if(clazz == null || clazz.equals(Object.class)) {
            return Collections.emptyList();
        }
        Field[] fields = clazz.getDeclaredFields();
        List<Field> inheritedFields = getAllFields(clazz.getSuperclass());

        List<Field> allFields = new ArrayList<>();
        allFields.addAll(inheritedFields);
        allFields.addAll(Arrays.asList(fields));

        return allFields;
    }

    public static void testSetters(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);

        for(Field field : fields) {
            String setterName = "set" + capitalizeFirstLetter(field.getName());

            Method setter = null;
            try {
                setter = clazz.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(String.format("Setter: %s not found", setterName));
            }

            if(!setter.getReturnType().equals(void.class)) {
                throw new IllegalStateException(String.format(
                        "Setter method %s() has to return void", setterName)
                );
            }

        }
    }

    public static void testGetters(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        Map<String, Method> nameToMethod = mapMethodNameToMethod(clazz);

        for(Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());

            if(!nameToMethod.containsKey(getterName)) {
                throw new IllegalStateException(String.format(
                        "Field %s does not have a getter method", field.getName())
                );
            }

            Method getter = nameToMethod.get(getterName);

            if(!getter.getReturnType().equals(field.getType())) {
                throw new IllegalStateException(String.format(
                        "Getter method %s() has return type %s but expected %s",
                        getter.getName(),
                        getter.getReturnType().getTypeName(),
                        field.getType().getTypeName()
                ));
            }

            if(getter.getParameterCount() > 0) {
                throw new IllegalStateException(String.format(
                        "Getter: %s() has %d arguments",
                        getter.getName(),
                        getter.getParameterCount())
                );
            }


        }

    }

    private static String capitalizeFirstLetter(String name) {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    private static Map<String, Method> mapMethodNameToMethod(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        Map<String, Method> nameToMethod = new HashMap<>();

       for(Method method : methods) {
           nameToMethod.put(method.getName(), method);
       }

       return nameToMethod;
    }
}
