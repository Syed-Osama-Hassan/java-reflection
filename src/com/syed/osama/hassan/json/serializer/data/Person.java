package com.syed.osama.hassan.json.serializer.data;

public class Person {
    private final String name;
    private final boolean employeed;
    private final int age;
    private final float salary;
    private Address address;
    private Company company;

    public Person(String name, boolean employed, int age,
                  float salary, Address address, Company company) {
        this.name = name;
        this.employeed = employed;
        this.age = age;
        this.salary = salary;
        this.address = address;
        this.company = company;
    }
}
