package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsWizard() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Wizard");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsWizard() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        store.add(new Role("1", "Hulk"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Wizard");
    }

    @Test
    void whenReplaceThenRoleNameIsHulk() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        store.replace("1", new Role("1", "Hulk"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Hulk");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        store.replace("10", new Role("10", "Hulk"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Wizard");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsWizard() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Wizard");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        boolean result = store.replace("1", new Role("1", "Hulk"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Wizard"));
        boolean result = store.replace("10", new Role("1", "Hulk"));
        assertThat(result).isFalse();
    }
}