package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 1);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    void isThisCub() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    void numberOfVerticesSphere() {
        Box box = new Box(0, 1);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(0)
                .isNotNull();
    }

    @Test
    void numberOfVerticesCube() {
        Box box = new Box(8, 6);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(8)
                .isNotNull();
    }

    @Test
    void isExistSphere() {
        Box box = new Box(0, 1);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(true)
                .isTrue()
                .isNotNull();
    }

    @Test
    void isExistCube() {
        Box box = new Box(8, 6);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(true)
                .isTrue()
                .isNotNull();
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 1);
        double area = box.getArea();
        assertThat(area).isEqualTo(12.566370614359172D)
                .isNotNull();
    }

    @Test
    void getAreaCube() {
        Box box = new Box(8, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(216D)
                .isNotNull();
    }
}