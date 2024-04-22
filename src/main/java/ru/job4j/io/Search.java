package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile()
                .getName()
                .endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root,
                                    Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validation(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Program arguments are empty or incomplete");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (args[1].length() < 2 || !args[1].startsWith(".")
                || !Character.isLetter(args[1].charAt(1))) {
            throw new IllegalArgumentException(
                    "The file extension is unspecified or incorrect");
        }
    }
}
