package ru.job4j.collection;

import java.util.*;

public class ForwardLinked<T> implements Iterable<T> {

    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> currentNode = head;
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> next = head;
        for (int i = 0; i < index; i++) {
            next = next.next;
        }
        return next.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T tempElement = head.item;
        Node<T> tempNode = head.next;
        head.item = null;
        head.next = null;
        head = tempNode;
        size--;
        modCount++;
        return tempElement;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int expectedModCount = modCount;
            Node<T> currentNode = head;
            Node<T> nextNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                nextNode = currentNode;
                currentNode = currentNode.next;
                return nextNode.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
