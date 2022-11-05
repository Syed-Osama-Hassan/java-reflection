package com.syed.osama.hassan.fields.settingvalues;

import com.syed.osama.hassan.fields.settingvalues.data.GameConfig;
import com.syed.osama.hassan.fields.settingvalues.data.UserInterfaceConfig;
import com.syed.osama.hassan.restricted.classes.game.Game;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final Path GAME_CONFIG_PATH = FileSystems.getDefault()
            .getPath("src/resources", "game.properties");
    private static final Path UI_CONFIG_PATH = FileSystems.getDefault()
            .getPath("src/resources", "user-interface.properties");


    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameConfig gameConfig = createConfigObject(GameConfig.class, GAME_CONFIG_PATH);
        System.out.println(gameConfig);
        UserInterfaceConfig config = createConfigObject(UserInterfaceConfig.class, UI_CONFIG_PATH);
        System.out.println(config);
    }

    public static <T> T createConfigObject(Class<T> clazz, Path filePath) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(filePath);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T configInstance = (T) constructor.newInstance();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] nameValuePair = line.split("=");
            String propertyName = nameValuePair[0];
            String propertyValue = nameValuePair[1];

            Field field;
            try {
                field = clazz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                System.out.println(String.format("Property name %s does not exist", propertyName));
                continue;
            }
            field.setAccessible(true);
            Object parseValue = parseValue(field.getType(), propertyValue);
            field.set(configInstance, parseValue);
        }

        return configInstance;
    }

    private static Object parseValue(Class<?> type, String propertyValue) {
        if(type.equals(int.class)) {
            return Integer.parseInt(propertyValue);
        } else if(type.equals(short.class)) {
            return Short.parseShort(propertyValue);
        } else if(type.equals(double.class)) {
            return Double.parseDouble(propertyValue);
        } else if(type.equals(long.class)) {
            return Long.parseLong(propertyValue);
        } else if(type.equals(float.class)) {
            return Float.parseFloat(propertyValue);
        } else if(type.equals(String.class)) {
            return propertyValue;
        }

        throw new RuntimeException(String.format("Type %s not supported", type.getTypeName()));
    }

}
