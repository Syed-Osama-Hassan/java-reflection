package com.syed.osama.hassan.modifiers;

import com.syed.osama.hassan.modifiers.data.Auction;
import com.syed.osama.hassan.modifiers.data.Bid;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        printClassModifiers(Auction.class);
        printClassModifiers(Bid.class);
        printClassModifiers(Bid.Builder.class);
        printClassModifiers(Serializable.class);

        System.out.println("---------------------------------------------");
        printMethodModifiers(Auction.class.getDeclaredMethods());

        System.out.println("-----------------------------------------------");
        printFieldsModifiers(Auction.class.getDeclaredFields());

    }

    private static void printFieldsModifiers(Field[] declaredFields) {
        for(Field field : declaredFields) {
            int modifier = field.getModifiers();
            System.out.println(String.format("Field %s has access modifier %s",
                    field.getName(),
                    getAccessModifierName(modifier)));

            if(Modifier.isStatic(modifier)) {
                System.out.println("The field is static");
            }
            if(Modifier.isVolatile(modifier)) {
                System.out.println("The field is volatile");
            }
            if(Modifier.isFinal(modifier)) {
                System.out.println("The field is final");
            }
            if (Modifier.isTransient(modifier)) {
                System.out.println("The field is transient and will not be serialized");
            }

            System.out.println();
        }
    }

    private static void printMethodModifiers(Method[] methods) {
        for(Method method : methods) {
            int modifier = method.getModifiers();
            System.out.println(String.format("%s() access modifier is %s",
                    method.getName(),
                    getAccessModifierName(modifier)));

            if(Modifier.isSynchronized(modifier)) {
                System.out.println("The method is synchronized");
            } else {
                System.out.println("The method is not synchronized");
            }

            System.out.println();
        }
    }

    private static void printClassModifiers(Class<?> clazz) {
        int modifier = clazz.getModifiers();
        System.out.println(String.format("Class %s modifier is %s",
                clazz.getSimpleName(),
                getAccessModifierName(modifier)));

        if(Modifier.isAbstract(modifier)) {
            System.out.println("The class is abstract");
        }
        if(Modifier.isInterface(modifier)) {
            System.out.println("The class is an interface");
        }
        if(Modifier.isStatic(modifier)) {
            System.out.println("This is a static class");
        }

    }

    private static String getAccessModifierName(int modifier) {
        if(Modifier.isPublic(modifier)) {
            return "public";
        } else if(Modifier.isPrivate(modifier)) {
            return "private";
        } else if(Modifier.isProtected(modifier)) {
            return "protected";
        } else {
            return "package-private";
        }
    }

    public static void runAuction() {
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = Bid.builder()
                .setBidderName("Abc")
                .setPrice(10)
                .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .setBidderName("Xyz")
                .setPrice(200)
                .build();
        auction.addBid(bid2);
        auction.stopAuction();
        System.out.println("All bids: " + auction.getAllBids());
        System.out.println("Highest bid: " + auction.getHighestBid());

    }

}
