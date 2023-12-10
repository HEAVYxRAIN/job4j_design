package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        container = grow();
        container[size++] = value;
        modCount++;
    }

    private T[] grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 2);
        } else if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        return container;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removedValue = get(index);
        if ((size - 1) > index) {
            System.arraycopy(this.container, index + 1,
                    this.container, index, size - index - 1);
        }
            container[size - 1] = null;
        --size;
            modCount++;
            return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException();
                    }
                return index < size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
