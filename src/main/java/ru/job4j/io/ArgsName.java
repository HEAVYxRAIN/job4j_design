package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    "This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Arguments not passed to program.");
        }
        for (String s : args) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '"
                        + s + "' does not contain an equal sign");
            }
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(
                        "Invalid parameter entered. Error: This argument '"
                                + s + "' does not start with a '-' character");
            }
            if (s.startsWith("-=")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + s + "' does not contain a key");
            }
            if (s.endsWith("=") && s.split("=").length == 1) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + s + "' does not contain a value");
            }
        }
    }

    private void parse(String[] args) {
        validate(args);
        Map<String, String> map = new HashMap<>();
        String[] array;
        for (String s: args) {
            array = s.split("=", 2);
            map.put(array[0], array[1]);
        }
        for (String s: map.keySet()) {
            values.put(s.replaceAll("[^.a-zA-Zа]", ""), map.get(s));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
