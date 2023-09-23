package com.tungngt.dev.model;

public class Person implements Comparable<Person> {
    public String id;
    public String name;
    public Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        if (this.name.compareTo(other.name) > 0)
            return 1;
        else if (this.name.compareTo(other.name) < 0)
            return -1;

        return this.age.compareTo(other.age);
    }
}
