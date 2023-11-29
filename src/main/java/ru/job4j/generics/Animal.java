package ru.job4j.generics;

public class Animal {
    String name;
    String food;
    int age;

    public Animal(String name, String food, int age) {
        this.name = name;
        this.food = food;
        this.age = age;

    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", food='" + food + '\''
                + ", age=" + age
                + '}';
    }
}
