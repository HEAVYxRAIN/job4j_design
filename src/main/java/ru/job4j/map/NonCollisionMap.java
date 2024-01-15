package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int basket = getNumberOfBasket(key);
        boolean result = table[basket] == null;
        if (result) {
            table[basket] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> oneElement : table) {
            if (oneElement != null) {
                newTable[getNumberOfBasket(oneElement.key)] = oneElement;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int basket = getNumberOfBasket(key);
        if (checkBasketByNumber(key, basket)) {
            result = table[basket].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int basket = getNumberOfBasket(key);
        boolean result = checkBasketByNumber(key, basket);
        if (result) {
            table[basket] = null;
            count--;
            modCount++;
        }
        return result;
    }

    private int getNumberOfBasket(Object key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean checkBasketByNumber(K key, int basket) {
        boolean result = false;
        if (table[basket] != null) {
            K keyFromTable = table[basket].key;
            result = Objects.hashCode(key) == Objects.hashCode(keyFromTable)
                    && Objects.equals(key, keyFromTable);
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>()  {
            private int index;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
