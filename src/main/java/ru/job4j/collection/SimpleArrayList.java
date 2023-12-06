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
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 1);
        } else if (size == container.length) {
            container = grow();
        }
        container[size++] = value;
        modCount++;
    }

    public T[] grow() {
        container = Arrays.copyOf(container, container.length * 2);
        return container;
    }

    @Override
    public T set(int index, T newValue) {
        get(index);
        T oldValue = container[index];
        container[index] = newValue;
        modCount++;
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
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = size;
            private int index = 0;

            @Override
            public boolean hasNext() {
                    if (expectedModCount != size) {
                        throw new ConcurrentModificationException();
                    }
                return index < container.length && container[index] != null;
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
