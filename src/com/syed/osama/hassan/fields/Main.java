package com.syed.osama.hassan.fields;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Movie movie = new Movie("Hercules",
                2001,
                13.78,
                true,
                Category.ACTION);
        printDeclaredFields(movie.getClass(), movie);
    }

    public static <T> void printDeclaredFields(Class<? extends T> clazz, T instance) throws IllegalAccessException {
        for(Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(String.format(
                    "Field name: %s type %s",
                    field.getName(),
                    field.getType().getName()
            ));
            System.out.println("Is synthetic field " + field.isSynthetic());
            System.out.println(String.format("Field value is %s", field.get(instance)));
            System.out.println();
        }
    }

    public static class Movie extends Product {
        public static final double MINIMUM_PRICE = 10.99;
        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, double actualPrice,
                     boolean isReleased, Category category) {
            super(name, year);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = Math.max(actualPrice, MINIMUM_PRICE);
        }

        // Nested class
        public class MovieStats {
            private double timesWatched;

            public MovieStats(double timesWatched) {
                this.timesWatched = timesWatched;
            }

            public double getRevenue() {
                return actualPrice * timesWatched;
            }
        }
    }

    public static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }

    }

    public enum Category {
        ADVENTURE,
        ACTION,
        COMEDY
    }
}
