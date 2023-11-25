package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && column >= data[row].length) {
            row++;
            column = 0;
        }
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer element = data[row][column];
        column++;
        if (column > data[row].length) {
            row++;
            column = 0;
        }
        return element;
    }
}
