package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> input2 = List.of(1, 3, 5);

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 5, 3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfMoreThan1() {
        ListUtils.removeIf(input, element -> element > 1);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIfMoreThan1() {
        ListUtils.replaceIf(input, element -> element > 1, 5);
        assertThat(input).hasSize(2).containsSequence(1, 5);
    }

    @Test
    void whenAddAndRemoveAll() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.addAfter(input, 4, 6);
        assertThat(input).hasSize(6).containsSequence(1, 2, 3, 4, 5, 6);
        ListUtils.removeAll(input, input2);
        assertThat(input).hasSize(3).containsSequence(2, 4, 6);
    }
}