package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!line.isBlank() && !line.startsWith("#")) {
                    String[] keyAndValue = line.split("=", 2);
                    if (keyAndValue.length != 2
                            || keyAndValue[0].isEmpty()
                            || keyAndValue[1].isEmpty()) {
                        throw new IllegalArgumentException(
                                "Нарушение шаблона key=value");
                    }
                    values.put(keyAndValue[0], keyAndValue[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных из файла!");
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new NoSuchElementException("Отсутствует ключ");
        }
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных из файла!");
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
