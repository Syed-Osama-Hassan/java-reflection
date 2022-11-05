package com.syed.osama.hassan.restricted.classes.init;

import com.syed.osama.hassan.restricted.classes.game.Game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Game game = (Game) createObjectRecursively(
                Class.forName("com.syed.osama.hassan.restricted.classes.game.internal.TicTacToeGame")
        );
        game.startGame();
    }

    private static <T> T createObjectRecursively(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructors = getFirstConstructor(clazz);
        List<Object> constructorArgs = new ArrayList<>();

        for(Class<?> argumentType : constructors.getParameterTypes()) {
            Object argumentValue = createObjectRecursively(argumentType);
            constructorArgs.add(argumentValue);
        }
        constructors.setAccessible(true);

        return (T) constructors.newInstance(constructorArgs.toArray());
    }

    private static Constructor<?> getFirstConstructor(Class<?> clazz) {
        Constructor<?> [] constructors = clazz.getDeclaredConstructors();
        if(constructors.length == 0) {
            throw new IllegalStateException(
                    String.format("No constructor has been found for class %s",
                        clazz.getName())
            );
        }

        return constructors[0];
    }

}
