package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("1", "2", "3", "4", "5");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .contains("3")
                .contains("2", "4", "1")
                .anySatisfy(e -> {
                    assertThat(e).isLessThan("10");
                    assertThat(e).isGreaterThan("0");
                });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("AAA", "BBB", "CCC", "DDD");
        assertThat(set).isNotNull()
                .hasSize(4)
                .contains("BBB", "CCC")
                .startsWith("AAA")
                .endsWith("DDD")
                .first().isEqualTo("AAA");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("A", "B", "C");
        assertThat(map).isNotNull()
                .hasSize(3)
                .containsKeys("A", "B", "C")
                .doesNotContainValue(100);
    }

}