package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Monkey", "Bananas", 20));
        second.add(new Predator("Feline", "Meat", 15 - 30));
        third.add(new Tiger("King", "Gazelle", 30));

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        generics.printBoundedWildCard(first);
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
        generics.printLowerBoundedWildCard(third);
    }

    /*public void printObject(List<Object> list) {
        for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {*/
    public void printObject(List<?> list) {
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*public void printBoundedWildCard(List<Predator> list) {
        for (Iterator<Predator> iterator = list.iterator(); iterator.hasNext();) {*/
    public void printBoundedWildCard(List<? extends Animal> list) {
        for (Iterator<? extends Animal> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*public void printLowerBoundedWildCard(List<Predator> list) {
        for (Iterator<Predator> iterator = list.iterator(); iterator.hasNext();) {*/
    public void printLowerBoundedWildCard(List<? super Tiger> list) {
        for (Iterator<? super Tiger> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}